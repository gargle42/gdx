package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import de.willkowsky.core.invaders.GameWorld;
import de.willkowsky.core.invaders.misc.ModelFactory;

import java.util.ArrayList;
import java.util.List;

public class InvasionFleet {

    private static final float SPEED_X = 1f;
    public static final float STEP_DOWN = -0.3f;

    public static final int INVADER_COLUMNS = 10;
    public static final int INVADER_ROWS = 5;

    private static final float SPACE_X = Invader.WIDTH * 1.5f;
    private static final float SPACE_Y = Invader.HEIGHT * 1.5f;

    private float direction = 1f;
    private boolean moveDown = false;

    private List<Invader> invaders =new ArrayList<Invader>();

    public InvasionFleet() {
        initInvasionFleet();
    }

    private void initInvasionFleet() {
        for (int i = 0; i < INVADER_COLUMNS; i++) {
            for (int j = 0; j < INVADER_ROWS; j++) {
                Invader invader = new Invader(ModelFactory.getInstance().getInvaderModel());
                invader.transform.translate(
                        (float) i * SPACE_X - (INVADER_COLUMNS + Invader.WIDTH),
                        (float) j * SPACE_Y - (INVADER_ROWS + Invader.HEIGHT), 0f);
                invaders.add(invader);
            }
        }
    }

    public List<Invader> getInvaders() {
        return invaders;
    }

    public void update(float delta) {
        float leftPosition = 1000;
        float rightPosition = -1000;

        float stepY = getStepY(delta);
        float stepX = getStepX(delta);

        for (Invader invader : invaders) {
            //invader.update(delta);

            Matrix4 transform = invader.transform;
            Vector3 translation = transform.getTranslation(new Vector3());
//            transform.translate(stepX + translation.x, stepY + translation.y, 0f + translation.z);
            transform.translate(stepX , stepY , 0f );
            leftPosition = Math.min(leftPosition, translation.x);
            rightPosition = Math.max(rightPosition, translation.x);
        }

        calculateFleetsNextMove(leftPosition, rightPosition);
    }

    private void calculateFleetsNextMove(float leftPosition, float rightPosition) {
        if ( !GameWorld.FIELD.contains(new Vector3(leftPosition, 0,0))) {
            direction = Math.abs(direction);
            moveDown = true;
        }
        if ( !GameWorld.FIELD.contains(new Vector3(rightPosition, 0,0))) {
            direction = -Math.abs(direction) ;
            moveDown = true;
        }
    }

    private float getStepY(float delta) {
        if (moveDown) {
            moveDown = false;
            return STEP_DOWN;
        }
        return 0f;
    }

    private float getStepX(float delta) {
        return direction * delta * SPEED_X * getSpeed();
    }

    private float getSpeed() {
        return (1 + ((INVADER_COLUMNS * INVADER_ROWS) - invaders.size()) / 2.5f);
    }

    public boolean hasArrived(Ship ship) {
        BoundingBox boundingBox = new BoundingBox();
        boundingBox = ship.extendBoundingBox(boundingBox);
        Vector3 translation = new Vector3();
        for (Invader invader : invaders) {
            translation = invader.transform.getTranslation(translation);
            if (boundingBox.contains(translation)) {
                return true;
            }
        }
        return false;
    }
}
