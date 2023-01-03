package jhu.softwaresleuthers.clueless.models;

import lombok.Getter;

public class CaseFile {

    @Getter
    private CardEntityInterface character;

    @Getter
    private CardEntityInterface room;

    @Getter
    private CardEntityInterface weapon;

    public CaseFile(CardEntityInterface character,
                    CardEntityInterface room,
                    CardEntityInterface weapon) {
        this.character = character;
        this.room = room;
        this.weapon = weapon;
    }

    // The Character, Room, and Weapon must match the Case File in order to be equal
    public boolean equals(Guess accusation) {
        return this.character.getName().equals(accusation.getCharacter().getName()) &&
                this.room.getName().equals(accusation.getRoom().getName()) &&
                this.weapon.getName().equals(accusation.getWeapon().getName());
    }

}
