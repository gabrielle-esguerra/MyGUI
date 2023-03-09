import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
/**
 * Making some sliders and responding to mouse events
 * This includes:
 * A slider to adjust the size of the line drawn
 * A button to change the circle to a rectangle and vice versa
 * A colour picker
 * Draws the shape on the centre of the cursor
 * Pressed and released draws rectangle 
 * 
 * @author Gabrielle
 * @version 7/3/23
 */
public class MsPaintClone
{
    // instance variables
    private double size; 
    private String shape;
    private double startX, startY; // fields to remember "pressed" position
    private double endX, endY; // fields to remember "released" position
    private double left, top, width, height; // fields to draw a rectangle

    // remember the colour
    private Color currentColor = Color.black;
    
    /**
     * Constructor for objects of class MyGui
     */
    public MsPaintClone()
    {
        // initialise instance variables
        size = 0;
        shape = "oval";
        
        // set up buttons
        UI.addButton("Quit", UI::quit);
        UI.addButton("Change Shape", this::changeShape);
        
        // colour buttons
        UI.addButton("Colour", this::chooseColour);
        UI.addButton("Random Colour", this::changeColour);
    
        // set up slider
        UI.addSlider("Size", 0, 50, 25, this::setSize);
        
        // set up mouse listener
        UI.setLineWidth(10);
        UI.setMouseListener(this::doMouse);
    }

    /**
     * Callback method for line size slider
     */
    public void setSize(double px) {
        UI.setLineWidth(px);
        // set size to new size
        this.size = px;
    }
    
    /**
     * Callback method to mouse listener
     * ONLY MAKE 1 CALLBACK METHOD TO THE MOUSE LISTENER
     */
    public void doMouse(String action, double x, double y){
        if (action.equals("clicked")){
            doShape(x,y); // draw either square or circle
        } else if (action.equals("pressed")){
            this.startX = x;
            this.startY = y;
        } else if (action.equals("released")){
            this.endX = x;
            this.endY = y;

            // calculate width and height depending on if end point is bigger or smaller than start point
            // also changes the left and top of rectangle depending on where the start of the rectangle is
            if (startX>endX) {
                width = startX-endX;
                left = endX; 
            } else {
                width = endX-startX;
                left = startX; 
            }
            if (startY>endY) {
                height = startY-endY;
                top = endY;
            } else {
                height = endY-startY;
                top = startY;
            }

            UI.fillRect(left, top, width, height);
        }
    }
    
    /**
     * Change to a random colour
     */
    public void changeColour() {
        Color col = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
        UI.setColor(col);
    }
    
    /**
     * Allows the user to choose a colour using the swing library
     */
    public void chooseColour() {
        this.currentColor = JColorChooser.showDialog(null, "Choose colour", this.currentColor);
        UI.setColor(this.currentColor);
    }
    
    /**
     * Callback method for shape button
     */
    public void changeShape() {
        if (shape.equals("oval")) { // if shape is already oval, change to rectangle
            shape = "rect";
        }
        else if (shape.equals("rect")) {
            shape = "oval";
        }
    }
    
    /**
     * Draw shape in the centre of where the mouse is clicked
     */
    public void doShape(double x, double y) {
         // set size of shape
        double width = 50;
        double height = 50;
        // draw shape
        if (shape.equals("oval")) {
            UI.fillRect (x-(width/2), y-(height/2), width, height);
        }
        else if (shape.equals("rect")) {
            UI.fillOval (x-(width/2), y-(height/2), width, height);
        }
    }
}
