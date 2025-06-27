package xXxDungeon_EscapexXx.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Equipable> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Equipable item) {
        // count existing items with the same name
        int sameCount = 0;
        for (Equipable existing : items) {
            if (existing.getName().equals(item.getName())) {
                sameCount++;
                if (sameCount >= 2) {
                    // If more than two already exist, skip them. So no bloating.
                    return;
                }
            }
        }
        // if we get here, sameCount is 0 or 1
        items.add(item);
    }

    public boolean remove(Equipable item) {
        return items.remove(item);
    }

    public List<Equipable> getItems() {
        return items;
    }

    public int size() {
        return items.size();
    }

    public void printInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventaire vide.");
        } else {
            int count = 1;
            System.out.println("Contenu :");
            for (Equipable item : items) {
                System.out.println(count + " - " + item.getName());
                count++;
            }
        }
    }
}
