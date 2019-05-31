package engine.components;

import engine.PitchYawEular;
import engine.Vector3;

public class Transform extends Component {
    
    public Vector3 position;
    public Vector3 rotation;
    public PitchYawEular rotation2;
    
    public Vector3 up;
    public Vector3 right;
    public Vector3 forward;
    
    public Transform () {
        position = new Vector3();
        rotation = new Vector3();
        
        rotation2 = new PitchYawEular();
        
        up = new Vector3(0, 1, 0);
        right = new Vector3(1, 0, 0);
        forward = new Vector3(0, 0, 1);
    }
    
}