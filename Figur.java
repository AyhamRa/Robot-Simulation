import java.awt.Color;


/**
 * The Calss "Figur" is a Superclass form "Abstract" Type
 * @Abstract Class its like a General Class, That's mean which Method have abstract Type here have no body and should do override it in the 
 * required Classes to declare the Methods.
 * Normal Methods can also be written.
 */
public abstract class Figur
{
    // instance variables - replace the example below with your own
   Point position;
   Color color;

   /**
    * Constructor for objects of class Figur
    */
    public Figur(Point position,Color color)
    {
        // initialise instance variables
        this.position = position;
        this.color = color;
    }

   /**
    * Transfer the Methods from Class "Rechteck" to the Supercall Hier. So we can use it for the "Kreis Robot" to.
    */  
   public Color getColor(){
        return color;
        }
     
   public Point getPosition(){
       return position;
        }
        
    /**
     * the Color Error should be in every station so if we make an objekt from the constructor or set
     * the Color the program will be able to find the issue
     */   
    public void setColor(Color color){
     this.color = color;
     if (color.equals(Color.white)){ 
     System.out.println("Color cant't be White");
        }
     }
     
    public void setPosition(Point position){
      this.position = position;
    } 
        
    public void setPosition(int x, int y){
      position.setX(x);
      position.setY(y);
    } 
    
    /**
     * changed the current position of a rectangle by dx and dy pixels
     */
     public void moveAround(int dx, int dy){
     position.moveAround(dx,dy);
    }
    
    /**
     * the position of the rectangle around the x and y values of the parameter
     * transferred shift vector changed
     */
    public void moveAround(Point shiftVector){
    position.moveAround(shiftVector.getX(),shiftVector.getY());
    
    }    
    
    /**
     * Abstract Methods and will be overridden in Classes " Kreis & Rechteck".
     */
    public abstract int minX();
    public abstract int minY();
    public abstract int maxX();
    public abstract int maxY();
    
}
