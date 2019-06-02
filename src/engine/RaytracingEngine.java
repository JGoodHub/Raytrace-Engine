package engine;

import engine.components.Camera;
import engine.components.Light;
import engine.components.PlaneCollider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.components.SphereCollider;
import engine.managers.ColliderManager;
import engine.managers.LightManager;
import javafx.scene.layout.VBox;

public class RaytracingEngine extends Application {
    
    private Screen screen;
    private long nanoStartTime;
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {        
        screen = new Screen(1280/2, 720/2);
        Scene scene = new Scene(new VBox(screen.imageView), screen.width, screen.height);
        
        primaryStage.setTitle("Raytrace Engine Test");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        initialise();
    }

    public void initialise () {
        System.out.println("Initalising the scene");
        nanoStartTime = System.nanoTime();
        
        ColliderManager.initalise();
        LightManager.initialise();
        
        System.out.println("Creating scene objects");
        GameObject cameraObject = new GameObject();
        cameraObject.attachComponent(new Camera(screen, Color.GREY));
        cameraObject.transform.forward = new Vector3(0, 0, 1);
        
        GameObject sphereObject1 = new GameObject();
        sphereObject1.attachComponent(new SphereCollider(5f, Color.RED));
        sphereObject1.transform.position = new Vector3(-10, -2.5f, 30);
        
        GameObject sphereObject2 = new GameObject();
        sphereObject2.attachComponent(new SphereCollider(2f, Color.GREEN));
        sphereObject2.transform.position = new Vector3(0, 0, 30);
        
        GameObject sphereObject3 = new GameObject();
        sphereObject3.attachComponent(new SphereCollider(15f, Color.BLUE));
        sphereObject3.transform.position = new Vector3(25, 5, 45);
        
        GameObject planeObject = new GameObject();
        planeObject.attachComponent(new PlaneCollider(Color.BURLYWOOD));
        planeObject.transform.position = new Vector3(0, -5, 0);
        
        GameObject lightObject = new GameObject();
        lightObject.attachComponent(new Light(1f, Color.WHITE));
        lightObject.transform.position = new Vector3(10, 50, 45);
        
        System.out.println("Rendering scene to window");
        Camera camera = (Camera)cameraObject.getComponent(Camera.class);
        screen.drawColourMapToScreen(camera.raycastPixelColourMap());
        
        System.out.println("Scene took: " + Math.round((System.nanoTime() - nanoStartTime) / 1000000f) + " milliseconds to render");
             
    }   
    
}
