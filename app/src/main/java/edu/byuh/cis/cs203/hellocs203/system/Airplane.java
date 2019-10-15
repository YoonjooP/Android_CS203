package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

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
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.medium_airplane);
        } else {
            bitmap = BitmapFactory.decodeResource(r, R.drawable.little_airplane);
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


}
