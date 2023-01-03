package jhu.softwaresleuthers.clueless.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterTests {

    @Test
    void TestCharacterConstructor() {
        Character character = new Character(Character.CharacterName.PEACOCK);

        assert character.getCharacterName().equals(Character.CharacterName.PEACOCK);
        assert character.getCurrentLocationId().equals(Character.CharacterName.PEACOCK.getLabel());
    }

    @Test
    void TestGetCharacterName() {
        Character character = new Character(Character.CharacterName.WHITE);

        assert character.getCharacterName().equals(Character.CharacterName.WHITE);
    }

    @Test
    void TestGetCharacterLocation() {
        Room room = new Room(Room.RoomName.BILLIARD_ROOM);
        Character character = new Character(Character.CharacterName.WHITE);
        character.setCurrentLocationId(room.getId());

        assert character.getCurrentLocationId().equals(room.getId());
    }


    @Test
    void TestGetCharacterId() {
        Character character = new Character(Character.CharacterName.WHITE);

        assert character.getId() != null &&  character.getId() != "";
    }

    @Test
    void TestGetCharacterNameEnums() {
        Character peacock = new Character(Character.CharacterName.PEACOCK);
        Character white = new Character(Character.CharacterName.WHITE);
        Character plum = new Character(Character.CharacterName.PLUM);
        Character scarlet = new Character(Character.CharacterName.SCARLET);
        Character mustard = new Character(Character.CharacterName.MUSTARD);
        Character green = new Character(Character.CharacterName.GREEN);

        assert peacock.getName().equals(Character.CharacterName.PEACOCK.getLabel());
        assert white.getName().equals(Character.CharacterName.WHITE.getLabel());
        assert plum.getName().equals(Character.CharacterName.PLUM.getLabel());
        assert scarlet.getName().equals(Character.CharacterName.SCARLET.getLabel());
        assert mustard.getName().equals(Character.CharacterName.MUSTARD.getLabel());
        assert green.getName().equals(Character.CharacterName.GREEN.getLabel());
    }
}
