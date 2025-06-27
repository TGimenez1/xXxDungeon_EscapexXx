package xXxDungeon_EscapexXx;

import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class GameStart {
    public static void main(String[] args) {
        Scanner scanner       = new Scanner(System.in);
        Random rand           = new Random();
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

                Room chosen   = (sel == 1 ? roomA : roomB);
                Room alternative = (sel == 1 ? roomB : roomA);

                // Entrée dans la salle
                boolean survived = chosen.enter(player, manager);
                if (!survived) {
                    System.out.println("Vous êtes mort… GAME OVER");
                    return;
                }

                // Si c'était le boss et qu'il est vaincu, on passe au level suivant
                if (chosen.isBossRoom()) {
                    System.out.println("Boss vaincu ! Vous passez au niveau " + (level+1));
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
                "Khan, Seigneur des Laves"
            );
            String bossName = bosses.get((level-1) % bosses.size());
            e = new Enemy(bossName, 200 + level*50, rand.nextInt(10)+5,
                100 + level*50, 20*level, 30*level);
            e.initBossLoot(level);
        } else {
            String[] names = {"Gobelin","Orc","Squelette","Rat géant"};
            String mName = names[rand.nextInt(names.length)];
            e = new Enemy(mName, 20*level + rand.nextInt(11),
                rand.nextInt(10)+1, 10*level + rand.nextInt(11),
                5*level, 8*level);
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
            if (c == 1) return;   // renvoie au choix de salle
            if (c == 2) {
                player.getInventory().printInventory();
                System.out.print("Index main droite: ");
                int r = sc.nextInt();
                player.unequipRight();
                player.equipRight(player.getInventory().getItems().get(r));
                System.out.print("Index main gauche: ");
                int l = sc.nextInt();
                player.unequipLeft();
                player.equipLeft(player.getInventory().getItems().get(l));
            } else {
                System.out.println("Fin de partie.");
                System.exit(0);
            }
        }
    }
}
