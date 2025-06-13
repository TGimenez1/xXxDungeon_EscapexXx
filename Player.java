package xXxDungeon_EscapexXx;

import java.util.Random;

import xXxDungeon_EscapexXx.Entity.*;
import xXxDungeon_EscapexXx.Equipable.*;

public class Player extends Entity {
    private Equipable equippedRight;
    private Equipable equippedLeft;
    private int xp;
    private int playerLevel;
    private static final int XP_PER_LEVEL = 100;

    public Player(String name, int hp, int speed) {
        super(name, hp, speed);
        this.equippedRight = null;
        this.equippedLeft = null;
        this.xp = 0;
        this.playerLevel = 1;
    }

    public Equipable getEquippedRight() {
        return equippedRight;
    }

    public void equipRight(Equipable item) {
        this.equippedRight = item;
    }

    public Equipable unequipRight() {
        Equipable old = equippedRight;
        this.equippedRight = null;
        return old;
    }

    public Equipable getEquippedLeft() {
        return equippedLeft;
    }

    public void equipLeft(Equipable item) {
        this.equippedLeft = item;
    }

    public Equipable unequipLeft() {
        Equipable old = equippedLeft;
        this.equippedLeft = null;
        return old;
    }

    public int getXp() {
        return xp;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void gainXp(int amount) {
        xp += amount;
        checkLevelUp();
    }

    private void checkLevelUp() {
        int requiredXp = playerLevel * XP_PER_LEVEL;
        while (xp >= requiredXp) {
            xp -= requiredXp;
            playerLevel++;
            requiredXp = playerLevel * XP_PER_LEVEL;
            onLevelUp();
        }
    }

    protected void onLevelUp() {
        hp += 10;
        speed += 1;
        System.out.println("Le joueur " + name + " est passé au niveau " + playerLevel + " !");
    }

    /**
     * Simulates an initiative roll
     * Rolls between 70% and 100% of speed for both player and enemy.
     * Returns true if the player wins ties against the enemy.
     */

    public boolean playerHasInitiative(int enemySpeed) {
        Random random = new Random();
        int playerRoll = random.nextInt(speed * 3 / 10 + 1) + speed * 7 / 10;
        int enemyRoll = random.nextInt(enemySpeed * 3 / 10 + 1) + enemySpeed * 7 / 10;
        return playerRoll >= enemyRoll;
    }
}