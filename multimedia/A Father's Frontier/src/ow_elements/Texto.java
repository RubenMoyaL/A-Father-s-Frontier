package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

/**
 * Crea los di�logos con los NPCs de la Ciudad,
 * los cuales pueden ser pasados con clic izquierdo, y se
 * dibujar�n letra a letra, reproduciendo un sonido en
 * determinadas letras.
 * @author Rub�n Moya
 */
public class Texto extends Actor {
    private Texture caja;
    private String text;
    private String voice;
    private Label texto;
    public LabelStyle uiStyle;
    public Sound letra;
    float x = getX(); // Ajustar la posici�n de la caja
    float y = getY();
    float width = Parametros.getAnchoPantalla(); // Ajustar el tama�o de la caja
    float height = Parametros.getAltoPantalla();
    private float temporizador=0;
    private int contador=0;
    private char letraActual;
    public boolean completo = false;

    public Texto(String text, String voice) {
        FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
        FreeTypeFontParameter ftfp= new FreeTypeFontParameter();

        BitmapFont fuentePropia=ftfg.generateFont(ftfp);
        uiStyle=new LabelStyle();
        fuentePropia.getData().setScale(2f);
        uiStyle.font=fuentePropia;

        this.text=text;
        this.voice=voice;

        caja = ResourceManager.getTexture("Menu/dialogoOW.png");

        texto=new Label("", uiStyle);
        texto.setPosition(30, 92);
    }

    @Override
    public void act(float delta) {
        if (!completo) {
            temporizador += Gdx.graphics.getDeltaTime();
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
                
                contador++;
                temporizador = 0;
            } else if (contador == text.length()) {
                completo = true;
            }
        }
        else if (completo) {
        	texto.remove();
            texto=new Label(text, uiStyle);
            texto.setPosition(30, 48);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(caja, x, y, width, height);
        texto.draw(batch, parentAlpha);
    }

	/**
	 * Elimina el actor.
	 */
    public void hide() {
        this.remove();
    }
}