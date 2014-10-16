package de.willkowsky.core.invaders;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import de.willkowsky.core.invaders.misc.ModelFactory;
import de.willkowsky.core.invaders.objects.Camera;
import de.willkowsky.core.invaders.objects.InvasionFleet;
import de.willkowsky.core.invaders.objects.Ship;
import de.willkowsky.core.invaders.objects.Shot;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private ModelBatch modelBatch = new ModelBatch();
    private Camera camera = new Camera();
    private Ship  ship;
    private Shot shot;
    private InvasionFleet invasionFleet = new InvasionFleet();
    public static BoundingBox FIELD = new BoundingBox(new Vector3(-30, -20, -1), new Vector3(30, 7, 1));

    public GameWorld() {
        init();
    }

    private void init() {
        ModelFactory factory = ModelFactory.getInstance();
        ship = new Ship(factory.getShipModel());
        invasionFleet = new InvasionFleet();
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

        if (invasionFleet.hasArrived(ship)) {
        }
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
