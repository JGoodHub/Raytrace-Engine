package engine.components;

import javafx.scene.paint.Color;
import engine.RaycastHit;
import engine.Raycaster;
import engine.Screen;
import engine.Vector3;

public class Camera extends Component {
       
    private int xResolution;
    private float horizontalFoV;
    
    private int yResolution;
    private float verticalFoV;
    
    private float nearClippingPlane = 0.1f;
    private float farClippingPlane = 100f;
    
    private Screen targetScreen;
    
    private Color backgroundColour;
    
    public Camera (Screen targetScreen, float fieldOfView, Color backgroundColour) {
        this.targetScreen = targetScreen;    
        
        xResolution = targetScreen.width;
        yResolution = targetScreen.height;
        
        horizontalFoV = fieldOfView;
        verticalFoV = (horizontalFoV / xResolution) * yResolution;
                
        this.backgroundColour = backgroundColour;
    }
    
    public Color[][] raycastPixelColourMap () {
        Color[][] colourMap = new Color[xResolution][yResolution];
        float fovAngleStep = horizontalFoV / xResolution;
                
        Vector3 raycastDirection = Vector3.copy(parent.transform.rotation);
        System.out.println(raycastDirection.normalised());
        raycastDirection.rotateAroundAxis(-1 * (horizontalFoV / 2), Vector3.Axis.Y);
        System.out.println(raycastDirection.normalised());
        raycastDirection.rotateAroundAxis(-1 * (verticalFoV / 2), Vector3.Axis.X);
        System.out.println(raycastDirection.normalised());
        for (int y = 0; y < yResolution; y++) {
            for (int x = 0; x < xResolution; x++) {
                RaycastHit rayhit = Raycaster.fireRaycast(parent.transform.position, raycastDirection, farClippingPlane);
                if (rayhit != null) {
                    colourMap[x][y] = rayhit.sphere.colour;
                } else {
                    colourMap[x][y] = backgroundColour;
                }
                
                raycastDirection.rotateAroundAxis(fovAngleStep, Vector3.Axis.Y);
                //System.out.println("Pixel: x - " + x + " y - " + y + " Raycast Direction Vector: " + raycastDirection);
            }
            
            raycastDirection.rotateAroundAxis(-fovAngleStep * xResolution, Vector3.Axis.Y);
            raycastDirection.rotateAroundAxis(fovAngleStep, Vector3.Axis.X);
        }
        
        return colourMap;
    } 
    
    
}
