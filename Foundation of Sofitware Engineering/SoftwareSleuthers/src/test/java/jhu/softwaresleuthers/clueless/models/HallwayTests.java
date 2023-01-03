package jhu.softwaresleuthers.clueless.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HallwayTests {

    @Test
    void TestHallwayConstructor() {
        Room study = new Room(Room.RoomName.STUDY);
        Room hall = new Room(Room.RoomName.HALL);
        Hallway hallway = new Hallway(1);

        assert hallway.getCharacterId().equals("");
        assert hallway.getHallwayNum().equals(1);
    }

    @Test
    void TestGetHallwayId() {
        Hallway hallway = new Hallway(1);

        assert hallway.getId() != null && hallway.getId() != "";
    }

    @Test
    void TestGetHallwayCharacterId() {
        Character character = new Character(Character.CharacterName.PEACOCK);
        Hallway hallway = new Hallway(1);
        hallway.setCharacterId(character.getId());

        assert hallway.getCharacterId().equals(character.getId());
    }

    @Test
    void TestGetHallwayRoomIdList() {
        Room study = new Room(Room.RoomName.STUDY);
        Room hall = new Room(Room.RoomName.HALL);
        Hallway hallway = new Hallway(1);

        assert hallway.getCharacterId().equals("");
        assert hallway.getMoveOptions().contains(study.getRoomName());
        assert hallway.getMoveOptions().contains(hall.getRoomName());
    }
}
