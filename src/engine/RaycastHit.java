package engine;

import engine.components.Collider;

public class RaycastHit {
    
    public Vector3 point;
    public Vector3 normal;
    public Collider collider;

    public RaycastHit(Vector3 point, Vector3 normal, Collider collider) {
        this.point = point;
        this.normal = normal;
        this.collider = collider;
    }    
    
}
