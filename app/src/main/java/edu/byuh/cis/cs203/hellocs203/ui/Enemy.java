package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.res.Resources;

/**
 * enemy abstract class
 */
public abstract class Enemy extends Sprite {

    protected Size size;

    public Enemy(Resources r) {
        super(r);
    }
}
