package engine;

import engine.components.Collider;
import engine.managers.ColliderManager;

public class Raycaster {
    
    public static float rayAccuracy = 0.1f;
    
    public static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance) {
        return fireRaycast(origin, direction, distance, rayAccuracy, false);
    }   
    
    private static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance, float accuracy, boolean isSubRay) {
        Vector3 checkPoint = Vector3.copy(origin);
        Vector3 stepVector = Vector3.copy(direction).normalised().scaled(accuracy);
        float distanceTravelled = 0f;
        
        checkPoint.add(stepVector);
        distanceTravelled += accuracy;
                
        while (distanceTravelled < distance) {
            Collider collidedObject = ColliderManager.checkPointForCollision(checkPoint);
            if (collidedObject != null) {
                RaycastHit rayhit;
                if (isSubRay == true) {                    
                    rayhit = new RaycastHit(checkPoint, collidedObject.getNormalOfSurfacePoint(checkPoint), collidedObject) ;     
                } else {
                    checkPoint.subtract(stepVector);
                    rayhit = fireRaycast(checkPoint, direction, accuracy * 2, accuracy / 100, true);
                }
            
                return rayhit;
            } else {
                checkPoint.add(stepVector);
            }
            
            distanceTravelled += accuracy;
        }
        
        Vector3 finalPoint = Vector3.copy(direction).normalised().scaled(distance);
        return new RaycastHit(Vector3.copy(origin).add(finalPoint), new Vector3(), null);
    }
    
}
