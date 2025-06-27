package xXxDungeon_EscapexXx;

import java.util.Scanner;

public class Room {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final Enemy enemy;
    private final boolean isBossRoom;

    public Room(Enemy enemy, boolean isBossRoom) {
        this.enemy = enemy;
        this.isBossRoom = isBossRoom;
    }

    public String getDescription() {
        String type = isBossRoom ? "[BOSS]" : "[Salle]";
        return String.format("%s %s (PV: %d, VIT: %d)",
            type,
            enemy.getName(),
            enemy.getHp(),
            enemy.getSpeed()
        );
    }

    public boolean enter(Player player, CombatManager manager) {
        System.out.println("\nVous entrez dans une " 
            + (isBossRoom ? "salle de boss" : "salle") + "...");
        if (enemy != null) {
            System.out.println("Un ennemi apparaît !");
            enemy.describe();
            System.out.print("Combat (c) ou Fuir (f) ? ");
            String input = SCANNER.nextLine().trim().toLowerCase();
            if (input.equals("f")) {
                int penalty = 5;
                System.out.println("Vous fuyez et perdez " + penalty + " PV.");
                player.takeDamage(penalty);
                return player.isAlive();
            }
            manager.resolveCombat(player, enemy);
            return player.isAlive();
        }
        System.out.println("La salle est vide.");
        return true;
    }

    public boolean isBossRoom() {
        return isBossRoom;
    }

    public String getShortDescription() {
    String tag = isBossRoom ? "[BOSS]" : "[Salle]";
    return tag + " " + enemy.getName();
    }

}
