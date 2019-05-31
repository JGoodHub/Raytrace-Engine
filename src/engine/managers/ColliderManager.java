package engine.managers;

import java.util.ArrayList;
import java.util.List;
import engine.Vector3;
import engine.components.SpherePrimative;

public class ColliderManager {
    
    public static List<SpherePrimative> spherePrimatives = new ArrayList<>();
    
    public ColliderManager () {}
    
    public static SpherePrimative checkPointSphereCollision (Vector3 point) {
        for (SpherePrimative sphere : spherePrimatives) {
            if (sphere.isVectorWithinSphere(point) == true) {
                return sphere;
            }
        }
        
        return null;
    }
    
}
