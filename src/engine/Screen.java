package engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Screen {
    
    private Image screenImage;
    public ImageView imageView;
    
    private PixelWriter screenWriter;
    private WritableImage writableScreen;
    
    public int width = 640;
    public int height = 480;
    
    public Screen (int width, int height) {
        this.width = width;
        this.height = height;
        
        writableScreen = new WritableImage(this.width, this.height);
        imageView = new ImageView(writableScreen);
        screenWriter = writableScreen.getPixelWriter();        
    }
    
    public void fillScreenWithColour (Color fillColour) {
        for (int y = 0; y < screenImage.getHeight(); y++) {
            for (int x = 0; x < screenImage.getWidth(); x++) {
                drawPixelToScreen(x, y, fillColour);
            }
        }
    }
    
    public void drawPixelToScreen (int x, int y, Color colour) {
        screenWriter.setColor(x, y, colour);  
    }
    
    public void drawColourMapToScreen (Color[][] colourMap) {
        for (int y = 0; y < colourMap[0].length; y++) {
            for (int x = 0; x < colourMap.length; x++) {
                drawPixelToScreen(x, y, colourMap[x][y]);
            }
        }
    }
       
    public void clearScreen () {
        for (int y = 0; y < screenImage.getHeight(); y++) {
            for (int x = 0; x < screenImage.getWidth(); x++) {
                drawPixelToScreen(x, y, Color.WHITE);
            }
        }
    }
    
    
}
