package engine.components;

import engine.managers.LightManager;
import javafx.scene.paint.Color;

public class Light extends Component {
    
    private float intensity;
    private Color colour;

    public Light(float intensity, Color colour) {
        this.intensity = intensity;
        this.colour = colour;
        
        LightManager.sceneLights.add(this);
    }
      
}
