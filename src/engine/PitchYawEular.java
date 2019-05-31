package engine;

public class PitchYawEular {
    
    private float yaw;
    private float pitch;
    
    public PitchYawEular () {}

    public PitchYawEular(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setYaw(float newYaw) {
        if (newYaw >= -180 && newYaw <= 180) {
            yaw = newYaw;
        } else {
            System.err.println("ERROR: Attempt to set yaw to invalid value");
        }
    }

    public void setPitch(float newPitch) {
        if (newPitch >= -180 && newPitch <= 180) {
            yaw = newPitch;
        } else {
            System.err.println("ERROR: Attempt to set pitch to invalid value");
        }
    }
    
    public Vector3 convertToDirectionalVector () {
        Vector3 direction = new Vector3(0, 0, 1);
        
        if (yaw < 0) {
            
        } else if (yaw > 0) {
            
        }
        
        return direction;
    }
    
    
    
    
}
