package com.github.duryang.exoplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

public class DragController {

    private static final float dragStartDistance = 50f;
    private static final float maxDragVelocity = 3000f;
    private static final float dragVelocityScale = 10f;

    /**
     * If set, drag is potentially initiated with these starting coordinates.
     * Right click is pressed but not released. Actual drag may not have started if the mouse has not moved far enough
     * from the initiating coordinated.
     */
    private Vector2 dragInitiatingCoordinates;

    /**
     * Actually dragging if true
     */
    @Getter
    private boolean dragging;

    private final Vector2 dragVelocity;

    public DragController() {
        dragging = false;
        dragInitiatingCoordinates = null;
        dragVelocity = new Vector2();
    }

    public void initiate(int screenX, int screenY) {
        dragInitiatingCoordinates = new Vector2(screenX, screenY);
    }

    public void update() {
        if (!dragging && dragInitiatingCoordinates != null &&
            dragInitiatingCoordinates.dst(Gdx.input.getX(), Gdx.input.getY()) > dragStartDistance) {
            dragging = true;
        }

        if (dragging) {
            dragVelocity.set(Gdx.input.getX(), Gdx.input.getY()).sub(dragInitiatingCoordinates);
        }
    }

    public float getDeltaX() {
        if (!dragging) {
            return 0f;
        }

        return MathUtils.clamp(dragVelocity.x * dragVelocityScale, -maxDragVelocity, maxDragVelocity);
    }

    public float getDeltaY() {
        if (!dragging) {
            return 0f;
        }

        return MathUtils.clamp(dragVelocity.y * dragVelocityScale, -maxDragVelocity, maxDragVelocity);
    }

    public void stop() {
        dragging = false;
        dragInitiatingCoordinates = null;
    }
}
