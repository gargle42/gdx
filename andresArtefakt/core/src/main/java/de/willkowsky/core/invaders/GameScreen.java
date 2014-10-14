package de.willkowsky.core.invaders;

import de.willkowsky.core.invaders.misc.AbstractGameScreen;

public class GameScreen extends AbstractGameScreen {

    private final GameWorld game;

    public GameScreen() {
        game = new GameWorld();
    }

    @Override
    public void renderScreen(float delta) {
        game.render(delta);
    }

}
