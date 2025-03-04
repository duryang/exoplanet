package com.github.duryang.exoplanet.entity.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.github.duryang.exoplanet.entity.Fighter;
import com.github.duryang.exoplanet.entity.Ship;

import java.util.Random;

public class ShipFactory {

    private final AssetManager assetManager;

    private final Random random = new Random();

    public ShipFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Ship build() {

        Texture texture = assetManager.get("img/ships/drakir-fighter.png", Texture.class);

        Ship fighter = new Fighter(texture, random.nextInt(900), random.nextInt(600));

        return fighter;
    }
}
