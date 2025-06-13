package xXxDungeon_EscapexXx.Entity;
import java.util.Random;

public abstract class Entity {
    protected String name;
    protected int hp;
    protected int speed;

    public Entity(String name, int hp, int speed) {
        this.name  = name;
        this.hp    = hp;
        this.speed = speed;
    }

    /* never below zero. */
    public void takeDamage(int damage) {
        hp = Math.max(hp - damage, 0);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * Simulates an initiative roll
     * Here we assume both sides roll entire values between 70% of speed and speed
     * ; returns true if the player wins ties against enemy.
     */
    public boolean playerHasInitiative(int enemySpeed) {
        Random random = new Random();
        int playerRoll = random.nextInt(speed * 3 / 10 + 1) + speed * 7 / 10;
        int enemyRoll  = random.nextInt(enemySpeed * 3 / 10 + 1) + enemySpeed * 7 / 10;
        return playerRoll >= enemyRoll;
    }
}