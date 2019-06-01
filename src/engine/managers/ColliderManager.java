package engine.managers;

import java.util.ArrayList;
import java.util.List;
import engine.Vector3;
import engine.components.Collider;
import engine.components.SphereCollider;

public class ColliderManager {
    
    public static List<Collider> sceneColliders;
    
    public ColliderManager () {}
    
    public static void initalise() {
        sceneColliders = new ArrayList<>();
    }
    
    public static Collider checkPointForCollision (Vector3 point) {
        for (Collider col : sceneColliders) {
            if (col.doesPointCollide(point) == true) {
                return col;
            }
        }
        
        return null;
    }
    
}
