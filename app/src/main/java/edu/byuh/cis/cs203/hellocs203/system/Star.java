package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

public class Star extends Sprite {

    private Resources r;
    private Direction di;

    public Star(Resources r, Direction di) {
        super();
        this.r = r;
        this.di = di;
        bitmap = BitmapFactory.decodeResource(r, R.drawable.star);
        velocity.set(0.0f, 0.0f);
    }

    @Override
    protected float relativeWidth() {
        return 0.2f;
    }

    @Override
    public void setPosition(float x, float y) {
        if (di == Direction.LEFT_TO_RIGHT){
            pos.offsetTo(x-pos.width()/2+395, y-pos.height()/2-155);
        } else {
            pos.offsetTo(x-pos.width()/2-360, y-pos.height()/2-150);
        }
    }
}
