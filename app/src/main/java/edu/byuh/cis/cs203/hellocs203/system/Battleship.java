package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;


public class Battleship extends Sprite {

    private static Battleship battleship;

    /**
     * battleship
     */
    private Battleship() {
        super();
        bitmap = ImageCache.getBattleshipImage();
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        velocity.set(0.0f, 0.0f);
    }

    /**
     * tick
     */
    @Override
    public void tick() {
        move();
    }


    public static Battleship getInstance() {
        if (battleship == null) {
            battleship = new Battleship();
        }

        return battleship;
    }
}
