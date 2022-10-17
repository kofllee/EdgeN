package kofllee.edgen.core;

import static org.lwjgl.glfw.GLFW.*;

public class Timer {

    private double lastLoopTime;

    private int fps;
    private int localFps;

    private float timeCount;

    public int getFps() {
        return fps > 0 ? fps : localFps;
    }

    public void init(){
        lastLoopTime = getTime();
    }

    public void update(){
        if(timeCount > 1f) {
            fps = localFps;
            localFps = 0;

            timeCount -= 1f;
        }
    }

    public void updateFps(){
        localFps++;
    }

    public float getDelta(){
        double now = getTime();
        float delta = (float) (now - lastLoopTime);
        lastLoopTime = now;
        timeCount += delta;
        return delta;
    }

    public double getTime(){
        return glfwGetTime();
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
