package edu.byuh.cis.cs203.hellocs203;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class GameView extends View {


    private Bitmap battleship;
    private Bitmap bigAir;
    private Bitmap bigSub;
    private Bitmap litAir;
    private Bitmap litSub;
    private Bitmap medAir;
    private Bitmap medSub;
    private Bitmap water;

    private boolean init;

    public GameView (Context c) {
        super(c);
        init = false;
        battleship = BitmapFactory.decodeResource(getResources(), R.drawable.battleship);
        bigAir = BitmapFactory.decodeResource(getResources(), R.drawable.big_airplane);
        bigSub = BitmapFactory.decodeResource(getResources(), R.drawable.big_submarine);
        litAir = BitmapFactory.decodeResource(getResources(), R.drawable.little_airplane);
        litSub = BitmapFactory.decodeResource(getResources(), R.drawable.little_submarine);
        medAir = BitmapFactory.decodeResource(getResources(), R.drawable.medium_airplane);
        medSub = BitmapFactory.decodeResource(getResources(), R.drawable.medium_submarine);
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
    }
        @Override
        public void onDraw (Canvas c){
            float w = c.getWidth();
            float h = c.getHeight();
            if (init == false) {
                float shipsize = w * 0.2f;
                battleship = Bitmap.createScaledBitmap(battleship,
                        (int) shipsize, (int) shipsize, true);
                init = true;
            }
            c.drawColor(Color.GREEN);
            c.drawBitmap(battleship, 0, 0, null);
            //somechanges123
        }

}
