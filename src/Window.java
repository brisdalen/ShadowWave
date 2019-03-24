
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
    
    ShadowWave sw;
    ArrayList<Circle> allyGraphic;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        
        Group root = new Group();
        
        sw = new ShadowWave();
        allyGraphic = new ArrayList<>();
        Polygon hex = new Polygon();
        
        hex.getPoints().addAll(new Double[]{
            200.0, 50.0,
            400.0, 50.0,
            450.0, 150.0,
            400.0, 250.0,
            200.0, 250.0,
            150.0, 150.0,
        });
        hex.setFill(Color.BLUE);
        
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(1000));
        scale.setNode(hex);
        scale.setByY(1.5);
        scale.setByX(1.5);
        scale.setCycleCount(50);
        scale.setAutoReverse(true);
        scale.play();
     
        for(Creep c : sw.allies) {
            allyGraphic.add(new Circle(c.getX(), c.getY(), c.getRadius(), Color.GREENYELLOW));
            Text text = new Text(c.getX(), c.getY()-10, ""+c.getHealth());
            text.setFont(Font.font("verdana", 14));
            root.getChildren().add(text);
        }
        
        root.getChildren().add(new Circle(sw.getOrigin().x, 
                sw.getOrigin().y, 10, Color.CRIMSON));
        root.getChildren().add(hex);
        
        for(Circle c : allyGraphic) {
            root.getChildren().add(c);
        }
        
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
