package entities;

import java.util.Arrays;

public class Defaults {
    public static User DEFAULT_USER = new User().set(u-> {
        u.setName("epam");
        u.setPassword("1234");
        u.setFullName("PITER CHAILOVSKII");
    });

    public static MetalsAndColorsPageData DEFAULT_DATA = new MetalsAndColorsPageData().set(d-> {
        d.setSummary(Arrays.asList(3, 8));
        d.setElements(Arrays.asList("Water", "Fire"));
        d.setColor("Red");
        d.setMetals("Selen");
        d.setVegetables(Arrays.asList("Cucumber", "Tomato"));
    });
}
