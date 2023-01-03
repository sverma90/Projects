package jhu.softwaresleuthers.clueless.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Enum is an exhaustive list of available weapons.
 */
public enum Weapon implements CardEntityInterface {
    CANDLESTICK("Candlestick"),
    DAGGER("Dagger"),
    LEAD_PIPE("Lead Pipe"),
    REVOLVER("Revolver"),
    ROPE("Rope"),
    WRENCH("Wrench");

    String name;

    Weapon(String name) {
        this.name = name;
    }

    // Required for the CardEntityInterface
    public String getName() {
        return this.name;
    }

    public static Weapon fromName(String name) {
        return Arrays.stream(Weapon.values())
                .filter(weaponEnum -> weaponEnum.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }


    public static List<String> names() {
        return Arrays.stream(Weapon.values()).map((characterName1 -> characterName1.getName())).collect(Collectors.toList());
    }
}
