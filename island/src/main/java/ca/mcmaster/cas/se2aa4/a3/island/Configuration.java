package ca.mcmaster.cas.se2aa4.a3.island;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;
/**Provides command line options**/
public class Configuration {
    public static final String INPUT = "i";

    public static final String OUTPUT = "o";

    public static final String LAKES = "lakes";

    public static final String RIVER = "rivers";

    public static final String VISUAL = "v";

    public static final String LAGOON = "l";

    public static final String PROFILE = "p";

    public static final String SHAPE = "s";

    public static final String HELP = "help";

    public static final String HEIGHT = "h";

    public static final String WIDTH = "w";

    public static final String SEED = "seed"; 

    public static final String CITIES = "cities"; 


    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
            if (cli.hasOption(HELP)) {
                help();
            }
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }
    public String input() {
        return this.cli.getOptionValue(INPUT);
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for(Option o: cli.getOptions()){
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    public String export(String key) {
        return cli.getOptionValue(key);
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar generator.jar", options());
        System.exit(0);
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file"));
        options.addOption(new Option(OUTPUT, true, "Output file"));
        options.addOption(new Option(LAKES, true, "Number of Lakes"));
        options.addOption(new Option(RIVER, true, "Number of rivers"));
        options.addOption(new Option(VISUAL, true, "Visualization type"));
        options.addOption(new Option(LAGOON, false, "Lagoon option"));
        options.addOption(new Option(PROFILE, true, "Elevation Profile"));
        options.addOption(new Option(SHAPE, true, "Island Shape"));
        options.addOption(new Option(HELP, false, "print help message"));

        options.addOption(new Option(WIDTH, true, "Width of input mesh"));
        options.addOption(new Option(HEIGHT, true, "Height of input mesh"));

        options.addOption(new Option(SEED, true, "Seed controlling randomness"));

        options.addOption(new Option(CITIES, true, "Add Number of Cities"));

        return options;
    }

}
