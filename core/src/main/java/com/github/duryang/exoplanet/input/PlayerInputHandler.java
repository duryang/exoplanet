package com.github.duryang.exoplanet.input;

import com.badlogic.gdx.InputProcessor;
import com.github.duryang.exoplanet.CameraController;

public class PlayerInputHandler implements InputProcessor {

    private final CameraController cameraController;

    public PlayerInputHandler(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == KeyBindings.MOVE_CAMERA_RIGHT) {
            cameraController.setMoveRight(true);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_LEFT) {
            cameraController.setMoveLeft(true);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_UP) {
            cameraController.setMoveUp(true);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_DOWN) {
            cameraController.setMoveDown(true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == KeyBindings.MOVE_CAMERA_RIGHT) {
            cameraController.setMoveRight(false);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_LEFT) {
            cameraController.setMoveLeft(false);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_UP) {
            cameraController.setMoveUp(false);
        }

        if (keycode == KeyBindings.MOVE_CAMERA_DOWN) {
            cameraController.setMoveDown(false);
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
