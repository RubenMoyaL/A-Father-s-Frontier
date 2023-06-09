package fs_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

/**
 * Establece la persona 1 del d�a 3 de la Frontera.
 * @author Rub�n Moya
 */
public class FS3_1 extends Actor {
    private Texture persona9;
    private float elapsedTime = 0;
	public static Texto texto;
    private Stage mainStage;
    private boolean fadingOut = false;
    private float opacity = 1f;

    public FS3_1(Stage mainStage) {
    	this.mainStage=mainStage;
    	setBounds(81.5f, 59, 80, 117);
        persona9 = ResourceManager.getTexture("01-FS/Personajes/FS.3_1.png");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && Parametros.controlesActivos==true && Parametros.analizar==true) {
        	
        	//Texto
        	texto = new Texto("Howard: Se lo agradez-\n"
        					+ "co mucho, mi estimado.", 7, 154, "talk1");
        	mainStage.addActor(texto);
        	
        	//Sonido
            AudioManager.playSound("01-FS/Audio/sounds/correcto.wav");
        	
        	//Estad�sticas
        	Parametros.dinero+=Parametros.gananciasFS;
        	
        	//Variables
        	Parametros.analizar=false;
        	Parametros.analizado1=true;
        }
        
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DEL) && Parametros.controlesActivos==true && Parametros.analizar==true) {
        	
        	//Texto
        	texto = new Texto("Howard: Estoy harto de\n"
        					+ "las personas como t�.", 7, 154, "talk1");
        	mainStage.addActor(texto);
        	
        	//Sonido
            AudioManager.playSound("01-FS/Audio/sounds/incorrecto.wav");
            
            //Estad�sticas
        	Parametros.dinero-=Parametros.gananciasFS;
            
            //Variables
        	Parametros.analizado1=true;
        	Parametros.analizar=false;
        }
        
        if (elapsedTime < 1) {
            setY(getY() + 1 * delta);
        }
        else if (elapsedTime < 2) {
            setY(getY() - 1 * delta);
        }

        if (elapsedTime > 3) {
            elapsedTime = 0;
        } else {
            elapsedTime += delta;
        }

        if (fadingOut) {
            opacity -= delta * 5;
            if (opacity < 0) {
                opacity = 0;
                fadingOut = false;
                remove();
            }
        }
    }

    /**
     * Desvanece poco a poco al actor.
     */
    public void fadeOut() {
        fadingOut = true;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, opacity);
        batch.draw(persona9, getX(), getY(), getWidth(), getHeight());
        batch.setColor(1, 1, 1, 1);
    }

    /**
     * Oculta al actor.
     */
    public void hide() {
    	this.persona9 = ResourceManager.getTexture("01-FS/Personajes/transparente.png");
    }

    /**
     * Muestra al actor.
     */
    public void show() {
        persona9 = ResourceManager.getTexture("01-FS/Personajes/FS.3_1.png");
    }
}