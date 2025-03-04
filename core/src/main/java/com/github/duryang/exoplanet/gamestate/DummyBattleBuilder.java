package com.github.duryang.exoplanet.gamestate;

import com.badlogic.gdx.assets.AssetManager;
import com.github.duryang.exoplanet.entity.Ship;
import com.github.duryang.exoplanet.entity.factory.ShipFactory;

import java.util.Collections;

public class DummyBattleBuilder {

    private final ShipFactory shipFactory;

    public DummyBattleBuilder(AssetManager assetManager) {
        shipFactory = new ShipFactory(assetManager);
    }

    public Battle build() {
        Side side = new Side(1);
        Ship fighter = shipFactory.build();
        side.getShips().add(fighter);

        return new Battle(Collections.singletonList(side));
    }
}
