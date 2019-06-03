package engine.components;

import engine.GameObject;
import engine.Vector3;
import javafx.scene.paint.Color;

public interface Collider {
    
    public boolean doesPointCollide (Vector3 point);
    public Vector3 getNormalOfSurfacePoint (Vector3 point);
    public Color getColour();
    public GameObject getParent();
    
}
