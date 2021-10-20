package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlays.AbstractOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.InfoOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.PauseMenuOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

/**
 * @author Jenny Carlsson
 * A class to display info overlay
 */

public class InfoOverlay extends AbstractOverlay {
    private final InfoOverlayController infoOverlayController;

    private final Group infoGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png"));

    public InfoOverlay(AbstractOverlayController abstractOverlayController, InfoOverlayController infoOverlayController) {
        super(abstractOverlayController);
        this.infoOverlayController = infoOverlayController;
    }

    @Override
    protected void initialize() {
        stage.addActor(infoGroup);
        if (!infoGroup.hasChildren()) {
            infoGroup.addActor(backgroundImage);

            backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
            ImageButton exitButton = createExitPauseMenuButton(infoGroup, backgroundImage);
            infoOverlayController.addExitPauseMenuButtonClickListener(exitButton);

            Label infoTitleLabel = new Label("Game info", FontFactory.getLabelStyle36BlackBold());
            infoGroup.addActor(infoTitleLabel);
            infoTitleLabel.setPosition(backgroundImage.getX() + (backgroundImage.getWidth() / 2 - infoTitleLabel.getWidth() / 2), backgroundImage.getY() + 320);
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
