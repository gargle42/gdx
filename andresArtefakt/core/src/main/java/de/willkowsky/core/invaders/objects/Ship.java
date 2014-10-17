package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class Ship extends ModelInstance {

    ShipGestureListener shipGestureListener;

    public Ship(Model model) {
        super(model);
        shipGestureListener = new ShipGestureListener(this);
        transform.translate(0f, (float) -17, 0f);

        Gdx.input.setInputProcessor(new GestureDetector(shipGestureListener));
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            move(true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            transform.translate(.2f, 0f, 0f);
        }
    }

    public void move(boolean left) {
        float direction = left ? -1f : 1f;
        transform.translate(direction * .2f, 0f, 0f);
    }

    public void moveShip(Vector3 worldCoordinates) {
        Vector3 myPosition = transform.getTranslation(new Vector3());
        boolean direction = myPosition.x > worldCoordinates.x ;
        move(direction);
    }
}
