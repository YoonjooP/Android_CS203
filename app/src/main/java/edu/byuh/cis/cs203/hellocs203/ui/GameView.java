package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.system.Airplane;
import edu.byuh.cis.cs203.hellocs203.system.Battleship;
import edu.byuh.cis.cs203.hellocs203.system.DepthCharge;
import edu.byuh.cis.cs203.hellocs203.system.Direction;
import edu.byuh.cis.cs203.hellocs203.system.Missile;
import edu.byuh.cis.cs203.hellocs203.system.Submarine;

public class GameView extends View {


    private Battleship battleship;
    private Bitmap water;
    ArrayList<Airplane> air;
    ArrayList<Submarine> sub;
    ArrayList<Missile> mis;
    ArrayList<DepthCharge> dech;
    private boolean init;
    float w;
    float h;

    /**
     * game view constructor
     * @param c
     */
    public GameView (Context c) {
        super(c);
        init = false;
        battleship = new Battleship(getResources());
        air = new ArrayList<>();
        sub = new ArrayList<>();
        mis = new ArrayList<>();
        dech = new ArrayList<>();
        for (int i=0; i<5; i++){
            air.add(new Airplane(getResources()));
            sub.add(new Submarine(getResources()));
        }
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);

        Timer timer = new Timer();
    }
        @Override
        /**
         * drawing pictures
         */
        public void onDraw (Canvas c){
        // screen size
            w = c.getWidth();
            h = c.getHeight();
            float watersize = w * 0.02f;

            if (init == false) {
                battleship.scale(w);
                for ( Airplane a : air ) {
                    a.scale(w);
                    a.getH(h);
                    a.setPosition(w, (float)Math.random()*h/3);
                }
                for ( Submarine s : sub ) {
                    s.scale(w);
                    s.getH(h);
                    s.setPosition(0, (float)Math.random()*h/3+2*h/3);
                }
                water = Bitmap.createScaledBitmap(water,
                        (int) watersize, (int) watersize, true);

                init = true;
            }
            c.drawColor(Color.WHITE);

            for (float x = 0.0f; x < w; x += watersize) {
                c.drawBitmap(water, x, h/2, null);
            }
            /* relative positions*/
            battleship.setPosition(w/2, h/2-watersize);


            battleship.draw(c);
            for ( Airplane a : air) {
                a.draw(c);
            }
            for ( Submarine s : sub ) {
                s.draw(c);
            }
            for ( Missile mi : mis ) {
                mi.draw(c);
            }
            for ( DepthCharge dc : dech ) {
                dc.draw(c);
            }



        }

    /**
     * Inner class
     */
    public class Timer extends Handler {

        public Timer () {
            sendMessageDelayed(obtainMessage(), 0);
        }

        @Override
        public void handleMessage (Message m) {

            for ( Airplane a : air ) {
                a.move();
            }
            for ( Submarine s : sub ) {
                s.move();
            }
            for ( Missile mi : mis ) {
                mi.move();
            }
            for ( DepthCharge dc : dech ) {
                dc.move();
            }
            invalidate();
            sendMessageDelayed(obtainMessage(), 50);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        float x = m.getX();
        float y = m.getY();
        if (m.getAction() == MotionEvent.ACTION_MOVE) {
            if (y>h/2) {
                Log.d("Half screen:", "Clicked!");
                dech.add(new DepthCharge(getResources()));
                dech.get(dech.size()-1).scale(w);
                dech.get(dech.size()-1).setPosition(w/2, h/2);
            } else {
                if (x>w/2){
                    mis.add(new Missile(Direction.LEFT_TO_RIGHT));
                    mis.get(mis.size()-1).setPosition(w/2, h/2);
                } else {
                    mis.add(new Missile(Direction.RIGHT_TO_LEFT));
                    mis.get(mis.size()-1).setPosition(w/2, h/2);
                }
            }
        }
        return true;
    }

}