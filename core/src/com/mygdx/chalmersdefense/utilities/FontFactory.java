package com.mygdx.chalmersdefense.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * @author Daniel Persson
 * Utility class for getting LabelStyle for font usage.
 */
public abstract class FontFactory {
    /**
     * Generates a LabelStyle from CenturyGothic font with size 36px, color black and bold.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle36BlackBold() {
        return generateLabelStyle(36, Color.BLACK, 1);
    }

    /**
     * Generates a LabelStyle from CenturyGothic font with size 26px, color black.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle26Black() {
        return generateLabelStyle(26, Color.BLACK, 0);
    }

    /**
     * Generates a LabelStyle from CenturyGothic font with size 24px, color black and semi bold.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle24BlackSemiBold() {
        return generateLabelStyle(24, Color.BLACK, 0.5f);
    }

    /**
     * Generates a LabelStyle from CenturyGothic font with size 18px, color black.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle18Black() {
        return generateLabelStyle(18, Color.BLACK, 0);
    }

    private static BitmapFont generateBitmapFont(int size, float borderWidth) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/CenturyGothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderWidth = borderWidth;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private static LabelStyle generateLabelStyle(int size, Color color, float borderWidth) {
        BitmapFont font36 = generateBitmapFont(size, borderWidth);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
    }
}
