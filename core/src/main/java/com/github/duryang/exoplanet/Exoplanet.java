package com.github.duryang.exoplanet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Exoplanet extends Game {

    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
