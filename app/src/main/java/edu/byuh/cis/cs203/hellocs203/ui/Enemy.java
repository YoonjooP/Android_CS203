package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;


public abstract class Enemy extends Sprite {

    protected Size size;
    /**
     * enemy abstract class
     */
    public Enemy(Resources r) {
        super(r);
    }
}
