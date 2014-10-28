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
    CameraInputAdapter cameraInputAdapter;

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

        cameraInputAdapter = new CameraInputAdapter();
        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(cameraInputAdapter);


    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void update() {
        if (cameraInputAdapter.isCameraMove()) {
            cameraPosition.add(cameraInputAdapter.getMovement());
            cameraPosition.rotate(cameraInputAdapter.getRotation(), 1f);
            camera.position.set(cameraPosition);
            camera.lookAt(X, Y, 0f);
            camera.update();
        }
    }

    class CameraInputAdapter extends InputAdapter {
        private boolean cameraMove;
        private Vector3 movement = new Vector3();
        private Vector3 rotation = new Vector3();

        @Override
        public boolean keyUp(int keycode) {
            cameraMove = false;
            movement = new Vector3();
            rotation = new Vector3();

            return super.keyUp(keycode);
        }

        @Override
        public boolean keyDown(int keycode) {

            cameraMove = true;

            switch (keycode) {
                case Input.Keys.A:
                    movement.x = +.1f;
                    break;
                case Input.Keys.D:
                    movement.x = -.1f;
                    break;
                case Input.Keys.UP:
                    movement.y = -.1f;
                    break;
                case Input.Keys.DOWN:
                    movement.y = +.1f;
                    break;
                case Input.Keys.W:
                    movement.z = -.1f;
                    break;
                case Input.Keys.S:
                    movement.z = +.1f;
                    break;
                case Input.Keys.T:
                    rotation.x = -1f;
                    break;
                case Input.Keys.G:
                    rotation.x = +1f;
                    break;
                case Input.Keys.F:
                    rotation.y = -1f;
                    break;
                case Input.Keys.H:
                    rotation.y = +1f;
                    break;
            }

            return super.keyDown(keycode);
        }

        public boolean isCameraMove() {
            return cameraMove;
        }

        public Vector3 getMovement() {
            return movement;
        }

        public Vector3 getRotation() {
            return rotation;
        }
    }
}
