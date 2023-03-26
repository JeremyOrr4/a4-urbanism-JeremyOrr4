package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
/**Abstract class which is to be implemented by specific profiles which provide method for generating elevation**/
public abstract class ElevationProfile {

    abstract double ProduceElevation(double x, double y, double centerX, double centerY);


    
}
