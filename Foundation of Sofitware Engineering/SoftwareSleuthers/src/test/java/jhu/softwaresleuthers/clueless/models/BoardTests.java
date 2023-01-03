package jhu.softwaresleuthers.clueless.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@SpringBootTest
public class BoardTests {

    @Test
    void TestBoardInitialize() {
        Board board = new Board(Collections.emptyList());

        assert !board.getCharacters().isEmpty() && !board.getRooms().isEmpty();
        assert board.getCharacters().size() == 6;
        assert board.getRooms().size() == 9;
        assert board.getCaseFile() != null;
    }

    @Test
    void TestBoardReinitialize() {
        Board board = new Board(Collections.emptyList());

        assert board.getRooms().get(Room.RoomName.STUDY).getWeapons().isEmpty();

        board.getRooms().get(Room.RoomName.STUDY).setWeapons(Collections.singletonList(Weapon.fromName("candlestick")));
        assert !board.getRooms().get(Room.RoomName.STUDY).getWeapons().isEmpty();

        board.reinitialize(Collections.emptyList());
        assert board.getRooms().get(Room.RoomName.STUDY).getWeapons().isEmpty();
    }

    @Test
    void TestBoardRoomNavigation() {
        Board board = new Board(Collections.emptyList());
        Room library = board.getRooms().get(Room.RoomName.LIBRARY);
        Hallway hallway3 = board.getHallways().get(3);

        assert board.moveOptionsRoom(library).contains(hallway3);
    }

    @Test
    void TestBoardHallwayNavigation() {
        Board board = new Board(Collections.emptyList());
        Room library = board.getRooms().get(Room.RoomName.LIBRARY);
        Hallway hallway3 = board.getHallways().get(3);

        assert board.moveOptionsHallway(hallway3).contains(library);
    }

    @Test
    void TestEvaluateAccusation() {
        Board board = new Board(Collections.emptyList());
        CaseFile caseFile = board.getCaseFile();
        Guess guess = new Guess(caseFile.getCharacter(), caseFile.getRoom(), caseFile.getWeapon(), true);

        assert board.evaluateAccusation(guess);
    }

    @Test
    void TestDealCards() {
        Board board = new Board(Collections.emptyList());
        HashMap<Character, ArrayList<CardEntityInterface>> hands = board.getHands();
        assert hands.values().size() == board.getCharacters().size();

        for (ArrayList hand : hands.values()) {
            assert !hand.contains(board.getCaseFile().getCharacter());
            assert !hand.contains(board.getCaseFile().getRoom());
            assert !hand.contains(board.getCaseFile().getWeapon());
        }
    }
}
