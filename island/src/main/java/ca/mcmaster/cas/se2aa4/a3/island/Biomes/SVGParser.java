package ca.mcmaster.cas.se2aa4.a3.island.Biomes;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGParser {


    List<BiomeData> biomeData = new ArrayList<BiomeData>();

    SVGParser(String filePath) {

        try {

            File file = new File("biome.svg");
            Scanner scanner = new Scanner(file);

            Pattern viewBoxPattern = Pattern.compile("viewBox=\"(.*?)\"");
            String viewBox = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = viewBoxPattern.matcher(line);
                if (matcher.find()) {
                    viewBox = matcher.group(1);
                    break;
                }
            }

            scanner.close();

           

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.contains("<path")) {

                    String biomeName = line.substring(line.indexOf("id=\"") + 4,
                            line.indexOf("\"", line.indexOf("id=\"") + 4));

                    int dIndex = line.indexOf(" d=\"");

                    String path = line.substring(dIndex + 4, line.indexOf("\"", dIndex + 4));
                    // System.out.println(biomeName);
                    // System.out.println(path);

                    biomeData.add(new BiomeData(biomeName, path)); 
                }

            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Could Not parse Whittaker Diagram:" + e);
        }

    }


    public List<BiomeData> getBiomeMap(){


        return this.biomeData; 

    }

}
