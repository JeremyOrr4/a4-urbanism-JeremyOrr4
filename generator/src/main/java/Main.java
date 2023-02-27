import ca.mcmaster.WavefrontGenerator;
import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import ca.mcmaster.WavefrontGenerator;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException {


        
        Options options = new Options(); 
        options.addOption("width", true,  "specify output width"); 
        options.addOption("height", true,  "specify output height"); 
        options.addOption("square_size", true, "specify frequency of grid points"); 
        options.addOption("lloyd", true, "number of lloyd relaxation steps"); 
        options.addOption("offset", true, "control randomness of gridpoints");
        options.addOption("grid", false, "set a grid based mesh type");
        options.addOption("debug", false, "Debug Mode?");


        int width =500;
        int height = 500;
        int square_size = 40;
        int lloyd = 3; 
        int offset = 10;
        boolean irregular = false;
        boolean debug = false;


        CommandLineParser parser = new DefaultParser(); 

        try{
            CommandLine cmd = parser.parse(options, args); 

             width = Integer.parseInt(cmd.getOptionValue("width", "520"));
             height = Integer.parseInt(cmd.getOptionValue("height", "520"));
             square_size = Integer.parseInt(cmd.getOptionValue("square_size", "40"));
             lloyd = Integer.parseInt(cmd.getOptionValue("lloyd", "3")); 
             offset = Integer.parseInt(cmd.getOptionValue("offset", "0"));
             irregular = !cmd.hasOption("grid");
             debug = cmd.hasOption("debug");







        }catch(Exception e){
            System.out.println("Parsing error: " + e);
        }
        CommArgs.width = width;
        CommArgs.height=height;
        CommArgs.square_size=square_size;
        CommArgs.lloyd=lloyd;
        CommArgs.irregular=irregular;
        CommArgs.offset = offset;
        CommArgs.debug = debug;





        DotGen generator = new DotGen();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        WavefrontGenerator Wave = new WavefrontGenerator(myMesh);
        factory.write(myMesh, args[0]);
    }
    

}
