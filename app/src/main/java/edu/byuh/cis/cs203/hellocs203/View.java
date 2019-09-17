package edu.byuh.cis.cs203.hellocs203;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class View {


    private Bitmap battleship;
    private boolean init;

    public Activity (Context c);
        super(c);
    init = false;
    battleship = BitmapFactory.decodeResource(getResources(), R.drawable.battleship);

    @Override
    public void onDraw(Canvas c) {
        float w = c.getWidth();
        if (init == false) {
            float shipsize = w * 0.2f;
            ship = Bitmap.createScaledBitmap(duck,
                    (int)duckSize, (int)duckSize, true);
            init = true;
        }
        c.drawColor(Color.GREEN);
        c.drawBitmap(duck, 0, 0, null);
        //somechanges123
    }

}
