package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;

public class Star extends Sprite {

    private Direction di;
    private boolean isdraw;

    /**
     * star
     * @param di direction
     */
    public Star(Direction di) {
        super();
        this.di = di;
        bitmap = ImageCache.getCannonFire();
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        velocity.set(0.0f, 0.0f);
        isdraw = false;
    }

    /**
     * star position
     * @param x center
     * @param y center
     */
    @Override
    public void setPosition(float x, float y) {
        if (di == Direction.LEFT_TO_RIGHT){
            pos.offsetTo(x-pos.width()/2+390, y-pos.height()/2-170);
        } else {
            pos.offsetTo(x-pos.width()/2-400, y-pos.height()/2-180);
        }
    }

    /**
     * draw
     * @param c canvas
     */
    @Override
    public void draw(Canvas c) {
        super.draw(c);
        isdraw = true;
    }

    /**
     *
     * @return boolean isdraw
     */
    public boolean getIsdraw() {
        return isdraw;
    }

    /**
     * tick
     */
    @Override
    public void tick() {
        move();
    }
}
