package ca.mcmaster.cas.se2aa4.a3.island;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    public static final String INPUT = "i";

    public static final String LAKES = "lakes";

    public static final String RIVER = "rivers";

    public static final String VISUAL = "v";

    public static final String LAGOON = "l";

    public static final String PROFILE = "p";

    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
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

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file (SVG)"));
        options.addOption(new Option(LAKES, true, "Number of Lakes"));
        options.addOption(new Option(RIVER, true, "Number of rivers"));
        options.addOption(new Option(VISUAL, true, "Visualization type"));
        options.addOption(new Option(LAGOON, false, "Lagoon option"));
        options.addOption(new Option(PROFILE, true, "Elevation Profile"));
        return options;
    }

}