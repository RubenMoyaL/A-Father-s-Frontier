package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class WIPNombreZona extends Actor {
    private String text;
    private BitmapFont font;
    private float textX;
    private float textY;

    public WIPNombreZona(String text, float textX, float textY, float fadeInTime, float waitTime, float fadeOutTime) {
        this.textX = textX;
        this.textY = textY;

        // Cargar la fuente con FreeTypeFontGenerator
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        font = generator.generateFont(parameter);
        generator.dispose();

        // A�adir acciones de fade in y fade out
        addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(fadeInTime), Actions.delay(waitTime), Actions.fadeOut(fadeOutTime), Actions.removeActor()));
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.setColor(1, 0, 0, parentAlpha);
        font.draw(batch, text, textX, textY);
    }
}