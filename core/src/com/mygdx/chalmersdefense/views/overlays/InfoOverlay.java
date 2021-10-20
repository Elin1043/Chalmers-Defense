package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.chalmersdefense.controllers.overlays.AbstractOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.InfoOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.PauseMenuOverlayController;

public class InfoOverlay extends AbstractOverlay {

    private final Group infoGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png"));

    public InfoOverlay(AbstractOverlayController abstractOverlayController) {
        super(abstractOverlayController);
    }

    @Override
    protected void initialize() {
        stage.addActor(infoGroup);
        if (!infoGroup.hasChildren()) {
            infoGroup.addActor(backgroundImage);

            backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
            ImageButton exitButton = createExitPauseMenuButton(infoGroup, backgroundImage);
        }
    }

    @Override
    public void render() {
        super.render();
        infoGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        infoGroup.setVisible(false);
    }

}
