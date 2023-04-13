package fs_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.FrontierScreen;

public class FS2_4 extends Actor {
    private Stage mainStage;
    private Texture persona6;
	public static Texto texto;
    private float elapsedTime = 0;
    private float opacity = 1f;
    private boolean fadingOut = false;

    public FS2_4(Stage mainStage) {
    	this.mainStage=mainStage;
    	setBounds(98, 68, 46, 98);
        persona6 = new Texture("01-FS/Personajes/FS.2_4.png");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && Parametros.controlesActivos==true && Parametros.analizar==true) {
        	
        	//Texto
        	texto = new Texto("Abella: Un momento...\n"
        					+ "E... �E-Eres t�!...", 7, 154);
        	mainStage.addActor(texto);
        	
        	//Sonido
            AudioManager.playSound("01-FS/Audio/sounds/correcto.mp3");
        	
        	//Estad�sticas
        	Parametros.dinero+=3;
            FrontierScreen.reloj.modTiempo(20);
        	
        	//Variables
        	Parametros.analizar=false;
        	Parametros.analizado4=true;
        }
        
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DEL) && Parametros.controlesActivos==true && Parametros.analizar==true) {
        	
        	//Texto
        	texto = new Texto("Abella: Un momento...\n"
        					+ "E... �E-Eres t�!...", 7, 154);
        	mainStage.addActor(texto);
        	
        	//Sonido
            AudioManager.playSound("01-FS/Audio/sounds/incorrecto.mp3");
            
            //Estad�sticas
        	Parametros.dinero-=3;
            FrontierScreen.reloj.modTiempo(-10);
            
            //Variables
        	Parametros.analizado4=true;
        	Parametros.analizar=false;
        }
        
        if (elapsedTime < 1) {
            setY(getY() + 1 * delta);
        }
        else if (elapsedTime < 2) {
            setY(getY() - 1 * delta);
        }

        if (elapsedTime > 5) {
            elapsedTime = 0;
        } else {
            elapsedTime += delta;
        }

        if (fadingOut) {
            opacity -= delta * 2;
            if (opacity < 0) {
                opacity = 0;
                fadingOut = false;
                remove();
            }
        }
    }

    public void fadeOut() {
        fadingOut = true;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, opacity);
        batch.draw(persona6, getX(), getY(), getWidth(), getHeight());
        batch.setColor(1, 1, 1, 1);
    }

    public void hide() {
    	this.persona6 = new Texture("01-FS/Personajes/transparente.png");
    }
    
    public void show() {
        persona6 = new Texture("01-FS/Personajes/FS.2_4.png");
    }
}