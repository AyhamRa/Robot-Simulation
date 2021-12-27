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
    public final static int BREIT = 1000;
    public final static int LANG = 1000;
    private static Random zufallsgenerator = new Random();
    int stop = 0;
    private Robot roboter;
    private Canvas canvas;
    

    /**
     * Constructor for objects of class Spielfeld
     * @leinwand, a Change requierd because it's now a Singleton Pattern, so we need to use The Methode "Leinwand.getinstnas()".
     */
    public PlayingField()
    {
       roboter = new Robot();
       canvas = Canvas.getInstance(); 
    }
    
    /**
     * This Method give The user the possibility to input numbers of Points and the Dimensionen (x,y).
     * @Continue: The continue statement breaks one iteration (in the loop), if a specified condition occurs,
     * and continues with the next iteration in the loop.
     */
    public Point[] punkteEingeben(){
    
    Scanner scanner= new Scanner(System.in);  // read the Input form the user
    int pointsCount;
    System.out.println("How many Points do you want?");
    try{ 
    pointsCount= scanner.nextInt(); // storing the Input in the Variable pointsCount
    if (pointsCount < 2){
    System.out.println("Number of Points can't be less than 2");
    System.out.println("=====================================");
    return punkteEingeben();
               }
     }
    catch(InputMismatchException e){
        System.out.println("Please Enter only an Integer Number");
        System.out.println("=====================================");
        return punkteEingeben();
    }
    
    System.out.println("Points Number is: " + pointsCount);
    System.out.println("=====================================");
    
    Point[] punkte= new Point[pointsCount]; //a declaration the Arry
    
    punkte[0] = new Point(); // The 1 Point always should be (0,0) so we call the first Constructor from 
                             // Class Punkt.
    System.out.println("Point 1 is always (0,0)");
    System.out.println("=====================================");
     for (int i=1 ; i < punkte.length; i++){
        System.out.println("Please enter the coordinates X of the Point "+(i+1));
        try{
        int x = scanner.nextInt();  // read the Input form the user
        if (x < 0 || x > BREIT) {
        System.out.println("your input lies outside the boundaries of allowed values. Please choose a valid number from 1 to a 1000");
        i--;
        continue;
        }
        System.out.println("Please enter the coordinates Y of the Point "+(i+1));
        int y = scanner.nextInt();
         if (y < 0 || y > LANG) {
        System.out.println("your input lies outside the boundaries of allowed values. Please choose a valid number from 1 to a 1000");
        i--;
        continue;
         }
        if (x <= roboter.getDiameter() && y <= roboter.getDiameter()) {
        System.out.println("At least One of the coordinates should be greater than The Robot Radius here 50 ");
        i--;
        continue; }
        Point punkt= new Point(x,y); // making new  object from the Second Constructor from Class Punkt
        punkte[i]= punkt; // Storing the object punkt in the Arry
        System.out.println("Point "+i+" :"+" x= "+x+ ", y = "+y);
        }  catch(InputMismatchException e){
            i--; // Why this? because he in the  normal way he will give us the Error then add 1, with this he 
                 // will add 1 and sub 1 so we get back to the Correct  Point
            System.out.println("Please Enter only an Integer Number");
            scanner= new Scanner(System.in); // without this the loop will be infinitif
        }
     }
    return punkte; 

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
        double minAbstand = 0; // we gave the variable here to rest the minAbstand every Time in this loop
        int index = sorted_index[i];
        for (int j=1 ; j<size ; j++){
            if(contains(sorted_index, j)) continue;
            
            double abstand = poi[index].giveDistance(poi[j]);
            if(abstand <= minAbstand || minAbstand == 0){
              minAbstand = abstand;
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
      public void hindernislisteErzeugen(){
       ArrayList<Rectangle> obstacles= new ArrayList<>(); //create a new ArrayList object
       System.out.println("How many obstacles do you want?");
       Scanner scanner= new Scanner(System.in); // read the Input form the user
       int obstackesCount = 0;
       try {
       obstackesCount = scanner.nextInt(); // storing the Input in the Variable obstackesCount
         if (obstackesCount < 0){
            System.out.println("Please enter a Number greater than or equal to 0");
            System.out.println("=====================================");
            hindernislisteErzeugen();
            return;
            }
        }       
          catch(InputMismatchException e){
            
            System.out.println("Please Enter only an Integer Number");
            System.out.println("=====================================");
            hindernislisteErzeugen();
            return;   //to stop the Methode
        }
        
        // With the for loop we Generate the obstacles randomly until the Numbers of obstacles done.
        for (int i=0 ; i <obstackesCount ; i++){
           Rectangle rechteck= new Rectangle(); // Produce an new object with type Rechteck
               
              // check if there are 50 consecutive overlapping rectangles then break the loop and dont Generate anymore
            if (stop == 50){
                 break;
                }
           int x = zufallszahl(0,BREIT-1);  
           int y = zufallszahl(0,LANG-1);
                      // Don't allow to drow Obtcalse in the Robot Radiuas at Position (0,0)
           if ( x <= roboter.getDiameter() && y <= roboter.getDiameter()){
              x = roboter.getDiameter()+1;  
              y = roboter.getDiameter()+1; 
                }  
                
           Point poistion= new Point(x,y);   // declare the Position of each obstacles
           rechteck.setPosition(poistion);
           int breite= zufallszahl(1,100);
           int laenge= zufallszahl(1,100);  // Calling the Method int zufallszahl
           rechteck.setBreite(breite);      // Calling the Method from Class Rechteck
           rechteck.setLaenge(laenge);      // Why set Method because it will Generate Numbers thats mean a Change Method 
           rechteck.setBezeichnung("Rechteck " + i+1); // How many obstackes will be Produce
       
           // to Generate the obstacles only in the Range of the Play Field
            if (x+breite > BREIT){
             breite = BREIT - x;
            }
            if (y+laenge > LANG){
             laenge = LANG  - y;
            }
      
           Color color= zufallsfarbe();    // Calling the Method Color zufallszahl
           rechteck.setFarbe(color);
           
           //Calling the Method ueberlappt from Class Rechteck and let each Item in Arraylist to Check if overlapping or not
           // Every obstacles is Storing in ArryList and he is a type Rechteck so i use it as Parmeters
            boolean save = true;
            for (int j=0 ; j<obstacles.size() ; j++){
              
                   if(rechteck.ueberlappt(obstacles.get(j)) == true){
                       save =false;
                       stop++;
                       break;
                         }
                     else {
                        stop = 0;
                            }
            }
       
            if(save){
             obstacles.add(rechteck);    // Storing the rectangles in the Arraylist
            } 
         }   
         zeichnen(obstacles, null);   //Calling the Method for Drawing the sign area with the obstacles and the Robot
         hindernisse_umfahren(obstacles);
       }
    
    /**
     * randomly Numbers will be Generate
     */
     private int zufallszahl(int von, int bis){
       double randomNumbers = Math.random(); // Generate randomly Numbers between 0-0.99999
       randomNumbers = randomNumbers*bis; // Change the Range of our Numbers Generator 0-bis
       randomNumbers = randomNumbers+von; // Increase the Range of our Numbers Generator von-bis
       int rn = (int) randomNumbers; // Cast the number to an Integer
        return rn;
    }
    
    /**
     * randomly Colors will be Generate, 
     */
     //from internet web "Stackoverflow"
     private Color zufallsfarbe(){
      int r = zufallsgenerator.nextInt(255); // declar the 3 Basics Colors 
      int g = zufallsgenerator.nextInt(255);
      int b = zufallsgenerator.nextInt(255);
      Color randomColor = new Color(r, g, b); // Generate randomly Colors
      return randomColor;
    }
    
    /**
     * Main Method, with it can the User a solving tasks writing until he/she Input "End".
     * A Main Method, its like a new class so However you find it in the "Spielfeld class", but you can't call the methodes without 
     * declaring a new Object from class Speilfeld
     */
     public static void main(String[] args){
        
        PlayingField spielfeld= new PlayingField();
        System.out.println("1-Press A if you want to see me driving from Point to other");
        System.out.println("2-Press B if you want to see me driving around the Obstacles");
        System.out.println("3-Press C if you want to ask me Questions");
        System.out.println("Press End if you want to Quit the Program");
        Scanner scanner= new Scanner(System.in); // read the Input form the user

        for (String word = scanner.nextLine(); !word.toUpperCase().equals("END"); word = scanner.nextLine()) {
            
           if ( word.toUpperCase().equals("END")  ){
                    break;
                    }
            if (word.toUpperCase().equals("A")){
               Point[] punkte = spielfeld.punkteEingeben(); // calling the Method punkteEingeben()
               punkte = spielfeld.poiSortieren(punkte);     // calling the Method poiSortiere()
               spielfeld.punkte_umfahren(punkte);
            }
             else if (word.toUpperCase().equals("B")){
            
            spielfeld.hindernislisteErzeugen();
           }
            else if (word.toUpperCase().equals("C")){
            spielfeld.roboter.spracherkennung();             // calling the Method spracherkennung()
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
     public void zeichnen(ArrayList<Rectangle> hindernisse , Point[] punkte){
      canvas.zeichnen(hindernisse ,roboter, punkte);
    }
    
    /**
     * The Robot will move and a void the Obstcals until he arrive the one or both Edges or stick between 2 Obstacles.
     * The Robot need not to search another way to go down
     * Positive x values extend to the right, positive y values to the bottom
     */
    public void hindernisse_umfahren(ArrayList<Rectangle> obstacles){
     int dx = 0;
     int dy = 0;
     int step = 1;                     
     roboter.reset();  // reset (Positon,Color) if we reenter
     boolean canMove = true;
      while (canMove) {
          if(roboter.anWand(BREIT, LANG)){
             canMove = false;
             roboter.setFarbe(Color.GREEN);   
            }
            else { 
          dx = 0;
          dy = 0; 
          if(roboter.canMoveRight(step,obstacles)){
             dx = step; 
            }
          if(roboter.canMoveDown(step,obstacles)){
             dy = step; 
          }
          
          if(dx == 0 && dy == 0){
             canMove = false;
             roboter.setFarbe(Color.RED);   // Change the Robot Color to red
          } 
           else {
                roboter.bewegeUm(dx,dy);   // Moving the Robot at dx,dy
                canvas.waite(10);        // Movement speed
            }
        }
          canvas.repaint();              // When the Robote move meaning we should always redraw it 
     }
   }
    
    /**
     * The Robot will drive to the nearest Point
     */
    public void punkte_umfahren(Point[] punkte){
       roboter.reset();
       zeichnen(null, punkte);    
       for (Point punkt : punkte){
           roboter.moveTo(punkt);
           canvas.waite(70);   //stay at Point for short Time
       }
   }
}
