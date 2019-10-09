package edu.byuh.cis.cs203.hellocs203.system;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Missile extends Sprite {

    private Direction dir;

    /**
     * missile
     * @param d
     */
    public Missile (Direction d) {
        super();
        dir = d;
        pos.set(0, 0, 30, 30);
        if (dir == Direction.LEFT_TO_RIGHT) {
            velocity.set(15.0f, -15.0f);
        } else {
            velocity.set(-15.0f, -15.0f);
        }
    }

    @Override
    public void draw(Canvas c) {
        Paint pinkPaint = new Paint();
        Paint purplePaint = new Paint();
        pinkPaint.setColor(Color.rgb(255, 103, 130));
        purplePaint.setColor(Color.rgb(146, 79, 207));
        pinkPaint.setStrokeWidth(15.0f);
        purplePaint.setStrokeWidth(15.0f);
        if (dir == Direction.LEFT_TO_RIGHT){
            c.drawLine(pos.left, pos.bottom, pos.right, pos.top, pinkPaint);
        } else {
            c.drawLine(pos.left, pos.top, pos.right, pos.bottom, purplePaint);
        }

    }

    @Override
    public void setPosition(float x, float y) {
        if (dir == Direction.LEFT_TO_RIGHT){
            pos.offsetTo(x-pos.width()/2+395, y-pos.height()/2-155);
        } else {
            pos.offsetTo(x-pos.width()/2-360, y-pos.height()/2-150);
        }
    }

    @Override
    protected float relativeWidth() {
        return 0;
    }
}
