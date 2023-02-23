import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Main {

    public static void main(String[] args) throws IOException {


        Options options = new Options(); 
        options.addOption("width", true,  "specify output width"); 
        options.addOption("height", true,  "specify output height"); 


        CommandLineParser parser = new DefaultParser(); 

        try{
            CommandLine cmd = parser.parse(options, args); 

            
        }catch(Exception e){
            System.out.println("Parsing error: " + e);
        }
        


        DotGen generator = new DotGen();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
