package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;

public class VolcanoProfile extends ElevationProfile{


    double setVertexElevation(double x, double y, double centerX, double centerY){

        Circle circle = new Circle(1920/2, 1080/2, 500); 

        int d = (int) Math.pow(Circle.distance(x,y,centerX,centerY),1.3);


        return Math.max(0, 1000-d);



    }



    
}
