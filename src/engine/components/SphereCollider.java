package engine.components;

import engine.GameObject;
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
        return Vector3.distanceSquared(point, parent.transform.position) <= Math.pow(radius, 2);
    }
    
    @Override
    public Vector3 getNormalOfSurfacePoint(Vector3 point) {
        return Vector3.getDirection(parent.transform.position, point);
    }
    
    @Override
    public Color getColour() {
        return colour;
    }
    
    @Override
    public GameObject getParent () {
        return parent;
    }
    
}
