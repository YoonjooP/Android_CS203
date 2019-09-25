package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

public abstract class Sprite {
    protected Bitmap bitmap;
    protected RectF pos;

    public Sprite(Resources r) {
        pos = new RectF();

    }

    protected abstract float relativeWidth();

    public void scale(float screenWidth) {

        float sclX = screenWidth * relativeWidth();
        float sclY = sclX * ((float)bitmap.getHeight() / (float)bitmap.getWidth());

        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int)sclX, (int)sclY, true);
    }

    public void setPosition(float x, float y) {
        pos.offsetTo(x-bitmap.getWidth()/2, y-bitmap.getHeight()/2);
    }

    public void draw(Canvas c) {
        c.drawBitmap(bitmap, pos.left, pos.top, null);
    }
}
