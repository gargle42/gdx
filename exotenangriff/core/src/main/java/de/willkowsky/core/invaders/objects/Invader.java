package de.willkowsky.core.invaders.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Invader extends ModelInstance {

    public static final float HEIGHT = 2f;
    public static final float WIDTH = 2f;
    public static final float DEPTH = 2f;

    public static final float DEAD_ZONE = 1.5f;

    private float totalDegrees = 0f;
    private float direction = 1f;

    private static float ROTATE_LIMIT = 150f;
    private static float ROTATION_SPEED = 10f;

    private Random random = new Random();
    private Vector3 axis;
    private float speed;

    public Invader(Model model) {
        super(model);
        axis = getAxis();
        direction = getDirection();
        speed = getSpeed();
    }

    private float getSpeed() {
        return 135f;
        //        return (float) random.nextInt(((int) ROTATION_SPEED) * 10)
        // + 1f;
    }

    private float getDirection() {
        //                return 1f;
        return (random.nextInt(10)) == 1 ? -1 : 1;
    }

    public void update(float delta) {
        float degrees = getDegrees(delta);
        totalDegrees += degrees;
        //        Vector3 currentTranslation = transform.getTranslation(new
        // Vector3());
        //        System.out.println("currentTranslation " +
        // currentTranslation);
        //        transform.translate(-currentTranslation.x,
        // -currentTranslation.y,
        //            -currentTranslation.z);
        Vector3 afterTranslation = transform.getTranslation(new Vector3());
//        System.out.println("afterTranslation " + afterTranslation);
        //        transform.setToTranslation(0, 0, 0);
        transform.translate(-2.7f/2, -1.9f/2, -.5f/2);
        transform.rotate(Vector3.Z, degrees);
        transform.translate(+2.7f/2, +1.9f/2, +.5f/2);
//        transform.translate(afterTranslation);
        //        transform.translate(currentTranslation.x,
        // currentTranslation.y,
        //            currentTranslation.z);

        //        afterTranslation = transform.getTranslation(new Vector3());
        //        System.out.println("afterTranslation2 " + afterTranslation
        // + ": " +
        //            axis);
    }

    private Vector3 getAxis() {
        //        int i = random.nextInt(3);
        //        switch (i) {
        //            case 2:
        //                return Vector3.Y;
        //            case 1:
        //                return Vector3.X;
        //            default:
        //                return Vector3.Z;
        //        }

        return Vector3.Z;
    }

    private float getDegrees(float delta) {
        float degrees = calculateRotationIncrement(delta);
        if (Math.abs(degrees + totalDegrees) > Math.abs(ROTATE_LIMIT)) {
            direction = direction * -1f;
            degrees = calculateRotationIncrement(delta);
            speed = getSpeed();
            axis = getAxis();
        }
        return degrees;
    }

    private float calculateRotationIncrement(float delta) {
        return direction * delta * speed;
    }

}
