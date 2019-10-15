package edu.byuh.cis.cs203.hellocs203.system;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class Timer extends Handler {

    ArrayList<TickListener> tili;

    public Timer () {

        tili = new ArrayList<>();
        sendMessageDelayed(obtainMessage(), 0);

        }

        public void subscibe (TickListener t) {
            tili.add(t);
        }

        public void unsubscibe (TickListener t) {
            tili.remove(t);
        }


        @Override
        public void handleMessage (Message m) {


            sendMessageDelayed(obtainMessage(), 50);

        }
}
