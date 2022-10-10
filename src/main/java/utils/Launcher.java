package utils;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.lwjgl.*;

import org.apache.maven.model.*;
import org.apache.log4j.*;

import windows.MainWindow;

import java.io.FileReader;

public class Launcher {

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    public static final String NAME = "EdgeN";

    public static String version = new String();
    public static String id = new String();
    public static String groupId = new String();
    public static String artifactId = new String();

    public static MainWindow window;

    public static void main(String[] args) {
        ConsoleAppender appender = new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
        LOGGER.addAppender(appender);

        Launch();
    }

    private static void Launch() {
        if(!readPom()) return;

        LOGGER.log(Level.INFO,NAME + " " + version + " was successfully launched with LWJGL " + Version.getVersion());

        init();

        window.run();
    }

    private static boolean init(){
        window = new MainWindow(1000, 600, "EdgeN");

        return true;
    }

    private static boolean readPom(){
        Model model = new Model();
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            model = reader.read( new FileReader("pom.xml"));
        }
        catch (Exception e){
            LOGGER.log(Level.WARN, e.getMessage());
            return false;
        }

        version = model.getVersion();
        id = model.getId();
        groupId = model.getGroupId();;
        artifactId = model.getArtifactId();

        return true;
    }
}
