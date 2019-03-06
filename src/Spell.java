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
    public static final int NUM_LEVELS = 4;
    // castTime in milliseconds
    private int castTime;
    private double cooldown;
    private int castRange;
    private boolean canTargetEnemies;
    private boolean canTargetAllies;

    public int getCastRange() {
        return castRange;
    }
    
    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }
    
    public void setCastRange(int castRange) {
        this.castRange = castRange;
    }

    public void setCanTargetAllies(boolean canTargetAllies) {
        this.canTargetAllies = canTargetAllies;
    }

    public void setCanTargetEnemies(boolean canTargetEnemies) {
        this.canTargetEnemies = canTargetEnemies;
    }

    public boolean isCanTargetEnemies() {
        return canTargetEnemies;
    }

    public boolean isCanTargetAllies() {
        return canTargetAllies;
    }
    
}
