package edu.byuh.cis.cs203.hellocs203.system;


import android.graphics.Bitmap;
import android.util.Log;

import edu.byuh.cis.cs203.hellocs203.misc.Direction;

public abstract class Enemy extends Sprite {

    protected static float screenWidth;
    protected static float screenHeight;
    protected Direction dir;
    boolean exploding;


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

    public void reset() {
        double rannum = Math.random();

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
