package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.misc.Size;

public class Airplane extends Enemy {

    Size size;
    /**
     * airplane
     */
    public Airplane() {
        super();
//        double rannum = Math.random();
//        if (rannum<0.33) {
//            size = Size.BIG;
//        } else if (rannum <0.66) {
//            size = Size.MEDIUM;
//        } else {
//            size = Size.SMALL;
//        }

        bitmap = ImageCache.getAirplaneImage(size, dir);
        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
//        if (dir == Direction.RIGHT_TO_LEFT) {
//            velocity.set(-(float) Math.random() * 10 - 3, 0.0f);
//        } else {
//            velocity.set((float) Math.random() * 10 - 3, 0.0f);
//        }
    }


    @Override
    public void move() {
        super.move();
        if (dir == Direction.RIGHT_TO_LEFT) {
            if (pos.right < -250) {
                pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3);
            }
        } else {
            if (pos.left >screenWidth) {
                pos.offsetTo(0, (float) Math.random() * screenHeight / 3);
            }
        }

    }


    @Override
    public void tick() {
        move();
    }

    @Override
    public Bitmap getExplodingImage(){
        return ImageCache.getAirplaneExposion();

    }

    @Override
    public int getPointValue() {
        if (size == Size.BIG) {
            return 15;
        } else if (size == Size.MEDIUM) {
            return 20;
        } else {
            return 75;
        }
    }
}
