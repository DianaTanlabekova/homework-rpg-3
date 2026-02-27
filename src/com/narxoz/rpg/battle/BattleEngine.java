package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        // TODO: reset any battle state if you add it
        this.random = new Random(1L);
    }

   public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        if (teamA == null || teamB == null || teamA.isEmpty() || teamB.isEmpty()) {
            throw new IllegalArgumentException("Battle cannot start: one or both teams are missing members.");
        }

        EncounterResult result = new EncounterResult();
        List<Combatant> activeA = new ArrayList<>(teamA);
        List<Combatant> activeB = new ArrayList<>(teamB);
        
        result.addLog(">>> Conflict Started <<<");
        result.addLog("Roster Team A: " + getTeamNames(activeA));
        result.addLog("Roster Team B: " + getTeamNames(activeB));
        result.addLog("");

        int roundCounter = 0;
        while (!activeA.isEmpty() && !activeB.isEmpty()) {
            roundCounter++;
            result.addLog("--- Round " + roundCounter + " ---");

            
            for (Combatant striker : activeA) {
                if (activeB.isEmpty()) break;
                Combatant victim = activeB.get(random.nextInt(activeB.size()));
                int power = striker.getAttackPower();
                
                victim.takeDamage(power);
                result.addLog("[" + striker.getName() + "] deals " + power + " damage to [" + victim.getName() + "]");
                
                if (!victim.isAlive()) {
                    result.addLog("!! " + victim.getName() + " has been eliminated !!");
                    activeB.remove(victim);
                }
            }

            
            if (activeB.isEmpty()) break;
            for (Combatant striker : activeB) {
                if (activeA.isEmpty()) break;

                Combatant victim = activeA.get(random.nextInt(activeA.size()));
                int power = striker.getAttackPower();
                
                victim.takeDamage(power);
                result.addLog("[" + striker.getName() + "] deals " + power + " damage to [" + victim.getName() + "]");
                
                if (!victim.isAlive()) {
                    result.addLog("!! " + victim.getName() + " has been eliminated !!");
                    activeA.remove(victim);
                }
            }
            result.addLog("");
        }

        result.setRounds(roundCounter);
        
        if (!activeA.isEmpty()) {
            result.setWinner("Team A");
            result.addLog(">>> Final Result: Team A is victorious! <<<");
        } else {
            result.setWinner("Team B");
            result.addLog(">>> Final Result: Team B has won the fight! <<<");
        }
        return result;
    }
    private String getTeamNames(List<Combatant> participants) {
    if (participants.isEmpty()) return "";
    String namesList = participants.get(0).getName();
    for (int i = 1; i < participants.size(); i++) {
        namesList = namesList + ", " + participants.get(i).getName();
    }
    return namesList;
}
}
