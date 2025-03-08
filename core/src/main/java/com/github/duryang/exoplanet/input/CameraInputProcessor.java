package com.github.duryang.exoplanet.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.github.duryang.exoplanet.CameraController;

public class CameraInputProcessor implements InputProcessor {

    private final CameraController cameraController;

    public CameraInputProcessor(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    @Override
    public boolean keyDown(int keycode) {
        return keyAction(keycode, true);
    }

    @Override
    public boolean keyUp(int keycode) {
        return keyAction(keycode, false);
    }

    private boolean keyAction(int keycode, boolean down) {
        boolean processed = false;

        if (keycode == KeyBindings.MOVE_CAMERA_RIGHT) {
            cameraController.setMoveRight(down);
            processed = true;
        }

        if (keycode == KeyBindings.MOVE_CAMERA_LEFT) {
            cameraController.setMoveLeft(down);
            processed = true;
        }

        if (keycode == KeyBindings.MOVE_CAMERA_UP) {
            cameraController.setMoveUp(down);
            processed = true;
        }

        if (keycode == KeyBindings.MOVE_CAMERA_DOWN) {
            cameraController.setMoveDown(down);
            processed = true;
        }

        return processed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT) {
            cameraController.getDrag().initiate(screenX, screenY);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT) {
            boolean wasDragging = cameraController.getDrag().isDragging();
            cameraController.getDrag().stop();

            // don't handle it further if it stopped camera dragging
            return wasDragging;
        }

        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
