package com.github.duryang.exoplanet.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.duryang.exoplanet.entity.Ship;
import com.github.duryang.exoplanet.renderer.ShipRenderer;

public class BattleRenderer {

    private final SpriteBatch batch;
    private final Battle battle;

    private final ShipRenderer shipRenderer;

    public BattleRenderer(SpriteBatch batch, Battle battle) {
        this.batch = batch;
        this.battle = battle;

        shipRenderer = new ShipRenderer(batch);
    }

    public void render() {
        for (Side side : battle.getSides()) {
            for (Ship ship : side.getShips()) {
                shipRenderer.render(ship);
            }
        }
    }
}
