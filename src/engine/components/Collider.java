package engine.components;

import engine.Vector3;
import javafx.scene.paint.Color;

public interface Collider {
    
    public boolean doesPointCollide (Vector3 point);
    public Color getColour();
    
}
