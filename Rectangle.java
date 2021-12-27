import java.awt.Color;
import java.awt.Graphics;

/**
 * In this Class,rectangles will determine attributes and at end we will finde if two rectangles overlapping 
 * The Class "Rechteck" is a SubCalss from SuperClass "Figur"
 */
public class Rectangle extends Figur
{
     private int breite;
     private int laenge;
     private String bezeichnung;
     
     /**
     * Constructor for objects of class Rechteck
     */
     public Rectangle(){
        super(new Point(), Color.red);
 
     }

     /**
      * Second Constructor
      * @if I have write equals because I combire 2 objekts.
      */
     public Rectangle(Point position, int breite , int laenge , String bezeichnung ,
     Color farbe){
        super(position , farbe );
        this.breite = breite;
        this.laenge = laenge;
        this.bezeichnung = bezeichnung;
        if (farbe.equals(Color.white)){
        System.out.println("Color cant't be White");
        }
        }
     
    /**
     * Methods to read the Attributs
     */   
     public int getBreite(){ 
        return breite;
        }
        
    public int getLaenge(){
        return laenge;
        }
       
        
    public String getBezeichnung(){
        return bezeichnung;
        }   
     
    /**
    * Methods to Change the Attributs
    */   
    public void setBreite(int breite){
        this.breite = breite;
        }   
        
    public void setLaenge(int laenge){
        this.laenge = laenge;
        }   
 
        
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
        } 

    /**
     * You can't change the type of objekt to another in the nomal way, here
     * i call the Method from class Punkt. It's do the work
     */
    public void ausgabeAttribute(){
        position.outputAttributes();
        System.out.println("breite ="+breite+", Laenge ="+ laenge);
        System.out.println("bezeichnung: "+bezeichnung);
        System.out.println(farbe.toString());
    
    }
    /**
     * In this Method i will Find if two rectangles overlapping and return "true" if they overlapping and "false" otherwise.
     * To do this we have to Compare one Point top Right of the first rectangle with bottom Left of the second rectangle.
     * @x1, @y1 I assume that's my first point from the objekt "r" 
     * @x2, @y2 the second Point came from the "Rechteck Class", that's mean it's already an object from Class "Rechteck" 
     * and need not to set the name of object before calling the Method.
     */
    boolean ueberlappt(Rectangle r){
        int x1= r.getPosition().getX();  // I declare a variable so we can write Comfortable and understanding code.
        int x2= getPosition().getX();
        int y1= r.getPosition().getY();
        int y2= getPosition().getY();
            
          // Summe the x1 with the Breite and Compare it with the x2 so we will know if x2 left or right the x1 
          // first time x1 is top Right and second  time if x2 is top Right.
          if (x1+r.getBreite() < x2 || x2+getBreite() < x1){
          return false;
           }
           // Summe the y1 with the Breite and Compare it with the y2 so we will know if y2 left or right the y1 
              if (y1+r.getLaenge() < y2 || y2+getLaenge() < y1 ){
               return false;
               }
                 else 
                   return true;
   

    }
    
    /**
    * @override the Methods from the Abstract Class "Figur"
    */
    
    public int minX(){
    return 0;
    }
    
    public int minY(){
    return 0;
    }
    
    public int maxX(){
    return PlayingField.BREIT-breite;
    }
    
    public int maxY(){
    return PlayingField.LANG-laenge;
    }
    
    /** 
     * Drawing the Obstacles here and call the method in Class "Leinwand"
     */
    public void zeichnen(Graphics g){
    g.setColor(getFarbe());   
    g.fillRect(getPosition().getX() ,getPosition().getY() ,getBreite(),getLaenge());
    }
 
}
