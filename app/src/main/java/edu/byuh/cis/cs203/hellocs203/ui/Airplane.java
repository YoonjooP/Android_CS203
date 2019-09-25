package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

public class Airplane extends Enemy {
    public Airplane(Resources r) {
        super(r);
        double rannum = Math.random();
        if (rannum<0.33) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.big_airplane);
            size = Size.BIG;
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.medium_airplane);
            size = Size.MEDIUM;
        } else {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.little_airplane);
            size = Size.SMALL;
        }
    }

    @Override
    protected float relativeWidth() {
        switch (size) {
            case BIG:
                return 0.2f;
            case MEDIUM:
                return 0.10f;
            default:
                return 0.05f;
        }
    }
}
