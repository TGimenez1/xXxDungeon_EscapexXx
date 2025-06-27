package xXxDungeon_EscapexXx;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test joueurs
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

        Weapon Katana3 = new Weapon("Katana angélique", 65);
        p1.equipRight(Katana3);
        System.out.println("Armé à droite de : " + p1.getEquippedRight().getName());

        Weapon Katana2 = new Weapon("Katana anti-gravité", 45);
        p1.equipLeft(Katana2);
        System.out.println("Armé à gauche de : " + p1.getEquippedLeft().getName());
        p1.getInventory().printInventory();
        System.out.println();

        // Inventaire après combat
        System.out.println("\nInventaire final de " + p2.getName() + " :");
        p2.getInventory().printInventory();

        System.out.println("\nInventaire final de " + p1.getName() + " :");
        p1.getInventory().printInventory();

    }


}
