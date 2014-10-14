package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera {

    public static final float Y = -6f;
    public static final float X = 2f;

    private Vector3 cameraPosition = new Vector3(X, Y, 20f);

    private com.badlogic.gdx.graphics.PerspectiveCamera camera;

    public Camera() {
        camera = new com.badlogic.gdx.graphics.PerspectiveCamera(67, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.position.set(cameraPosition);
        camera.lookAt(X, Y, 0f);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
    }


    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cameraPosition.x += .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            cameraPosition.x -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cameraPosition.y -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cameraPosition.y += .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cameraPosition.z -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            cameraPosition.z += .1;
        }
        camera.position.set(cameraPosition);
        camera.update();
    }
}
