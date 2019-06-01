package engine;

import engine.components.Collider;
import engine.components.SphereCollider;
import engine.managers.ColliderManager;

public class Raycaster {
    
    public static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance) {
        Vector3 checkPoint = Vector3.copy(origin);
        Vector3 stepVector = Vector3.copy(direction).normalised().scaled(0.1f);
        
        while (Vector3.distance(origin, checkPoint) < distance) {
            Collider collidedObject = ColliderManager.checkPointForCollision(checkPoint);
            if (collidedObject != null) {
                return new RaycastHit(checkPoint, new Vector3(), collidedObject);
            } else {
                checkPoint.add(stepVector);
            }
        }
        
        return null;
    }
    
}
