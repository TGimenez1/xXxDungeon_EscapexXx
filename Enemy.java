package xXxDungeon_EscapexXx;

import java.util.*;

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
        return (maxDamage <= minDamage) ? minDamage
            : random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void addLoot(Equipable item, double dropRate) {
        if (dropRate < 0 || dropRate > 1) {
            throw new IllegalArgumentException("Taux de drop invalide (entre 0 et 1)");
        }
        lootTable.put(item, dropRate);
    }

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

    public Map<Equipable, Double> getLootTable() {
        return Map.copyOf(lootTable);
    }

    @Override
    public String toString() {
        return String.format("%s (HP: %d, SPD: %d)", name, hp, speed);
    }
}
