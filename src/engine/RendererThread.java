package engine;

import engine.components.Camera;
import engine.components.Light;
import engine.components.PlaneCollider;
import engine.components.SphereCollider;
import engine.managers.ColliderManager;
import engine.managers.LightManager;
import javafx.scene.paint.Color;

public class RendererThread implements Runnable {

    private long nanoStartTime;
    
    @Override
    public void run() {
        initialise();
    }
    
    public void initialise () {
        System.out.println("Initalising the scene");
        nanoStartTime = System.nanoTime();
        
        ColliderManager.initalise();
        LightManager.initialise();
        
        System.out.println("Creating scene objects");
        GameObject cameraObject = new GameObject();
        cameraObject.attachComponent(new Camera(RaytracingEngine.screen, Color.GREY));
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
        lightObject.attachComponent(new Light(150f, Color.WHITE));
        lightObject.transform.position = new Vector3(10, 20, 40);
        
        System.out.println("Rendering scene to window");
        Camera camera = (Camera)cameraObject.getComponent(Camera.class);
        camera.drawRaycastColourMapToScreen();
        
        System.out.println("Scene took: " + Math.round((System.nanoTime() - nanoStartTime) / 1000000f) + " milliseconds to render");
             
    }   
    
}
