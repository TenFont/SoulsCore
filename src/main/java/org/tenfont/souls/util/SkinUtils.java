package org.tenfont.souls.util;

import java.util.Random;
import java.util.UUID;

public class SkinUtils {
    private static final UUID[] lostSoulSkins = {
            UUID.fromString("65a289dd-de9a-4933-b7af-cb325085cad5"),
            UUID.fromString("f587e7d1-08ef-4c43-90cb-37e237000153")
    };

    public static UUID getRandomLostSoulSkin() {
        int index = new Random().nextInt(lostSoulSkins.length);
        return lostSoulSkins[index];
    }
}
