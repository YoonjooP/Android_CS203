package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.Bitmap;
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
        bitmap = ImageCache.getAirplaneImage(size, dir);

        pos.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }


    @Override
    public void move() {
        super.move();
        if (pos.right < -250) {
            reset();
            bitmap = ImageCache.getAirplaneImage(size,dir);
            this.pos.set(0,0,bitmap.getWidth(), bitmap.getHeight());
            pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3);
        } else if (pos.left >screenWidth) {
            reset();
            bitmap = ImageCache.getAirplaneImage(size,dir);
            this.pos.set(0,0,bitmap.getWidth(), bitmap.getHeight());
            pos.offsetTo(-250, (float) Math.random() * screenHeight / 3);
        }
        if(pos.top > screenHeight/2) {
            pos.offsetTo(screenWidth, (float) Math.random() * screenHeight / 3+70);
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
