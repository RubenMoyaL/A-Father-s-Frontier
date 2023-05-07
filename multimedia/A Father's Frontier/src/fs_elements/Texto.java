package fs_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import managers.AudioManager;

public class Texto extends Actor {
    private Texture caja;
    private String text;
    private String voice;
    private Label texto;
	public LabelStyle uiStyle;
	private float textX;
	private float textY;
	public boolean completo = false;
	private float temporizador = 0;
	private int contador = 0;
	private char letraActual;
    
    public Texto(String text, float textX, float textY, String voice) {
        FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
		FreeTypeFontParameter ftfp= new FreeTypeFontParameter();
		
		BitmapFont fuentePropia=ftfg.generateFont(ftfp);
		uiStyle=new LabelStyle();
		fuentePropia.getData().setScale(0.5f);
		uiStyle.font=fuentePropia;
		
    	this.text=text;
    	this.voice=voice;
    	this.textX=textX;
    	this.textY=textY;
    	
    	setBounds(0, 150, 100, 30);
        caja = new Texture("Menu/menuBoton.png");
        
        texto=new Label("", uiStyle);
        texto.setPosition(this.textX, this.textY+11);
    }

    @Override
    public void act(float delta) {
        if (!completo ) {
            temporizador  += Gdx.graphics.getDeltaTime();
            if(temporizador > 0.01f && contador < text.length()) {
                letraActual = text.charAt(contador);
                texto.setText(text.substring(0, contador+1));
                switch(letraActual) {
            	case ' ':
            		break;
            	case '.':
            		break;
            	case ',':
            		break;
            	case ':':
            		break;
            	case '�':
            		break;
            	case '!':
            		break;
            	case '�':
            		break;
            	case '?':
            		break;
            	case '�':
            		break;
            	case '(':
            		break;
            	case ')':
            		break;
	            	default:
	    	            AudioManager.playSound("02-OW/Audio/sounds/" + this.voice + ".wav");
	            		break;
                }
                contador ++;
                temporizador = 0;
            } else if (contador == text.length()) {
                completo = true;
            }
        }
        else if (completo) {
        	texto.remove();
            texto=new Label(text, uiStyle);
            texto.setPosition(this.textX, this.textY);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(caja, getX(), getY(), getWidth(), getHeight());
        texto.draw(batch, parentAlpha);
    }
    
    public void hide() {
    	this.remove();
    }
}