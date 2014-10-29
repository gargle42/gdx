package de.willkowsky.core.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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

    public static Camera camera;
    private static Vector3 boundingBoxLeft = new Vector3(-30, -20, -1);
    private static Vector3 boundingBoxRight = new Vector3(30, 7, 1);
    public static BoundingBox FIELD = new BoundingBox(boundingBoxLeft,
        boundingBoxRight);
    private List<ModelInstance> instances = new ArrayList<ModelInstance>();
    private ModelBatch modelBatch = new ModelBatch();
    private Ship ship;
    private Shot shot;
    private InvasionFleet invasionFleet = new InvasionFleet();
    private Environment environment = new Environment();
    private List<ModelInstance> debugModels = new ArrayList<ModelInstance>();
    private GameWorldInputAdapter gameWorldInputAdapter = new
        GameWorldInputAdapter();

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

        instances.addAll(invasionFleet.getInvaders());
        instances.add(ship);
        shot = new Shot(factory.getShotModel(), instances, ship);

        ((InputMultiplexer) Gdx.input.getInputProcessor())
            .addProcessor(gameWorldInputAdapter);

    }

    /**
     * Die hier definierten modelle werden über eine Taste sichtbar gemacht,
     * um Orientierungshilfen im Spielfeld einzublenden
     */
    private List<ModelInstance> buildDebugModels() {
        List<ModelInstance> result = new ArrayList<ModelInstance>();

        Model model = new ModelBuilder()
            .createXYZCoordinates(1f, new Material(),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);


        result.add(new ModelInstance(model, 0, 0, 0));
        result.add(new ModelInstance(model, camera.getCamera().position));
        result.add(new ModelInstance(model, boundingBoxLeft));
        result.add(
            new ModelInstance(model, boundingBoxLeft.x, boundingBoxLeft.y,
                boundingBoxRight.z));

        model = new ModelBuilder()
            .createLineGrid(10, 10, 40, 50, new Material(),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);
        result.add(new ModelInstance(model, 0, 0, 0));
//
//        Vector3 vector3 = new Vector3(2f, -6f, 0f);
//        model = new ModelBuilder()
//            .createArrow(camera.getCamera().position, vector3, new Material(),
//                Usage.Position | Usage.Normal | Usage.TextureCoordinates);
//        result.add(new ModelInstance(model, 0, 0, 0));

        return result;
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
        if (gameWorldInputAdapter.isDebugHintsEnabled()) {
            modelBatch.render(debugModels, environment);
        }
        modelBatch.end();
    }

    class GameWorldInputAdapter extends InputAdapter {
        private boolean debugHintsEnabled;

        public boolean isDebugHintsEnabled() {
            return debugHintsEnabled;
        }

        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.C:
                    debugHintsEnabled = !debugHintsEnabled;
                    // initialize the debugmodels
                    if (debugModels.size() == 0) {
                        debugModels.addAll(buildDebugModels());
                    }
            }

            return super.keyDown(keycode);
        }
    }
}