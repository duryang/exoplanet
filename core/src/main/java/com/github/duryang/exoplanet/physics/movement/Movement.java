package com.github.duryang.exoplanet.physics.movement;

import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

public abstract class Movement {

    public static Movement NO_MOVEMENT = new NoMovement();

    @Getter
    protected Vector2 velocity;

    public Movement() {
        velocity = new Vector2();
    }

    public abstract void move();
    public abstract boolean arrived();

    private static class NoMovement extends Movement {

        private NoMovement() {
            super();
        }

        @Override
        public void move() {
            throw new UnsupportedOperationException("Can't move a stationary object.");
        }

        @Override
        public boolean arrived() {
            return true;
        }
    }
}
