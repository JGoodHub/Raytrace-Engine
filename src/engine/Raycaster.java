package engine;

import engine.components.Collider;
import engine.managers.ColliderManager;

public class Raycaster {
    
    public static float rayAccuracy = 1f;
    
    public static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance) {
        return fireRaycast(origin, direction, distance, rayAccuracy, false);
    }   
    
    public static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance, float accuracy, boolean isSubRay) {
        Vector3 checkPoint = Vector3.copy(origin);
        Vector3 stepVector = Vector3.copy(direction).normalised().scaled(accuracy);
        float distanceTravelled = 0f;
        
        while (distanceTravelled < distance) {
            Collider collidedObject = ColliderManager.checkPointForCollision(checkPoint);
            if (collidedObject != null) {
                RaycastHit rayhit;
                if (isSubRay == true) {
                    rayhit = new RaycastHit(checkPoint, new Vector3(), collidedObject) ;     
                } else {
                    checkPoint.subtract(stepVector);
                    rayhit = fireRaycast(checkPoint, direction, accuracy * 2, accuracy / 10, true);
                }
            
                return rayhit;
            } else {
                checkPoint.add(stepVector);
            }
            
            distanceTravelled += accuracy;
        }
        
        return null;
    }
    
}
