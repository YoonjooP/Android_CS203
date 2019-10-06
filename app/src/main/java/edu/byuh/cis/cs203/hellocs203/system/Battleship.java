package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;


public class Battleship extends Sprite {
    /**
     * battleship
     */
    public Battleship(Resources r) {
        super();
        bitmap = BitmapFactory.decodeResource(r, R.drawable.battleship);
        velocity.set(0.0f, 0.0f);
    }

    @Override
    protected float relativeWidth() {
        return 0.4f;
    }


}