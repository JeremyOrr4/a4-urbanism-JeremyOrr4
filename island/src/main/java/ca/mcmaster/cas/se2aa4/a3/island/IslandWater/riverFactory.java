package ca.mcmaster.cas.se2aa4.a3.island.IslandWater;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class riverFactory {
    public Structs.Mesh riverGenerator(Structs.Mesh aMesh){
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Structs.Property color = Structs.Property.newBuilder().setValue("rgb_color").setValue("0,0,255").build();
            s = Structs.Segment.newBuilder(s).addProperties(color).build();
        }
    }
}
