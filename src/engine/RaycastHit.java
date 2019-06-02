package engine;

import engine.components.Collider;

public class RaycastHit {
    
    public Vector3 point;
    public Collider collider;

    public RaycastHit(Vector3 point, Collider collider) {
        this.point = point;
        this.collider = collider;
    }
    
    
}
