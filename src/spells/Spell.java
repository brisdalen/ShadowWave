package spells;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public abstract class Spell {
    private int numOfLevels;
    // currentLevel start out at -1, max 3
    // currentLevel should be between 0 and 3 when in use
    private int currentLevel = -1;
    // castTime in milliseconds
    private int castTime;
    private double cooldown;
    private int castRange;
    private boolean canTargetEnemies;
    private boolean canTargetAllies;

    protected void setLevel(int level) {
        if(level >= -1 && level <= 3) {
            currentLevel = level;
        }
    }
    
    protected void setNumOfLevels(int numOfLevels) {
        this.numOfLevels = numOfLevels;
    }
    
    protected int getNumOfLevels() {
        return this.numOfLevels;
    }
    
    protected int getLevel() {
        return currentLevel;
    }
    
    protected int getCastRange() {
        return castRange;
    }
    
    protected void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    protected void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }
    
    protected void setCastRange(int castRange) {
        this.castRange = castRange;
    }

    protected void setCanTargetAllies(boolean canTargetAllies) {
        this.canTargetAllies = canTargetAllies;
    }

    protected void setCanTargetEnemies(boolean canTargetEnemies) {
        this.canTargetEnemies = canTargetEnemies;
    }

    protected boolean isCanTargetEnemies() {
        return canTargetEnemies;
    }

    protected boolean isCanTargetAllies() {
        return canTargetAllies;
    }
    
}
