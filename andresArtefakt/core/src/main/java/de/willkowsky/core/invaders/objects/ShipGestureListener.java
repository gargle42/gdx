package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.willkowsky.core.invaders.GameWorld;

public class ShipGestureListener extends GestureDetector.GestureAdapter {

    private Ship ship;

    public ShipGestureListener(Ship ship) {
        this.ship = ship;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Vector3 screenCoordinates = new Vector3(x, y, 0f);

        Vector3 worldCoordinates = GameWorld.CAMERA.getCamera()
            .unproject(screenCoordinates);

        ship.moveShip(worldCoordinates);


        return false;
    }

}
