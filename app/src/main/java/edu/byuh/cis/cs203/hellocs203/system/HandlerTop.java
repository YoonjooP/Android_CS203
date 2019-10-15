package edu.byuh.cis.cs203.hellocs203.system;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class HandlerTop extends Handler {

        ArrayList<TickListener> tili;

        public interface TickListener {

        }

        public class HandlerTop implements TickListener {
            public void tick() {

            }
        }



}

