package ca.mcmaster.cas.se2aa4.a3.island.Elevation;


import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
/**Elevation increases as you approach island middle**/
public class VolcanoProfile extends ElevationProfile{


    double ProduceElevation(double x, double y, double centerX, double centerY){

        Circle circle = new Circle(Extractor.MeshWidth/2, Extractor.MeshHeight /2, Extractor.MinDimension*0.5);

        int d = (int) Math.pow(Circle.distance(x,y,centerX,centerY),1.1);


        return Math.max(0, 800-d);



    }
    
   
}

