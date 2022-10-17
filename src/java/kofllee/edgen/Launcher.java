package kofllee.edgen;

import kofllee.edgen.core.Engine;
import kofllee.edgen.core.VariableTimestepEngine;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.lwjgl.*;

import org.apache.maven.model.*;
import org.apache.log4j.*;

import java.io.FileReader;

public class Launcher {

    public static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    public static final String NAME = "EdgeN";

    public static String version;
    public static String id;
    public static String groupId;
    public static String artifactId;

    public static Engine engine;

    public static void main(String[] args) {
        ConsoleAppender appender = new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
        LOGGER.addAppender(appender);

        Launch();
    }

    private static void Launch() {
        readPom();

        LOGGER.log(Level.INFO,NAME + " " + version + " was successfully launched with LWJGL " + Version.getVersion());

        init();
        engine.start();
    }

    private static void init(){
        engine = new VariableTimestepEngine();
    }

    private static void readPom(){
        Model model = new Model();
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            model = reader.read( new FileReader("pom.xml"));
        }
        catch (Exception e){
            LOGGER.log(Level.WARN, e);
        }

        version = model.getVersion();
        id = model.getId();
        groupId = model.getGroupId();;
        artifactId = model.getArtifactId();
    }
}
