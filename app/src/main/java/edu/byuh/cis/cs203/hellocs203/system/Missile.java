package edu.byuh.cis.cs203.hellocs203.system;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Missile extends Sprite {

    private Direction dir;

    public Missile (Direction d) {
        super();
        dir = d;
        pos.set(0, 0, 10, 10);
        if (dir == Direction.LEFT_TO_RIGHT) {
            velocity.set(5.0f, -5.0f);
        } else {
            velocity.set(-5.0f, -5.0f);
        }
    }

    @Override
    public void draw(Canvas c) {
        Paint pinkPaint = new Paint();
        pinkPaint.setColor(Color.rgb(255, 196, 203));
        pinkPaint.setStrokeWidth(15.0f);
        if (dir == Direction.LEFT_TO_RIGHT){
            c.drawLine(pos.left, pos.bottom, pos.right, pos.top, pinkPaint);
        } else {
            c.drawLine(pos.left, pos.top, pos.right, pos.bottom, pinkPaint);
        }

    }

    @Override
    public void setPosition(float x, float y) {
        pos.offsetTo(x-pos.width()/2, y-pos.height()/2);
    }

    @Override
    protected float relativeWidth() {
        return 0;
    }
}
