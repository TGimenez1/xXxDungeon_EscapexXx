package xXxDungeon_EscapexXx;

public class Main {
    public static void main(String[] args) {
        // Tesst joueurs
        Player p1 = new Player("PV.D MBANZOULOU", 100, 10);
        System.out.println("Nom : " + p1.getName());
        System.out.println("PV : " + p1.getHp());
        System.out.println("Vitesse : " + p1.getSpeed());
        System.out.println("Niveau : " + p1.getPlayerLevel());
        p1.getInventory().printInventory();
        System.out.println();

        Player p2 = new Player("Thom's GIMENEZ", 100, 10);
        System.out.println("Nom : " + p2.getName());
        System.out.println("PV initiaux : " + p2.getHp());
        System.out.println("Vitesse : " + p2.getSpeed());
        System.out.println("Niveau : " + p2.getPlayerLevel());
        p2.depositToInventory(new Weapon("Dague", 15));
        p2.getInventory().printInventory();
        System.out.println();

        // Weapon
        Weapon katana = new Weapon("Katana", 25);
        p2.equipRight(katana);
        System.out.println("Armé à droite de : " + p2.getEquippedRight().getName());
        p2.unequipRight();

        Weapon dague = new Weapon("Dague Lunaire", 35);
        p2.equipLeft(dague);
        System.out.println("Armé à gauche de : " + p2.getEquippedLeft().getName());
        p2.unequipLeft();
        p2.getInventory().printInventory();
        System.out.println();

        // Combat
        Enemy goblin = new Enemy("Gobelin", 40, 8, 50, 5, 10);
        goblin.addLoot(new Weapon("Dague rouillée", 5), 0.6);
        goblin.addLoot(new Weapon("Mini-Haches rare", 12), 0.3);
        goblin.addLoot(new Weapon("Lame maudite", 25), 0.1);

        Enemy Baran = new Enemy("Baran - Roi des enfers", 100, 5, 1500, 30, 100);
        goblin.addLoot(new Weapon("Longue lame de feu", 25), 0.6);
        goblin.addLoot(new Weapon("Lame enragée", 50), 0.3);
        goblin.addLoot(new Weapon("Katana des enfers", 90), 0.1);

        CombatManager manager = new CombatManager();
        manager.resolveCombat(p2, goblin);
        manager.resolveCombat(p1, Baran);

        // Inventaire après combat
        System.out.println("\nInventaire final de " + p2.getName() + " :");
        p2.getInventory().printInventory();

        System.out.println("\nInventaire final de " + p1.getName() + " :");
        p1.getInventory().printInventory();
    }
}
