package xXxDungeon_EscapexXx;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Level {
    private List<Room> rooms;

    public Level(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean play(Player player, CombatManager manager) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;

        while (index < rooms.size()) {
            Room current = rooms.get(index);
            boolean survived = current.enter(player, manager);
            if (!survived) return false;

            if (current.isBossRoom()) {
                System.out.println("Vous avez vaincu le boss de ce niveau !");
                return true;
            }

            if (index + 2 < rooms.size()) {
                System.out.println("Choisissez votre chemin : 1 ou 2 ?");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    index += 1;
                } else {
                    index += 2;
                }
            } else {
                index += 1;
            }
        }

        return true;
    }
}
