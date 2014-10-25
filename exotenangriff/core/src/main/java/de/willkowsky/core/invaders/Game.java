package de.willkowsky.core.invaders;

import com.badlogic.gdx.*;

public class Game extends com.badlogic.gdx.Game {

    private boolean continousRendering = false;
    private long lastPauseTimestamp;

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void create() {
        setScreen(new GameScreen());

        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(new GameInputAdapter());
    }

    class GameInputAdapter extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.ESCAPE:
                case Input.Keys.Q:
                    System.exit(0);
                case Input.Keys.P:
                    if ((System
                        .currentTimeMillis() - lastPauseTimestamp) > 200) {
                        // Pausieren null alle 200ms erlauben
                        Gdx.graphics.setContinuousRendering(continousRendering);
                        continousRendering = !continousRendering;
                        lastPauseTimestamp = System.currentTimeMillis();
                    }
                    break;
            }

            return super.keyDown(keycode);
        }
    }
}
