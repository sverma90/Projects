package jhu.softwaresleuthers.clueless.models;

import jhu.softwaresleuthers.clueless.models.Character;
import jhu.softwaresleuthers.clueless.models.Room;
import jhu.softwaresleuthers.clueless.models.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;


@SpringBootTest
public class RoomTests {

    @Test
    void TestRoomConstructor() {
        Room study = new Room(Room.RoomName.STUDY);
        Room hall = new Room(Room.RoomName.HALL);
        Room lounge = new Room(Room.RoomName.LOUNGE);
        Room library = new Room(Room.RoomName.LIBRARY);
        Room billiardRoom = new Room(Room.RoomName.BILLIARD_ROOM);
        Room diningRoom = new Room(Room.RoomName.DINING_ROOM);
        Room conservatory = new Room(Room.RoomName.CONSERVATORY);
        Room ballroom = new Room(Room.RoomName.BALLROOM);
        Room kitchen = new Room(Room.RoomName.KITCHEN);

        assert study.getRoomName().equals(Room.RoomName.STUDY);
        assert hall.getRoomName().equals(Room.RoomName.HALL);
        assert lounge.getRoomName().equals(Room.RoomName.LOUNGE);
        assert library.getRoomName().equals(Room.RoomName.LIBRARY);
        assert billiardRoom.getRoomName().equals(Room.RoomName.BILLIARD_ROOM);
        assert diningRoom.getRoomName().equals(Room.RoomName.DINING_ROOM);
        assert conservatory.getRoomName().equals(Room.RoomName.CONSERVATORY);
        assert ballroom.getRoomName().equals(Room.RoomName.BALLROOM);
        assert kitchen.getRoomName().equals(Room.RoomName.KITCHEN);
    }

    @Test
    void TestRoomShortcuts() {
        Room study = new Room(Room.RoomName.STUDY);
        assert study.getShortcut().equals(Room.RoomName.KITCHEN);

        Room kitchen = new Room(Room.RoomName.KITCHEN);
        assert kitchen.getShortcut().equals(Room.RoomName.STUDY);

        Room library = new Room(Room.RoomName.LIBRARY);
        assert library.getShortcut() == null;
    }


    @Test
    void TestRoomHallways() {
        Room study = new Room(Room.RoomName.STUDY);
        assert study.getHallMoveOptions().equals(Set.of(1, 3));
    }

    @Test
    void TestGetRoomCharacters() {
        Room diningRoom = new Room(Room.RoomName.DINING_ROOM);
        // TODO
    }

    @Test
    void TestGetRoomWeapons() {
        Room kitchen = new Room(Room.RoomName.KITCHEN);
        // TODO
    }

}