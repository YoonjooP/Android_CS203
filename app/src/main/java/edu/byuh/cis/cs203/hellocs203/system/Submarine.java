package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

import static java.lang.Math.random;

public class Submarine extends Enemy {

    Size size;
    /**
     * submarine
     */
    public Submarine() {
        super();
        double rannum = random();
        if (rannum<0.33) {
            size = Size.BIG;
        } else if (rannum>=0.33 && rannum <0.66) {
            size = Size.MEDIUM;
        } else {
            size = size.SMALL;
        }

        bitmap = ImageCache.getSubmarineImage(size, dir);
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());

        if (dir == Direction.LEFT_TO_RIGHT) {
            velocity.set((float) Math.random() * 10 + 3, 0.0f);
        } else {
            velocity.set(-(float) Math.random() * 10 + 3, 0.0f);
        }
    }

    @Override
    public void move() {
        super.move();
        if (dir == Direction.LEFT_TO_RIGHT) {
            if (pos.left > screenWidth) {
                pos.offsetTo(-250, (float) Math.random() * screenHeight / 3 + screenHeight * 2 / 3);
            }

        } else {
            if (pos.right < 0) {
                pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3 + screenHeight * 2 / 3);
            }

        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public Bitmap getExplodingImage() {
        return ImageCache.getSubmarineExplosion();
    }

    @Override
    public int getPointValue() {
        if (size == Size.BIG) {
            return 25;
        } else if (size == Size.MEDIUM) {
            return 40;
        } else {
            return 150;
        }
    }
}
