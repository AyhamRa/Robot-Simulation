import java.awt.Color;

/**
 * Class "Kreis" is a subClass of the SuperCalss "Figur"
 * This Class will contains the Attributes and Methods only for the Robot
 */
public class Circle extends Figur
{
    // instance variables
    int d;
    /**
     * Constructor for objects of class Circle
     */
    public Circle(int d ,Color color)
    {
        // initialise instance variables
        super(new Point(),color);
        this.d= d;
    }
    
    public int getDiameter(){
     return d;
    }
    
    public void  setDiameter(int d){
    this.d = d;
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
    return PlayingField.WIDE-d;
    }
    
    public int maxY(){
    return PlayingField.LONG-d;
    }
}
