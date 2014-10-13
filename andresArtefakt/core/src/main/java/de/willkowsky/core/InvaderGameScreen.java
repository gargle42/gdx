package de.willkowsky.core;

public class InvaderGameScreen extends AbstractGameScreen {

    private final AndresGame game;

    public InvaderGameScreen() {
        game = new AndresGame();
        game.create();
    }

    @Override
    public void render(float delta) {
        game.render(delta);
    }

}
