package com.github.duryang.exoplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.duryang.exoplanet.command.MoveCommand;
import com.github.duryang.exoplanet.entity.Ship;
import com.github.duryang.exoplanet.gamestate.Battle;
import com.github.duryang.exoplanet.gamestate.BattleRenderer;
import com.github.duryang.exoplanet.gamestate.DummyBattleBuilder;

public class GameScreen implements Screen, InputProcessor {

    private final Exoplanet game;
    private final AssetManager assetManager;

    private Battle battle;
    private BattleRenderer battleRenderer;

    private Texture background;

    public GameScreen(Exoplanet game) {
        this.game = game;
        this.assetManager = new AssetManager();
    }

    @Override
    public void show() {
        // load assets
        assetManager.load("img/background/bkgd_0.png", Texture.class);
        assetManager.load("img/ships/drakir-fighter.png", Texture.class);

        assetManager.finishLoading();

        background = assetManager.get("img/background/bkgd_0.png");

        battle = new DummyBattleBuilder(assetManager).build();
        battleRenderer = new BattleRenderer(game.batch, battle);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            MoveCommand moveCommand = new MoveCommand(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            Ship ship = battle.getSides().get(0).getShips().get(0);
            ship.giveCommand(moveCommand);
        }

        battle.update();

        ScreenUtils.clear(Color.BLACK);
        game.batch.begin();

        game.batch.draw(background, 0, 0);
        battleRenderer.render();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    // region input processors

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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

    // endregion
}
