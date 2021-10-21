package com.mygdx.chalmersdefense.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * @author Daniel Persson
 * Utility class for getting LabelStyle for font usage.
 *
 *
 * 2021-10-11 Modified by Elin Forsberg: Added more labelstyles
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
     * Generates a LabelStyle from CenturyGothic font with size 36px and color.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle36Black() {
        return generateLabelStyle(36, Color.BLACK, 0);
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

    /**
     * Generates a LabelStyle from CenturyGothic font with size 20px, color black.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle20Black() {
        return generateLabelStyle(20, Color.BLACK, 0.25f);
    }

    /**
     * Generates a LabelStyle from CenturyGothic font with size 20px, color black and semibold.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle20BlackSemiBold() {
        return generateLabelStyle(20, Color.BLACK, 0.5f);
    }

    /**
     * Generates a LabelStyle from CenturyGothic font with size 24px, color light gray and semi bold.
     *
     * @return a LabelStyle with corresponding size, color and border width.
     */
    public static LabelStyle getLabelStyle34SkyBold() {
        return generateLabelStyle(34, Color.SKY, 1f);
    }


    //Generate the BitMapFont
    private static BitmapFont generateBitmapFont(int size, float borderWidth) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/CenturyGothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderWidth = borderWidth;
        BitmapFont font = generator.generateFont(parameter);
        font.setUseIntegerPositions(false);
        generator.dispose();
        return font;
    }

    //Generate the labelStyle
    private static LabelStyle generateLabelStyle(int size, Color color, float borderWidth) {
        BitmapFont font36 = generateBitmapFont(size, borderWidth);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
    }


}
