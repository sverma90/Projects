/*
 * This file contains code that establishes a client that binds to a specific port to send a message to a server.
 * References for the code written here can be found at:
 * https://www.javatpoint.com/socket-programming
 * https://www.geeksforgeeks.org/socket-programming-in-java/
 */

package jhu.softwaresleuthers.clueless.messaging;

import jhu.softwaresleuthers.clueless.GUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jhu.softwaresleuthers.clueless.models.CardEntityInterface;
import jhu.softwaresleuthers.clueless.models.Character;
import jhu.softwaresleuthers.clueless.models.Room;
import jhu.softwaresleuthers.clueless.models.Weapon;

/*
 * This class defines the client involved in a server-client connection
 */
public class Client
{
    private static final List<String> PLAYER_OPTIONS = Arrays.asList(
        "Move to a room",
        "Make a suggestion",
        "Make an accusation"
    );
    private static final List<String> CHARACTERS = Character.CharacterName.names();
    private static final List<String> WEAPONS = Weapon.names();
    private static final List<String> ROOMS = Room.RoomName.names();

    private static final String OBTAIN_VALID = "OBTAINVALID";
    private String[] characterList;

    private String characterName = "";
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean gameEnded = false;
    private BlockingQueue<String> userInput = new LinkedBlockingQueue<>();
    private BlockingQueue<Object> serverResponse = new LinkedBlockingQueue<>();
    private final List<CardEntityInterface> hand = new ArrayList<>();
    private GUI gui;

    enum ClientState {
        PICK_CHARACTER,
        WAITING_FOR_SERVER,
        CAN_START_GAME,
        GAME_STARTED,
        GAME_ENDED
    }
    ClientState state = ClientState.PICK_CHARACTER;

    private Thread userInputThread = null;
    private Thread serverMessageThread = null;

