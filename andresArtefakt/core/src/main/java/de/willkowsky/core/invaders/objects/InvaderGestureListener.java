package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InvaderGestureListener extends GestureDetector.GestureAdapter {

    private Vector3 cameraPosition;

    public InvaderGestureListener(Vector3 cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
        Vector2 pointer1, Vector2 pointer2) {

        float dstBefore = initialPointer1.dst(initialPointer2);
        float dstAfter = pointer1.dst(pointer2);

        float pinchDifference = dstAfter - dstBefore;
        cameraPosition.z -= pinchDifference/1000;

        return true;
    }

}
