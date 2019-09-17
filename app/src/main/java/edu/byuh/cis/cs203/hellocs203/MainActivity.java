package edu.byuh.cis.cs203.hellocs203;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LetiView lv = new LetiView(this);
        setContentView(lv);
    }
}
