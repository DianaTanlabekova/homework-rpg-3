package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.enemy.Orc; 
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");

        
        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        
        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(new Goblin("Leo")));
        enemies.add(new EnemyCombatantAdapter(new Orc("Donatello"))); 

        
        BattleEngine engine = BattleEngine.getInstance();
        BattleEngine secondEngineCheck = BattleEngine.getInstance();
        System.out.println("[System] Checking Singleton instance...");
        System.out.println("Are both engines the same instance? " + (engine == secondEngineCheck));
        System.out.println();

        
        System.out.println("[Battle] Starting encounter...");
        engine.setRandomSeed(42L);
        EncounterResult result = engine.runEncounter(heroes, enemies);

        
        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }

        
        System.out.println("\n=== Final Summary ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds played: " + result.getRounds());
        
       
        System.out.println("\nSurvivors Status:");
        for (Combatant h : heroes) {
            String status = h.isAlive() ? "ALIVE (HP: " + h.getHealth() + ")" : "DEAD";
            System.out.println(h.getName() + ": " + status);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}
