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
        options.addOption("square_size", true, "specify frequency of grid points"); 
        options.addOption("lloyd", true, "number of lloyd relaxation steps"); 
        options.addOption("offset", true, "control randomness of gridpoints");
        
        options.addOption("grid", false, "set a grid based mesh type"); 



        int width =500; 
        int height = 500; 
        int square_size = 40; 
        int lloyd = 3; 
        int offset = 15; 
        boolean irregular = true; 


        CommandLineParser parser = new DefaultParser(); 

        try{
            CommandLine cmd = parser.parse(options, args); 

             width = Integer.parseInt(cmd.getOptionValue("width", "500")); 
             height = Integer.parseInt(cmd.getOptionValue("height", "500")); 
             square_size = Integer.parseInt(cmd.getOptionValue("square_size", "40")); 
             lloyd = Integer.parseInt(cmd.getOptionValue("lloyd", "3")); 
             offset = Integer.parseInt(cmd.getOptionValue("offset", "15")); 
            irregular = !cmd.hasOption("grid"); 




            

            
        }catch(Exception e){
            System.out.println("Parsing error: " + e);
        }
        




        DotGen generator = new DotGen();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
