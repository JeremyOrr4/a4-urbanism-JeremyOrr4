package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;

import java.util.Random;

public class Elevation{

    public String Elevation() {
        Random random = new Random();

        return ""+random.nextInt(10);
    }
}
