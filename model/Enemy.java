package xXxDungeon_EscapexXx.model;

import java.util.*;

import xXxDungeon_EscapexXx.items.Equipable;
import xXxDungeon_EscapexXx.items.Weapon;

public class Enemy extends Entity {
    private final int xpReward;
    private final int minDamage;
    private final int maxDamage;
    private final Map<Equipable, Double> lootTable = new HashMap<>();

    public Enemy(String name, int hp, int speed, int xpReward, int minDamage, int maxDamage) {
        super(name, hp, speed);
        this.xpReward = xpReward;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getXpReward() {
        return xpReward;
    }

    public int rollDamage() {
        Random random = new Random();
        return (maxDamage <= minDamage)
            ? minDamage
            : random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    /** Initialise la lootTable pour un ennemi standard selon son level */
    public void initStandardLoot(int level) {
        lootTable.clear();
        lootTable.put(new Weapon("Dague Lv" + level, 5 * level), 0.5);
        lootTable.put(new Weapon("Hachette Lv" + level, 8 * level), 0.3);
        lootTable.put(new Weapon("Épée Lv"   + level, 12 * level), 0.1);
    }

    /** Initialise la lootTable pour le boss de ce niveau */
    public void initBossLoot(int level) {
        lootTable.clear();
        switch (level) {
            case 1:
                // Baran, Roi des Enfers
                lootTable.put(new Weapon("Lame des Enfers",       50), 0.1);
                lootTable.put(new Weapon("Hache de l'Abîme",       35), 0.3);
                lootTable.put(new Weapon("Massue Sombre",          25), 0.6);
                break;
            case 2:
                // Beru, Prince des Fourmis
                lootTable.put(new Weapon("Antenne Cinglante",      55), 0.1);
                lootTable.put(new Weapon("Dard Venimeux",          40), 0.3);
                lootTable.put(new Weapon("Mandibule Tranchante",   30), 0.6);
                break;
            case 3:
                // Igris, Chevalier Royal
                lootTable.put(new Weapon("Lance Royale",           60), 0.1);
                lootTable.put(new Weapon("Épée du Marquis",        45), 0.3);
                lootTable.put(new Weapon("Bouclier d'Or",          35), 0.6);
                break;
            case 4:
                // Khan, Seigneur des Laves
                lootTable.put(new Weapon("Katana de la Magma",     65), 0.1);
                lootTable.put(new Weapon("Glaive de Cendres",      50), 0.3);
                lootTable.put(new Weapon("Poignard Solaire",       40), 0.6);
                break;
            default:
                // Boss générique
                lootTable.put(new Weapon("Épée du Destin",         70), 0.05);
                lootTable.put(new Weapon("Poignard Tragique",       50), 0.25);
                lootTable.put(new Weapon("Casseur d'Os",            30), 0.7);
        }
    }

    /** Simule le drop : chaque entrée est tirée aléatoirement selon son taux */
    public List<Equipable> rollLoot() {
        List<Equipable> drops = new ArrayList<>();
        Random random = new Random();
        for (Map.Entry<Equipable, Double> entry : lootTable.entrySet()) {
            if (random.nextDouble() <= entry.getValue()) {
                drops.add(entry.getKey());
            }
        }
        return drops;
    }

    /** Retourne une vue en lecture seule de la table de loot */
    public Map<Equipable, Double> getLootTable() {
        return Map.copyOf(lootTable);
    }

    @Override
    public String toString() {
        return String.format("%s (HP: %d, SPD: %d)", name, hp, speed);
    }

    /** Affiche en détail les stats de l'ennemi */
    public void describe() {
        System.out.println("=== ENNEMI RENCONTRÉ ===");
        System.out.println("Nom       : " + name);
        System.out.println("PV        : " + hp);
        System.out.println("Vitesse   : " + speed);
        System.out.println("XP Recomp : " + xpReward);
        System.out.println("Dégâts    : " + minDamage + " → " + maxDamage);
        System.out.println("========================");
    }
}
