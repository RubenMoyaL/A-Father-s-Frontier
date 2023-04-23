package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import game.Parametros;

public class Texto extends Actor {
    private Texture caja;
    private String text;
    private Label texto;
	public LabelStyle uiStyle;
    float x = getX(); // Ajustar la posici�n de la caja
    float y = getY();
    float width = Parametros.getAnchoPantalla(); // Ajustar el tama�o de la caja
    float height = Parametros.getAltoPantalla();;
    
	public Texto(String text) {
	    FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
	    FreeTypeFontParameter ftfp= new FreeTypeFontParameter();
	    
	    BitmapFont fuentePropia=ftfg.generateFont(ftfp);
	    uiStyle=new LabelStyle();
	    fuentePropia.getData().setScale(2f);
	    uiStyle.font=fuentePropia;
	    
	    this.text=text;
	    
		caja = new Texture(Gdx.files.internal("Menu/dialogoOW.png"));
	    
	    texto=new Label(this.text, uiStyle);
	    texto.setPosition(30, 50);
	}

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(caja, x, y, width, height);
        texto.draw(batch, parentAlpha);
    }
    
    public void hide() {
    	this.remove();
    }
}