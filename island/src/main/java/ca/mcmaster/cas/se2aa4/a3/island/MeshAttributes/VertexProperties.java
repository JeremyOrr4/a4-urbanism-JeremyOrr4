package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class VertexProperties {
    public Structs.Mesh SetVertexProperties(Structs.Mesh aMesh){
        List<Structs.Vertex> NewVertices = new ArrayList<>();
        Elevation ElevSetter = new Elevation();
        for (Structs.Vertex v: aMesh.getVerticesList()){
            Structs.Property Elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(ElevSetter.Elevation()).build();
            Structs.Property Thickness = Structs.Property.newBuilder().setKey("Thickness").setValue(Elevation.getValue()).build();
            NewVertices.add(Structs.Vertex.newBuilder(v).addProperties(Elevation).build());
        }
        return Structs.Mesh.newBuilder().addAllVertices(NewVertices).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(aMesh.getPolygonsList()).build();
    }
}
