import java.util.*;
import java.util.Random;
import java.awt.Color;
import java.util.InputMismatchException;
import javax.swing.*;

/**
 *  In this class, the playing Field will be created on which the robot will later move.
 */
public class PlayingField
{
    //  Constante variables "final" means Constat
    public final static int WIDE = 1000;
    public final static int LONG = 1000;
    private static Random randomGenerator = new Random();
    int stop = 0;
    private Robot robot;
    private Canvas canvas;
    

    /**
     * Constructor for objects of class Spielfeld
     * @leinwand, a Change requierd because it's now a Singleton Pattern, so we need to use The Methode "Leinwand.getinstnas()".
     */
    public PlayingField()
    {
       robot = new Robot();
       canvas = Canvas.getInstance(); 
    }
    
    /**
     * This Method give The user the possibility to input numbers of Points and the Dimensionen (x,y).
     * @Continue: The continue statement breaks one iteration (in the loop), if a specified condition occurs,
     * and continues with the next iteration in the loop.
     */
    public Point[] EnterPoints(){
    
    Scanner scanner= new Scanner(System.in);  // read the Input form the user
    int pointsCount;
    System.out.println("How many Points do you want?");
    try{ 
    pointsCount= scanner.nextInt(); // storing the Input in the Variable pointsCount
    if (pointsCount < 2){
    System.out.println("Number of Points can't be less than 2");
    System.out.println("=====================================");
    return EnterPoints();
               }
     }
    catch(InputMismatchException e){
        System.out.println("Please Enter only an Integer Number");
        System.out.println("=====================================");
        return EnterPoints();
    }
    
    System.out.println("Points Number is: " + pointsCount);
    System.out.println("=====================================");
    
    Point[] points= new Point[pointsCount]; //a declaration the Arry
    
    points[0] = new Point(); // The 1 Point always should be (0,0) so we call the first Constructor from 
                             // Class Punkt.
    System.out.println("Point 1 is always (0,0)");
    System.out.println("=====================================");
     for (int i=1 ; i < points.length; i++){
        System.out.println("Please enter the coordinates X of the Point "+(i+1));
        try{
        int x = scanner.nextInt();  // read the Input form the user
        if (x < 0 || x > WIDE) {
        System.out.println("your input lies outside the boundaries of allowed values. Please choose a valid number from 1 to a 1000");
        i--;
        continue;
        }
        System.out.println("Please enter the coordinates Y of the Point "+(i+1));
        int y = scanner.nextInt();
         if (y < 0 || y > LONG) {
        System.out.println("your input lies outside the boundaries of allowed values. Please choose a valid number from 1 to a 1000");
        i--;
        continue;
         }
        if (x <= robot.getDiameter() && y <= robot.getDiameter()) {
        System.out.println("At least One of the coordinates should be greater than The Robot Radius here 50 ");
        i--;
        continue; }
        Point point= new Point(x,y); // making new  object from the Second Constructor from Class Punkt
        points[i]= point; // Storing the object punkt in the Arry
        System.out.println("Point "+i+" :"+" x= "+x+ ", y = "+y);
        }  catch(InputMismatchException e){
            i--; // Why this? because he in the  normal way he will give us the Error then add 1, with this he 
                 // will add 1 and sub 1 so we get back to the Correct  Point
            System.out.println("Please Enter only an Integer Number");
            scanner= new Scanner(System.in); // without this the loop will be infinitif
        }
     }
    return points; 

    }
    
    /**  
     * Sorting the Points in order from the nearest one to the furthest one
     * The Robot will start Up-left and search for the nearest point to go to
     */
     public Point[] poiSortieren(Point[] poi){
    
    int size = poi.length; // stor number of items in the Arrays in the variable size   
    int sorted_index[] = new int[size]; // New Array to save the shortest Point in it
    Point[] sorted_points = new Point[size];
    sorted_index[0] = 0;
     
     sorted_points[0] = poi[0];
    /* 
     * in this loop he will exam every 2 points and Comparing the result with the minAbstand 
     * @size-1 because if we have 5 Points we need to calculate just 4 Points.
     * @contains look the method behind
     */                                                
     for (int i = 0 ; i<size-1 ; i++){
        double minDistance = 0; // we gave the variable here to rest the minAbstand every Time in this loop
        int index = sorted_index[i];
        for (int j=1 ; j<size ; j++){
            if(contains(sorted_index, j)) continue;
            
            double distance = poi[index].giveDistance(poi[j]);
            if(distance <= minDistance || minDistance == 0){
              minDistance = distance;
              sorted_index[i+1] = j; // Thats mean the min Distance will be stored in the next index.for Ex: if we have check 
                                     // the nearest Point for index 0 then the shortest Distnce are storing in index 1
            }
        }
        
        sorted_points[i+1] = poi[sorted_index[i+1]];
     }
      return sorted_points;
     }

