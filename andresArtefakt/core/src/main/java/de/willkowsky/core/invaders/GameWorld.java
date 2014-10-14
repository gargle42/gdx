package de.willkowsky.core.invaders;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import de.willkowsky.core.invaders.misc.ModelFactory;
import de.willkowsky.core.invaders.objects.Camera;
import de.willkowsky.core.invaders.objects.InvasionFleet;
import de.willkowsky.core.invaders.objects.Ship;
import de.willkowsky.core.invaders.objects.Shot;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private ModelBatch modelBatch = new ModelBatch();
    private InvasionFleet invasionFleet = new InvasionFleet();
    private Camera camera = new Camera();
    private Ship  ship;
    private Shot shot;

    public GameWorld() {
        init();
    }

    private void init() {
        ModelFactory factory = ModelFactory.getInstance();
        ship = new Ship(factory.getShipModel());
        shot = new Shot(factory.getShotModel(), invasionFleet.getInvaders(), ship);
    }

    public void render(float delta) {
        updateModels(delta);
        renderModels();
    }

    private void updateModels(float delta) {
        ship.update(delta);
        invasionFleet.update(delta);
        shot.update(delta);
        camera.update();
    }

    private void renderModels() {
        modelBatch.begin(camera.getCamera());
        modelBatch.render(getModelInstances());
        modelBatch.end();
        camera.getCamera().update();
    }

    private List<ModelInstance> getModelInstances() {
        List<ModelInstance> instances = new ArrayList<ModelInstance>();
        instances.addAll(invasionFleet.getInvaders());
        instances.add(ship);
        if (shot.isActive()) {
            instances.add(shot);
        }
        return instances;
    }

}
