package xXxDungeon_EscapexXx;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Equipable> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Equipable item) {
        items.add(item);
    }

    public boolean remove(Equipable item) {
        return items.remove(item);
    }

    public List<Equipable> getItems() {
        return items;
    }

    public void printInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventaire vide.");
        } else {
            System.out.println("Contenu :");
            for (Equipable item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }
}
