import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * In this Class we will drawing the obstacles, Robot and Points in Grafics
 */
 public class Canvas
{
   // declaration the Class Attributes
   
   private static Canvas instance;
   JFrame window;
   private DrawingArea drawingArea;
   private int width;
   private int length;
   private Color background;
   ArrayList<Rectangle> obstacles;
   
    /**
     * Constructor for objects of class Canvas
     * @private Contstructor because we want to use the "Singleton Pattern", Which allow to Creat only one Object.
     * Every Object is Unique even if 2 Objects having the same Parameters, That's why we need Singleton Pattern.
     */
    private Canvas(int length, int width,  Color background)
    {
       window = new JFrame();
       this.length = length;
       this.width = width;
       this.background =  background;  // the Backgroung Color
       obstacles = new ArrayList<>();   // givieng the obstacles a value
       window.setSize(length ,width);
       window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // to close the Window
       window.setBackground(background);
       window.setResizable(false);              // dont let them Change
       window.setTitle( "Robotersimulation" );  // give the Window a Tile
        
        
    }
   
   /**
    * The Static Method help to isnstance the Only Object. 
    * @if Statment required because we need only one Object so he will check, if it's null, then he well creat it and return it and if not
    * just return it.
    * @Parameters I change the Attributs from Class "Spielfeld" to public and Static to be able to use it here, of course this work only
    * if the Variable a Constant& Static Variable
    */
   public static Canvas getInstance(){
        if (instance == null){
              instance = new Canvas(PlayingField.LONG,PlayingField.WIDE,Color.WHITE);
        }
        return instance;
    }
    
    /**
     * Wait for a specified number of milliseconds before finishing This provides an easy way to specify
     * a small delay which can be used when producing animations.
     * @param  milliseconds  the number 
     */
    public void waite(int millisekunden)
    {
    try
    {
      Thread.sleep(millisekunden);   // Thread.sleep = do nothing
    }
    catch (Exception e)
    {
      // Exception ignorieren
    }
   }
  
    /**
     * Method to repaint and wait in the same Time, just to make it easy instead of calling 2 Methods
     */
    public void update(){
      repaint();
      waite(1000/60); // 60 fps
   }
  
   /**
   * The canvas is recreated, the ArrayList with the obstructions is passed to the canvas,
   * and the window is redisplayed.
   * Draw the Obstcales,Robot and The Points to.
   */
   public void draw(ArrayList<Rectangle> obstacles,Robot robot, Point[] points){
      // Recreate drawing area.
      drawingArea = new DrawingArea(obstacles,robot,points);
      drawingArea.setBackground(background);
      window.getContentPane().removeAll();            // Show new window.
      window.add(drawingArea);
      window.setVisible(true);                        // let the Window come Visible

      }
      
   /**
    * repaint need it when the Robot will move.
    */
   public void repaint(){
      drawingArea.repaint();
    }
}


