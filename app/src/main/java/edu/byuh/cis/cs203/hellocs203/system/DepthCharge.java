package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;

public class DepthCharge extends Sprite {
    /**
     * scale, position, draw
     *
     */
    public DepthCharge() {
        super();
        bitmap = ImageCache.getDepthChargeImage();
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        velocity.set(0, 20.0f);
    }


    @Override
    public void setPosition(float x, float y) {
            pos.offsetTo(x-pos.width()/2, y-pos.height()/2+50);

    }

    @Override
    public void tick() {
        move();
    }
}
