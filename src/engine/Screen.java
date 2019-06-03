package engine;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Screen {
    
    public ImageView imageView;
    
    private PixelWriter screenWriter;
    private WritableImage writableScreen;
    
    public int width;
    public int height;
    
    public Screen (int width, int height) {
        this.width = width;
        this.height = height;
        
        writableScreen = new WritableImage(this.width, this.height);
        imageView = new ImageView(writableScreen);
        screenWriter = writableScreen.getPixelWriter();        
    }
    
    public void fillScreenWithColour (Color fillColour) {
        for (int y = 0; y < writableScreen.getHeight(); y++) {
            for (int x = 0; x < writableScreen.getWidth(); x++) {
                drawPixelToScreen(x, y, fillColour);
            }
        }
    }
    
    public void drawPixelToScreen (int x, int y, Color colour) {
        screenWriter.setColor(x, y, colour);
    }
    
    public synchronized void drawLineToScreen (int y, Color[] colourRow) {
        for (int x = 0; x < colourRow.length; x++) {
            drawPixelToScreen(x, y, colourRow[x]);
        }
    }
    
    public void drawColourMapToScreen (Color[][] colourMap) {
        for (int y = 0; y < colourMap[0].length; y++) {
            for (int x = 0; x < colourMap.length; x++) {
                drawPixelToScreen(x, y, colourMap[x][y]);
            }
        }
    }
       
    public void clearScreen () {
        for (int y = 0; y < writableScreen.getHeight(); y++) {
            for (int x = 0; x < writableScreen.getWidth(); x++) {
                drawPixelToScreen(x, y, Color.WHITE);
            }
        }
    }    
    
}
