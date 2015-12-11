import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Application {
    
    private static final String DEFAULT_MAX_WIDTH = "72";
    private static final String DEFAULT_ASPECT_RATIO = "0.5";

    public static void main(String[] args) throws ParseException, FileNotFoundException, UnsupportedEncodingException {
        Options options = new Options();
        options.addOption("M", "max-width", true, "maximum width in characters of banner (default is 72)");
        options.addOption("d", "dark", false, "whether to invert image for a dark background. (default is false)");
        options.addOption("r", "aspect-ratio", true, "correction to makes sure height is correct to accomodate the fact that fonts are taller than they are wide. (default is 0.5)");
        options.addOption("o", "output", true, "output file path (default is ./banner.txt)");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);
        
        HelpFormatter formatter = new HelpFormatter();
        if (cmd.getArgs().length == 0) {
            formatter.printHelp("java -jar image2springbanner.jar", "Create a Spring Boot banner from an image", options, null, true);
            return;
        }
        
        ImageBanner banner = new ImageBanner(new File(cmd.getArgs()[0]));
        PrintWriter writer = new PrintWriter(cmd.getOptionValue("output", "banner.txt"), "UTF-8");
        String bannerStr = banner.printBanner(
                Integer.parseInt(cmd.getOptionValue("M", DEFAULT_MAX_WIDTH)),
                Double.parseDouble(cmd.getOptionValue("r", DEFAULT_ASPECT_RATIO)), 
                cmd.hasOption("d"));
        writer.println( bannerStr );
        writer.close();
        
        System.out.println(bannerStr
                .replace("${AnsiColor.DEFAULT}", "\u001B[39m")
                .replace("${AnsiColor.BLACK}", "\u001B[30m")
                .replace("${AnsiColor.RED}", "\u001B[31m")
                .replace("${AnsiColor.GREEN}", "\u001B[32m")
                .replace("${AnsiColor.YELLOW}", "\u001B[33m")
                .replace("${AnsiColor.BLUE}", "\u001B[34m")
                .replace("${AnsiColor.MAGENTA}", "\u001B[35m")
                .replace("${AnsiColor.CYAN}", "\u001B[36m")
                .replace("${AnsiColor.WHITE}", "\u001B[37m")
                .replace("${AnsiColor.BRIGHT_BLACK}", "\u001B[90m")
                .replace("${AnsiColor.BRIGHT_RED}", "\u001B[91m")
                .replace("${AnsiColor.BRIGHT_GREEN}", "\u001B[92m")
                .replace("${AnsiColor.BRIGHT_YELLOW}", "\u001B[93m")
                .replace("${AnsiColor.BRIGHT_BLUE}", "\u001B[94m")
                .replace("${AnsiColor.BRIGHT_MAGENTA}", "\u001B[95m")
                .replace("${AnsiColor.BRIGHT_CYAN}", "\u001B[96m")
                .replace("${AnsiColor.BRIGHT_WHITE}", "\u001B[97m")
                .replace("${AnsiBackground.BLACK}", "\u001B[40m")
                .replace("${AnsiBackground.DEFAULT}", "\u001B[49m")
        );
    }

}
