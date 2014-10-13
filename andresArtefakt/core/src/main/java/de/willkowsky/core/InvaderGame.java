package de.willkowsky.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class InvaderGame extends Game {

    @Override
    public void render() {
        Screen currentScreen = getScreen();
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void create () {
        setScreen(new InvaderGameScreen());
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }
}
