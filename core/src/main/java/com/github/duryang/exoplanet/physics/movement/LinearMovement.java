package com.github.duryang.exoplanet.physics.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class LinearMovement extends Movement {

    private final Vector2 position;
    private final Vector2 destination;
    private final float speed;

    public LinearMovement(Vector2 position, Vector2 destination, float speed) {
        super();

        this.position = position;
        this.destination = destination;
        this.speed = speed;
    }

    @Override
    public void move() {
        // velocity is adjusted to minimize incremental error
        velocity.set(destination).sub(position).nor().scl(speed);

        float delta = Gdx.graphics.getDeltaTime();

        position.mulAdd(velocity, delta);
    }

    @Override
    public boolean arrived() {
        return position.dst(destination) < 2f;
    }
}
