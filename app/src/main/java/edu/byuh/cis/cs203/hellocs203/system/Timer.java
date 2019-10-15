package edu.byuh.cis.cs203.hellocs203.system;

import android.os.Handler;
import android.os.Message;

public class Timer extends Handler {
    public Timer () {
            sendMessageDelayed(obtainMessage(), 0);
        }

        @Override
        public void handleMessage (Message m) {


            sendMessageDelayed(obtainMessage(), 50);

        }
}
