package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.Cell.Point;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;

public class VolcanoProfile extends ElevationProfile{


    double ProduceElevation(double x, double y, double centerX, double centerY){

        Circle circle = new Circle(1920/2, 1080/2, 500); 

        int d = (int) Math.pow(Circle.distance(x,y,centerX,centerY),1.1);


        return Math.max(0, 800-d);



    }
    
    double getElevation(Point point) {


        return 0; 
     }
}

