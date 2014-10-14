package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

import java.util.Collections;
import java.util.List;

public class Shot extends ModelInstance {

    public static final float SHOT_SPEED = Invader.HEIGHT * 20f;
    private boolean active;
    private List<Invader> invaders;
    private ModelInstance ship;

    public Shot(Model model, List<Invader> invaders, ModelInstance ship) {
        super(model);
        this.invaders = invaders;
        this.ship = ship;
    }

    private void checkForHit() {
        for (ModelInstance target : Collections.unmodifiableList(invaders)) {
            Vector3 shotPosition = new Vector3();
            transform.getTranslation(shotPosition);
            Vector3 instancePosition = new Vector3();
            boolean isHit = shotPosition.dst(target.transform.getTranslation(instancePosition)) < Invader.DEAD_ZONE;
            if (isHit) {
                invaders.remove(target);
                active = false;
                return;
            }
        }
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!isActive()) {
                setActive(true);
                Vector3 shipPosition = new Vector3();
                ship.transform.getTranslation(shipPosition);
                transform.setToTranslation(shipPosition);
            }
        }

        if (isActive()) {
            transform.translate(0f, SHOT_SPEED * delta, 0f);
            checkForHit();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
