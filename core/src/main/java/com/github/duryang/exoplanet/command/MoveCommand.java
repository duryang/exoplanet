package com.github.duryang.exoplanet.command;

import com.badlogic.gdx.math.Vector2;
import lombok.Value;

@Value
public class MoveCommand {
    Vector2 destination;

    public MoveCommand(float x, float y) {
        destination = new Vector2(x, y);
    }
}
