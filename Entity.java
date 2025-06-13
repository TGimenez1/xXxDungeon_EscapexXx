package xXxDungeon_EscapexXx;

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

}