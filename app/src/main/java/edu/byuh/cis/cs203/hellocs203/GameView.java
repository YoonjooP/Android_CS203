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
            float watersize = w * 0.02f;
            if (init == false) {
                float shipsizew = w * 0.45f;
                float shipsizeh = h * 0.15f;
                battleship = Bitmap.createScaledBitmap(battleship,
                        (int) shipsizew, (int) shipsizeh, true);
                float bigAirsizew = w * 0.15f;
                float bigAirsizeh = h * 0.1f;
                bigAir = Bitmap.createScaledBitmap(bigAir,
                        (int) bigAirsizew, (int) bigAirsizeh, true);
                float bigSubsize = w * 0.2f;
                bigSub = Bitmap.createScaledBitmap(bigSub,
                        (int) bigSubsize, (int) bigSubsize, true);
                float litAirsizew = w * 0.04f;
                float litAirsizeh = h * 0.08f;
                litAir = Bitmap.createScaledBitmap(litAir,
                        (int) litAirsizew, (int) litAirsizeh, true);
                float litSubsizew = w * 0.04f;
                float litSubsizeh = h * 0.06f;
                litSub = Bitmap.createScaledBitmap(litSub,
                        (int) litSubsizew, (int) litSubsizeh, true);
                float medAirsizew = w * 0.1f;
                float medAirsizeh = h * 0.1f;
                medAir = Bitmap.createScaledBitmap(medAir,
                        (int) medAirsizew, (int) medAirsizeh, true);
                float medSubsize = w * 0.15f;
                medSub = Bitmap.createScaledBitmap(medSub,
                        (int) medSubsize, (int) medSubsize, true);

                water = Bitmap.createScaledBitmap(water,
                        (int) watersize, (int) watersize, true);

                init = true;
            }
            c.drawColor(Color.WHITE);

            for (float x = 0.0f; x < w; x += watersize) {
                c.drawBitmap(water, x, h/2, null);
            }
            float shipPosX = w/2 - battleship.getWidth()/2;
            float shipPosY = h/2 - battleship.getHeight()+watersize;
            c.drawBitmap(battleship, shipPosX, shipPosY, null);
            float baPosX = 3 * w/5;
            float baPosY = h/13;
            c.drawBitmap(bigAir, baPosX, baPosY, null);
            float bsPosX = 2 * w/5;
            float bsPosY = 2 * h/3;
            c.drawBitmap(bigSub, bsPosX, bsPosY, null);
            float laPosX = w/10;
            float laPosY = h/10;
            c.drawBitmap(litAir, laPosX, laPosY, null);
            float lsPosX = w/4;
            float lsPosY = 2 * h/3;
            c.drawBitmap(litSub, lsPosX, lsPosY, null);
            float maPosX = w/4;
            float maPosY = h/5;
            c.drawBitmap(medAir, maPosX, maPosY, null);
            float msPosX = w/2;
            float msPosY = h - 60;
            c.drawBitmap(medSub, msPosX, msPosY, null);
        }

}
