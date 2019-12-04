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
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    private int highscore;
    private MediaPlayer depthchargesound, leftgunsound, planeexplodesound, rightgunsound, subexplodesound;

    /**
     * game view constructor
     * @param c
     */
    public GameView (Context c) {
        super(c);
        init = false;
        Enemy.setPreference(PrefActivity.getSpeedPref(getContext()), PrefActivity.getDirPref(getContext()));
        air = new ArrayList<>();
        sub = new ArrayList<>();
        mis = new ArrayList<>();
        dech = new ArrayList<>();
        star = new ArrayList<>();
        timer = new Timer();
        highscore = 0;
        depthchargesound = MediaPlayer.create(getContext(),
                R.raw.depth_charge);
        leftgunsound = MediaPlayer.create(getContext(),
                R.raw.left_gun);
        planeexplodesound = MediaPlayer.create(getContext(),
                R.raw.plane_explode);
        rightgunsound = MediaPlayer.create(getContext(),
                R.raw.right_gun);
        subexplodesound = MediaPlayer.create(getContext(),
                R.raw.sub_explode);

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
            c.drawText(getResources().getString(R.string.score_co)+score, 50, h/2+100, p);

            if (countdown%60 <10) {
                c.drawText(getResources().getString(R.string.time_co) + countdown/60 + ":0" + countdown%60, w-400, h/2+100, p);
            } else {
                c.drawText(getResources().getString(R.string.time_co) + countdown/60 + ":" + countdown%60, w-400, h/2+100, p);
            }

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

            trashhh.clear();

            List<Missile> doomed = mis.stream().filter(t -> t.getPos().bottom <0).collect(Collectors.toList());
            doomed.forEach(m->timer.unsubscribe(m));
            mis.removeIf(m->m.getPos().bottom<0);

            List<DepthCharge> dooomed = dech.stream().filter(t -> t.getPos().bottom <0).collect(Collectors.toList());
            dooomed.forEach(m->timer.unsubscribe(m));
            dech.removeIf(m->m.getPos().bottom<0);

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
                if (PrefActivity.getDepthPref(getContext())) {
                    dech.add(new DepthCharge());
                    timer.subscribe(dech.get(dech.size()-1));
                    dech.get(dech.size()-1).setPosition(w/2, h/2);
                } else {
                    if (dech.size()<1) {
                        dech.add(new DepthCharge());
                        timer.subscribe(dech.get(dech.size()-1));
                        dech.get(dech.size()-1).setPosition(w/2, h/2);
                    }
                }

                if (PrefActivity.getMusicPref(getContext())) {
                    depthchargesound.start();
                }
            } else {
                if (x>w/2){
                    if (PrefActivity.getMissilePref(getContext())) {
                        mis.add(new Missile(Direction.LEFT_TO_RIGHT));
                        timer.subscribe(mis.get(mis.size()-1));
                        mis.get(mis.size()-1).setPosition(w/2, h/2);
                        star.add(new Star(Direction.LEFT_TO_RIGHT));
                        timer.subscribe(star.get(star.size()-1));
                        star.get(star.size()-1).setPosition(w/2, h/2);
                    } else {
                        if (mis.size()<1) {
                            mis.add(new Missile(Direction.LEFT_TO_RIGHT));
                            timer.subscribe(mis.get(mis.size()-1));
                            mis.get(mis.size()-1).setPosition(w/2, h/2);
                            star.add(new Star(Direction.LEFT_TO_RIGHT));
                            timer.subscribe(star.get(star.size()-1));
                            star.get(star.size()-1).setPosition(w/2, h/2);
                        }
                    }

                    if (PrefActivity.getMusicPref(getContext())) {
                        rightgunsound.start();
                    }
                } else {
                    mis.add(new Missile(Direction.RIGHT_TO_LEFT));
                    timer.subscribe(mis.get(mis.size()-1));
                    mis.get(mis.size()-1).setPosition(w/2, h/2);
                    star.add(new Star(Direction.RIGHT_TO_LEFT));
                    timer.subscribe(star.get(star.size()-1));
                    star.get(star.size()-1).setPosition(w/2, h/2);
                    if (PrefActivity.getMusicPref(getContext())) {
                        leftgunsound.start();
                    }

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
            timer.setGameover();
            AlertDialog.Builder eg = new AlertDialog.Builder(getContext());
            eg.setTitle(R.string.battleship_war_title)
                    .setMessage(R.string.game_over_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yesyes, (d, i) -> {
                            timer.setGameNotover();
                            air.clear();
                            sub.clear();
                            mis.clear();
                            dech.clear();
                            countdown = 10;
                            resetGame();
                    })
                    .setNegativeButton(R.string.nono, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Activity parent = (Activity) getContext();
                            parent.finish();
                        }
                    });
            AlertDialog.Builder hs = new AlertDialog.Builder(getContext());
            hs.setTitle(R.string.battleship_war_title)
                    .setMessage(R.string.hurray_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yesyes, (d, i) -> {
                        timer.setGameNotover();
                        air.clear();
                        sub.clear();
                        mis.clear();
                        dech.clear();
                        countdown = 10;
                        saveScore();
                        resetGame();
                    })
                    .setNegativeButton(R.string.nono, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Activity parent = (Activity) getContext();
                            parent.finish();
                        }
                    });


            Log.d("High, this:", Integer.toString(highscore) + ", " + Integer.toString(score));
            if (score <= highscore) {
                AlertDialog box = eg.create();
                box.show();
            } else {
                AlertDialog box2 = hs.create();
                box2.show();
            }

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
                    if (PrefActivity.getMusicPref(getContext())) {
                        planeexplodesound.start();
                    }

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
                    if (PrefActivity.getMusicPref(getContext())) {
                        subexplodesound.start();
                    }
                    traash.add(d);
                }
            }
        }
        for (Sprite s : traash) {
            dech.remove(s);
        }
        traash.clear();
    }

    /**
     * reset the game
     */
    public void resetGame() {
        loadScore();
        ImageCache.init(getResources(),w,h);
        Enemy.setwh(w,h);
        battleship = Battleship.getInstance();
        timer.subscribe(battleship);
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
        for (int i=0; i<PrefActivity.getLevelPref(getContext()); i++){
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
        score = 0;
    }

    /**
     * save score
     */
    public void saveScore(){
        try (OutputStream os = getContext().openFileOutput("score.txt", Context.MODE_PRIVATE)){
            String scr = ""+ score;
            Log.d("Writing:", scr);
            os.write(scr.getBytes());
        } catch (IOException e) {
            Log.d("File error:", "Writing Error");
        }
    }

    /**
     * load score
     */
    private void loadScore() {
        try {
            FileInputStream fis = getContext().openFileInput("score.txt");
            Scanner s = new Scanner(fis);
            String lana = s.nextLine();
            highscore = Integer.parseInt(lana);
            Log.d("Read:", Integer.toString(highscore));
            s.close();
        } catch (FileNotFoundException e) {
            Log.d("File error:", "FNFE");
        }
    }

    /**
     * on puase
     */
    public void onPause() {
        timer.onPause();
    }

    /**
     * on resume
     */
    public void onResume() {
        timer.onResume();
    }


}
