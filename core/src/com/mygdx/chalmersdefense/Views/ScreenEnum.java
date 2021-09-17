package com.mygdx.chalmersdefense.Views;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen() {
            return new MainScreen();
        }
    },
    GAME {
        public AbstractScreen getScreen() {
            return new GameScreen();
        }
    };

    public abstract AbstractScreen getScreen();
}