     /**
      * To avoid recalculate the same index again 
      */ 
      // From Internet web "Mkyong.com" 
      private static boolean contains(final int[] array, final int v) {
        boolean result = false;

        for(int i : array){
             if(i == v){
                result = true;
                break;
            }
        }
        
        return result;
     }


     /** 
     * Giving the User the opportunity to choose the Number of Obstacles
     * The Obstacles will be randomly generated (Color, Position , Distance) 
     * @ArrayList: If you don't know how many items are going to be held in your array then use ArrayList
     * An ArrayList is a dynamic data structure, meaning items can be added and removed from the list
     */
      public void createObstacleList(){
       ArrayList<Rectangle> obstacles= new ArrayList<>(); //create a new ArrayList object
       System.out.println("How many obstacles do you want?");
       Scanner scanner= new Scanner(System.in); // read the Input form the user
       int obstackesCount = 0;
       try {
       obstackesCount = scanner.nextInt(); // storing the Input in the Variable obstackesCount
         if (obstackesCount < 0){
            System.out.println("Please enter a Number greater than or equal to 0");
            System.out.println("=====================================");
            createObstacleList();
            return;
            }
        }       
          catch(InputMismatchException e){
            
            System.out.println("Please Enter only an Integer Number");
            System.out.println("=====================================");
            createObstacleList();
            return;   //to stop the Methode
        }
        
        // With the for loop we Generate the obstacles randomly until the Numbers of obstacles done.
        for (int i=0 ; i <obstackesCount ; i++){
           Rectangle rectangle= new Rectangle(); // Produce an new object with type Rectangle
               
              // check if there are 50 consecutive overlapping rectangles then break the loop and dont Generate anymore
           if (stop == 50){
                 break;
                }
           int x = randomNumber(0,WIDE-1);  
           int y = randomNumber(0,LONG-1);
                      // Don't allow to drow Obtcalse in the Robot Radiuas at Position (0,0)
           if ( x <= robot.getDiameter() && y <= robot.getDiameter()){
              x = robot.getDiameter()+1;  
              y = robot.getDiameter()+1; 
                }  
                
           Point poistion= new Point(x,y);   // declare the Position of each obstacles
           rectangle.setPosition(poistion);
           int width= randomNumber(1,100);
           int length= randomNumber(1,100);  // Calling the Method int randomNumber
           rectangle.setWidth(width);      // Calling the Method from Class Rectangle
           rectangle.setLength(length);      // Why set Method because it will Generate Numbers thats mean a Change Method 
           rectangle.setDescription("Rectangle " + i+1); // How many obstackes will be Produce
       
           // to Generate the obstacles only in the Range of the Play Field
            if (x+width > WIDE){
             width = WIDE - x;
            }
            if (y+length > LONG){
             length = LONG  - y;
            }
      
           Color color= randomColor();    // Calling the Method Color randomNumber
           rectangle.setColor(color);
           
           //Calling the Method overlaps from Class Rectangle and let each Item in Arraylist to Check if overlapping or not
           // Every obstacles is Storing in ArryList and his type a Rectangle so i use it as Parmeters
            boolean save = true;
            for (int j=0 ; j<obstacles.size() ; j++){
              
                  if(rectangle.overlaps(obstacles.get(j)) == true){
                       save =false;
                       stop++;
                       break;
                         }
                     else {
                        stop = 0;
                            }
            }
       
            if(save){
             obstacles.add(rectangle);    // Storing the rectangles in the Arraylist
            } 
         }   
         draw(obstacles, null);   //Calling the Method for Drawing the sign area with the obstacles and the Robot
         avoidObstacles(obstacles);
       }
    
