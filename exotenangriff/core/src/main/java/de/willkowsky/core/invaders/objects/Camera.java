package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class Camera {

    public static final float Y = -6f;
    public static final float X = 2f;

    private Vector3 cameraPosition = new Vector3(X, Y, 30f);

    private PerspectiveCamera camera;

    public Camera() {
        camera = new PerspectiveCamera(80, Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        camera.position.set(cameraPosition);
        camera.lookAt(X, Y, 0f);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(
            new GestureDetector(new InvaderGestureListener(cameraPosition)));

        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(new CameraInputAdapter());
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void update() {
    }

    class CameraInputAdapter extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {

            switch (keycode) {
                case Input.Keys.A:
                    cameraPosition.x += .1;
                    break;
                case Input.Keys.D:
                    cameraPosition.x -= .1;
                    break;
                case Input.Keys.UP:
                    cameraPosition.y -= .1;
                    break;
                case Input.Keys.DOWN:
                    cameraPosition.y += .1;
                    break;
                case Input.Keys.W:
                    cameraPosition.z -= .1;
                    break;
                case Input.Keys.S:
                    cameraPosition.z += .1;
                    break;
            }

            camera.position.set(cameraPosition);
            camera.update();

            return super.keyDown(keycode);
        }
    }
}
