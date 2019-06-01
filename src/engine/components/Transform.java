package engine.components;

import engine.Vector3;

public class Transform extends Component {
    
    public Vector3 position;
    public Vector3 forward;
    
    public Transform () {
        position = new Vector3();
        forward = new Vector3(0, 0, 1);
    }
    
    public void setForward (Vector3 newForward) {
        forward = newForward.normalised();
    }
    
}