package com.github.duryang.exoplanet.gamestate;

import com.badlogic.gdx.utils.Array;
import com.github.duryang.exoplanet.entity.Ship;
import lombok.Getter;

@Getter
public class Side {

    private final int id;
    private final Array<Ship> ships;

    public Side(int id) {
        this.id = id;
        ships = new Array<>();
    }
}
