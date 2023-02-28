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


        
        Options options = new Options();  //define options for command line flags with default values
        options.addOption("width", true,  "specify output width"); 
        options.addOption("height", true,  "specify output height"); 
        options.addOption("square_size", true, "specify frequency of grid points"); 
        options.addOption("lloyd", true, "number of lloyd relaxation steps"); 
        options.addOption("offset", true, "control randomness of gridpoints");
        options.addOption("grid", false, "set a grid mesh type");
        options.addOption("debug", false, "Debug Mode?");

        options.addOption("help", false, "display a list of options"); 


        int width =500;
        int height = 500;
        int square_size = 40;
        int lloyd = 3; 
        int offset = 10;
        boolean grid = false;
        boolean debug = false;


        CommandLineParser parser = new DefaultParser(); 

        try{
            CommandLine cmd = parser.parse(options, args); 
            //setting flag indicator and default value
             width = Integer.parseInt(cmd.getOptionValue("width", "520"));
             height = Integer.parseInt(cmd.getOptionValue("height", "520"));
             square_size = Integer.parseInt(cmd.getOptionValue("square_size", "40"));
             lloyd = Integer.parseInt(cmd.getOptionValue("lloyd", "3")); 
             offset = Integer.parseInt(cmd.getOptionValue("offset", "10"));
             grid = cmd.hasOption("irregular");
             debug = cmd.hasOption("debug");
            
             if(cmd.hasOption("help")){
                System.out.println("Options: ");
                System.out.println("-width : specify output width");
                System.out.println("-height : specify output height");
                System.out.println("-square_size : specify frequency of grid points");
                System.out.println("-lloyd : number of lloyd relaxation steps");
                System.out.println("-offset :  control randomness of gridpoints");
                System.out.println("-grid : set an irregular mesh type");
                System.out.println("-debug : debug mode, highlight neighbors and make centroids red");

             }





        }catch(Exception e){
            System.out.println("Parsing error: " + e);
        }
        //send given values to command args class so other classes can access it
        CommArgs.width = width;
        CommArgs.height=height;
        CommArgs.square_size=square_size;
        CommArgs.lloyd=lloyd;
        CommArgs.grid=grid;
        CommArgs.offset = offset;
        CommArgs.debug = debug;




        //generate mesh
        DotGen generator = new DotGen();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        WavefrontGenerator Wave = new WavefrontGenerator(myMesh);
        factory.write(myMesh, args[0]);
    }
    

}
