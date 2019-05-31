package engine;

public class Vector3 {
    
    public enum Axis {Y, X, Z}
    
    public float x = 0;
    public float y = 0;
    public float z = 0;
    
    public Vector3() {}

    public Vector3 (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static float distance(Vector3 vectorA, Vector3 vectorB) {
        Vector3 relativeVector = vectorB.subtract(vectorA);
        return (float)Math.sqrt(Math.pow(relativeVector.x, 2) + Math.pow(relativeVector.y, 2) + Math.pow(relativeVector.z, 2));
    }
    
    public static Vector3 copy (Vector3 original) {
        return new Vector3(original.x, original.y, original.z);
    }
    
    public static void normalise (Vector3 vector) {
        float startingMagnitude = vector.magnitude();
        vector.x *= 1 / startingMagnitude;
        vector.y *= 1 / startingMagnitude;
        vector.z *= 1 / startingMagnitude;
    }
    
    public Vector3 add (Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }
    
    public Vector3 subtract (Vector3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        return this;
    }
    
    public Vector3 scaled (float scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        z *= scaleFactor;
        return this;
    }
    
    public float magnitude () {
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    
    public Vector3 normalised () {
        float startingMagnitude = magnitude();
        return this.scaled(1 / startingMagnitude);
    }
    
    public void rotateAroundAxis (float angle, Axis axisOfRotation) {
        float radians = (float)Math.toRadians(angle);
        float xPrime, yPrime, zPrime;
        
        switch (axisOfRotation) {
            case Y:
                radians *= -1;
                xPrime = (float)((x * Math.cos(radians)) - (z * Math.sin(radians)));
                zPrime = (float)((x * Math.sin(radians)) + (z * Math.cos(radians)));
                x = xPrime;
                z = zPrime;
                break;
            case X:
                yPrime = (float)((y * Math.cos(radians)) - (z * Math.sin(radians)));
                zPrime = (float)((y * Math.sin(radians)) + (z * Math.cos(radians)));
                y = yPrime;
                z = zPrime;
                break;
            case Z:
                radians *= -1;
                xPrime = (float)((x * Math.cos(radians)) - (y * Math.sin(radians)));
                yPrime = (float)((x * Math.sin(radians)) + (y * Math.cos(radians)));
                x = xPrime;
                y = yPrime;
                break;
        }
    }

    @Override
    public String toString() {
        return "Vector3{" + "x = " + (Math.round(x * 100) / 100f) + ", y = " + (Math.round(y * 100) / 100f) + ", z = " + (Math.round(z * 100) / 100f) + '}';
    }
     
    
}
