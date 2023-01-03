package jhu.softwaresleuthers.clueless.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WeaponTests {

    @Test
    void TestWeaponDefaultEnums() {
        assert Weapon.values().length == 6;
        assert List.of(Weapon.values())
                .containsAll(List.of(
                        Weapon.CANDLESTICK,
                        Weapon.DAGGER,
                        Weapon.LEAD_PIPE,
                        Weapon.REVOLVER,
                        Weapon.ROPE,
                        Weapon.WRENCH));
    }

    @Test
    void TestGetWeaponName() {
        assert Weapon.LEAD_PIPE.getName().equals("Lead Pipe");
    }

    @Test
    void TestGetWeaponFromString() {
        assert Weapon.fromName("Lead Pipe").equals(Weapon.LEAD_PIPE);
        assert Weapon.fromName("Lead pope") == null;
    }
}