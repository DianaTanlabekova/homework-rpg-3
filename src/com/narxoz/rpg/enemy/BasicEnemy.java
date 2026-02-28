package com.narxoz.rpg.enemy;

public class BasicEnemy implements Enemy {
    private final String title;
    private final int damage;
    private int health;
    private boolean isEnraged;
    

    public BasicEnemy(String title, int damage, int health) {
        this.title = title;
        this.damage = damage;
        this.health = health;
        this.isEnraged = false;
        
    }

    @Override
    public String getTitle() {
         if (isEnraged) {
            return title + " Enraged";
        }
        return title;
    }

    @Override
    public int getDamage() {
         return isEnraged ? damage * 2 : damage;
    }

    @Override
    public void applyDamage(int amount) {
        health = Math.max(0, health - amount);
        if (health < 20 && health > 0) {
            isEnraged = true;
        }
    }

    @Override
    public boolean isDefeated() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    public int getRageMode() {
        return isEnraged ? 2 : 1;
    }
}
