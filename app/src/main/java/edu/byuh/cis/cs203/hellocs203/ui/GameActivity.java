package edu.byuh.cis.cs203.hellocs203.ui;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView vi = new GameView(this);
        setContentView(vi);
    }
}
