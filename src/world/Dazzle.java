package world;


import javafx.scene.paint.Color;
import world.Hero;
import spells.PlaceholderSpell;
import spells.PlaceholderUltimate;
import spells.ShadowWave;
import spells.ShallowGrave;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public class Dazzle extends Hero {
    
    public Dazzle(float x, float y) {
        super(x, y, 100.0f, 800, 4);
        
        setFill(Color.PURPLE);
        
        setSpell(0, new PlaceholderSpell());
        setSpell(1, new ShallowGrave());
        setSpell(2, new ShadowWave());
        setSpell(3, new PlaceholderUltimate());
    }
    
}
