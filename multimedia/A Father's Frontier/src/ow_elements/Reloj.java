package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

import managers.ResourceManager;

/**
 * Crea el reloj de la Ciudad, el cual va reduci�ndose
 * poco a poco hasta que llega a cero.
 * @author Rub�n Moya
 */
public class Reloj extends Actor {

    private BitmapFont font;
    public static float tiempoRestante = 300;
    public static String tiempoTexto;
    private Texture reloj;
    public Sound tiktak;
    private float textX;
    private float textY;
    private boolean empezar = false;

    public Reloj() {
    	
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 29; // Tama�o de la fuente
        this.font = generator.generateFont(parameter);
        generator.dispose();
        Reloj.tiempoTexto = "" + convertirTiempo(tiempoRestante);
        
        textX = 656;
        textY = 577;
        setBounds(647, 540, 143, 50);
        
        this.reloj = ResourceManager.getTexture("Menu/menuBoton.png");
    }

	/**
	 * El tiempo se va restando milisegundo a milisegundo hasta que llega a cero.
	 * Dependiendo de la longitud del n�mero, la posici�n del texto cambiar�.
	 */
    public void act(float delta) {
        super.act(delta);
        
        if(empezar == true) {
            tiempoRestante -= delta;
	        
	        if (tiempoRestante <= 0) {
	            tiempoRestante = 0;
	            empezar = false;
	        }
	        
	        tiempoTexto = convertirTiempo(tiempoRestante);
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(reloj, getX(), getY(), getWidth(), getHeight());
        font.draw(batch, tiempoTexto, textX, textY);
    }

	/**
	 * Comienza el reloj.
	 */
    public void start() {
        empezar = true;
    }

	/**
	 * Detiene el reloj.
	 */
    public void stop() {
        empezar = false;
    }

	/**
	 * Modifica el tiempo del reloj.
	 */
    public void modTiempo(float tiempo) {
        tiempoRestante += tiempo;
        tiempoTexto = convertirTiempo(tiempoRestante);
    }

	/**
	 * Conviere los segundos dados a milisegundos y minutos.
	 */
    private String convertirTiempo(float tiempo) {
        int minutos = (int) tiempo / 60;
        int segundos = (int) tiempo % 60;
        int milisegundos = (int) ((tiempo - (minutos * 60 + segundos)) * 1000);
        return String.format("%02d:%02d:%03d", minutos, segundos, milisegundos);
    }
}