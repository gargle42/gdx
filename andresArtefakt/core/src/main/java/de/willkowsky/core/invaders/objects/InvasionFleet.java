package de.willkowsky.core.invaders.objects;

import de.willkowsky.core.invaders.misc.ModelFactory;

import java.util.ArrayList;
import java.util.List;

public class InvasionFleet {

    public static final int INVADER_COLUMNS = 10;
    public static final int INVADER_ROWS = 5;
    private static final float SPACE_X = Invader.WIDTH * 1.5f;
    private static final float SPACE_Y = Invader.HEIGHT * 1.5f;

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
        for (Invader modelInstance : invaders) {
            modelInstance.update(delta);
        }

    }
}
