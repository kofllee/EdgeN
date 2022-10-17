package kofllee.edgen.core;

public class VariableTimestepEngine extends Engine{

    @Override
    public void gameLoop() {
        float delta;

        while(running){
            if(window.isClosing())
                running = false;

            delta = timer.getDelta();

            update(delta);

            timer.updateFps();
            timer.update();

            window.update();

            if (!window.isVSyncEnabled()) {
                sync(FPS);
            }
        }
    }
}
