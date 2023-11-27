package com.example.cs2340c_team28.models.powerup;

public class Decorator {
    PowerUp wrapped;

    public Decorator(PowerUp wrapped) {
        this.wrapped = wrapped;
    }

    public void activate() {
        wrapped.activate();
    }
}
