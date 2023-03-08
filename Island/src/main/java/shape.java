import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import java.util.ArrayList;

public interface shape {
    public ArrayList<Structs.Polygon> shapeFunction(ArrayList<Structs.Polygon> polys,ArrayList<Structs.Vertex> vertices);
    public Structs.Polygon checkBeach(Structs.Polygon p,ArrayList<Structs.Polygon> polys);
}
