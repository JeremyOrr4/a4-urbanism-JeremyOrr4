package ca.mcmaster.cas.se2aa4.a3.island.Shapes;

public class Circle implements BoundedShape{

double radius; 
double centerX; 
double centerY; 

public Circle(double centerX, double centerY, double radius){
    this.radius = radius; 
    this.centerX = centerX; 
    this.centerY = centerY; 
} 
       
public boolean contains(double x, double y){
    return (distance(x,y,centerX,centerY)<this.radius); 
   
}


public static double distance(double x1, double y1, double x2, double y2){
    return Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2)); 
}


public Circle scale(double factor){
    return new Circle(centerX, centerY, radius*factor); 
}



}
