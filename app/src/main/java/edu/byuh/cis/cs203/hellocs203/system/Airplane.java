package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

public class Airplane extends Enemy {
    /**
     * airplane
     */
    public Airplane() {
        super();
        double rannum = Math.random();
        if (rannum<0.33) {
            bitmap = ImageCache.getAirplaneImage(Size.BIG, Direction.LEFT_TO_RIGHT);
            pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = ImageCache.getAirplaneImage(Size.MEDIUM, Direction.LEFT_TO_RIGHT);
            pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        } else {
            bitmap = ImageCache.getAirplaneImage(Size.SMALL, Direction.LEFT_TO_RIGHT);
            pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        }

        velocity.set (-(float)Math.random()*10 -3, 0.0f);
    }


    @Override
    public void move() {
        super.move();

        if (pos.right<-250) {
            pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3);
        }

    }


    @Override
    public void tick() {
        move();
    }
}
