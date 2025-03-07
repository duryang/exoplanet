package com.github.duryang.exoplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.duryang.exoplanet.command.MoveCommand;
import com.github.duryang.exoplanet.entity.Ship;
import com.github.duryang.exoplanet.gamestate.Battle;
import com.github.duryang.exoplanet.gamestate.BattleRenderer;
import com.github.duryang.exoplanet.gamestate.DummyBattleBuilder;
import com.github.duryang.exoplanet.input.PlayerInputHandler;

public class GameScreen implements Screen {

    private final Exoplanet game;
    private final AssetManager assetManager;

    private Battle battle;
    private BattleRenderer battleRenderer;

    private Texture background;

    private OrthographicCamera camera;
    private CameraController cameraController;

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

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        // this sets the camera to bottom left corner initially
        camera.position.set(camera.viewportWidth / 2f * camera.zoom, camera.viewportHeight / 2f * camera.zoom, 0);
        camera.update();

        cameraController = new CameraController(camera, background.getWidth(), background.getHeight());
        InputProcessor inputProcessor = new PlayerInputHandler(cameraController);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {

        cameraController.update();

        if (Gdx.input.isTouched()) {
            Vector3 gameCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            MoveCommand moveCommand = new MoveCommand(gameCoordinates.x, gameCoordinates.y);

            Ship ship = battle.getSides().get(0).getShips().get(0);
            ship.giveCommand(moveCommand);
        }

        battle.update();

        ScreenUtils.clear(Color.BLACK);

        game.batch.begin();

        game.batch.setProjectionMatrix(camera.combined);

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
}
