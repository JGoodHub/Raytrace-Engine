package engine.components;

import engine.managers.LightManager;
import javafx.scene.paint.Color;

public class Light extends Component {
    
    public float falloffRange;
    public Color colour;

    public Light(float intensity, Color colour) {
        this.falloffRange = intensity;
        this.colour = colour;
        
        LightManager.sceneLights.add(this);
    }
      
}
