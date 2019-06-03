package engine.managers;

import engine.components.Light;
import java.util.ArrayList;
import java.util.List;

public class LightManager {
    
    public static List<Light> sceneLights;
    
    public float ambientLightIntensity;
    
    public static void initialise () {
        sceneLights = new ArrayList<>();
    }
       
    
}
