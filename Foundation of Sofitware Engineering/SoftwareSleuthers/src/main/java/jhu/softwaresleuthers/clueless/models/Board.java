package jhu.softwaresleuthers.clueless.models;

import lombok.Data;

import java.util.*;

@Data
public class Board {

    private static Random random;

    private List<Character> characters;

    private List<Character> players;

    private HashMap<Room.RoomName, Room> rooms;

    private HashMap<Integer, Hallway> hallways;

    private HashMap<Character, ArrayList<CardEntityInterface>> hands;

    private final List<CardEntityInterface> cards = new ArrayList<>();

    private final static List<Weapon> weapons = List.of(
            Weapon.CANDLESTICK,
            Weapon.DAGGER,
            Weapon.LEAD_PIPE,
            Weapon.REVOLVER,
            Weapon.ROPE,
            Weapon.WRENCH
    );

    private CaseFile caseFile;

    // Map Player Start -> adjacent Hallway numbers
    private static final HashMap<Character.CharacterName, Integer> playerStartMap = new HashMap<>();
    static {
        playerStartMap.put(Character.CharacterName.PEACOCK, 8);
        playerStartMap.put(Character.CharacterName.WHITE, 12);
        playerStartMap.put(Character.CharacterName.PLUM, 3);
        playerStartMap.put(Character.CharacterName.SCARLET, 2);
        playerStartMap.put(Character.CharacterName.MUSTARD, 5);
        playerStartMap.put(Character.CharacterName.GREEN, 11);
    }

    public Board(List<String> players) {
        this.reinitialize(players);
    }

    /**
     * This public method is used to create and reset the board at any time.
     */
    public void reinitialize(List<String> players) {
        random = new Random();

        this.characters = List.of(
                new Character(Character.CharacterName.GREEN),
                new Character(Character.CharacterName.MUSTARD),
                new Character(Character.CharacterName.PEACOCK),
                new Character(Character.CharacterName.PLUM),
                new Character(Character.CharacterName.SCARLET),
                new Character(Character.CharacterName.WHITE)
        );

        if (players.size() == 0) { // default to all characters being played
            this.players = this.characters;
        } else {
            this.players = new ArrayList<>();
            for (String player : players) {
                this.players.add(getCharacter(player).get());
            }
        }

        this.rooms = initializeRooms();

        this.hallways = initializeHallways();

        initializeCaseFile();

        initializeCards();

        this.hands = dealCards();
    }

    // Create new room objects
    public HashMap<Room.RoomName, Room> initializeRooms() {
        HashMap<Room.RoomName, Room> roomMap = new HashMap<>();
        for (Room.RoomName name : Room.RoomName.values()) {
            roomMap.put(name, new Room(name));
        }
        return roomMap;
    }

    // Create new hallway objects
    public HashMap<Integer, Hallway> initializeHallways() {
        HashMap<Integer, Hallway> hallways = new HashMap<>();
        for (Integer hallwayNum : Hallway.connectionMap.keySet()) {
            hallways.put(hallwayNum, new Hallway(hallwayNum));
        }
        return hallways;
    }

    // Get ids of rooms to move to from hallway
    public List<Room> moveOptionsHallway(Hallway hallway) {
        List<Room> roomIds = new ArrayList<>();
        for (Room.RoomName roomOption : hallway.getMoveOptions()) {
            roomIds.add(this.rooms.get(roomOption));
        }
        return roomIds;
    }

    // Get ids of hallways to move to from room
    public List<Hallway> moveOptionsRoom(Room room) {
        List<Hallway> hallwayIds = new ArrayList<>();
        for (Integer hallwayNum : room.getHallMoveOptions()) {
            hallwayIds.add(this.hallways.get(hallwayNum));
        }
        return hallwayIds;
    }

    // Get ids of hallways to move to from room
    public Hallway moveOptionsPlayerStart(Character.CharacterName player) {
        return hallways.get(playerStartMap.get(player));
    }

    // Create new case file (winning character, room, & weapon)
    private void initializeCaseFile() {
        Character randomCharacter = this.characters.get(random.nextInt(characters.size()));
        List<Room> roomsList = new ArrayList<>(this.rooms.values());
        Room randomRoom = roomsList.get(random.nextInt(roomsList.size()));
        Weapon randomWeapon = weapons.get(random.nextInt(weapons.size()));
        this.caseFile = new CaseFile(randomCharacter, randomRoom, randomWeapon);
        // TODO: Eventually delete, but helpful for testing during development
        System.out.println(randomCharacter.getName() + ", " + randomRoom.getName() + ", " + randomWeapon.getName());
    }

    // Create and populate the list of game cards
    public void initializeCards() {
        cards.addAll(characters);
        cards.addAll(rooms.values());
        cards.addAll(weapons);
    }

    // Deal out cards to all players, excluding ones in the CaseFile
    public HashMap<Character, ArrayList<CardEntityInterface>> dealCards() {
        HashMap<Character, ArrayList<CardEntityInterface>> hands = new HashMap<>();
        for (Character character : this.players) {
            hands.put(character, new ArrayList<>());
        }

        // Exclude cards in case file
        ArrayList<CardEntityInterface> dealCards = new ArrayList<>();
        for (CardEntityInterface card : cards) {
            if ((card.equals(this.caseFile.getCharacter()))
                    || (card.equals(this.caseFile.getWeapon()))
                    || (card.equals(this.caseFile.getRoom()))) {
                continue;
            }
            dealCards.add(card);
        }

        Collections.shuffle(dealCards);
        for (int i=0; i < dealCards.size(); i++) {
            hands.get(this.players.get(i % this.players.size())).add(dealCards.get(i));
        }
        return hands;
    }

    // Return the card that matches the given name
    public Optional<CardEntityInterface> getCard(String name) {
        return cards.stream().filter(card -> card.getName().equals(name)).findFirst();
    }

    // Return the character that matches the given name
    public Optional<Character> getCharacter(String name) {
        return this.characters.stream().filter(character -> character.getName().equals(name)).findFirst();
    }

    // Return the list of game cards
    public List<CardEntityInterface> getAllCards() {
        return cards;
    }

    // Public helper method to expose the board's Random object
    public static int getRandomInt() {
        return random.nextInt();
    }

    // Determine if accusation matches case file
    public boolean evaluateAccusation(Guess guess) {
        return this.caseFile.equals(guess);
    }

    public List<CardEntityInterface> getHand(String playerName) {
        return hands.get(getCharacter(playerName).get());
    }

    // Prove/disprove suggestion based on player hand
    public CardEntityInterface evaluateSuggestion(String playerName, Guess guess) {
        List<CardEntityInterface> hand = hands.get(getCharacter(playerName).get());
        for (CardEntityInterface card : hand) {
            if (card.equals(guess.getCharacter()) || card.equals(guess.getRoom()) || card.equals(guess.getWeapon())) {
                return card;
            }
        }
        return null;
    }
}
