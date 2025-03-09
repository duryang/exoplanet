package com.github.duryang.exoplanet.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import lombok.Getter;
import lombok.Setter;

public class CameraController {

    private static final float movementSpeed = 500f;
    private static final float padding = 10f;
    private static final float[] zoomLevels = { 0.3f, 0.4f, 0.5f, 0.65f, 0.8f, 1f, 1.2f, 1.4f, 1.7f, 2f, 2.5f, 3f };

    private final OrthographicCamera camera;
    private final float worldWidth;
    private final float worldHeight;

    private boolean needsUpdate;

    /**
     * The states of the current camera movement
     */
    @Setter
    private boolean moveRight, moveLeft, moveUp, moveDown;

    /**
     * Camera limits starting from left side and moving clockwise
     */
    private final float[] limits;

    @Getter
    private final DragController drag;

    private int zoomLevelIndex;

    public CameraController(OrthographicCamera camera, float worldWidth, float worldHeight) {
        this.camera = camera;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        limits = new float[4];
        refreshLimits();

        drag = new DragController();
        zoomLevelIndex = 5;
    }

    /**
     * Needs to be called for every frame to update the camera position, zoom, etc...
     */
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float moveAmount = movementSpeed * deltaTime;

        float x = camera.position.x;
        float y = camera.position.y;

        if (moveRight) {
            x += moveAmount;
            needsUpdate = true;
        }

        if (moveLeft) {
            x -= moveAmount;
            needsUpdate = true;
        }

        if (moveUp) {
            y += moveAmount;
            needsUpdate = true;
        }

        if (moveDown) {
            y -= moveAmount;
            needsUpdate = true;
        }

        drag.update();
        if (drag.isDragging()) {
            x += drag.getDeltaX() * deltaTime;
            y -= drag.getDeltaY() * deltaTime;
            needsUpdate = true;
        }

        if (needsUpdate) {
            if (worldWidth / camera.zoom < Gdx.graphics.getWidth()) {
                camera.position.x = worldWidth / 2f;
            } else {
                camera.position.x = MathUtils.clamp(x, limits[0], limits[2]);
            }

            if (worldHeight / camera.zoom < Gdx.graphics.getHeight()) {
                camera.position.y = worldHeight / 2f;
            } else {
                camera.position.y = MathUtils.clamp(y, limits[1], limits[3]);
            }

            camera.update();

            needsUpdate = false;
        }
    }

    public void refreshLimits() {
        limits[0] = camera.viewportWidth / 2f * camera.zoom - padding;
        limits[1] = camera.viewportHeight / 2f * camera.zoom - padding;
        limits[2] = worldWidth - camera.viewportWidth / 2f * camera.zoom + padding;
        limits[3] = worldHeight - camera.viewportHeight / 2f * camera.zoom + padding;
    }

    public void zoomIn() {
        zoomLevelIndex = MathUtils.clamp(zoomLevelIndex - 1, 0, zoomLevels.length - 1);
        zoomEvent();
    }

    public void zoomOut() {
        zoomLevelIndex = MathUtils.clamp(zoomLevelIndex + 1, 0, zoomLevels.length - 1);
        zoomEvent();
    }

    public void resetZoom() {
        zoomLevelIndex = 5;
        zoomEvent();
    }

    private void zoomEvent() {
        camera.zoom = zoomLevels[zoomLevelIndex];
        camera.update();

        refreshLimits();
        needsUpdate = true;
    }
}
