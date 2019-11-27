package edu.byuh.cis.cs203.hellocs203.system;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class Timer extends Handler {

    ArrayList<TickListener> tili;
    boolean gameover;
    boolean check;
    /**
     * Handler subclass timer
     */
    public Timer () {
        check = true;
        tili = new ArrayList<>();
        gameover = false;
        sendMessageDelayed(obtainMessage(), 0);

        }

    /**
     * subscribe
     * @param t ticklistner
     */
    public void subscribe (TickListener t) {
            tili.add(t);
        }

    public void setGameover () {
        gameover = true;
        }

    public void setGameNotover () { gameover = false;}

    /**
     * unsubscribe
     * @param t ticklistener
     */
    public void unsubscribe (TickListener t) {
            tili.remove(t);
        }

    /**
     * handle message
     * @param m message
     */
    @Override
        public void handleMessage (Message m) {
            for ( TickListener t : tili) {
                t.tick();
            }
            if (gameover == true) {
                tili.clear();
            }
            if (check == true) {
                sendMessageDelayed(obtainMessage(), 50);
            }

        }

        public void onPause(){
            check = false;

        }

        public void onResume() {
            check = true;
            sendMessageDelayed(obtainMessage(), 0);
        }
}
