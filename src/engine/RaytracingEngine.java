package engine;

import engine.components.Camera;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.components.SpherePrimative;
import javafx.scene.layout.VBox;

public class RaytracingEngine extends Application {
    
    Screen screen;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {        
        screen = new Screen(50, 50);
        Scene scene = new Scene(new VBox(screen.imageView), screen.width, screen.height);
        
        primaryStage.setTitle("Raycast Engine Test");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        initialise();
    }

    public void initialise () {
        GameObject cameraObject = new GameObject();
        cameraObject.attachComponent(new Camera(screen, 90f, Color.GREY));
        cameraObject.transform.rotation = new Vector3(0, 0, 1);
        
        GameObject sphereTestObject = new GameObject();
        sphereTestObject.attachComponent(new SpherePrimative(5f, Color.RED));
        sphereTestObject.transform.position = new Vector3(0, 0, 40);
        
        Camera camera = (Camera)cameraObject.getComponent(Camera.class);
        screen.drawColourMapToScreen(camera.raycastPixelColourMap());
    }   
    
}
