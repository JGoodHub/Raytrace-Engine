package engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class RaytracingEngine extends Application {
    
    public static Screen screen;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {        
        screen = new Screen(1280, 720);
        Scene scene = new Scene(new VBox(screen.imageView), screen.width, screen.height);
        
        primaryStage.setTitle("Raytrace Engine Test");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        Thread rendererThread = new Thread(new RendererThread());
        rendererThread.start();
        
    }
    
}
