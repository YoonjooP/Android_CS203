package edu.byuh.cis.cs203.hellocs203.system;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;


public abstract class Sprite implements TickListener{
    protected Bitmap bitmap;
    protected RectF pos;
    protected PointF velocity;


    /**
     * scale, position, draw
     */
    public Sprite() {
        pos = new RectF();
        velocity = new PointF();
    }



    /**
     * bitmap center
     * @param x center
     * @param y center
     */
    public void setPosition(float x, float y) {
        pos.offsetTo(x-bitmap.getWidth()/2, y-bitmap.getHeight()/2);

    }

    /**
     * drawBitmap
     * @param c
     */
    public void draw(Canvas c) {
        c.drawBitmap(bitmap, pos.left, pos.top, null);
    }

    /**
     * move
     */
    public void move() {
        pos.offset (velocity.x, velocity.y);
    }

    /**
     * get pos method
     * @return pos
     */
    public RectF getPos() {
        return pos;
    }

    /**
     * check if it's overlapped
     * @param other
     * @return
     */
    public boolean overlaps (Sprite other) {
        return RectF.intersects(this.pos, other.pos);
    }

}
