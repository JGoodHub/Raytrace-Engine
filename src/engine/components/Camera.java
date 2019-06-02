package engine.components;

import javafx.scene.paint.Color;
import engine.RaycastHit;
import engine.Raycaster;
import engine.RaytracingEngine;
import engine.Screen;
import engine.Vector3;
import engine.managers.LightManager;

public class Camera extends Component {
       
    private int xResolution;
    private int yResolution;
    private float fieldOfView = 60f;    
    private float farClippingPlane = 300f;
    
    private Vector3[][] vectorPixelMap;    
    private Screen targetScreen;    
    private Color backgroundColour;
    
    public boolean renderShadows = true;
    
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
        float pixelDelta = (float)((Math.tan(fovInRadians / 2) * 1f) / (xResolution / 2));
        Vector3 pixelMapper = new Vector3(
                -pixelDelta * (xResolution / 2),
                pixelDelta * (yResolution / 2),
                1f
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
    
    public void drawRaycastColourMapToScreen () {
        Color[] colourRow = new Color[xResolution];
        Vector3 rayDirection;
        
        for (int y = 0; y < yResolution; y++) {
            for (int x = 0; x < xResolution; x++) {
                rayDirection = Vector3.copy(vectorPixelMap[x][y]).normalised();
                
                //Check if that pixel contains a collider
                RaycastHit colliderRayhit = Raycaster.fireRaycast(parent.transform.position, rayDirection, farClippingPlane);
                if (colliderRayhit.collider != null) {
                    if (renderShadows == true) {
                        //Check if that part of the collider is in shadow
                        for (Light l : LightManager.sceneLights) {                 
                            RaycastHit lightRayhit = Raycaster.fireRaycast(
                                    colliderRayhit.point, 
                                    Vector3.getDirection(colliderRayhit.point, l.parent.transform.position), 
                                    Vector3.distance(colliderRayhit.point, l.parent.transform.position)
                            );

                            if (Vector3.distance(lightRayhit.point, l.parent.transform.position) < 1f) {
                                colourRow[x] = colliderRayhit.collider.getColour();
                            } else {
                                colourRow[x] = colliderRayhit.collider.getColour().darker().darker();
                            }
                        }
                    } else {
                        colourRow[x] = colliderRayhit.collider.getColour();
                    }
                } else {
                    colourRow[x] = backgroundColour;
                }                
            }
            
            System.out.println("Progress: " + (Math.round((y / (float)yResolution) * 1000) / 10f) + "%");
            RaytracingEngine.screen.drawLineToScreen(y, colourRow);
            
        }
    } 
    
    
}
