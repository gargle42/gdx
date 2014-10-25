package de.willkowsky.core.invaders;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
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
    public static Camera camera = new Camera();
    private Ship ship;
    private Shot shot;
    private InvasionFleet invasionFleet = new InvasionFleet();
    public static BoundingBox FIELD = new BoundingBox(new Vector3(-30, -20, -1),
        new Vector3(30, 7, 1));
    private Environment environment = new Environment();

    public GameWorld() {
        init();
    }

    private void init() {
        ModelFactory factory = ModelFactory.getInstance();
        ship = new Ship(factory.getShipModel());
        invasionFleet = new InvasionFleet();
        shot = new Shot(factory.getShotModel(), invasionFleet.getInvaders(),
            ship);

        //        GameWorldGestureListener gameWorldGestureListener = new
        //            GameWorldGestureListener(
        //            this);
        //        Gdx.input
        //            .setInputProcessor(new GestureDetector
        // (gameWorldGestureListener));

        environment.set(
            new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,
                1f));
        environment.add(
            new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
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
        modelBatch.render(getModelInstances(), environment);
        modelBatch.end();
    }

    public void replaceShip() {
        ship = new Ship(ModelFactory.getInstance().getShipModel());
    }

    private List<ModelInstance> getModelInstances() {
        List<ModelInstance> instances = new ArrayList<ModelInstance>();
        instances.addAll(invasionFleet.getInvaders());

        //        Vector3 shipsPlace = new Vector3();
        //        ship.transform.getTranslation(shipsPlace);
        //        ship.transform.setToTranslation(0, 0, 0);
        //        ship.transform.setToTranslation(shipsPlace);
        //        ship.transform.setToRotation(0, 0, 1, 10);

        instances.add(ship);
        if (shot.isActive()) {
            instances.add(shot);
        }

        return instances;
    }

}
