package de.willkowsky.core.invaders.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import de.willkowsky.core.invaders.objects.Invader;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.*;

public class ModelFactory {

    private static final ModelFactory INSTANCE = new ModelFactory();

    private ModelFactory() {}

    public static ModelFactory getInstance() {
        return INSTANCE;
    }

    public Model getInvaderModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Texture texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
        return modelBuilder.createBox(Invader.WIDTH, Invader.HEIGHT, Invader.DEPTH,
                new Material(TextureAttribute.createDiffuse(texture)),
                Position | Normal | TextureCoordinates);
    }

    public Model getShipModel() {
        return getInvaderModel();
    }

    public Model getShotModel() {
        return getInvaderModel();
    }
}