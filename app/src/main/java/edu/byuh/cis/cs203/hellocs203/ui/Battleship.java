package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

/**
 * battleship
 */
public class Battleship extends Sprite {
    public Battleship(Resources r) {
        super(r);
        bitmap = BitmapFactory.decodeResource(r, R.drawable.battleship);
    }

    @Override
    protected float relativeWidth() {
        return 0.4f;
    }
}
