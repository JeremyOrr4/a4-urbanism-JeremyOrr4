package ca.mcmaster.cas.se2aa4.a3.island.Elevation;


/**Elevation increases as you increase diagonally downwards**/
public class ExampleElevationProfile extends ElevationProfile{
    double ProduceElevation(double x, double y, double centerX, double centerY){
        return (x+y)/3;
    }
   
    
}
