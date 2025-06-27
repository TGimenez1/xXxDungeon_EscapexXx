package xXxDungeon_EscapexXx;

import java.util.List;

public class LootManager {

    public static void handleDrops(Player player, List<Equipable> drops) {
        if (drops.isEmpty()) {
            System.out.println("No loot this time.");
            return;
        }
        for (Equipable item : drops) {
            System.out.printf("You found a %s!%n", item.getName());

            // simple auto equip: if right is empty, use it; else left; else put in inventory
            if (player.getEquippedRight() == null) {
                player.equipRight(item);
            } else if (player.getEquippedLeft() == null) {
                player.equipLeft(item);
            } else {
                player.getInventory().add(item);
                System.out.printf("You put the %s in your inventory.%n", item.getName());
            }
        }
    }
}
