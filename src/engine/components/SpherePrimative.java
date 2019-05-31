package engine.components;

import javafx.scene.paint.Color;
import engine.Vector3;
import engine.managers.ColliderManager;

public class SpherePrimative extends Component {
        
    public float radius;
    public Color colour;
    
    public SpherePrimative (float radius, Color colour) {
        this.radius = radius;
        this.colour = colour;
        
        ColliderManager.spherePrimatives.add(this);
    }
    
    public boolean isVectorWithinSphere (Vector3 point) {
        if (Vector3.distance(point, parent.transform.position) <= radius) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
}
