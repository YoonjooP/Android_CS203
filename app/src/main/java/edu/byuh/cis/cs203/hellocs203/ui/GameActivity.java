package edu.byuh.cis.cs203.hellocs203.ui;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    GameView vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vi = new GameView(this);
        setContentView(vi);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        vi.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        vi.onResume();
    }
}
