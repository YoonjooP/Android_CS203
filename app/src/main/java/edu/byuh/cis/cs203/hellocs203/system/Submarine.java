package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

import static java.lang.Math.random;

public class Submarine extends Enemy {

    /**
     * submarine
     */
    public Submarine() {
        super();
        double rannum = random();
        if (rannum<0.33) {
            bitmap = ImageCache.getSubmarineImage(Size.BIG, Direction.LEFT_TO_RIGHT);
        } else if (rannum>=0.33 && rannum <0.66) {
            bitmap = ImageCache.getSubmarineImage(Size.MEDIUM, Direction.LEFT_TO_RIGHT);
        } else {
            bitmap = ImageCache.getSubmarineImage(Size.SMALL, Direction.LEFT_TO_RIGHT);
        }
        velocity.set ((float)Math.random()*10+3, 0.0f);
    }

    @Override
    public void move() {
        super.move();
        if (pos.left>screenWidth) {
            pos.offsetTo(-250, (float)Math.random()*screenHeight/3+screenHeight*2/3);
        }
    }

    @Override
    public void tick() {
        move();
    }
}
