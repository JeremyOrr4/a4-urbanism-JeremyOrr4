package ca.mcmaster.cas.se2aa4.a3.island.Shapes;

public class Square implements BoundedShape {



    double topLeftX; 
    double topLeftY; 
    double w; 
    double h; 

    public Square(double centerX, double centerY, double w, double h){

        this.topLeftX = centerX-w/2; 
        this.topLeftY=centerY-h/2; 
        this.h=h; 
        this.w=w; 

    }


    public boolean contains(double x, double y){

        boolean containsX = (x>topLeftX && x<(topLeftX+w)); 
        boolean containsY = (y>topLeftY && y<(topLeftY+h)); 

        return containsX && containsY; 



    }
    
}
