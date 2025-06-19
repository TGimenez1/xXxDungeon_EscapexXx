package xXxDungeon_EscapexXx;

import java.util.Random;

public class Player extends Entity {
    private Equipable equippedRight;
    private Equipable equippedLeft;
    private Inventory inventory;
    private int xp;
    private int playerLevel;
    private static final int XP_PER_LEVEL = 100;

    public Player(String name, int hp, int speed) {
        super(name, hp, speed);
        this.equippedRight = null;
        this.equippedLeft  = null;
        this.inventory     = new Inventory();
        this.xp            = 0;
        this.playerLevel   = 1;
    }

    public Equipable getEquippedRight() {
        return equippedRight;
    }

    public void equipRight(Equipable item) {
        if (equippedRight != null) {
            Equipable old = equippedRight;
            old.unequip(this);
            inventory.add(old);
        }
        item.equip(this);
        equippedRight = item;
    }

    public Equipable unequipRight() {
        if (equippedRight == null) return null;
        Equipable old = equippedRight;
        old.unequip(this);
        inventory.add(old);
        equippedRight = null;
        return old;
    }

    public Equipable getEquippedLeft() {
        return equippedLeft;
    }

    public void equipLeft(Equipable item) {
        if (equippedLeft != null) {
            Equipable old = equippedLeft;
            old.unequip(this);
            inventory.add(old);
        }
        item.equip(this);
        equippedLeft = item;
    }

    public Equipable unequipLeft() {
        if (equippedLeft == null) return null;
        Equipable old = equippedLeft;
        old.unequip(this);
        inventory.add(old);
        equippedLeft = null;
        return old;
    }

    public void depositToInventory(Equipable item) {
        inventory.add(item);
    }

    public Inventory getInventory() {
        return inventory;
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

    public boolean playerHasInitiative(int enemySpeed) {
        Random random   = new Random();
        int playerRoll = random.nextInt(speed * 3 / 10 + 1) + speed * 7 / 10;
        int enemyRoll  = random.nextInt(enemySpeed * 3 / 10 + 1) + enemySpeed * 7 / 10;
        return playerRoll >= enemyRoll;
    }
}
  