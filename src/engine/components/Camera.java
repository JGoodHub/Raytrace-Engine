package engine.components;

import javafx.scene.paint.Color;
import engine.RaycastHit;
import engine.Raycaster;
import engine.Screen;
import engine.Vector3;

public class Camera extends Component {
       
    private int xResolution;
    private int yResolution;
    private float fieldOfView = 60f;    
    
    private float nearClippingPlane = 1f;
    private float farClippingPlane = 100f;
    
    private Vector3[][] vectorPixelMap;
    
    private Screen targetScreen;
    
    private Color backgroundColour;
    
    public Camera (Screen targetScreen, Color backgroundColour) {
        this.targetScreen = targetScreen;    
        
        xResolution = targetScreen.width;
        yResolution = targetScreen.height;
        
        this.backgroundColour = backgroundColour;
        
        generatePixelVectorMap();
    }
    
    private void generatePixelVectorMap () {
        vectorPixelMap = new Vector3[xResolution][yResolution];        
        
        float fovInRadians = (float)Math.toRadians(fieldOfView);
        float pixelDelta = (float)((Math.tan(fovInRadians / 2) * nearClippingPlane) / (xResolution / 2));
        Vector3 pixelMapper = new Vector3(
                -pixelDelta * (xResolution / 2),
                pixelDelta * (yResolution / 2),
                nearClippingPlane
        );
                
        for (int y = 0; y < yResolution; y++) {
            for (int x = 0; x < xResolution; x++) {
                vectorPixelMap[x][y] = Vector3.copy(pixelMapper);
                pixelMapper.x += pixelDelta;
            }
            
            pixelMapper.x -= pixelDelta * xResolution;
            pixelMapper.y -= pixelDelta;
        }
        
    }
    
    public Color[][] raycastPixelColourMap () {
        Color[][] colourMap = new Color[xResolution][yResolution];
        Vector3 rayDirection;      
        
        for (int y = 0; y < yResolution; y++) {
            for (int x = 0; x < xResolution; x++) {
                rayDirection = Vector3.copy(vectorPixelMap[x][y]).normalised();
                                
                RaycastHit rayhit = Raycaster.fireRaycast(parent.transform.position, rayDirection, farClippingPlane);
                if (rayhit != null) {
                    colourMap[x][y] = rayhit.collider.getColour();
                } else {
                    colourMap[x][y] = backgroundColour;
                }                
            }            
        }
        
        return colourMap;
    } 
    
    
}