    /**
     * randomly Numbers will be Generate
     */
     private int randomNumber(int from, int to){
       double randomNumbers = Math.random(); // Generate randomly Numbers between 0-0.99999
       randomNumbers = randomNumbers*to; // Change the Range of our Numbers Generator 0-to
       randomNumbers = randomNumbers+from; // Increase the Range of our Numbers Generator from-to
       int rn = (int) randomNumbers; // Cast the number to an Integer
        return rn;
    }
    
    /**
     * randomly Colors will be Generate, 
     */
     //from internet web "Stackoverflow"
     private Color randomColor(){
      int r = randomGenerator.nextInt(255); // declar the 3 Basics Colors 
      int g = randomGenerator.nextInt(255);
      int b = randomGenerator.nextInt(255);
      Color randomColor = new Color(r, g, b); // Generate randomly Colors
      return randomColor;
    }
    
    /**
     * Main Method, with it can the User a solving tasks writing until he/she Input "End".
     * A Main Method, its like a new class so However you find it in the "Spielfeld class", but you can't call the methodes without 
     * declaring a new Object from class PlayingField
     */
     public static void main(String[] args){
        
        PlayingField playField= new PlayingField();
        System.out.println("1-Press A if you want to see me driving from Point to other");
        System.out.println("2-Press B if you want to see me driving around the Obstacles");
        System.out.println("3-Press C if you want to ask me Questions");
        System.out.println("Press End if you want to Quit the Program");
        Scanner scanner= new Scanner(System.in); // read the Input form the user

        for (String word = scanner.nextLine(); !word.toUpperCase().equals("END"); word = scanner.nextLine()) {
            
           if (word.toUpperCase().equals("END")  ){
                    break;
                    }
            if (word.toUpperCase().equals("A")){
               Point[] points = playField.EnterPoints(); // calling the Method EnterPoints()
               points = playField.poiSortieren(points);     // calling the Method poiSortiere()
               playField.driveAroundPoints(points);
            }
             else if (word.toUpperCase().equals("B")){
            
            playField.createObstacleList();
           }
            else if (word.toUpperCase().equals("C")){
            playField.robot.speechRecognition();             // calling the Method speechRecognition()
                        }
          System.out.println("1-Press A if you want to see me driving from Point to other");
          System.out.println("2-Press B if you want to see me driving around the Obstacles");
          System.out.println("3-Press C if you want to ask me Questions");
          System.out.println("Press End if you want to Quit the Program");
        }
        
    }
    
    /**
     * Drawing the sign area with the obstacles and the Robot
     */
    public void draw(ArrayList<Rectangle> obstacles , Point[] points){
      canvas.draw(obstacles ,robot, points);
    }
    
    /**
     * The Robot will move and a void the Obstcals until he arrive the one or both Edges or stick between 2 Obstacles.
     * The Robot need not to search another way to go down
     * Positive x values extend to the right, positive y values to the bottom
     */
    public void avoidObstacles(ArrayList<Rectangle> obstacles){
     int dx = 0;
     int dy = 0;
     int step = 1;                     
     robot.reset();  // reset (Positon,Color) if we reenter
     boolean canMove = true;
      while (canMove) {
          if(robot.onWall(WIDE, LONG)){
             canMove = false;
             robot.setColor(Color.GREEN);   
            }
            else { 
          dx = 0;
          dy = 0; 
         if(robot.canMoveRight(step,obstacles)){
             dx = step; 
            }
          if(robot.canMoveDown(step,obstacles)){
             dy = step; 
          }
          
          if(dx == 0 && dy == 0){
             canMove = false;
             robot.setColor(Color.RED);   // Change the Robot Color to red
          } 
           else {
                robot.moveAround(dx,dy);   // Moving the Robot at dx,dy
                canvas.waite(10);        // Movement speed
            }
        }
          canvas.repaint();              // When the Robote move meaning we should always redraw it 
     }
   }
    
    /**
     * The Robot will drive to the nearest Point
     */
    public void driveAroundPoints(Point[] points){
       robot.reset();
       draw(null, points);    
       for (Point point : points){
           robot.moveTo(point);
           canvas.waite(70);   //stay at Point for short Time
       }
   }
}
