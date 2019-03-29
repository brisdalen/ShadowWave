package world;


import world.Creep;
import spells.ShadowWave;
import java.util.ArrayList;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author bjornar.risdalen
 */
public class Window extends Application {
    
    ArrayList<Circle> allyGraphic;
    
    int w = 300;
    int h = 150;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        
        Group root = new Group();
        
        World world = new World(w, h);
        
        root.getChildren().add(world);
        
        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.rgb(11, 104, 140));
        
        primaryStage.setTitle("Shadow Wave!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
