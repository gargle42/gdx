package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class Ship extends ModelInstance {

    public Ship(Model model) {
        super(model);
        transform.translate(0f, -17f, 0f);

        ShipGestureListener shipGestureListener;
        shipGestureListener = new ShipGestureListener(this);
        Gdx.input.setInputProcessor(new GestureDetector(shipGestureListener));
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move(true);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move(false);
            }
        }
    }

    public void move(boolean moveLeft) {
        float direction = moveLeft ? -1f : 1f;
        transform.translate(direction * .2f, 0f, 0f);
    }

    public void moveShip(Vector3 worldCoordinates) {
        Vector3 myPosition = transform.getTranslation(new Vector3());
        boolean moveLeft = worldCoordinates.x/15f < myPosition.x;
        move(moveLeft);
    }
}
