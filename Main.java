package xXxDungeon_EscapexXx;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("PV.D MBANZOULOU", 100, 10);
        System.out.println("Nom : " + p1.getName());
        System.out.println("PV : " + p1.getHp());
        System.out.println("Vitesse : " + p1.getSpeed());
        System.out.println("Niveau : " + p1.getPlayerLevel());
        p1.getInventory().printInventory();

        Player p2 = new Player("Thom's GIMENEZ", 100, 10);
        System.out.println("Nom : " + p2.getName());
        System.out.println("PV initiaux : " + p2.getHp());
        System.out.println("Vitesse : " + p2.getSpeed());
        System.out.println("Niveau : " + p2.getPlayerLevel());
        p2.depositToInventory(new Weapon("Dague", 15));
        p2.getInventory().printInventory();

        Weapon w = new Weapon("Katana", 25);
        p2.equipRight(w);
        System.out.println("Armé de : " + p2.getEquippedRight().getName());
        p2.unequipRight();
        Weapon w2 = new Weapon("Dague", 15);
        p2.equipLeft(w2);
        System.out.println("Armé de : " + p2.getEquippedLeft().getName());
        p2.unequipLeft();
        p2.getInventory().printInventory();
    }
}
