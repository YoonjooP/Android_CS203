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

    /**
     * submarine
     */
    public Submarine() {
        super();
        bitmap = ImageCache.getSubmarineImage(size, dir);
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    /**
     * reset submarine
     */
    @Override
    public void move() {
        super.move();
        if (pos.left > screenWidth) {
            reset();
            bitmap=ImageCache.getSubmarineImage(size,dir);
            this.pos.set(0,0,bitmap.getWidth(), bitmap.getHeight());
            pos.offsetTo(-250, (float) Math.random() * screenHeight / 3 + screenHeight * 2 / 3 - 70);
        } else if (pos.right < -250) {
            reset();
            bitmap=ImageCache.getSubmarineImage(size,dir);
            this.pos.set(0,0,bitmap.getWidth(), bitmap.getHeight());
            pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3 + screenHeight * 2 / 3 - 70);
        }
        if(pos.top < screenHeight/2) {
            pos.offsetTo(-250, (float) Math.random() * screenHeight / 3 + screenHeight * 2 / 3 - 70);
        }
    }

    /**
     * tick
     */
    @Override
    public void tick() {
        move();
    }

    /**
     *
     * @return get the image of explosion
     */
    @Override
    public Bitmap getExplodingImage() {
        return ImageCache.getSubmarineExplosion();
    }

    /**
     * the score of each size
     * @return
     */
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
