import javax.swing.*;
import java.util.*;
import java.awt.Graphics;
   
/**
 * Class Zeichenflaeche als subClass to JPanel witch need the Keywoerd "extends"
 * We will be ables to use the Methods from the SuperClass "JPanel" like paintComponent()
 */ 
 public class DrawingArea extends JPanel{
       // Attribut declaration
       private ArrayList<Rectangle> obstacles;      
       private Robot roboter;
       private Point[] punkte;
       
       /**
        * Constructor for objects of class Zeichenflaeche
        */
         public DrawingArea (ArrayList<Rectangle> obstacles, Robot roboter , Point[] punkte) {
        this.obstacles = obstacles;
        this.roboter = roboter;
        this.punkte = punkte;
     }
    
     /**
      * This Method is from Super class JPanel, hier will draw all obstacles with help from class Graphics
      * @g.fillRect & @g.fillOval (x,y,width,height);
      * The difference between fillRect and drawRect is with fill will paint inside the Obstacles.
      */
       public void paintComponent(Graphics g){
          super.paintComponent(g);
          if ( obstacles != null) {
           for (Rectangle rechteck : obstacles)  {
           // calling Methods from Rechteck
           rechteck.zeichnen(g);
          }
        }
       //@roboter how can i use the Object "Roboter" to call the Method for Class "Kreis"? because Class "Roboter is a SubClass from "Kreis"
         roboter.zeichnen(g);
       
         // Drawing the Points
            if ( punkte != null){   
           for (Point punkt: punkte){
            punkt.draw(g);
            }
         }
      }
    }

