package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

public class Submarine extends Enemy {

    /**
     * submarine
     */
    public Submarine(Resources r) {
        super(r);
        double rannum = Math.random();
        if (rannum<0.33) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.big_submarine);
            size = Size.BIG;
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.medium_submarine);
            size = Size.MEDIUM;
        } else {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.little_submarine);
            size = Size.SMALL;
        }
    }

    @Override
    protected float relativeWidth() {
        switch (size) {
            case BIG: return 0.1f;
            case MEDIUM: return 0.07f;
            default: return 0.05f;
        }
    }
}
