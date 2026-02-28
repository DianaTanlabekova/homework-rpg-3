package com.narxoz.rpg.adapter;

import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.enemy.Enemy;

public class EnemyCombatantAdapter implements Combatant {
    private final Enemy enemy;

    public EnemyCombatantAdapter(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public String getName() {
        return enemy.getTitle();
    }

    @Override
    public int getAttackPower() {
        // TODO: translate enemy damage to combat attack
        return enemy.getDamage();
    }

    @Override
    public void takeDamage(int amount) {
        enemy.applyDamage(amount);
    }

    @Override
    public boolean isAlive() {
        return !enemy.isDefeated();
    }
    @Override
    public int getHealth() { 
        return enemy.getHealth(); 
    }
    @Override
    public String getSymbol() {
    return "[ENEMY]"; 
}
    
}
