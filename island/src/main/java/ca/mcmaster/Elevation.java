package ca.mcmaster;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;

import java.util.Random;

public class Elevation{

    public double elevation;

    public Elevation() {
        Random random = new Random();

        this.elevation = random.nextDouble(1);
    }
}
