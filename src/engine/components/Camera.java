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
    
    private float lightFalloffStartAngle = 40f;
    private float lightFalloffEndAngle = 110f;
    
    private Vector3[][] vectorPixelMap;    
    private Screen targetScreen;    
    private Color backgroundColour;
    
    public boolean renderShadows = true;
    
    public Camera (Screen targetScreen, Color backgroundColour) {
        this.targetScreen = targetScreen;    
        
        xResolution = targetScreen.width;
        yResolution = targetScreen.height;
        
        this.backgroundColour = backgroundColour;
        
        generateRayDirectionMap();
    }
    
    private void generateRayDirectionMap () {
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
                    Color baseColour = colliderRayhit.collider.getColour();
                    Color pixelColour = colliderRayhit.collider.getColour();
                    
                    //Check if that part of the collider is in shadow
                    for (Light l : LightManager.sceneLights) {
                        Vector3 lightDirection = Vector3.getDirection(colliderRayhit.point, l.parent.transform.position);
                        RaycastHit lightRayhit = Raycaster.fireRaycast(
                                colliderRayhit.point,
                                lightDirection,
                                Vector3.distance(colliderRayhit.point, l.parent.transform.position)
                        );
                                                
                        if (Vector3.distance(lightRayhit.point, l.parent.transform.position) > 1f) {
                            pixelColour = modifiyColour(pixelColour, 0.25f);
                        }
                        
                        float angleToLight = Vector3.angleBetween(colliderRayhit.normal, lightDirection);                        
                        if (angleToLight >= lightFalloffStartAngle && angleToLight <= lightFalloffEndAngle) {
                            float normalAngleModifier =  1 - ((angleToLight - lightFalloffStartAngle) / (lightFalloffEndAngle - lightFalloffStartAngle));
                            pixelColour = modifiyColour(baseColour, 0.25f + (normalAngleModifier * 0.75f));
                        }                        
                    }                                  
                    
                    colourRow[x] = pixelColour;
                } else {
                    colourRow[x] = backgroundColour;
                }                
            }
            
            RaytracingEngine.screen.drawLineToScreen(y, colourRow);
            
        }
    } 
    
    private float clamp (float value, float min, float max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
    
    private Color modifiyColour (Color col, float lightnessModifier) {
        return new Color(
                col.getRed() * lightnessModifier,
                col.getGreen() * lightnessModifier,
                col.getBlue() * lightnessModifier,
                1f);
    }
    
    
}
