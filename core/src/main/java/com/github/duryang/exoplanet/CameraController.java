package com.github.duryang.exoplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import lombok.Getter;
import lombok.Setter;

public class CameraController {

    private static final float movementSpeed = 500f;
    private static final float padding = 10f;

    private final OrthographicCamera camera;
    private final float worldWidth;
    private final float worldHeight;

    /**
     * The states of the current camera movement
     */
    @Setter
    private boolean moveRight, moveLeft, moveUp, moveDown;

    @Getter
    private final DragController drag;

    public CameraController(OrthographicCamera camera, float worldWidth, float worldHeight) {
        this.camera = camera;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        drag = new DragController();
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float moveAmount = movementSpeed * deltaTime;

        boolean updated = false;

        float x = camera.position.x;
        float y = camera.position.y;

        if (moveRight) {
            x += moveAmount;
            updated = true;
        }

        if (moveLeft) {
            x -= moveAmount;
            updated = true;
        }

        if (moveUp) {
            y += moveAmount;
            updated = true;
        }

        if (moveDown) {
            y -= moveAmount;
            updated = true;
        }

        drag.update();
        if (drag.isDragging()) {
            x += drag.getDeltaX() * deltaTime;
            y -= drag.getDeltaY() * deltaTime;
            updated = true;
        }

        if (updated) {
            // TODO: maybe precalculate min and max and update only when zoom changes
            camera.position.x = MathUtils.clamp(x, camera.viewportWidth / 2f * camera.zoom - padding,
                worldWidth - camera.viewportWidth / 2f * camera.zoom + padding);
            camera.position.y = MathUtils.clamp(y, camera.viewportHeight / 2f * camera.zoom - padding,
                worldHeight - camera.viewportHeight / 2f * camera.zoom + padding);

            camera.update();
        }
    }
}
