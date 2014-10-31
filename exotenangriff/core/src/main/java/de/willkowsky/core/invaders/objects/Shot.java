package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import de.willkowsky.core.invaders.GameWorld;

import java.util.Collections;
import java.util.List;

public class Shot extends ModelInstance {

    public static final float SHOT_SPEED = Invader.HEIGHT * 20f;
    private final Sound shotSound;
    private boolean active;
    private List<ModelInstance> invaders;
    private ModelInstance ship;

    public Shot(Model model, List<ModelInstance> invaders,
        final ModelInstance ship, Sound shotSound) {
        super(model);
        this.invaders = invaders;
        this.ship = ship;
        this.shotSound = shotSound;

        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(new ShotInputAdapter());
    }

    private void checkForHit() {
        for (ModelInstance target : Collections.unmodifiableList(invaders)) {
            if (target != this && target != ship) {
                Vector3 shotPosition = new Vector3();
                transform.getTranslation(shotPosition);
                Vector3 instancePosition = new Vector3();
                boolean isHit = shotPosition.dst(target.transform
                    .getTranslation(instancePosition)) < Invader.DEAD_ZONE;
                if (isHit) {
                    shotSound.play();
                    invaders.remove(target);
                    setActive(false);
                    return;
                }
            }
        }
    }

    public void update(float delta) {
        if (isActive()) {
            transform.translate(0f, SHOT_SPEED * delta, 0f);
            checkForHit();

            Vector3 vector = new Vector3();
            transform.getTranslation(vector);
            if (!GameWorld.FIELD.contains(vector)) {
                setActive(false);
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            invaders.add(this);
        } else {
            invaders.remove(this);
        }
    }

    class ShotInputAdapter extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {
            if (Input.Keys.SPACE == keycode) {
                if (!isActive()) {
                    setActive(true);
                    Vector3 shipPosition = new Vector3();
                    ship.transform.getTranslation(shipPosition);
                    // Schussposition korrigieren, damit der mittig aus dem
                    // Schiff herauskommt
                    shipPosition.x += 2;
                    transform.setToTranslation(shipPosition);
                }
            }

            return super.keyDown(keycode);
        }
    }
}