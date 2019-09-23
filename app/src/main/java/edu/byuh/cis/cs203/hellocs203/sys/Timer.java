package edu.byuh.cis.cs203.hellocs203.sys;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Timer extends Handler {

    public Timer() {
        sendMessageDelayed(obtainMessage(), 0);
    }

    /**
     * Override this method to do whatever you need
     * done on a recurring bassis.
     * @param m an Android message object (not using)
     */
    @Override
    public void handleMessage(Message m) {
        Log.d("CS203", "Hello Luke!")
                sendMessageDelayed(obtainMessage(), 1000);
    }
}