package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;

public class Star extends Sprite {

    private Resources r;
    private Direction di;
    private boolean isdraw;

    /**
     * star
     * @param r resources
     * @param di direction
     */
    public Star(Resources r, Direction di) {
        super();
        this.r = r;
        this.di = di;
        bitmap = BitmapFactory.decodeResource(r, R.drawable.star);
        velocity.set(0.0f, 0.0f);
        isdraw = false;
    }


    @Override
    public void setPosition(float x, float y) {
        if (di == Direction.LEFT_TO_RIGHT){
            pos.offsetTo(x-pos.width()/2+390, y-pos.height()/2-170);
        } else {
            pos.offsetTo(x-pos.width()/2-400, y-pos.height()/2-180);
        }
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        isdraw = true;
    }

    public boolean getIsdraw() {
        return isdraw;
    }
}
