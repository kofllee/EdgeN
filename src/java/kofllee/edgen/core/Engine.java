package kofllee.edgen.core;

import kofllee.edgen.graphic.Window;
import org.apache.log4j.Level;
import kofllee.edgen.Launcher;
import kofllee.edgen.graphic.MainWindow;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static java.lang.System.getLogger;
import static org.lwjgl.glfw.GLFW.*;

public abstract class Engine {

    public static final int FPS = 60;

    private GLFWErrorCallback errorCallback;
    protected boolean running;

    protected Window window;
    protected Timer timer;

    public Engine(){
        timer = new Timer();
    }

    public void start(){
        init();
        gameLoop();
        despose();
    }

    public void init() {
        errorCallback = GLFWErrorCallback.createPrint();
        glfwSetErrorCallback(errorCallback);

        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        window = new MainWindow("EdgeN", 640, 480, true, true);

        timer.init();
        running = true;
    }

    public void despose() {
        window.destroy();

        glfwTerminate();
        errorCallback.free();
    }

    public abstract void gameLoop();

    public void sync(int fps){
        double lastLoopTime = timer.getLastLoopTime();
        double now = timer.getTime();
        float targetTime = 1f / fps;

        while (now - lastLoopTime < targetTime){
            Thread.yield();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Launcher.LOGGER.log(Level.WARN, e);
            }

            now = timer.getTime();
        }
    }

    public void update(){

    }
    public void update(float delta) {

    }

    public static boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }
}
