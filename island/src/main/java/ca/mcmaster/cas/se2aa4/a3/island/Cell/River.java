package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class River extends Edge {

    int size;

    private static final Property riverColor = Property.newBuilder().setKey("rgb_color").setValue("0,0,255").build();
    private static final Property isRiver = Property.newBuilder().setKey("IsRiver").setValue("True").build();

    public River(Segment s, Mesh mesh) {

        super(s, mesh);
    }

    public Segment toSegment() {

        return Segment.newBuilder(this.segment).clearProperties().addProperties(riverColor).addProperties(isRiver)
                .build();

    }

}
