package edu.byuh.cis.cs203.hellocs203.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import edu.byuh.cis.cs203.hellocs203.R;

public class SplashActivity extends Activity {

    private ImageView iv;

    /**
     * override onCreate
     * @param b
     */
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        iv = new ImageView(this);
        iv.setImageResource(R.drawable.splash);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(iv);
    }

    /**
     * onTouchEvent
     * @param m
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent m) {
        float w = iv.getWidth();
        float h = iv.getHeight();
        RectF prefsButton = new RectF(0, 0, 0.1f*w, 0.167f*h);
        RectF aboutButton = new RectF(0, 0.83f*h, 0.1f*w, h);
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            float x = m.getX();
            float y = m.getY();
            if (prefsButton.contains(x,y)) {
                Log.d("CS203", "tapped inside prefs button");
                Intent pref = new Intent(this, PrefActivity.class);
                startActivity(pref);
            } else if (aboutButton.contains(x,y)) {
                Log.d("CS203", "about button!");
                AlertDialog.Builder eg = new AlertDialog.Builder(iv.getContext());
                eg.setTitle(R.string.battleship_war_title)
                        .setMessage(R.string.game_over_message)
                        .setCancelable(true);
                AlertDialog box = eg.create();
                box.show();
            } else {
                Log.d("CS203", "start button!");
                Intent start = new Intent(this, GameActivity.class);
                startActivity(start);
            }
        }
        return true;
    }
}


