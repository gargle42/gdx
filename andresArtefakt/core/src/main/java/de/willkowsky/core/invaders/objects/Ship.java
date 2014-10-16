package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Ship extends ModelInstance {

    public Ship(Model model) {
        super(model);
        transform.translate(0f, (float) -17, 0f);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            transform.translate(-.2f, 0f, 0f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            transform.translate(.2f, 0f, 0f);
        }
    }

}
