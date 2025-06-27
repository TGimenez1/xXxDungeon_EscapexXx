package xXxDungeon_EscapexXx.managers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import xXxDungeon_EscapexXx.items.Equipable;
import xXxDungeon_EscapexXx.items.Weapon;
import xXxDungeon_EscapexXx.model.Enemy;
import xXxDungeon_EscapexXx.model.Player;

public class CombatManager {
    private final Random rng = new Random();

    public void resolveCombat(Player player, Enemy enemy) {
        System.out.printf(" %s appears!%n", enemy.getName());

        boolean playerFirst = player.playerHasInitiative(enemy.getSpeed());

        // Turn loop
        while (player.isAlive() && enemy.isAlive()) {
            if (playerFirst) {
                playerAttack(player, enemy);
                //Wait a bit
                            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (enemy.isAlive()) {
                    enemyAttack(enemy, player);
                }
            } else {
                enemyAttack(enemy, player);
                                //Wait a bit
                            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (player.isAlive()) {
                    playerAttack(player, enemy);
                }
                
            }
            // Re compute the initiative because of modifiers
            playerFirst = player.playerHasInitiative(enemy.getSpeed());
            //Wait
            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (player.isAlive()) {
            System.out.printf("%s is defeated! You gain %d XP.%n",
                    enemy.getName(),
                    enemy.getXpReward());
            player.gainXp(enemy.getXpReward());
            //Re equilibrage
            int hpgain = player.getPlayerLevel()*5;
            System.out.printf("You absorb its soul and gain %d hp.%n",
                    hpgain);
            player.heal(hpgain);
            List<Equipable> loot = enemy.rollLoot();
            LootManager.handleDrops(player, loot);
        } else {
            System.out.println("You have been slain... Game over.");
        }
    }

    private void playerAttack(Player p, Enemy e) {
        attackWithHand(p, e, p.getEquippedRight(), "right hand");
        attackWithHand(p, e, p.getEquippedLeft(), "left hand");
    }

    private void attackWithHand(Player player,
            Enemy enemy,
            Equipable item,
            String handName) {

        if (item == null) {
            // If no item equipped :
            // unarmed jab: 70–100% of (playerLevel * 5)
            int base = player.getPlayerLevel() * 5 ;
            int damage = rng.nextInt(base * 3 / 10 + 1) + base * 7 / 10;
            enemy.takeDamage(damage);
            System.out.printf("%s jabs %s with its %s for %d damage!%n",
                    player.getName(),
                    enemy.getName(),
                    handName,
                    damage);
        } else if (item instanceof Weapon) {
            // weapon attack
            Weapon w = (Weapon) item;
            int damage = w.getDamage();
            enemy.takeDamage(damage);
            System.out.printf("%s strikes %s with %s for %d damage!%n",
                    player.getName(),
                    enemy.getName(),
                    w.getName(),
                    damage);
        }

    }

    private void enemyAttack(Enemy e, Player p) {
        int dmg = e.rollDamage();
        p.takeDamage(dmg);
        System.out.printf("%s strikes back for %d.%n",
                e.getName(), dmg);
    }
}
