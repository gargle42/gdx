package de.willkowsky.core.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Game extends com.badlogic.gdx.Game {

    @Override
    public void render() {
        checkForExit();
        com.badlogic.gdx.Screen currentScreen = getScreen();
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    private void checkForExit() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }
    }

    @Override
    public com.badlogic.gdx.Screen getScreen() {
        return super.getScreen();
    }

    @Override
    public void create () {
        setScreen(new GameScreen());
    }
}
