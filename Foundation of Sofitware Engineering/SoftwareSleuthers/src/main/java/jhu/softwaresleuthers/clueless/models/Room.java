package jhu.softwaresleuthers.clueless.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class Room implements CardEntityInterface {

    // String representing the Room ID
    private final String id = java.util.UUID.randomUUID().toString();

    // Enum representing the room name
    private final RoomName room;

    // List of Character ids that are currently in the Room
    private List<String> characterIds;

    // List of Hallway ids that connect to this Room
    private List<String> hallwayIds;

    // List of Weapons that are in this Room
    private List<Weapon> weapons;

    // Optional Shortcut that will be populated if one exists
    private Optional<Room> optionalShortcut;

    // Map Room -> adjacent Hallway numbers
    public static final HashMap<RoomName, Set<Integer>> hallwayMap = new HashMap<>();
    static {
        hallwayMap.put(RoomName.STUDY, Set.of(1, 3));
        hallwayMap.put(RoomName.HALL, Set.of(1, 4, 2));
        hallwayMap.put(RoomName.LOUNGE, Set.of(2, 5));
        hallwayMap.put(RoomName.LIBRARY, Set.of(3, 6, 8));
        hallwayMap.put(RoomName.BILLIARD_ROOM, Set.of(4, 6, 7, 9));
        hallwayMap.put(RoomName.DINING_ROOM, Set.of(5, 7, 10));
        hallwayMap.put(RoomName.CONSERVATORY, Set.of(8, 11));
        hallwayMap.put(RoomName.BALLROOM,Set.of(11, 9, 12));
        hallwayMap.put(RoomName.KITCHEN, Set.of(10, 12));
    }

    // Map Room -> Room connected by a shortcut
    public static final HashMap<RoomName, RoomName> shortcutMap = new HashMap<>();
    static {
        shortcutMap.put(RoomName.STUDY, RoomName.KITCHEN);
        shortcutMap.put(RoomName.KITCHEN, RoomName.STUDY);
        shortcutMap.put(RoomName.CONSERVATORY, RoomName.LOUNGE);
        shortcutMap.put(RoomName.LOUNGE, RoomName.CONSERVATORY);
    }


    public RoomName getRoomName() {
        return this.room;
    }

    public Set<Integer> getHallMoveOptions() {
        return hallwayMap.getOrDefault(this.room, null);
    }

    public RoomName getShortcut() {
        return shortcutMap.getOrDefault(this.room, null);
    }

    // Required for the CardEntityInterface
    public String getName() {
        return this.room.getLabel();
    }

    public Room(RoomName name) {
        this.room = name;
        this.characterIds = new ArrayList<>();
        this.hallwayIds = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.optionalShortcut = Optional.empty();
    }

    /**
     * This Enum is an exhaustive list of available rooms.
     */
    public enum RoomName {
        STUDY("Study"),
        HALL("Hall"),
        LOUNGE("Lounge"),
        LIBRARY("Library"),
        BILLIARD_ROOM("Billiard Room"),
        DINING_ROOM("Dining Room"),
        CONSERVATORY("Conservatory"),
        BALLROOM("Ballroom"),
        KITCHEN("Kitchen");

        private RoomName(String label) { this.label = label; }

        private final String label;

        public String getLabel() {
            return label;
        }

        public static Optional<RoomName> get(String label) {
            return Arrays.stream(RoomName.values()).filter(name -> name.getLabel().equals(label)).findFirst();
        }

        public static List<String> names() {
            return Arrays.stream(RoomName.values()).map((characterName1 -> characterName1.getLabel())).collect(Collectors.toList());
        }
    }
}