    private void createKeyBoardListener() {
         userInputThread =
            new Thread(
                () -> {
                    while (state!=ClientState.GAME_STARTED && state!=ClientState.GAME_ENDED) {
                        String input;
                        if(!(input = gui.getInputText()).isEmpty()) {
                            if (state == ClientState.PICK_CHARACTER) {
                                pickCharacter(input);
                            } else if (state == ClientState.CAN_START_GAME) {
                                System.out.println(input);
                                startGame();
                            }
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    System.out.println("thread stopped");
                });
         userInputThread.start();
    }

    private void startGame() {
        try {
            output.writeObject("START_GAME");
        } catch (IOException e) {
            System.err.println(e.getMessage() + "line 101");
        }
    }

    private void createServerListener() {
        serverMessageThread = new Thread(
            () -> {
                try {
                    while (true) {
                        final Object serverObject = input.readObject();
                        serverResponse.add(serverObject);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println(e.getMessage() + "line 114");
                }
            });
        serverMessageThread.start();
    }


    // Constructor for client object that executes socket code
    public Client(String address, int port)
    {
        // Create GUI
        gui = new GUI(this);

        try
        {
            printStatement("Welcome to Clue-Less Game."
                    + "\nTo begin, please select the name of the character you would like to play as.");
            // Create a socket on specified address and port
            clientSocket = new Socket(address, port);

            // Message is sent to server through output data stream
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
            createKeyBoardListener();
            createServerListener();
            String receivedMessage;

            while (!gameEnded)
            {
                receivedMessage = (String) serverResponse.take();
                String[] split = receivedMessage.split(",");

                if (split[0].equals("ALL"))
                {
                    switch(split[1]) {
                        case "ACCUSATION_TRUE":
                            endGame(split[2], split[3].split("_"));
                            break;

                        case "ACCUSATION_FALSE":
                            incorrectAccusation(split[2]);
                            break;

                        case "SUGGESTION_MADE":
                            printStatement(String.format("%s suggests %s committed murder with the %s in the %s.",
                                    split[2], split[3], split[4], split[5]));
                            break;

                        case "SUGGESTION_DISPROVE":
                            printStatement("The suggestion has been disproven.");
                            break;

                        case "SUGGESTION_CANNOT_DISPROVE":
                            printStatement("The suggestion cannot be disproven.");
                            break;

                        case "CHARACTER_LIST":
                            if(this.characterList!=null && this.characterList.length>0) {
                                printStatement("Your selected character is no longer available. Please make a new choice.");
                            }
                            this.characterList = split[2].split("\\|");
                            String options = IntStream.range(0,characterList.length).mapToObj(i -> (i+1) + ". " + characterList[i])
                                    .collect(Collectors.joining(System.lineSeparator()));
                            appendStatement("Character option:");
                            appendStatement(options);
                            break;

                        case "CHARACTER_SELECTED":
                            this.characterName = split[2];
                            printStatement("Character selection confirmed = " + characterName);
                            this.state = ClientState.WAITING_FOR_SERVER;
                            break;

                        case "CHARACTER_JOINED":
                            printStatement("Character " + split[2] + " joined");
                            break;

                        case "OK_TO_START":
                            if(state != ClientState.PICK_CHARACTER) {
                                printStatement("Type \"start\" to start the game");
                                this.state = ClientState.CAN_START_GAME;
                            }
                            break;

                        case "GAME_STARTED":
                            this.state = ClientState.GAME_STARTED;
                            printStatement("Game has started");
                            userInputThread.interrupt();
                            break;
                        case "GAME_ENDED":
                            endGameNoWinner(split[2], split[3].split("_"));
                            break;
                        case "MOVE_UPDATE":
                            printStatement(String.format("%s has moved to the %s", split[2], split[3]));
                            break;
                    }
                }
                else if (!split[0].equals(characterName))
                {
                    if (split[1].equals("DISPROVE_CARD")) {
                        printStatement(String.format("%s is picking a card to show...", split[0]));
                    }
                    else if (split[1].equals("CURRENTTURN")) {
                        printStatement(String.format("It is currently %s's turn", split[0]));
                    }
                    Thread.sleep(2000);
                }
                else
                {
                    switch(split[1])
                    {
                        case "CARDS":
                            String cardMessage = "Your cards are: ";
                            String[] cards = split[2].split("_");
                            gui.createCardPanel(cards);
                            for (String card : cards) {
                                cardMessage = cardMessage + card + "  ";
                            }
                            System.out.println(cardMessage);
                            break;

                        case "CURRENTTURN":
                            executeTurn();
                            break;

                        // Get card player would like to show for disproving suggestion
                        case "DISPROVE_CARD":
                            pickDisproveCard(split[2].split("_"));
                            break;

                        case "SUGGESTION_DISPROVE_CARD":
                            printStatement(String.format("The suggestion was disproven by %s with %s", split[2], split[3]));
                            break;

                        case "MOVE_OPTIONS":
                            String playerTurn = "MOVE,";
                            String chosenOption = executeMove(split[2]);
                            sendMessage(playerTurn + chosenOption);
                            break;

                        default:
                            break;
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            clean();
        }
    }

    public void pickDisproveCard(String[] message)
    {
        try
        {
            List<String> cardOptions = Arrays.asList(message);
            // Display card options
            System.out.println("You have a card that matches the suggestion.");
            // Get player choice and send to server
            int cardOptNum = getUserInput(cardOptions, "card");
            String card = cardOptions.get(cardOptNum);
            output.writeObject(card);
            output.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String waitForServer() {
        try {
            String receivedMessage;
            while (((receivedMessage = (String) input.readObject()) == null)) {
                Thread.sleep(2000);
            }
            return receivedMessage;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }

    public void presentOptions(List<String> options, String object) {
        String message = String.format("Please enter the number of the %s you would like", object);
        message += "\nYour options are:";

        for (int i = 0; i < options.size(); i++) {
            message += String.format("\n%d: %s", i + 1, options.get(i));
        }
        printStatement(message);
    }

    public int verifyInput(List<String> options, String userInput) {
        try {
            int numericalVal = Integer.parseInt(userInput);
            if (numericalVal > options.size() || numericalVal < 1)
            {
                appendStatement("Number is outside range of possible choices");
                return -1;
            }
            return numericalVal - 1;
        }
        catch (NumberFormatException e)
        {
            appendStatement("Failed converting string to integer");
            return -1;
        }
    }

    public void pickCharacter(String input)
    {
        try
        {
            int userNum = verifyInput(List.of(characterList), input);
            characterName = characterList[userNum];
            output.writeObject(characterName);
            output.flush();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public int getUserInput(List<String> options, String noun)
    {
        presentOptions(options, noun);

        String userChoice;
        while ((userChoice = gui.getInputText()).isEmpty()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        int userNum;

        while ((userNum = verifyInput(options, userChoice)) == -1)
        {
            printStatement("The input is not a valid integer. Please try again.");
            presentOptions(options, noun);
            while ((userChoice = gui.getInputText()).isEmpty()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return userNum;
    }

    public void executeTurn()
    {
        String playerTurn = "";
        printStatement("It is now your turn. What would like to do?");
        int userNum = getUserInput(PLAYER_OPTIONS, "choice");
        switch(userNum) {
            case 0:
                playerTurn += OBTAIN_VALID + "_ROOMS";
                break;

            case 1:
                playerTurn += "SUGGESTION";
                playerTurn += String.format(",%s", chooseParams(CHARACTERS, WEAPONS));
                printStatement(playerTurn);
                break;

            case 2:
                playerTurn += "ACCUSATION";
                playerTurn += String.format(",%s", chooseParams(CHARACTERS, WEAPONS, ROOMS));
                break;

            default:
                break;
        }
        try
        {
            sendMessage(playerTurn);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String executeMove(String moveOptionsString) {
        printStatement("These are the move options that you can choose from");
        List<String> moveOptions = List.of(moveOptionsString.split("-"));
        String noun = moveOptions.get(0).contains("HALLWAY") ? "hallway" : "room";
        int userNum = getUserInput(moveOptions, noun);
        return moveOptions.get(userNum);
    }

    public void endGame(String winner, String[] guess) {
        String character = Character.CharacterName.get(guess[0]).get().getLabel();
        String weapon = Weapon.fromName(guess[1]).getName();
        String room = Room.RoomName.get(guess[2]).get().getLabel();
        String message = String.format("%s won! The murderer was %s with the %s in the %s.", winner, character, weapon, room);
        printStatement(message);
        gameEnded = true;
    }

    public void endGameNoWinner(String loser, String[] caseFile) {
        incorrectAccusation(loser);
        String character = Character.CharacterName.get(caseFile[0]).get().getLabel();
        String weapon = Weapon.fromName(caseFile[1]).getName();
        String room = Room.RoomName.get(caseFile[2]).get().getLabel();
        String message = String.format("All players have lose. The murderer was %s with the %s in the %s.", loser, character, weapon, room);
        appendStatement(message);
        gameEnded = true;
    }

    public void incorrectAccusation(String loser) {
        String message = String.format("%s made an incorrect accusation.", loser);
        printStatement(message);
    }

    public String chooseParams(List<String> characters, List<String> weapons)
    {
        printStatement("These are the characters you can choose from");
        int characterOptNum = getUserInput(characters, "character");
        String character = characters.get(characterOptNum);

        printStatement(String.format("These are the weapons you can choose from for %s", character));
        int weaponOptNum = getUserInput(weapons, "weapon");
        String weapon = weapons.get(weaponOptNum);

        return String.format("%s_%s", character, weapon);
    }

    public String chooseParams(List<String> characters, List<String> weapons, List<String> rooms)
{
        printStatement("These are the characters you can choose from");
        int characterOptNum = getUserInput(characters, "character");
        String character = characters.get(characterOptNum);

        printStatement(String.format("These are the weapons you can choose from"));
        int weaponOptNum = getUserInput(weapons, "weapon");
        String weapon = weapons.get(weaponOptNum);

        printStatement(String.format("These are the rooms you can choose from"));
        int roomOptNum = getUserInput(rooms, "room");
        String room = rooms.get(roomOptNum);

        return String.format("%s_%s_%s", character, weapon, room);
    }

    public List<String> getValidOptions(String option) {
        try {
            output.writeObject(option);
            output.flush();

            //Use this serverResponse to read response from server
            List<String> received = (List<String>) serverResponse.take();
            return received;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void sendMessage(String message)  {
        System.out.println("sending: " + message);
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void clean()
    {
        try
        {
            clientSocket.close();
            output.close();
            input.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    // Public accessor to add a card to the user's hand
    public void addCard(CardEntityInterface card) {
        this.hand.add(card);
    }

    // Public accessor to retrieve cards of the current user
    public List<CardEntityInterface> getCards() {
        return Collections.unmodifiableList(this.hand);
    }

    private void printStatement(String message) {
        gui.setClientPrompt(message); // GUI version
        System.out.println(message); // Terminal version
    }

    private void appendStatement(String message)
    {
        gui.appendClientPrompt(message);
        System.out.println(message);
    }

    private void movePlayer(String player, String location) {
        gui.movePlayer(player, location);
    }

    public static void main(String[] args)
    {
        // Client object is created with localhost address and port 49882 (ensure that port is open before program is ran)
        Client client = new Client("127.0.0.1", 59882);
    }
}