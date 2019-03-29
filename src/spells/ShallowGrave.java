package spells;


import spells.Spell;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public class ShallowGrave extends Spell {
    
    private int[] castRanges = new int[]{550,700,850,1000};

    public ShallowGrave() {
        setCastTime(40);
        setCanTargetAllies(true);
        setCanTargetEnemies(false);
        setLevel(0);
        setCastRange(getLevel());
    }
    
}
