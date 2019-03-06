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
    public double[] healAmount = new double[Spell.NUM_LEVELS];

    public Heal() {
        setCanTargetAllies(true);
    }
    // Use an array of 4 doubles to set every amounts of healing
    public void setHealAmounts(double[] healAmounts) {
        // Check that the parameter healAmounts is an array of 4 elements
        if(healAmounts.length == Spell.NUM_LEVELS) {
            this.healAmount = healAmounts;
        } else {
            System.out.println("Too many or too few elements. Expected: 4");
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
