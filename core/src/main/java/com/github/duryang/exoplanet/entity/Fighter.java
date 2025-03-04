package com.github.duryang.exoplanet.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.duryang.exoplanet.command.MoveCommand;
import com.github.duryang.exoplanet.physics.movement.LinearMovement;
import com.github.duryang.exoplanet.physics.movement.Movement;

public class Fighter extends Ship {

    private static final float baseSpeed = 200f;
    private static final float rotationSpeed = 5f;

    public Fighter(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    public Fighter(Texture texture) {
        super(texture, 0, 0);
    }

    @Override
    public void update() {
        if (movement != Movement.NO_MOVEMENT) {
            movement.move();

            if (movement.arrived()) {
                movement = Movement.NO_MOVEMENT;
            }
        }

        rotate(movement.getVelocity());

        syncSpritePosition();
    }

    private void rotate(Vector2 velocity) {
        if (velocity.isZero()) {
            return;
        }

        float currentAngle = sprite.getRotation();
        float movementAngle = velocity.angleDeg() - 90f;

        float delta = Gdx.graphics.getDeltaTime();

        float newRotation = MathUtils.lerpAngleDeg(currentAngle, movementAngle, rotationSpeed * delta);

        sprite.setRotation(newRotation);
    }

    @Override
    public void giveCommand(MoveCommand command)
    {
        movement = new LinearMovement(position, command.getDestination(), baseSpeed);
    }
}
