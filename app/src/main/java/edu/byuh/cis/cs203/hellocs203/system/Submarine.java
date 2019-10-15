package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

import static java.lang.Math.random;

public class Submarine extends Enemy {

    /**
     * submarine
     */
    public Submarine(Resources r) {
        super();
        double rannum = random();
        if (rannum<0.33) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.big_submarine_f);
            size = Size.BIG;
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.medium_submarine_f);
            size = Size.MEDIUM;
        } else {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.little_submarine_f);
            size = Size.SMALL;
        }
        velocity.set ((float)Math.random()*10+3, 0.0f);
    }

    @Override
    protected float relativeWidth() {
        switch (size) {
            case BIG: return 0.1f;
            case MEDIUM: return 0.07f;
            default: return 0.05f;
        }
    }

    @Override
    public void move() {
        super.move();
        if (pos.left>screenWidth) {
            pos.offsetTo(-250, (float)Math.random()*screenHeight/3+screenHeight*2/3);
        }
    }
}
