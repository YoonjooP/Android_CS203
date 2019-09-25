package edu.byuh.cis.cs203.hellocs203.sys;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.ui.Airplane;
import edu.byuh.cis.cs203.hellocs203.ui.Battleship;
import edu.byuh.cis.cs203.hellocs203.ui.Submarine;

public class GameView extends View {


    private Battleship battleship;
    private Airplane bigAir;
    private Submarine bigSub;
    private Airplane litAir;
    private Submarine litSub;
    private Airplane medAir;
    private Submarine medSub;
    private Bitmap water;

    private boolean init;

    public GameView (Context c) {
        super(c);
        init = false;
        battleship = new Battleship(getResources());
        bigAir = new Airplane(getResources());
        bigSub = new Submarine(getResources());
        litAir = new Airplane(getResources());
        litSub = new Submarine(getResources());
        medAir = new Airplane(getResources());
        medSub = new Submarine(getResources());
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
        //Timer tim = new Timer();
    }
        @Override
        /**
         * drawing pictures
         */
        public void onDraw (Canvas c){
        // screen size
            float w = c.getWidth();
            float h = c.getHeight();
            float watersize = w * 0.02f;
            if (init == false) {
                battleship.scale(w);
                bigAir.scale(w);
                litAir.scale(w);
                medAir.scale(w);
                bigSub.scale(w);
                litSub.scale(w);
                medAir.scale(w);
                medSub.scale(w);
                water = Bitmap.createScaledBitmap(water,
                        (int) watersize, (int) watersize, true);

                init = true;
            }
            c.drawColor(Color.WHITE);

            for (float x = 0.0f; x < w; x += watersize) {
                c.drawBitmap(water, x, h/2, null);
            }
            /* relative positions*/
            battleship.setPosition(w/2, h/2);
            bigAir.setPosition(3 * w/5, h/13);
            bigSub.setPosition(2 * w/5, 2 * h/3);
            litAir.setPosition(w/10, h/10);
            litSub.setPosition(w/4,2 * h/3);
            medAir.setPosition(w/4, h/5);
            medSub.setPosition(w/2, 9 * h/11);

            battleship.draw(c);
            bigAir.draw(c);
            bigSub.draw(c);
            litAir.draw(c);
            litSub.draw(c);
            medAir.draw(c);
            medSub.draw(c);
        }


}
