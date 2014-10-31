package de.willkowsky.core.invaders.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import de.willkowsky.core.invaders.objects.Invader;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.*;

public class ModelFactory {

    private static final ModelFactory INSTANCE = new ModelFactory();
    public static final String TEXTUR_HEILE = "models/kegel.g3db";
    public static final String INVADER_MODEL_FILE = "models/invader1.g3db";
    public static final String LIBGDX_LOGO_PNG = "libgdx-logo.png";
    public static Model MODEL_HEILE;
    private static Model MODEL_INVADER;
    private Sound shotSound;
    private AssetManager assetManager;

    private ModelFactory() {
        initAssetManager();
    }

    public static ModelFactory getInstance() {
        return INSTANCE;
    }

    public Model getInvaderModel() {
        if (assetManager != null) {
            return MODEL_INVADER;
        } else {
            ModelBuilder modelBuilder = new ModelBuilder();
            Texture texture = new Texture(Gdx.files.internal(LIBGDX_LOGO_PNG));
            return modelBuilder
                .createBox(Invader.WIDTH, Invader.HEIGHT, Invader.DEPTH,
                    new Material(TextureAttribute.createDiffuse(texture)),
                    Position | Normal | TextureCoordinates);
        }
    }

    public Model getShipModel() {
        //Diese Funktion dient nur dem Neuladen von Blendermodellen,
        //damit ohne Neustart die Modelle aktualisiert werden können.
        initAssetManager();
        return MODEL_HEILE;
    }

    public Sound getShotSound() {
        return shotSound;
    }

    private void initAssetManager() {
        assetManager = new AssetManager();
        assetManager.load(TEXTUR_HEILE, Model.class);
        assetManager.load(INVADER_MODEL_FILE, Model.class);
        assetManager.update();
        assetManager.finishLoading();
        MODEL_HEILE = assetManager.get(TEXTUR_HEILE, Model.class);
        MODEL_INVADER = assetManager.get(INVADER_MODEL_FILE, Model.class);
        shotSound = Gdx.audio
            .newSound(Gdx.files.internal("sounds/20141030_172928.mp3"));
    }

    public Model getShotModel() {
        return getInvaderModel();
    }
}