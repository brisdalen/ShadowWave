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
public class PlaceholderSpell extends Spell {
    
    public PlaceholderSpell() {
        setLevel(0);
        setCanTargetAllies(true);
        setCanTargetEnemies(true);
        setCastRange(500);
        setCastTime(30);
        setCooldown(10);
    }
}
