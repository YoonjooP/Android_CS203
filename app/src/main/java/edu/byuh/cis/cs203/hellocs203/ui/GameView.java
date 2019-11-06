package edu.byuh.cis.cs203.hellocs203.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.byuh.cis.cs203.hellocs203.R;
import edu.byuh.cis.cs203.hellocs203.system.Airplane;
import edu.byuh.cis.cs203.hellocs203.system.Battleship;
import edu.byuh.cis.cs203.hellocs203.system.DepthCharge;
import edu.byuh.cis.cs203.hellocs203.misc.Direction;
import edu.byuh.cis.cs203.hellocs203.system.Enemy;
import edu.byuh.cis.cs203.hellocs203.system.ImageCache;
import edu.byuh.cis.cs203.hellocs203.system.Missile;
import edu.byuh.cis.cs203.hellocs203.system.Sprite;
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
    private int score;
    int countdown = 10;
    long timeBefore, timeNow;

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
                resetGame();
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
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setTextSize(70.0F);
            c.drawText("Score :"+score, 50, h/2+100, p);

            if (countdown%60 <10) {
                c.drawText("Time: " + countdown/60 + ":0" + countdown%60, w-400, h/2+100, p);
            } else {
                c.drawText("Time: " + countdown/60 + ":" + countdown%60, w-400, h/2+100, p);
            }

            trash = new ArrayList<>();
            trashh = new ArrayList<>();
            trashhh = new ArrayList<>();
            for (Star st : star) {
                if (st.getIsdraw() == true) {
                    trashhh.add(st);
                    timer.unsubscribe(st);
                }
            }
            for (Star st : trashhh) {
                star.remove(st);
            }

            for (DepthCharge de : dech ) {
                if (de.getPos().top>h){
                    trash.add(de);
                    timer.unsubscribe(de);
                }
            }
            for (DepthCharge de: trash) {
                dech.remove(de);
            }
            for (Missile mi : mis ) {
                if (mi.getPos().bottom<0) {
                    trashh.add(mi);
                    timer.unsubscribe(mi);
                }
            }
            for (Missile mi : trashh) {
                mis.remove(mi);
            }

            trash.clear();
            trashh.clear();
            trashhh.clear();

        }


    /**
     * on touch event
     * @param m motion event
     * @return boolean true
     */
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

    /**
     * tick
     */
    @Override
    public void tick() {
        invalidate();
        detectCollisions();

        timeNow = System.currentTimeMillis();
        if (countdown != 0) {
            if (timeNow - timeBefore > 1000) {
                countdown--;
                timeBefore = timeNow;
            }
        } else {
            saveScore();
            timer.setGameover();
            AlertDialog.Builder eg = new AlertDialog.Builder(getContext());
            eg.setTitle("Battleship War")
                    .setMessage("Game Over! Try again?")
                    .setCancelable(false)
                    .setPositiveButton("Yes!", (d, i) -> {
                            timer.setGameNotover();
                            air.clear();
                            sub.clear();
                            mis.clear();
                            dech.clear();
                            countdown = 180;
                            score = 0;
                            resetGame();
//                            loadScore();

                    })
                    .setNegativeButton("No!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Activity parent = (Activity) getContext();
                            parent.finish();
                        }
                    });
            AlertDialog box = eg.create();
            box.show();
        }

    }

    /**
     * when the sprites overlapped
     */
    public void detectCollisions() {
        ArrayList<Sprite> traash = new ArrayList<>();
        for (Missile m : mis) {
            for (Airplane a : air) {
                if (m.overlaps(a)) {
                    score += a.getPointValue();
                    a.explode();
                    traash.add(m);
                }
            }
        }
        for (Sprite s : traash) {
            mis.remove(s);
        }
        traash.clear();

        for (DepthCharge d : dech) {
            for (Submarine s : sub) {
                if (d.overlaps(s)){
                    score += s.getPointValue();
                    s.explode();
                    traash.add(d);
                }
            }
        }
        for (Sprite s : traash) {
            dech.remove(s);
        }
        traash.clear();
    }

    public void resetGame() {

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
        water = ImageCache.getWaterImage();

        timer.subscribe(this);
    }

    public void saveScore(){
        try {
            FileOutputStream fos = getContext().openFileOutput("score.txt", Context.MODE_PRIVATE);
            String scr = ""+ score;
            fos.write(scr.getBytes());
            fos.close();
        } catch (IOException e) {
            //blissfully ignore
        }
    }

    private void loadScore() {
        try {
            FileInputStream fis = getContext().openFileInput("score.txt");
            Scanner s = new Scanner(fis);
            String lana = s.nextLine();
            score = Integer.parseInt(lana);
            s.close();
        } catch (FileNotFoundException e) {
            score = 0;
        }
    }


}
