package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.system.Airplane;
import edu.byuh.cis.cs203.hellocs203.system.Battleship;
import edu.byuh.cis.cs203.hellocs203.system.DepthCharge;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.system.Enemy;
import edu.byuh.cis.cs203.hellocs203.system.ImageCache;
import edu.byuh.cis.cs203.hellocs203.system.Missile;
import edu.byuh.cis.cs203.hellocs203.system.Star;
import edu.byuh.cis.cs203.hellocs203.system.Submarine;
import edu.byuh.cis.cs203.hellocs203.system.TickListener;
import edu.byuh.cis.cs203.hellocs203.system.Timer;

public class GameView extends View implements TickListener {


    private Battleship battleship;
    private Bitmap water;
    ArrayList<Airplane> air;
    ArrayList<Submarine> sub;
    ArrayList<Missile> mis;
    ArrayList<Missile> trashh;
    ArrayList<DepthCharge> dech;
    ArrayList<DepthCharge> trash;
    ArrayList<Star> star;
    ArrayList<Star> trashhh;
    private boolean init;
    float w;
    float h;
    private Timer timer;

    /**
     * game view constructor
     * @param c
     */
    public GameView (Context c) {
        super(c);
        init = false;

        air = new ArrayList<>();
        sub = new ArrayList<>();
        mis = new ArrayList<>();
        dech = new ArrayList<>();
        star = new ArrayList<>();
        timer = new Timer();

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
                ImageCache.init(getResources(),w,h);
                Enemy.setwh(w,h);
                battleship = new Battleship();
                timer.subscribe(battleship);
                water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
                for (int i=0; i<5; i++){
                    air.add(new Airplane());
                    timer.subscribe(air.get(air.size()-1));
                    sub.add(new Submarine());
                    timer.subscribe(sub.get(sub.size()-1));
                }

                for ( Airplane a : air ) {
                    a.setPosition(w, (float)Math.random()*h/3);
                }
                for ( Submarine s : sub ) {
                    s.setPosition(0, (float)Math.random()*h/3+2*h/3);
                }
                water = Bitmap.createScaledBitmap(water,
                        (int) watersize, (int) watersize, true);

                timer.subscribe(this);

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
            for (Star st : star ) {
                st.draw(c);
            }
            for ( Missile mi : mis ) {
                mi.draw(c);
            }
            for ( DepthCharge dc : dech ) {
                dc.draw(c);
            }
            trash = new ArrayList<>();
            trashh = new ArrayList<>();
            trashhh = new ArrayList<>();
            for (Star st : star) {
                if (st.getIsdraw() == true) {
                    trashhh.add(st);
                }
            }
            for (Star st : trashhh) {
                star.remove(st);
            }

            for (DepthCharge de : dech ) {
                if (de.getPos().top>h){
                    trash.add(de);
                }
            }
            for (DepthCharge de: trash) {
                dech.remove(de);
            }
            for (Missile mi : mis ) {
                if (mi.getPos().bottom<0) {
                    trashh.add(mi);
                }
            }
            for (Missile mi : trashh) {
                mis.remove(mi);
            }

        }



    @Override
    public boolean onTouchEvent(MotionEvent m) {
        float x = m.getX();
        float y = m.getY();
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            if (y>h/2) {
                dech.add(new DepthCharge());
                timer.subscribe(dech.get(dech.size()-1));
                dech.get(dech.size()-1).setPosition(w/2, h/2);

            } else {
                if (x>w/2){
                    mis.add(new Missile(Direction.LEFT_TO_RIGHT));
                    timer.subscribe(mis.get(mis.size()-1));
                    mis.get(mis.size()-1).setPosition(w/2, h/2);
                    star.add(new Star(Direction.LEFT_TO_RIGHT));
                    timer.subscribe(star.get(star.size()-1));
                    star.get(star.size()-1).setPosition(w/2, h/2);
                } else {
                    mis.add(new Missile(Direction.RIGHT_TO_LEFT));
                    timer.subscribe(mis.get(mis.size()-1));
                    mis.get(mis.size()-1).setPosition(w/2, h/2);
                    star.add(new Star(Direction.RIGHT_TO_LEFT));
                    timer.subscribe(star.get(star.size()-1));
                    star.get(star.size()-1).setPosition(w/2, h/2);
                }
            }


        }
        return true;
    }

    @Override
    public void tick() {
        invalidate();
    }
}
