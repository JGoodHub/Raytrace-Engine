package engine.components;

import engine.GameObject;
import engine.Vector3;
import engine.managers.ColliderManager;
import javafx.scene.paint.Color;

public class PlaneCollider extends Component implements Collider {
    
    public Color colour;

    public PlaneCollider(Color colour) {
        this.colour = colour;
        
        ColliderManager.sceneColliders.add(this);
    }
    
    @Override
    public boolean doesPointCollide(Vector3 point) {
        return point.y <= parent.transform.position.y;
    }
    
    @Override
    public Vector3 getNormalOfSurfacePoint(Vector3 point) {
        return new Vector3(0, 1, 0);
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
