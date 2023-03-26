package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.Cell.Point;

public class ExampleElevationProfile extends ElevationProfile{






    @Override
    double getElevation(Point point) {




     return 255 - Math.pow(Point.distance(point, new Point(1920/2, 1080/2)),1)/3 + Math.random()*29; 
    }



    //DEPRECATED
    double ProduceElevation(double x, double y, double centerX, double centerY){

    
        return (x+y)/3;



    }
   
    
}
