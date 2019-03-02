package ru.mborisov.cli;

import org.apache.commons.cli.*;

public class InstacommentsCommandLineParser {
    private final Option outputFile = Option.builder("o")
                                            .argName("FILENAME")
                                            .longOpt("output-file")
                                            .hasArg(true)
                                            .required()
                                            .desc("output filename")
                                            .build();

    private final Option url = Option.builder("u")
                                     .argName("URL")
                                     .longOpt("url")
                                     .hasArg()
                                     .required()
                                     .desc("Instagram post url to fetch comments from")
                                     .build();

    private final Option help = Option.builder("h")
                                      .longOpt("help")
                                      .hasArg(false)
                                      .desc("display this message")
                                      .build();

    private final HelpFormatter helpFormatter = new HelpFormatter();

    public CommandLine parse(String[] args) {
        Options options = new Options();
        options.addOption(outputFile);
        options.addOption(url);
        options.addOption(help);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                helpFormatter.printHelp("instacomments -o FILENAME -u URL", options);
                System.exit(0);
            }
            return cmd;
        } catch (ParseException e) {
            helpFormatter.printHelp("instacomments -o FILENAME -u URL", options);
            System.exit(0);
        }
        return cmd;
    }
}
