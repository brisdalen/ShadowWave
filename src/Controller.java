
import world.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public class Controller {
    
    Player player;
    
    public Controller(Player player) {
        this.player = player;
    }
    
    public void debugMoveOrigin(int direction) {
        switch (direction) {
            case 0:
                moveOrigin(direction);
                System.out.println(player.getOrigin());
            case 2:
                moveOrigin(direction);
                System.out.println(player.getOrigin());
        }
    }
    
    public void moveOrigin(int direction) {
        
        float x = player.getOrigin().x;
        float y = player.getOrigin().y;
        
        switch (direction) {
            case 0:
                x = x - 5;
            case 2:
                x = x + 5;
        }
    }
    
    public void moveOrigin(int direction, float amount) {
        
        float x = player.getOrigin().x;
        float y = player.getOrigin().y;
        
        switch (direction) {
            case 0:
                x -= amount;
            case 1:
                y -= amount;
            case 2:
                x += amount;
            case 3:
                y += amount;    
        }
    }
}
