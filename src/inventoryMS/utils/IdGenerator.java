package inventoryMS.utils;

import java.util.UUID;

public class IdGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
