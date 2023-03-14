package ca.mcmaster.cas.se2aa4.a3.island.Biomes;

import java.awt.geom.Path2D;

import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.PathParser;




public class Whittaker {
    


    public Biome evaluateBiome(double humidity, double temperature){


     
        String svgPathString = "M100 100 L200 100 L150 200 Z";


        SVGParser p = new SVGParser("Biomes/biome.svg"); 
     
        Path2D path = createPathFromSvgString(svgPathString);

        path.setWindingRule(Path2D.WIND_EVEN_ODD);

        System.out.println(path.contains(130, 130));


        return new Biome(); 
    }


    public static Path2D createPathFromSvgString(String svgPathString) {
        PathParser pathParser = new PathParser();
        AWTPathProducer pathProducer = new AWTPathProducer();
        pathParser.setPathHandler(pathProducer);
        pathParser.parse(svgPathString);


        Path2D path = new Path2D.Double();
        path.append(pathProducer.getShape(), true);
        return path; 


    
    }


}
