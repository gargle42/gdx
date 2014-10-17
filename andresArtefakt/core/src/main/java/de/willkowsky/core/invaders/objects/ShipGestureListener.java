package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShipGestureListener extends GestureDetector.GestureAdapter {

    private boolean touchHappened = false;
    private float y = 0;
    private float x = 0;

    @Override
    public boolean touchDown (float x, float y, int pointer, int button) {
        this.x = x;
        this.y = y;
        return true;
    }

    public boolean isTouchHappened() {
        return touchHappened;
    }
}
