package jhu.softwaresleuthers.clueless.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Set;

@Data
public class Hallway {

    // String representing the Hallway ID
    private final String id = java.util.UUID.randomUUID().toString();

    // Character currently in the Hallway (only 1 character allowed)
    private String characterId;

    // Integer label corresponding to hallway
    public Integer hallwayNum;

    // Map Hallway number -> Set of rooms that the hallway connects
    public static final HashMap<Integer, Set<Room.RoomName>> connectionMap = new HashMap<>();
    static {
        connectionMap.put(1, Set.of(Room.RoomName.STUDY, Room.RoomName.HALL));
        connectionMap.put(2, Set.of(Room.RoomName.HALL, Room.RoomName.LOUNGE));
        connectionMap.put(3, Set.of(Room.RoomName.STUDY, Room.RoomName.LIBRARY));
        connectionMap.put(4, Set.of(Room.RoomName.HALL, Room.RoomName.BILLIARD_ROOM));
        connectionMap.put(5, Set.of(Room.RoomName.LOUNGE, Room.RoomName.DINING_ROOM));
        connectionMap.put(6, Set.of(Room.RoomName.LIBRARY, Room.RoomName.BILLIARD_ROOM));
        connectionMap.put(7, Set.of(Room.RoomName.BILLIARD_ROOM, Room.RoomName.DINING_ROOM));
        connectionMap.put(8, Set.of(Room.RoomName.LIBRARY, Room.RoomName.CONSERVATORY));
        connectionMap.put(9, Set.of(Room.RoomName.BILLIARD_ROOM, Room.RoomName.BALLROOM));
        connectionMap.put(10, Set.of(Room.RoomName.DINING_ROOM, Room.RoomName.KITCHEN));
        connectionMap.put(11, Set.of(Room.RoomName.CONSERVATORY, Room.RoomName.BALLROOM));
        connectionMap.put(12, Set.of(Room.RoomName.BALLROOM, Room.RoomName.KITCHEN));
    }

    public Set<Room.RoomName> getMoveOptions() {
        return connectionMap.get(this.hallwayNum);
    }

    public Hallway(Integer hallwayNum) {
        this.hallwayNum = hallwayNum;
        characterId = ""; // No character in hallway
    }
}
