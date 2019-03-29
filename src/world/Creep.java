package world;

import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public class Creep extends Entity {
    
    public Creep(float x, float y, float radius) {
        super(x, y, radius);
        
        setFill(Color.GREENYELLOW);
    }
    
    public Creep(float x, float y) {
        super(x, y, 100.0f);
        
        setFill(Color.GREENYELLOW);
    }
    
}
