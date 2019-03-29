package spells;


import world.Creep;
import spells.Spell;
import java.util.ArrayList;
import world.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public abstract class Heal extends Spell {
    // The amount of healing at every level
    public double[] healAmount;

    public Heal(int numOfSpells) {
        setCanTargetAllies(true);
        healAmount = new double[numOfSpells];
    }
    
    protected void healAll(ArrayList<Entity> toHeal, double amount) {
        for(Entity e : toHeal) {
            e.heal(amount);
        }
    }
    
    // Use an array of 4 doubles to set every amounts of healing
    public void setHealAmounts(double[] healAmounts) {
        // Check that the parameter healAmounts is an array of 4 elements
        if(healAmounts.length == healAmount.length) {
            this.healAmount = healAmounts;
        } else {
            System.out.println("Too many or too few elements. Expected: " 
                    + healAmount.length);
        }
    }

    public void setHealAmountLevel1(double healAmount) {
        this.healAmount[0] = healAmount;
    }
    
    public void setHealAmountLevel2(double healAmount) {
        this.healAmount[1] = healAmount;
    }
    
    public void setHealAmountLevel3(double healAmount) {
        this.healAmount[2] = healAmount;
    }
    
    public void setHealAmountLevel4(double healAmount) {
        this.healAmount[3] = healAmount;
    }
    
}
