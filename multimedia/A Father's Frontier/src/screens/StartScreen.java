package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import ow_elements.Reloj;
import ow_elements.Texto;

public class StartScreen extends BScreen {
private OrthographicCamera camera;
private Batch batch;
private Texture periodico;
private Actor periodicoActor;
private Texture blackBackground;
private Actor blackBackgroundActor;
private boolean desactivable = false;
private float fadeTimer = 0f;
private Music musicaCiudad;
private Music ruido;
private Music tiktak;
private int contador = 0;
private Texto texto;

    public StartScreen(Demo game) {
        super(game);

        texto = new Texto("�?: Si quiere recuperar a su hijo, deber� brindar\nconsigo veinte libras esterlinas al sur del distrito.", "talk1");
        texto.completo=true;
		this.tiktak = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/tiktak.wav"));
		ruido = Gdx.audio.newMusic(Gdx.files.internal("01-FS/Audio/music/final.wav"));
		ruido.setLooping(true);
		ruido.setVolume(0.2f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Parametros.getAltoPantalla(), Parametros.getAltoPantalla());

        batch = new SpriteBatch();
	    
        if (Parametros.frontera==false) {
        	ResourceManager.musicaTitulo.play();
        	Reloj.tiempoRestante = 180;
	        switch (Parametros.dia) {
            case 1:
                periodico = new Texture("Menu/periodico.0_0.png");
                break;
            case 2:
                periodico = new Texture("Menu/periodico.0_0.png");
                break;
            case 3:
                periodico = new Texture("Menu/periodico.0_0.png");
                break;
            default:
                periodico = new Texture("Menu/periodico.0_0.png");
                ResourceManager.musicaTitulo.stop();
                game.setScreen(new OverWorldScreen(game, musicaCiudad, tiktak));
		    }
        }
        
        else if (Parametros.frontera==true) {
	        switch (Parametros.dia) {
            case 1:
                periodico = new Texture("Menu/periodico.1_0.png");
                break;
            case 2:
                periodico = new Texture("Menu/periodico.2_0.png");
                break;
            case 3:
                periodico = new Texture("Menu/periodico.3_0.png");
                break;
            default:
                periodico = new Texture("Menu/periodico.3_0.png");
                ResourceManager.musicaTitulo.stop();
                game.setScreen(new FrontierScreen(game));
		    }
        }
        
        blackBackground = new Texture("Menu/blackBackground.png");
        blackBackgroundActor = new Image(blackBackground);
        this.uiStage.addActor(blackBackgroundActor);
        
        periodicoActor = new Image(periodico);
        this.uiStage.addActor(periodicoActor);
        periodicoActor.getColor().a = 0f; // set initial opacity to 0
        periodicoActor.setPosition(Parametros.getAnchoPantalla() / 2f, Parametros.getAltoPantalla() / 2f, Align.center);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	
    	switch(Parametros.dia) {
    	
    		case 1:
    			
    			//OW
    			
    	        if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==0 && texto.completo==true) {
    	        	texto.completo=false;
    	            periodicoActor.remove();
    	            Parametros.zoom=0.65f;
    	            ResourceManager.musicaTitulo.stop();
    	            ruido.play();
    	            this.uiStage.addActor(texto);
    	            contador ++;
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==1 && texto.completo==true) {
    	        	texto.remove();
    	            texto = new Texto("Tiene un l�mite de tres d�as, considere ma�ana\ncomo el primero, le aconsejo no perder el tiempo...", "talk1");
    	            this.uiStage.addActor(texto);
    	            contador ++;
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==2 && texto.completo==true) {
    	        	texto.remove();
    	            texto = new Texto("De lo contrario... No le ocurrir� nada agradable\na su preciada criatura... Evans, �Cierto?", "talk1");
    	            this.uiStage.addActor(texto);
    	            contador ++;
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==3 && texto.completo==true) {
    	        	texto.remove();
    	            texto = new Texto("Si intenta contactar a la polic�a, tenga claro que\nlo sabr�, as� que no tiene por qu� molestarse.", "talk1");
    	            this.uiStage.addActor(texto);
    	            contador ++;
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==4 && texto.completo==true) {
    	        	texto.remove();
    	            texto = new Texto("Mucha suerte, y buena vida. Conf�o en sus\ncapacidades... Le ver� en unos d�as.", "talk1");
    	            this.uiStage.addActor(texto);
    	            contador ++;
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==5 && texto.completo==true) {
    	        	texto.remove();
    	        	ruido.stop();
    	            game.setScreen(new OverWorldScreen(game, musicaCiudad, tiktak));
    	            contador ++;
    	        }
    	        
    	        //FS
    	        
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==true) {
    	            periodicoActor.remove();
    	            ResourceManager.musicaTitulo.stop();
    	            Parametros.zoom=0.30f;
    	            game.setScreen(new FrontierScreen(game));
    	        }
    	        else if(texto.completo==false) {
	    			texto.completo=true;
    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
    	        }
    	        break;
    	        
    		case 2:
    	        if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false) {
    	            periodicoActor.remove();
    	            ResourceManager.musicaTitulo.stop();
    	            Parametros.zoom=0.65f;
    	            game.setScreen(new OverWorldScreen(game, musicaCiudad, tiktak));
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==true) {
    	            periodicoActor.remove();
    	            ResourceManager.musicaTitulo.stop();
    	            Parametros.zoom=0.30f;
    	            game.setScreen(new FrontierScreen(game));
    	        }
    	        break;
    	        
    		case 3:
    	        if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false) {
    	            periodicoActor.remove();
    	            ResourceManager.musicaTitulo.stop();
    	            Parametros.zoom=0.65f;
    	            game.setScreen(new OverWorldScreen(game, musicaCiudad, tiktak));
    	        }
    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==true) {
    	            periodicoActor.remove();
    	            ResourceManager.musicaTitulo.stop();
    	            Parametros.zoom=0.30f;
    	            game.setScreen(new FrontierScreen(game));
    	        }
    	        break;
    			
    	}

        return false;
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        uiStage.act();
        
        fadeTimer += delta;
        if (fadeTimer < 1f) {
            periodicoActor.getColor().a = fadeTimer;
            desactivable = true;
        }

        batch.begin();
        batch.draw(blackBackground, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
        batch.end();

        uiStage.draw();
    }
}
