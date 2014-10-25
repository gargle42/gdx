package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import de.willkowsky.core.invaders.GameWorld;

public class Ship extends ModelInstance {

    ShipInputAdapter shipInputAdapter = new ShipInputAdapter();

    public Ship(Model model) {
        super(model);
        transform.translate(0f, -17f, 0f);

        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(shipInputAdapter);
    }

    public void update(float delta) {
        if (shipInputAdapter.isMouseDown()) {
            Vector3 worldCoordinates = new Vector3(shipInputAdapter.getMouseX(),
                shipInputAdapter.getMouseY(), 1f);
            GameWorld.camera.getCamera().unproject(worldCoordinates);
            moveShip(worldCoordinates);
        }

        if (shipInputAdapter.isKeyDown()) {
            move(shipInputAdapter.isMoveLeft());
        }
    }

    private void move(boolean moveLeft) {
        float direction = moveLeft ? -1f : 1f;
        transform.translate(direction * .2f, 0f, 0f);
    }

    public void moveShip(Vector3 worldCoordinates) {
        Vector3 myPosition = transform.getTranslation(new Vector3());
        boolean moveLeft = worldCoordinates.x / 15f < myPosition.x;
        move(moveLeft);
    }

    class ShipInputAdapter extends InputAdapter {

        private boolean mouseDown = false;
        private int mouseX;
        private int mouseY;
        private boolean keyDown;
        private boolean moveLeft;

        @Override
        public boolean keyUp(int keycode) {
            keyDown = false;
            return super.keyUp(keycode);
        }

        @Override
        public boolean keyDown(int keycode) {

            switch (keycode) {
                case Input.Keys.LEFT:
                    moveLeft = true;
                    keyDown = true;
                    break;
                case Input.Keys.RIGHT:
                    moveLeft = false;
                    keyDown = true;
                    break;
            }

            return super.keyDown(keycode);
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            mouseDown = false;
            return false;
        }

        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            this.mouseX = x;
            this.mouseY = y;
            mouseDown = true;
            return false;
        }

        public boolean isMouseDown() {
            return mouseDown;
        }

        public int getMouseY() {
            return mouseY;
        }

        public int getMouseX() {
            return mouseX;
        }

        public boolean isMoveLeft() {
            return moveLeft;
        }

        public boolean isKeyDown() {
            return keyDown;
        }
    }
}
