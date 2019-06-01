package engine.components;

import javafx.scene.paint.Color;
import engine.Vector3;
import engine.managers.ColliderManager;

public class SphereCollider extends Component implements Collider {
        
    public float radius;
    public Color colour;
    
    public SphereCollider (float radius, Color colour) {
        this.radius = radius;
        this.colour = colour;
        
        ColliderManager.sceneColliders.add(this);
    }
    
    @Override
    public boolean doesPointCollide (Vector3 point) {
        return Vector3.distance(point, parent.transform.position) <= radius;
    }
    
    @Override
    public Color getColour() {
        return colour;
    }
    
}
