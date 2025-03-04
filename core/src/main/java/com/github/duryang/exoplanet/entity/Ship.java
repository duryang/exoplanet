package com.github.duryang.exoplanet.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.github.duryang.exoplanet.command.MoveCommand;
import com.github.duryang.exoplanet.physics.movement.Movement;

public abstract class Ship {

    public final Sprite sprite;

    // the ship's center coordinates
    public Vector2 position;

    protected Movement movement;

    public Ship(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        position = new Vector2(x, y);

        movement = Movement.NO_MOVEMENT;

        syncSpritePosition();
    }

    public abstract void update();

    public abstract void giveCommand(MoveCommand moveCommand);

    public void syncSpritePosition() {
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
    }
}
