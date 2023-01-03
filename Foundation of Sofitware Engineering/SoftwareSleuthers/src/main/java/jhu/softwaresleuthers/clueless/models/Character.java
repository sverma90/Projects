package jhu.softwaresleuthers.clueless.models;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Character implements CardEntityInterface {

    // String representing the Character ID
    private final String id = java.util.UUID.randomUUID().toString();

    // Enum representing character name
    private final CharacterName characterName;

    // String representing the Hallway or Room id that the Character is currently in
    private String currentLocationId;

    public Character(CharacterName name) {
        characterName = name;
        // Character starting location is their assigned starting positions, which correspond to their name.
        currentLocationId = name.getLabel();
    }

    // Required for the CardEntityInterface
    public String getName() {
        return characterName.getLabel();
    }

    /**
     * This Enum is an exhaustive list of available characters.
     */
    public enum CharacterName {
        PEACOCK("Mrs. Peacock"),
        WHITE("Mrs. White"),
        PLUM("Professor Plum"),
        SCARLET("Miss Scarlet"),
        MUSTARD("Colonel Mustard"),
        GREEN("Mr. Green");

        private CharacterName(String label) { this.label = label; }

        private final String label;

        public String getLabel() {
            return label;
        }

        public static Optional<CharacterName> get(String label) {
            return Arrays.stream(CharacterName.values()).filter(name -> name.getLabel().equals(label)).findFirst();
        }

        public static List<String> names() {
            return Arrays.stream(CharacterName.values()).map((name -> name.getLabel())).collect(Collectors.toList());
        }
    }
}
