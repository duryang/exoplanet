package com.github.duryang.exoplanet.gamestate;

import com.github.duryang.exoplanet.entity.Ship;
import lombok.Getter;

import java.util.List;

@Getter
public class Battle {

    private final List<Side> sides;

    public Battle(List<Side> sides) {
        this.sides = sides;
    }

    public void update() {
        for (Side side : sides) {
            for (Ship ship : side.getShips()) {
                ship.update();
            }
        }
    }
}
