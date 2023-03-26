package ca.mcmaster.cas.se2aa4.a3.island.Biomes;

import java.awt.geom.Path2D;

import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.PathParser;




public class Whittaker {
    

    /**
     * Extracts a biome name from an svg Whittaker diagram given a humidity and temperature value
     * @param humidity the humidity of a given coordinate 
     * @param temperature the temperature of a given coordinate 
     * @return Biome name 
     */
    public String evaluateBiome(double humidity, double temperature){


        SVGParser p = new SVGParser("biome.svg"); 

        for(BiomeData bd : p.getBiomeMap()){
                 
        Path2D path = createPathFromSvgString(bd.path);
        path.setWindingRule(Path2D.WIND_EVEN_ODD);
        if(path.contains(humidity,temperature)) return bd.name; 
        }

        return "Invalid biome";
    }


    private static Path2D createPathFromSvgString(String svgPathString) {
        PathParser pathParser = new PathParser();
        AWTPathProducer pathProducer = new AWTPathProducer();
        pathParser.setPathHandler(pathProducer);
        pathParser.parse(svgPathString);
        Path2D path = new Path2D.Double();
        path.append(pathProducer.getShape(), true);
        return path;
    }






}
