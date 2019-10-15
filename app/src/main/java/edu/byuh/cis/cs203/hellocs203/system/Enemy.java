package edu.byuh.cis.cs203.hellocs203.system;



public abstract class Enemy extends Sprite {

    /**
     * enemy abstract class
     */
    public Enemy() {
        super();
    }

    @Override
    public void move() {
        super.move();
        randomizeSpeed();
    }

    public void randomizeSpeed() {
        if (Math.random()<0.1) {
            float v = (float)((Math.random()*15+6) * Math.signum(velocity.x));
            velocity.set(v, 0);
        }

    }
}
