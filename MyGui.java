import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
/**
 * Making some sliders and responding to mouse events
 *
 * @author Gabrielle
 * @version 7/3/23
 */
public class MyGui
{
    // instance variables - replace the example below with your own
    private double speed;
    private double startX, startY; // fields to remember "pressed" position

    // remember the colour
    private Color currentColor = Color.black;
    
    /**
     * Constructor for objects of class MyGui
     */
    public MyGui()
    {
        // initialise instance variables
        speed = 0;
        
        // set up buttons
        UI.addButton("Quit", UI::quit);
        
        // colour buttons
        UI.addButton("Colour", this::chooseColour);
        UI.addButton("Random Colour", this::changeColour);
    
        // set up slider
        UI.addSlider("Speed", 0, 100, 20, this::setSpeed);
        
        // set up mouse listener
        UI.setLineWidth(10);
        UI.setMouseListener(this::doMouse);
    }

    /**
     * Callback method for speed slider
     */
    public void setSpeed(double km) {
        // check if it's greater or less than last speed
        if (speed < km) {
            UI.println("Accelerating");
        } else if (speed > km) {
            UI.println("Delerating");
        } else {
            UI.println("Stationary");
        }
        
        // set speed to the new speed
        this.speed = km;
    }
    
    /**
     * Callback method to mouse listener
     * ONLY MAKE 1 CALLBACK METHOD TO THE MOUSE LISTENER
     */
    public void doMouse(String action, double x, double y){
        double width = 50;
        double height = 50;
        if (action.equals("clicked")){
            UI.fillOval(x-(width/2), y-(height/2), width, height);
        } else if (action.equals("pressed")){
            this.startX = x;
            this.startY = y;
        } else if (action.equals("released")){
            UI.drawLine(this.startX, this.startY, x, y);
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
}
