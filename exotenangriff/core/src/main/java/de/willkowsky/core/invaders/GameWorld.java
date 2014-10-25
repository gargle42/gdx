package de.willkowsky.core.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import de.willkowsky.core.invaders.misc.ModelFactory;
import de.willkowsky.core.invaders.objects.Camera;
import de.willkowsky.core.invaders.objects.InvasionFleet;
import de.willkowsky.core.invaders.objects.Ship;
import de.willkowsky.core.invaders.objects.Shot;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class GameWorld {

    private ModelBatch modelBatch = new ModelBatch();
    public static Camera camera;
    private Ship ship;
    private Shot shot;
    private InvasionFleet invasionFleet = new InvasionFleet();
    public static BoundingBox FIELD = new BoundingBox(new Vector3(-30, -20, -1),
        new Vector3(30, 7, 1));
    private Environment environment = new Environment();
    List<ModelInstance> instances = null;
    private ModelInstance marker;

    public GameWorld() {
        init();
    }

    private void init() {
        // damit alle Gameobjekte ihre eigenen Inputlistener hinzufügen
        // können, wird hier der InputMultiplexer benutzt
        Gdx.input.setInputProcessor(new InputMultiplexer());

        camera = new Camera();
        ModelFactory factory = ModelFactory.getInstance();
        ship = new Ship(factory.getShipModel());
        invasionFleet = new InvasionFleet();

        // Beleuchtung definieren
        environment.set(
            new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,
                1f));
        environment.add(
            new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        instances = new ArrayList<ModelInstance>();
        instances.addAll(invasionFleet.getInvaders());

        //        Vector3 shipsPlace = new Vector3();
        //        ship.transform.getTranslation(shipsPlace);
        //        ship.transform.setToTranslation(0, 0, 0);
        //        ship.transform.setToTranslation(shipsPlace);
        //        ship.transform.setToRotation(0, 0, 1, 10);

        instances.add(ship);
        shot = new Shot(factory.getShotModel(), instances, ship);
        Vector3 vector3 = new Vector3(2f, -6f, 0f);

        Model model = new ModelBuilder()
            .createArrow(vector3, camera.getCamera().position, new Material(),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);
        marker = new ModelInstance(model, 0, 0, 0);
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
        modelBatch.render(instances, environment);
        modelBatch.render(marker, environment);
        modelBatch.end();
    }

}
