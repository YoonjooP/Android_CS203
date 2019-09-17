package edu.byuh.cis.cs203.hellocs203;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

public class LetiView extends View {

    private Bitmap duck;
    private boolean init;

    public LetiView(Context c) {
        super(c);
        init = false;
        duck = BitmapFactory.decodeResource(getResources(), R.drawable.duck);
        Toast t = Toast.makeText(c, "This is another factory example!", Toast.LENGTH_LONG);
        t.show();
}

    @Override
    public void onDraw(Canvas c) {
        float w = c.getWidth();
        if (init == false) {
            float duckSize = w * 0.2f;
            duck = Bitmap.createScaledBitmap(duck,
                    (int)duckSize, (int)duckSize, true);
            init = true;
        }
        c.drawColor(Color.GREEN);
        c.drawBitmap(duck, 0, 0, null);
    }

}
