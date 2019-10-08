package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

public class DepthCharge extends Sprite {
    /**
     * scale, position, draw
     *
     * @param r
     */
    public DepthCharge(Resources r) {
        super();
        bitmap = BitmapFactory.decodeResource(r, R.drawable.depth_charge);
        velocity.set(0, 20.0f);
    }

    @Override
    protected float relativeWidth() {
        return 0.02f;
    }

    @Override
    public void setPosition(float x, float y) {
            pos.offsetTo(x-pos.width()/2, y-pos.height()/2+50);

    }
}
