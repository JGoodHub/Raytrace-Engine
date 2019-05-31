package engine;

import engine.components.SpherePrimative;
import engine.managers.ColliderManager;

public class Raycaster {
    
    public static RaycastHit fireRaycast (Vector3 origin, Vector3 direction, float distance) {
        Vector3 checkPoint = Vector3.copy(origin);
        Vector3 stepVector = direction.normalised().scaled(0.1f);
        
        int loopCounter = 0;
        while (Vector3.distance(origin, checkPoint) < distance && loopCounter < 10000) {
            SpherePrimative collidedSphere = ColliderManager.checkPointSphereCollision(checkPoint);
            if (collidedSphere != null) {
                return new RaycastHit(checkPoint, new Vector3(), collidedSphere);
            } else {
                checkPoint.add(stepVector);
            }
            
            loopCounter++;
        }
        
        if (loopCounter >= 10000) {
            System.err.println("ERROR IN RAYCAST WHILE LOOP");
        }       
        
        return null;
    }
    
}
