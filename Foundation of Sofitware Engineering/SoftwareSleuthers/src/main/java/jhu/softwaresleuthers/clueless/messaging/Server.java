/*
 * This file contains code that establishes a server that listens on a socket for incoming client connections.
 * References for the code written here can be found at:
 * https://www.javatpoint.com/socket-programming
 * https://www.geeksforgeeks.org/socket-programming-in-java/
 */

package jhu.softwaresleuthers.clueless.messaging;

import java.util.*;

import ch.qos.logback.core.net.server.Client;
import jhu.softwaresleuthers.clueless.models.*;
import jhu.softwaresleuthers.clueless.models.Character;
import java.io.*;
import java.net.*;

/*
 * This class defines the server involved in a server-client connection
 */
public class Server 
{
    public static List<String> availableCharacters = new LinkedList<>(Character.CharacterName.names());
    private static Board board;
    public static TurnManager turnManager = new TurnManager();
    private static boolean gameEnded;
    private static boolean gameStarted;
    private static final Object objectLock = new Object();
    private static HashMap<String, ClientHandler> clientHandlers = new HashMap<>();

    public static void main (String[] args)
    {
        // Server object is created that will listen on port 49882 (ensure port is open on machine before program is ran and port is same in Client.java)
        ServerSocket serverSocket = null;
        try 
        {
            // Create new socket for server that listens on specified port
            serverSocket = new ServerSocket(59882);
            int count = 0;
            while (count < 6)
            {
                // Incoming client connection is accepted
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                new Thread(client).start();
                ++count;
            }
        }
        catch (SocketException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (serverSocket != null)
            {
                try
                {
                    serverSocket.close();
                }
                catch (Exception e) {}
            }
        }
    }

