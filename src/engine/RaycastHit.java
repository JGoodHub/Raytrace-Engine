package engine;

import engine.components.SpherePrimative;

public class RaycastHit {
    
    public Vector3 point;
    public Vector3 normal;
    public SpherePrimative sphere;

    public RaycastHit(Vector3 point, Vector3 normal, SpherePrimative sphere) {
        this.point = point;
        this.normal = normal;
        this.sphere = sphere;
    }
    
}
