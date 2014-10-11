package de.willkowsky.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.*;

public class AndresGame implements ApplicationListener {
    public static final int INVADER_COLUMNS = 10;
    public static final int INVADER_ROWS = 5;
    private static final float SPACE_X = 2.5f;
    private static final float SPACE_Y = 5.5f;
    private static final float INVADER_HEIGHT = 3f;
    private static final float INVADER_WIDTH = 1f;

    private Texture texture;
    private ModelBatch modelBatch;
    private OrthographicCamera orthographicCamera;
    private ModelInstance modelInstance;
    private PerspectiveCamera perspectiveCamera;
    private ShapeRenderer shapeRenderer;

    int transX;
    int transY;
    private float boxTranslationX = 0f;
    private List<ModelInstance> instances;
    private float cameraYPos = 0;
    ModelInstance ship;
    private Vector3 my3dVector = new Vector3(0f, 0f, 30f);
    private boolean shipFired;
    private float shipBulletYPos;

    @Override
    public void create() {
        instances = new ArrayList<ModelInstance>();

        texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
        modelBatch = new ModelBatch();

        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        orthographicCamera.setToOrtho(true, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        orthographicCamera.update();

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        perspectiveCamera.position.set(my3dVector);
        perspectiveCamera.lookAt(0f, 0f, 0f);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();



        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createBox(INVADER_WIDTH, INVADER_HEIGHT, 4f,
                new Material(TextureAttribute.createDiffuse(texture)),
                Position | Normal | TextureCoordinates);
        instances.addAll(getInvaders(model));

        ship = new ModelInstance(model);
        ship.transform.translate(0f, (float) -17, 0f);
        instances.add(ship);

        shapeRenderer = new ShapeRenderer();

        transY = Gdx.graphics.getHeight() / 2;
        transX = Gdx.graphics.getWidth() / 2;

        Bullet.init();
    }

    private List<ModelInstance> getInvaders(Model model) {
        List<ModelInstance> result = new ArrayList<ModelInstance>();

        for (int i = 0; i < INVADER_COLUMNS; i++) {
            for (int j = 0; j < INVADER_ROWS; j++) {
                ModelInstance modelInstance = new ModelInstance(model);
                modelInstance.transform.translate((float) i * SPACE_X - (INVADER_COLUMNS + INVADER_WIDTH), (float) j * SPACE_Y - (INVADER_ROWS + INVADER_HEIGHT), 0f);
                result.add(modelInstance);
            }
        }

        return result;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        resetView();
        handleKeyboardInput();
        drawBox();
//        drawCrosshairs();
    }

    private void resetView() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    private void drawBox() {
        modelBatch.begin(perspectiveCamera);
        modelBatch.render(getModelInstance());
        modelBatch.end();
    }

    private List<ModelInstance> getModelInstance() {

        return instances;
    }

    private void handleKeyboardInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ship.transform.translate(-.2f, 0f, 0f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ship.transform.translate(.2f, 0f, 0f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            my3dVector.x += .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            my3dVector.x -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            my3dVector.y -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            my3dVector.y += .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            my3dVector.z -= .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            my3dVector.z += .1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            shipFired = true;
            float shipPosY = 0;
            shipBulletYPos = shipPosY;
        }
        perspectiveCamera.position.set(my3dVector);
        perspectiveCamera.update();

    }

    private void drawCrosshairs() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1f, 1f, 1f, 1);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.line(0, transY, 0, Gdx.graphics.getWidth(), transY, 0);
        shapeRenderer.line(transX, 0, 0, transX, Gdx.graphics.getHeight(), 0);
        shapeRenderer.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
