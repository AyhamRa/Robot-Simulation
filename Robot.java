import java.util.Scanner;
import java.awt.Graphics;
import java.util.*;
import java.awt.Color;

/**
 * In this Class the Robot will be able to answer few Questions
 * The Class Roboter is a SubCalss from Class Kreis
 */
public class Robot extends Circle
{
    /**
     * An enum is a special "class" that represents a group of constants and it's a static 
     * as a final variable they all in toUpperCase 
     */
     enum KeyWord{
       NAME,
       MANUFACTURER,
       GENDER,
       BIRTHDAY,
       NO;
    }
    
    /**
     * Constructor for objects of class Roboter
     */
     public Robot()
    {
        super(50,Color.ORANGE);
    }
    
    /**
     * In this Method, the User can ask Questions and the Roboter will answer, using KeyWords to define The Correct answer for the Question
     */
    // using equal to Compare two Strings words
    // using contains if you want to compare a word with whole sentence.
    // @nextLine because I store a whole sentence, @next if i want to store just a word.
    // @toUpperCase will transform any word in the Input to Capital letters.
     public void speechRecognition(){
        KeyWord kw;
        System.out.println("Ask me a Question");
        Scanner scanner= new Scanner(System.in); // read the Input form the user
        //PlayingField playField = new PlayingField();
        //playField.randomNumber(0,1);
        //String asnwer[] = {"Hey word", "Merhaba"};
         
         // The idea to use for Loop for End was from Internet but I have developed   
        for (String word = scanner.nextLine(); !word.toUpperCase().equals("END"); word = scanner.nextLine()) {
 
          if ( word.toUpperCase().equals("END")  ){
                    break;
                    }
          if (word.toUpperCase().contains("NAME") || word.toUpperCase().contains("CALL") ){
                  kw = KeyWord.NAME;
                }     
           else if (word.toUpperCase().contains("MANUFACTURER") || word.toUpperCase().contains("INVENTOR") || word.toUpperCase().contains("CREATOR") || word.toUpperCase().contains("DEVELOPER") ){ 
                  kw = KeyWord.MANUFACTURER;
                    }
            else if (word.toUpperCase().contains("GENDER")){
                  kw = KeyWord.GENDER;
                       }
             else if (word.toUpperCase().contains("BIRTHDAY") || word.toUpperCase().contains("OLD") || word.toUpperCase().contains("AGE")){
                  kw = KeyWord.BIRTHDAY;
                       }
              else {
                  kw = KeyWord.NO;
                           }
                      
            switch (kw) {
              case NAME:
                 System.out.println(" My name is kirito");
                 break;
              case MANUFACTURER:
                 System.out.println(" Well, my manufacturer is and he is more like my Dad.");
                 break;
              case GENDER:
                 System.out.println(" I am a Machine, a Machine have no Gender.Do you know otherwise?");  
                 break;
              case BIRTHDAY:
                 System.out.println(" I am only 3 months old, so in Humans words i am still a Baby."); 
                 break;
              default: 
                 System.out.println("Sorry, I can't answer that.");
                 break;
         }
            System.out.println("=====================================");
            System.out.println("Ask me another Question or Press End to Quit.");  
        }
        
    }
    
     /**
     * If the Robot at the Edge of our Panle then "return" true and "false" otherwise.
     */
      public boolean onWall(int WallX, int WallY){
      if(getPosition().getX() >= WallX-getDiameter() || getPosition().getY() >= WallY-getDiameter()){
          return true;
        }
          else
             return false;
    }
     /**
      * Check if the Robot can move Right and return "false" if he can't and "true" if he can.
      * @getPosition().getX() < rechteck.getPosition().getX() the Robot will check if he at left of the Obstcal, without it the Robot won't move straight
      * @betweenObstcalsEdge_Y() is Down declare it 
      */
     public boolean canMoveRight(int step, ArrayList<Rectangle> obstacles){
       for (Rectangle rectangle : obstacles) {
         if (getPosition().getX()+getDiameter() + step > rectangle.getPosition().getX() && betweenObstcalsEdge_Y(rectangle) && getPosition().getX() < rectangle.getPosition().getX() ){
           return false;
         }
               
      }
      return true;
    }

    /**
    * Check if the Robot can move Down and return false if he can't and true if he can.
    * @getPosition().getY() < rechteck.getPosition().getY() the Robot will check if he is Up of the Obstcal, without it the Robot won't move straight
    * @betweenObstcalsEdge_X() is Down declare it 
    */
    public boolean canMoveDown(int step, ArrayList<Rectangle> obstacles){
       for (Rectangle rectangle : obstacles ){
         if (getPosition().getY()+getDiameter() + step > rectangle.getPosition().getY() && betweenObstcalsEdge_X(rectangle) && getPosition().getY() < rectangle.getPosition().getY()){
            return false;
         }
        
      }
      return true;
    }
   
    /**
    * Check if the Robot is bewtween Obstcal Edge of the Y-Achse
    */
    public boolean betweenObstcalsEdge_Y(Rectangle rectangle){
     if (getPosition().getY() >= rectangle.getPosition().getY() + rectangle.getLength() || getPosition().getY()+ getDiameter() <= rectangle.getPosition().getY()){
        return false;
        }
        return true;
    }
    /**
     * Check if the Robot is bewtween Obstcal Edge of the X-Achse
     */
    public boolean betweenObstcalsEdge_X(Rectangle rectangle){
      if (getPosition().getX() >= rectangle.getPosition().getX() + rectangle.getWidth() || getPosition().getX()+ getDiameter() <= rectangle.getPosition().getX()){
        return false;
        }
        return true;
    }
    
    /**
     * Method to help the Robot move from Point to other
     */
    public void moveTo(Point point){
        Canvas canvas = Canvas.getInstance(); 
        boolean notReached = true;
        int dx = 0;
        int dy = 0;
        
        // Comparing the Robot Position with the Point position
        if(point.getX() > getPosition().getX()){
            dx = 1;
        }else if(point.getX() < getPosition().getX()){
            dx = -1;
        }
        
         if(point.getY() > getPosition().getY()){
            dy = 1;
         }else if(point.getY() < getPosition().getY()){
            dy = -1;
        }
        
        //Ex: When the Robot reach the x of the Point he will stop going Right and start to move down or top. 
        while(notReached){
            if(point.getX() == getPosition().getX()+getDiameter()/2){
                dx = 0;
            }       
            if(point.getY() == getPosition().getY()+getDiameter()/2){
                dy = 0;
            }
            
            if(dx == 0 && dy == 0){
                notReached = false;
            }
            
            moveAround(dx, dy);
            canvas.update(); 
        }
    }
    /**
     * to Reset the Robot Position and Color
     * we need this method if we re-Enter a Obstcals Numbers without restarting the Program
     */
    public void reset(){
     setPosition(0,0);          
     setColor(Color.ORANGE);    
    }
    
    /**
     * Drawing the Robot and just call the method in Class "Leinwand"
     */
    public void draw(Graphics g){
    g.setColor(getColor());
    g.fillOval(getPosition().getX() ,getPosition().getY() ,getDiameter() ,getDiameter());
    }
}
