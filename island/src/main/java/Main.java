
import ca.mcmaster.cas.se2aa4.a3.island.EntryPoint;
import ca.mcmaster.cas.se2aa4.a3.island.Configuration;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;




import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       
     
        
        Configuration config = new Configuration(args);

       
        System.out.println(config.export("k") );
        int lakes = 1;
        if (config.export().containsKey(Configuration.LAKES)){
            lakes = Integer.parseInt(config.export(Configuration.LAKES));
        }
        Structs.Mesh exported = new MeshFactory().read(config.input());

        exported = EntryPoint.meshTest(exported,lakes);

        new MeshFactory().write(exported, config.export(Configuration.INPUT));
    }
}
