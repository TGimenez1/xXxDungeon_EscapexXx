package xXxDungeon_EscapexXx;

public class Weapon implements Equipable {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void equip(Entity cible) {
        System.out.println(cible.getName() + " a équipé l'arme : " + name);
    }

    @Override
    public void unequip(Entity cible) {
        System.out.println(cible.getName() + " a déséquipé l'arme : " + name);
    }
}
