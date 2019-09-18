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
                float bigAirsize = w * 0.2f;
                bigAir = Bitmap.createScaledBitmap(bigAir,
                        (int) bigAirsize, (int) bigAirsize, true);
                float bigSubsize = w * 0.2f;
                bigSub = Bitmap.createScaledBitmap(bigSub,
                        (int) bigSubsize, (int) bigSubsize, true);
                float litAirsize = w * 0.1f;
                litAir = Bitmap.createScaledBitmap(litAir,
                        (int) litAirsize, (int) litAirsize, true);
                float litSubsize = w * 0.1f;
                litSub = Bitmap.createScaledBitmap(litSub,
                        (int) litSubsize, (int) litSubsize, true);
                float medAirsize = w * 0.15f;
                medAir = Bitmap.createScaledBitmap(medAir,
                        (int) medAirsize, (int) medAirsize, true);
                float medSubsize = w * 0.15f;
                medSub = Bitmap.createScaledBitmap(medSub,
                        (int) medSubsize, (int) medSubsize, true);
                float watersize = w * 0.05f;
                water = Bitmap.createScaledBitmap(water,
                        (int) watersize, (int) watersize, true);

                init = true;
            }
            c.drawColor(Color.GREEN);
            c.drawBitmap(battleship, 0, 0, null);
            c.drawBitmap(bigAir, 0, 0, null);
            c.drawBitmap(bigSub, 0, 0, null);
            c.drawBitmap(litAir, 0, 0, null);
            c.drawBitmap(litSub, 0, 0, null);
            c.drawBitmap(medAir, 0, 0, null);
            c.drawBitmap(medSub, 0, 0, null);
            c.drawBitmap(water, 0, 0, null);
        }

}
