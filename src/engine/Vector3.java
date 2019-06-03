package engine;

public class Vector3 {
    
    public enum Axis {Y, X, Z}
    
    public float x = 0;
    public float y = 0;
    public float z = 0;
    
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3 (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static float distance(Vector3 vectorA, Vector3 vectorB) {
        Vector3 relativeVector = Vector3.copy(vectorB).subtract(vectorA);
        return (float)Math.sqrt(Math.pow(relativeVector.x, 2) + Math.pow(relativeVector.y, 2) + Math.pow(relativeVector.z, 2));
    }
    
    public static float distanceSquared(Vector3 vectorA, Vector3 vectorB) {
        Vector3 relativeVector = Vector3.copy(vectorB).subtract(vectorA);
        return (float)(Math.pow(relativeVector.x, 2) + Math.pow(relativeVector.y, 2) + Math.pow(relativeVector.z, 2));
    }
    
    public static Vector3 copy (Vector3 original) {
        return new Vector3(original.x, original.y, original.z);
    }
    
    public static Vector3 getDirection (Vector3 origin, Vector3 target) {
        return Vector3.copy(target).subtract(origin).normalised();
    }
    
    public static float dotProduct (Vector3 vectorA, Vector3 vectorB) {
        return (vectorA.x * vectorB.x) + (vectorA.y * vectorB.y) + (vectorA.z * vectorB.z);
    }
    
    public static float angleBetween (Vector3 vectorA, Vector3 vectorB) {
        float cosineAngle = Vector3.dotProduct(vectorA, vectorB) / (vectorA.magnitude() * vectorB.magnitude());
        return (float)Math.toDegrees(Math.acos(cosineAngle));
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
        return "Vector3{" + "x = " + (Math.round(x * 1000) / 1000f) + ", y = " + (Math.round(y * 1000) / 1000f) + ", z = " + (Math.round(z * 1000) / 1000f) + '}';
    }  
    
}