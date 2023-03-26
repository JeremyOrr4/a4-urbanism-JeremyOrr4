package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.Cell.Point;

public class ExampleElevationProfile extends ElevationProfile{


    double setVertexElevation(double x, double y, double centerX, double centerY){

    
        return (x+y)/3;



    }



    @Override
    double getElevation(Point point) {
       return (point.x + point.y)/3; 
    }
   
    
}
