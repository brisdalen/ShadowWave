/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author bjornar.risdalen
 */
public class World extends Pane {

    Player player;
    private static ArrayList<Entity> entities;
    private static ArrayList<Entity> allies;
    private static ArrayList<Entity> enemies;
    private static Point2D.Float playerPosition;
    private static float playerRadius;
    private int w, h;

    public World(int width, int height) {

        player = new Player(new Dazzle(125.0f, 145.0f));
        entities = new ArrayList<>();
        allies = new ArrayList<>();
        
        playerPosition = player.getOrigin();
        playerRadius = player.getPlayerRadius();

        this.w = width;
        this.h = height;

        setMinSize(w, h);

        setBackground(new Background(new BackgroundFill(new Color(11, 104, 140, 0),
                null, null)));

        for(Entity e : allies) {

            if(e instanceof Creep) {
                Text text = new Text(e.getX(), e.getY() - 10, "" + e.getHealth());
                text.setFont(Font.font("verdana", 14));
                getChildren().add(text);
            }
        }
    }

    public void update() {
        for(Entity e : entities) {
            e.update();
        }
    }

    public void addHero(Hero hero, boolean ally) {
        entities.add(hero);
        if(ally) {
            allies.add(hero);
        } else {
            enemies.add(hero);
        }
    }

    public void addCreep(Creep creep, boolean ally) {
        entities.add(creep);
        if(ally) {
            allies.add(creep);
        } else {
            enemies.add(creep);
        }
    }
    
    public static Point2D.Float getPlayerPoint() {
        return playerPosition;
    }
    
    public static float getPlayerRadius() {
        return playerRadius;
    }

    public static ArrayList<Entity> getEntities() {
        return entities;
    }

    public static ArrayList<Entity> getAllies() {
        return allies;
    }

    public static ArrayList<Entity> getEnemies() {
        return enemies;
    }

}
