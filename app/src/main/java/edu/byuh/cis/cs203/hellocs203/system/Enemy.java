package edu.byuh.cis.cs203.hellocs203.system;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

public abstract class Enemy extends Sprite {

    protected static float screenWidth;
    protected static float screenHeight;
    protected Direction dir;
    boolean exploding;
    protected Size size;
    protected static String direction;
    protected static int speed;


    /**
     * enemy abstract class
     */
    public Enemy() {
        super();
        exploding = false;
        reset();
    }


    /**
     * set random speed 1/10
     */
    @Override
    public void move() {
        super.move();
        if (Math.random()<0.1) {
            setRandomSpeed();
        }
    }

    /**
     * explode method
     */
    public void explode() {
        bitmap = getExplodingImage();
        exploding = true;
        velocity.set(0.0f, 0.0f);
    }

    /**
     * draw bitmap again when they explode
     * @param c
     */
    @Override
    public void draw(Canvas c) {
        super.draw(c);
        if (exploding == true) {
            exploding = false;
            reset();
            if(this instanceof  Airplane){
                this.bitmap = ImageCache.getAirplaneImage(size, dir);
            } else {
                this.bitmap = ImageCache.getSubmarineImage(size, dir);
            }
            this.pos.set(0,0,bitmap.getWidth(), bitmap.getHeight());
        }
    }

//    public boolean getExploded() {
//        return exploding;
//    }
//
//    public void setExploded() {
//        exploding = false;
//    }

    /**
     * reset everything
     */
    public void reset() {
        double rannum = Math.random();
        if (rannum<0.33) {
            size = Size.BIG;
        } else if (rannum <0.66) {
            size = Size.MEDIUM;
        } else {
            size = Size.SMALL;
        }
        if (direction.equals("left-to-right")) {
            dir = Direction.LEFT_TO_RIGHT;
        } else if (direction.equals("right-to-left")) {
            dir = Direction.RIGHT_TO_LEFT;
        } else {
            double rn = Math.random();
            if (rn < 0.5) {
                dir = Direction.LEFT_TO_RIGHT;
            } else {
                dir = Direction.RIGHT_TO_LEFT;
            }
        }

        if (dir == Direction.RIGHT_TO_LEFT) {
            velocity.x = -1;
        } else {
            velocity.x = 1;
        }
        //Log.d("direction", dir+" "+Float.toString(velocity.x));
        setRandomSpeed();

    }

    /**
     *
     * @param w screen width
     * @param h screen height
     */
    public static void setwh(float w, float h) {
        screenWidth = w;
        screenHeight = h;
    }

    /**
     * set random speed method
     */
    public void setRandomSpeed() {
        float v = (float)((Math.random()*15+6) * Math.signum(velocity.x));
        velocity.set(v+(float)speed, 0);
    }

    public abstract Bitmap getExplodingImage();
    public abstract int getPointValue();

    public static void setPreference(int i, String s) {
        direction = s;
        speed = i;
    }




}