    static void broadcastMessage(Object message) {
        for (ClientHandler clientHandler : clientHandlers.values()) {
            try {
                clientHandler.output.writeObject(message);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void initializeBoard() {
        List<String> players = new ArrayList<>(clientHandlers.keySet());
        board = new Board(players);
        // Let players know what cards they have
        for (String player : clientHandlers.keySet()) {
            String cardMessage = String.format("%s,CARDS,", player);
            Character playerChar = board.getCharacter(player).get();
            ArrayList<CardEntityInterface> hand = board.getHands().get(playerChar);
            for (CardEntityInterface card : hand) {
                cardMessage = cardMessage + card.getName() + "_";
            }
            broadcastMessage(cardMessage);
        }
    }

    static class ClientHandler implements Runnable
    {
        private Socket clientSocket;
        private ObjectInputStream input;
        private ObjectOutputStream output;
        private String name;
        private static final Object objectLock = new Object();

        public ClientHandler(Socket socket)
        {
            clientSocket = socket;
            gameEnded = false;
        }

        public void run()
        {
            try
            {
                input = new ObjectInputStream(clientSocket.getInputStream());
                output = new ObjectOutputStream(clientSocket.getOutputStream());
                String newCharacter = setCharacter(input, output);
                clientHandlers.put(newCharacter, this);

                if(availableCharacters.size()<=3) {
                    broadcastMessage("ALL,OK_TO_START");
                }
                if(availableCharacters.size()==0) {
                    broadcastMessage("ALL,GAME_STARTED");
                }

                while(!gameStarted) {
                    if(clientSocket.getInputStream().available()>0) {
                        try {
                            if(Objects.equals(input.readObject(),"START_GAME")) {
                                gameStarted = true;
                                broadcastMessage("ALL,GAME_STARTED");
                                initializeBoard();
                            }
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                while (!gameEnded)
                {
                    String playerTurn = String.format("%s,CURRENTTURN", turnManager.getPlayerTurn());
                    output.writeObject(playerTurn);
                    output.flush();
                    if (name.equals(turnManager.getPlayerTurn()))
                    {
                        executePlayerTurn();
                    }
                    Thread.sleep(2000);
                }
                clean();
            }
            catch (IOException | InterruptedException e) { clean(); }
        }

        public void executePlayerTurn() {
            try {
                String received = waitForClientResponse();
                System.out.println("Received message: " + received);
                String[] split = received.split(",");

                switch(split[0]) {
                    case "OBTAINVALID_ROOMS":
                        synchronized (objectLock) {
                            String currentLocationId = board.getCharacter(turnManager.getPlayerTurn()).get().getCurrentLocationId();
                            String[] splitLocation = currentLocationId.split("_");

                            String message = turnManager.getPlayerTurn() + ",MOVE_OPTIONS,";
                            if (splitLocation[0].equals("HALLWAY")) {
                                for (Room room: board.moveOptionsHallway(
                                        board.getHallways().get(Integer.parseInt(splitLocation[1])))) {
                                    message += room.getName() + "-";
                                }
                            } else if (Character.CharacterName.get(splitLocation[0]).isPresent()) {
                                // Player is in starting location
                                Hallway hallway = board.moveOptionsPlayerStart(Character.CharacterName.get(splitLocation[0]).get());
                                message += "HALLWAY_" + hallway.getHallwayNum().toString() + "-";
                            }
                            else {
                                for (Hallway hallway: board.moveOptionsRoom(
                                        board.getRooms().get(Room.RoomName.get(splitLocation[0]).get()))) {
                                    message += "HALLWAY_" + hallway.getHallwayNum() + "-";
                                }
                            }
                            // Trim the ending "-" character
                            message = message.substring(0, message.length()-1);
                            System.out.println("Message: " + message);
                            sendMessage(message);
                        }
                        break;

                    case "MOVE":
                        synchronized (objectLock) {
                            board.getCharacter(turnManager.getPlayerTurn()).get().setCurrentLocationId(split[1]);
                            String message = String.format("ALL,MOVE_UPDATE,%s,%s", turnManager.getPlayerTurn(), split[1]);
                            sendMessage(message);
                        }
                        turnManager.next();
                        break;

                    case "SUGGESTION":
                        evaluateSuggestion(split[1].split("_"));
                        turnManager.next();
                        break;

                    case "ACCUSATION":
                        evaluateAccusation(split[1].split("_"));
                        turnManager.next();
                        break;

                    default:
                        System.out.println("Did not match any case split[0] = " + split[0]);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public String setCharacter(ObjectInputStream input, ObjectOutputStream objStream)
        {
            try
            {
                while(name==null) {
                    objStream.writeObject(
                        "ALL,CHARACTER_LIST," + String.join("|", availableCharacters));
                    objStream.flush();

                    String chosenCharacter = waitForClientResponse();
                    synchronized (objectLock) {
                        if(availableCharacters.contains(chosenCharacter)) {
                            name = chosenCharacter;
                            availableCharacters.remove(name);
                            turnManager.addPlayer(name);
                            System.out.println(name + " has joined the game.");
                            objStream.writeObject("ALL,CHARACTER_SELECTED," + name);
                            broadcastMessage("ALL,CHARACTER_JOINED," + name);
                            return name;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            return null;
        }

        public String waitForClientResponse()
        {
            try
            {
                String receivedMessage;
                while (((receivedMessage = (String) input.readObject()) == null)) {
                    Thread.sleep(2000);
                }
                return receivedMessage;
            }
            catch (Exception e)
            {
                System.out.println(e.getCause());
                return null;
            }
        }

        public String listenForResponse(String player) {
            // Need a way to listen to players when it's not their turn (e.g. suggestions)
            ClientHandler clientHandler = clientHandlers.get(player);
            try {
                String receivedMessage;
                while (((receivedMessage = (String) clientHandler.input.readObject()) == null)) {
                    Thread.sleep(2000);
                }
                return receivedMessage;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        private void evaluateSuggestion(String[] messageSplit) throws IOException {
            synchronized(objectLock) {
                CardEntityInterface character = board.getCard(messageSplit[0]).get();
                CardEntityInterface weapon = board.getCard(messageSplit[1]).get();
                String playerCharacter = turnManager.getPlayerTurn();

                // Get current location of suggesting player as the room
                String locationId = board.getCharacter(playerCharacter).get().getCurrentLocationId();
                Room.RoomName roomName = Room.RoomName.get(locationId).get();
                CardEntityInterface room = board.getRooms().get(roomName);

                String sug_message = String.format("ALL,SUGGESTION_MADE,%s,%s,%s,%s", playerCharacter, character.getName(), weapon.getName(), room.getName());
                broadcastMessage(sug_message);

                Guess guess = new Guess(character, room, weapon, false);

                // Ask players in turn to prove/disprove suggestion
                String message = "";
                for (int i = 0; i < turnManager.turnOrder.size(); i++) {

                    // Check if current player has a matching card
                    CardEntityInterface card = board.evaluateSuggestion(turnManager.turnOrder.get(i), guess); // todo change return type to bool
                    if (card != null) {
                        // Broadcast to all players disproven suggestion
                        message = String.format("ALL,SUGGESTION_DISPROVE,%s_%s_%s", character, weapon, room);
                        broadcastMessage(message);

                        // Ask player which card they would like to show
                        String chosenCard = queryPlayerDisproveCard(turnManager.turnOrder.get(i), guess);

                        // Tell the suggesting player specific information
                        message = String.format("%s,SUGGESTION_DISPROVE_CARD,%s,%s", turnManager.getPlayerTurn(), turnManager.turnOrder.get(i), chosenCard);
                        broadcastMessage(message);
                        return;
                    }
                }
                // Broadcast to all players suggestion cannot be disproven
                message = String.format("ALL,SUGGESTION_CANNOT_DISPROVE,%s,%s_%s_%s", turnManager.getPlayerTurn(), character, weapon, room);
                broadcastMessage(message);
            }
        }

        private String queryPlayerDisproveCard(String playerName, Guess guess) throws IOException {
            List<CardEntityInterface> hand = board.getHand(playerName);
            String message = String.format("%s,DISPROVE_CARD,", playerName);
            for (CardEntityInterface card : hand) {
                if (card.equals(guess.getCharacter()) || card.equals(guess.getRoom()) || card.equals(guess.getWeapon())) {
                    message = message + card.getName() + '_';

                }
            }
            broadcastMessage(message);

            String chosenCard = listenForResponse(playerName);
            return chosenCard;
        }

        private void evaluateAccusation(String[] messageSplit) throws IOException {
            synchronized(objectLock)
            {
                CardEntityInterface character = board.getCard(messageSplit[0].stripLeading().stripTrailing()).get();
                CardEntityInterface weapon = board.getCard(messageSplit[1].stripLeading().stripTrailing()).get();
                CardEntityInterface room = board.getCard(messageSplit[2].stripLeading().stripTrailing()).get();
    
                Guess guess = new Guess(character, room, weapon, true);
                boolean correct = board.evaluateAccusation(guess);
                String message = "";
                String player = turnManager.getPlayerTurn();
                if (correct) {
                    message = String.format("ALL,ACCUSATION_TRUE,%s,%s_%s_%s",
                            player, character.getName(), weapon.getName(), room.getName());
                    // End game
                    gameEnded = true;
                } else {
                    message = String.format("ALL,ACCUSATION_FALSE,%s", player);
                    // Remove player from future game turns
                    turnManager.removePlayer(player);

                    // If all players have made false accusations
                    if (gameEnded) {
                        CaseFile caseFile = board.getCaseFile();
                        message = String.format("ALL,GAME_ENDED,%s,%s_%s_%s",
                                player, caseFile.getCharacter(), caseFile.getWeapon(), caseFile.getRoom());
                    }
                }
                sendMessage(message);
            }
        }

        private void sendMessage(String message) throws IOException {
            output.writeObject(message);
            output.flush();
        }

        public void clean()
        {
            try 
            {
                clientSocket.close();
                input.close();
                output.close();
            }
            catch (Exception e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class TurnManager {
        private int turnCounter = 0;
        private List<String> turnOrder = new LinkedList<>();

        public void addPlayer(String player) {
            turnOrder.add(player);
        }

        public String getPlayerTurn() {
            return turnOrder.get(turnCounter);
        }

        public void next() {
            turnCounter++;

            if (turnCounter == turnOrder.size()) {
                turnCounter = 0;
                }   
        }

        public void removePlayer(String player) {
            turnOrder.remove(player);
            turnCounter--;

            if (turnCounter < 0) {
                turnCounter = turnOrder.size() - 1;
            }

            // If all players have made incorrect accusations
            if (turnOrder.size() == 0) {
                System.out.println("All players have made incorrect accusations.");
                gameEnded = true;
            }
        }
    }
}