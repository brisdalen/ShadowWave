package world;


import world.Entity;
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
public abstract class Hero extends Entity {
    
    // spells should usually consist of 4 spells
    private Spell[] spells;
    
    public Hero(float x, float y, float radius, double maxHealth, int numSpells) {
        super(x, y, radius, maxHealth);
        this.spells = new Spell[numSpells];
    }
    
    protected void setSpell(int slot, Spell spell) {
        spells[slot] = spell;
    }
    
}
