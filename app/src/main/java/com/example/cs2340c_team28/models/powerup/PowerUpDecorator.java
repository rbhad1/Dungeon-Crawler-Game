package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;

public abstract class PowerUpDecorator extends PowerUp {
    private PowerUp wrapped;

    public PowerUpDecorator(PowerUp wrapped, Texture texture) {
        super(texture);
        this.wrapped = wrapped;
    }

    public PowerUp getWrapped() {
        return wrapped;
    }

    public void setWrapped(PowerUp wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public final void apply() {
        super.apply();
        if (wrapped != null) {
            wrapped.apply();
        }
    }
}
