/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

/**
 *
 * @author bjornar.risdalen
 */
public class PlaceholderUltimate extends UltimateSpell {
    
    public PlaceholderUltimate() {
        setCanTargetAllies(true);
        setCanTargetEnemies(true);
        setCastRange(1000);
        setCastTime(10);
        setCooldown(120);
    }
}
