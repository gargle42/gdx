package de.willkowsky.core.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class Game extends com.badlogic.gdx.Game {

    private boolean continousRendering = false;
    private long lastPauseTimestamp;

    @Override
    public void render() {
        checkForExit();
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    private void checkForExit() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input
                .isKeyPressed(Input.Keys.Q)) {
                System.exit(0);
            } else if (Gdx.input.isKeyPressed(Input.Keys.P) && (System
                .currentTimeMillis() - lastPauseTimestamp) > 200) {
                // Pause
                Gdx.graphics.setContinuousRendering(continousRendering);
                continousRendering = !continousRendering;
                lastPauseTimestamp = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
