package edu.byuh.cis.cs203.hellocs203.system;


import android.graphics.Bitmap;
import android.util.Log;

import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

public abstract class Enemy extends Sprite {

    protected static float screenWidth;
    protected static float screenHeight;
    protected Direction dir;
    boolean exploding;
    protected Size size;


    /**
     * enemy abstract class
     */
    public Enemy() {
        super();

        double ran = Math.random();
        if (ran<0.5) {
            dir = Direction.LEFT_TO_RIGHT;
        } else {
            dir = Direction.RIGHT_TO_LEFT;
        }

        reset();

    }



    @Override
    public void move() {
        super.move();
        randomizeSpeed();
    }

    public void explode() {
        bitmap = getExplodingImage();
        velocity.x = 0;
        exploding = true;
    }

    public boolean getExploded() {
        return exploding;
    }

    public void setExploded() {
        exploding = false;
    }

    public void reset() {
        double rannum = Math.random();
        if (rannum<0.33) {
            size = Size.BIG;
        } else if (rannum <0.66) {
            size = Size.MEDIUM;
        } else {
            size = Size.SMALL;
        }

        double rn = Math.random();
        if (rn < 0.5) {
            dir = Direction.LEFT_TO_RIGHT;
        } else {
            dir = Direction.RIGHT_TO_LEFT;
        }

        if (dir == Direction.RIGHT_TO_LEFT) {
            velocity.set(-(float) Math.random() * 10 - 3, 0.0f);
        } else {
            velocity.set((float) Math.random() * 10 - 3, 0.0f);
        }

    }

    public static void setwh(float w, float h) {
        screenWidth = w;
        screenHeight = h;
    }

    public void randomizeSpeed() {
        if (Math.random()<0.1) {
            float v = (float)((Math.random()*15+6) * Math.signum(velocity.x));
            velocity.set(v, 0);
        }


    }

    public abstract Bitmap getExplodingImage();

    public abstract int getPointValue();
}
