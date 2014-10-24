package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import de.willkowsky.core.invaders.GameWorld;

public class GameWorldGestureListener extends GestureDetector.GestureAdapter {

    private GameWorld gameWorld;

    public GameWorldGestureListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Vector3 screenCoordinates = new Vector3(x, y, 0f);

        Vector3 worldCoordinates = GameWorld.camera.getCamera()
            .unproject(screenCoordinates);

        gameWorld.replaceShip();

        return true;
    }

}
