package xXxDungeon_EscapexXx;

import java.util.Random;

public class Enemy extends Entity {
    private final int xpReward;
    private final int minDamage;
    private final int maxDamage;

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
        if (maxDamage <= minDamage) {
            return minDamage;
        }
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public String toString() {
        return String.format("%s (HP: %d, SPD: %d)", name, hp, speed);
    }
}
