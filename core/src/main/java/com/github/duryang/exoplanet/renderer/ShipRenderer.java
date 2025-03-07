package com.github.duryang.exoplanet.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.duryang.exoplanet.entity.Ship;

public class ShipRenderer {

    private final SpriteBatch batch;

    public ShipRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    public void render(Ship ship) {
        ship.sprite.draw(batch);
    }
}
