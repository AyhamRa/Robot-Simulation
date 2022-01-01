import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

/**
 * The Goal from this Class to store the location of the robot and the obstacles in the coordinate System
 */
public class Point
{
    // Koordinaten 
    private int x;
    private int y;

    /**
     * Constructor for objects of class Point
     */
    public Point()
    {
        // initialise instance variables
        x = 0;
        y = 0;
    }
    
     /**
     * Second constructor
     * When a Java class contains multiple constructors, we say that the constructor
     * is overloaded.
     * @this in front of the field name (this.number) is not necessary.
     * It just signals to the compiler that it is the field named number that is being
     * referred to. This explained in more detail in the section about constructor parameters.
     */ 
    public Point(int x, int y){
    this.x = x;
    this.y = y;
    }
    
    /** 
     * Methods to read the Attributs
     */
    public int getX()
    {
        return x;
    }
    
    public int getY(){
         return y;
    }
    
    /** 
     * Methods to Change Attributs
     */
    public void setX(int x) { 
        this.x=x;
        }
    
    public void setY(int y){
         this.y=y;
    }
    
    /**
     * Give the outputs coordinates of the current point on the console.
     * only Strings Can be Printed.
     */
   
    public void outputAttributes(){ 
        System.out.println("x =" + x + ",y =" + y);
        
    
    }
    
    /**
     * Method move around 
     */
    public void moveAround(int dx, int dy){
         x = x + dx;
         y = y + dy;
    }
    
    /**
     * Method for calculating the Distance between two points.
     * There are many Different methods to calculate the Distance.
     * Simply return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
     * @abs = absolute value
     */
   
    public double giveDistance(Point differentPoint){
       double ac = Math.abs (x - differentPoint.getX());
       double cb = Math.abs (y - differentPoint.getY());
       double abstand = Math.hypot (ac , cb);
       System.out.println("Abstand ="+abstand);
       return abstand;

    }

    /**
     * Draw the Points.
     */     
    public void draw(Graphics g){
    
    g.setColor(Color.BLACK);   
    g.fillOval(getX(),getY(),10,10);
    }

}
