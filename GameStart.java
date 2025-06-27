package xXxDungeon_EscapexXx;

import java.util.Scanner;

import xXxDungeon_EscapexXx.managers.CombatManager;
import xXxDungeon_EscapexXx.model.Enemy;
import xXxDungeon_EscapexXx.model.Player;
import xXxDungeon_EscapexXx.world.Room;

import java.util.Random;
import java.util.List;

public class GameStart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        CombatManager manager = new CombatManager();

        // Menu principal
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Commencer");
            System.out.println("2. Quitter");
            System.out.print("Choix: ");
            int choice = scanner.nextInt();
            if (choice == 2) {
                System.out.println("Au revoir !");
                return;
            } else if (choice != 1) {
                System.out.println("Choix invalide.");
                continue;
            }

            // Saisie pseudo
            scanner.nextLine();
            String name;
            do {
                System.out.print("Pseudo: ");
                name = scanner.nextLine();
                System.out.print("Confirmer '" + name + "' ? (o/n): ");
            } while (!scanner.nextLine().equalsIgnoreCase("o"));

            Player player = new Player(name, 100, 10);
            int level = 1;

            // Boucle de progression par niveau
            while (true) {
                System.out.println("\n--- Niveau " + level
                        + " | PV: " + player.getHp() + " | XP: " + player.getXp() + " ---");

                // Génération de deux salles possibles (boss inclus)
                Room roomA = makeRoom(rand, level);
                Room roomB = makeRoom(rand, level);

                // Affichage des choix : juste le nom et tag boss
                System.out.println("1) " + roomA.getShortDescription());
                System.out.println("2) " + roomB.getShortDescription());
                System.out.print("Choisissez une salle (1/2): ");
                int sel = scanner.nextInt();

                Room chosen = (sel == 1 ? roomA : roomB);
                Room alternative = (sel == 1 ? roomB : roomA);

                // Entrée dans la salle
                boolean survived = chosen.enter(player, manager);
                if (!survived) {
                    return;
                }

                // Si c'était le boss et qu'il est vaincu, on passe au level suivant
                if (chosen.isBossRoom()) {
                    System.out.println("Boss vaincu ! Vous passez au niveau " + (level + 1));
                    level++;
                    break; // sort de la boucle de ce niveau
                }

                // Victoire sur monstre régulier : options
                postCombatOptions(player, scanner);

                // Reprompt tant que boss non vaincu
                // Reboucle pour rechoisir entre roomA et roomB
            }
        }
    }

    /** Crée une Room : 10% de chance que ce soit le boss, sinon monstre standard */
    private static Room makeRoom(Random rand, int level) {
        boolean boss = rand.nextDouble() < 0.1;
        Enemy e;
        if (boss) {
            List<String> bosses = List.of(
                    "Baran, Roi des Enfers",
                    "Beru, Prince des Fourmis",
                    "Igris, Chevalier Royal",
                    "Khan, Seigneur des Laves");
            String bossName = bosses.get((level - 1) % bosses.size());
            e = new Enemy(bossName, 100 + level * 50, rand.nextInt(10) + 5,
                    100 + level * 50, 15 * level, 25 * level);
            e.initBossLoot(level);
        } else {
            String[] names = { "Gobelin", "Orc", "Squelette", "Rat géant" };
            String mName = names[rand.nextInt(names.length)];
            e = new Enemy(mName, 20 * level + rand.nextInt(11),
                    rand.nextInt(10) + 1, 10 * level + rand.nextInt(11),
                    5 * level, 8 * level);
            e.initStandardLoot(level);
        }
        return new Room(e, boss);
    }

    /** Options après victoire sur un monstre non-boss */
    private static void postCombatOptions(Player player, Scanner sc) {
        while (true) {
            System.out.println("\nQue voulez-vous faire ? 1) Continuer  2) Rééquiper  3) Quitter");
            System.out.print("Choix: ");
            int c = sc.nextInt();
            if (c == 1)
                return; // renvoie au choix de salle
            if (c == 2) {
                // Show inventory
                player.getInventory().printInventory();
                // Show equipped
                System.out.printf("Main droite : %s%n",
                        player.getEquippedRight() != null
                                ? player.getEquippedRight().getName()
                                : "Rien");
                System.out.printf("Main gauche : %s%n",
                        player.getEquippedLeft() != null
                                ? player.getEquippedLeft().getName()
                                : "Rien");
                System.out.println("Remplacez avec les indexs (0 pour garder)");
                System.out.print("Index main droite: ");
                int r = sc.nextInt();
                if (r == 0) {
                    // Aucun changement
                } else if (r >= 1 && r <= player.getInventory().size()) {
                    player.unequipRight();
                    player.equipRight(player.getInventory().getItems().get(r - 1));
                } else {
                    System.out.println("Index invalide pour la main droite, aucun changement.");
                }
                System.out.print("Index main gauche: ");
                int l = sc.nextInt();
                if (l == 0) {
                    // Aucun changement
                } else if (l >= 1 && l <= player.getInventory().size()) {
                    player.unequipLeft();
                    player.equipLeft(player.getInventory().getItems().get(l - 1));
                } else {
                    System.out.println("Index invalide pour la main gauche, aucun changement.");
                }
            } else {
                System.out.println("Fin de partie.");
                System.exit(0);
            }
        }
    }
}
