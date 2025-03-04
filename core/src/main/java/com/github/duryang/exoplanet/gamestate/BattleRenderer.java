package com.github.duryang.exoplanet.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.duryang.exoplanet.entity.Ship;

public class BattleRenderer {

    private final SpriteBatch batch;
    private final Battle battle;

    public BattleRenderer(SpriteBatch batch, Battle battle) {
        this.batch = batch;
        this.battle = battle;
    }

    public void render() {
        for (Side side : battle.getSides()) {
            for (Ship ship : side.getShips()) {
                ship.sprite.draw(batch);
            }
        }
    }
}
