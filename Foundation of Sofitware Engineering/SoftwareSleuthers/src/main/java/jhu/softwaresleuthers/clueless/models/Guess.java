package jhu.softwaresleuthers.clueless.models;

import lombok.Data;

@Data
public class Guess {

    // Character involved in the Guess/Accusation
    private CardEntityInterface character;

    // Room involved in the Guess/Accusation
    // NOTE: If this is an accusation, the room must be derived from the player's location!
    private CardEntityInterface room;

    // Weapon involved in the Guess/Accusation
    private CardEntityInterface weapon;

    // True if accusation, false if only suggestion
    private boolean accusation;

    public Guess(CardEntityInterface character,
                 CardEntityInterface room,
                 CardEntityInterface weapon,
                 boolean accusation) {
        this.character = character;
        this.room = room;
        this.weapon = weapon;
        this.accusation = accusation;
    }
}
