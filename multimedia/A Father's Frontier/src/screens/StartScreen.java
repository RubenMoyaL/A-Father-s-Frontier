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

/**
 * Establece la pantalla de inicio de cada d�a del juego, donde
 * se muestran los peri�dicos o los flashbacks.
 * @author Rub�n Moya
 */
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
private int contador = 0;
private Texto texto;
private Texture siluetaTexture;
private Actor silueta;

    public StartScreen(Demo game) {
        super(game);

        texto = new Texto("�?: Si quiere recuperar a su hijo, ha de traer\nveinte libras a la casa del sur del distrito.", "talk1");
        texto.completo=true;
		ruido = Gdx.audio.newMusic(Gdx.files.internal("01-FS/Audio/music/final.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Parametros.getAltoPantalla(), Parametros.getAltoPantalla());

        batch = new SpriteBatch();
	    
        if (Parametros.frontera==false) {
        	Reloj.tiempoRestante = Parametros.segundosBaseOW;
	        switch (Parametros.dia) {
	            case 1:
	                periodico = ResourceManager.getTexture("Menu/periodico.0_0.png");
	                siluetaTexture=ResourceManager.getTexture("02-OW/Personajes/p_sil.png");
	                silueta = new Image(siluetaTexture);
	                silueta.setBounds(362, 240, 76, 164);
	        		ruido.setLooping(true);
	        		ruido.setVolume(0.2f);
	                break;
	            case 2:
	                siluetaTexture = ResourceManager.getTexture("02-OW/Personajes/p.recuerdos.png");
	                silueta = new Image(siluetaTexture);
	                silueta.setBounds(342, 250, 100, 158);
	                ResourceManager.musicaTitulo.stop();
	        		ruido.setLooping(true);
	        		ruido.setVolume(0.2f);
	                break;
	            case 3:
	                siluetaTexture = ResourceManager.getTexture("02-OW/Personajes/p.recuerdos1.png");
	                silueta = new Image(siluetaTexture);
	                silueta.setBounds(362, 240, 76, 164);
	                ResourceManager.musicaTitulo.stop();
	        		ruido.setLooping(true);
	        		ruido.setVolume(0.2f);
	                break;
	            case 4:
	                siluetaTexture = ResourceManager.getTexture("02-OW/Personajes/p.recuerdos1.png");
	                ResourceManager.musicaTitulo.stop();
		    }
        }
        
        else if (Parametros.frontera==true) {
	        switch (Parametros.dia) {
            case 1:
                periodico = ResourceManager.getTexture("Menu/periodico.1_0.png");
                break;
            case 2:
                periodico = ResourceManager.getTexture("Menu/periodico.2_0.png");
                break;
            case 3:
                periodico = ResourceManager.getTexture("Menu/periodico.3_0.png");
                break;
            default:
                periodico = ResourceManager.getTexture("Menu/periodico.3_0.png");
                ResourceManager.musicaTitulo.stop();
                game.setScreen(new FrontierScreen(game));
		    }
        }
        
        blackBackground = ResourceManager.getTexture("Menu/blackBackground.png");
        blackBackgroundActor = new Image(blackBackground);
        this.uiStage.addActor(blackBackgroundActor);
        

       if((Parametros.dia==2 || Parametros.dia==3 || Parametros.dia==4) && Parametros.frontera == false) {
    	    if(Parametros.dia==2) {
                this.uiStage.addActor(silueta);
                texto = new Texto("Pero papi, yo no quiero irme de casa...\n�A d�nde vamos? Tengo miedo...", "talk2");
                texto.completo=true;
	            ruido.play();
    	    }
    	    if(Parametros.dia==3) {
                texto = new Texto("�SOCORROOOOOOOOOO!\n�QUE ALGUIEN ME AYUDE!", "talk2");
                texto.completo=true;
	           	AudioManager.playSound("02-OW/Audio/sounds/grito.wav");
	            ruido.play();
    	    }
    	    if(Parametros.dia==4) {
    	        texto = new Texto("Volker: Despu�s de todo el sacrificio...\nHa llegado el momento de la verdad.", "talk1");
    	        texto.completo=true;
    	    }
        	if(texto.completo==true) {
	            Parametros.zoom=0.65f;
        		texto.completo=false;
	            this.uiStage.addActor(texto);
        	}
       }
       else {
           periodicoActor = new Image(periodico);
           this.uiStage.addActor(periodicoActor);
           periodicoActor.getColor().a = 0f; // set initial opacity to 0
           periodicoActor.setPosition(Parametros.getAnchoPantalla() / 2f, Parametros.getAltoPantalla() / 2f, Align.center);
       }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if(texto.completo==true) {
	    	switch(Parametros.dia) {
	    	
	    		case 1:
	    			
	    			//OW
	    			
	    	        if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==0 && texto.completo==true) {
	    	        	texto.completo=false;
	    	            periodicoActor.remove();
	    	            this.uiStage.addActor(silueta);
	    	            Parametros.zoom=0.65f;
	    	            ResourceManager.musicaTitulo.stop();
	    	            ruido.play();
	    	            this.uiStage.addActor(texto);
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==1 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	            texto = new Texto("Tiene un l�mite de tres d�as, considere ma�ana\ncomo el primero, le aconsejo no perder el tiempo...", "talk1");
	    	            this.uiStage.addActor(texto);
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==2 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	            texto = new Texto("De lo contrario... No le ocurrir� nada agradable\na su preciada criatura... Evans, �Cierto?", "talk1");
	    	            this.uiStage.addActor(texto);
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==3 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	            texto = new Texto("Si intenta contactar a la polic�a, tenga claro que\nlo sabr�, as� que no tiene por qu� molestarse.", "talk1");
	    	            this.uiStage.addActor(texto);
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==4 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	            texto = new Texto("Mucha suerte, y buena vida. Conf�o en sus\ncapacidades... Le ver� en unos d�as.", "talk1");
	    	            this.uiStage.addActor(texto);
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==false && contador==5 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	        	silueta.remove();
	    	        	ruido.stop();
	    	            game.setScreen(new OverWorldScreen(game, musicaCiudad));
	    	            contador ++;
	    	        }
	    	        
	    	        //FS
	    	        
	    	        else if (button == Input.Buttons.LEFT && desactivable == true && Parametros.frontera==true) {
	    	            periodicoActor.remove();
	    	            ResourceManager.musicaTitulo.stop();
	    	            Parametros.zoom=0.30f;
	    	            game.setScreen(new FrontierScreen(game));
	    	        }
	    	        break;
	    	        
	    		case 2:
	    			if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==0 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("Ya te lo dije, Evans... No podemos quedarnos,\nAlemania ya no es un lugar seguro para nosotros.", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==1 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("Debes ser paciente, �Vale? Te prometo\nque mientras estemos juntos, todo ir� bien.", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==2 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("Est� bien, pero...\n�No vamos a volver nunca m�s?", "talk2");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==3 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("Quiz�s... Dentro de mucho, mucho tiempo...\nVamos, tenemos un largo camino por delante...", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    	        else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==4 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	        	silueta.remove();
	    	        	ruido.stop();
	    	            game.setScreen(new OverWorldScreen(game, musicaCiudad));
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && Parametros.frontera==true) {
	    	            periodicoActor.remove();
	    	            ResourceManager.musicaTitulo.stop();
	    	            Parametros.zoom=0.30f;
	    	            game.setScreen(new FrontierScreen(game));
	    	        }
	    	        break;
	    	        
	    		case 3:
	    			if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==0 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("...", "talk1");
			           	AudioManager.playSound("02-OW/Audio/sounds/disparo.wav");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==1 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	                    this.uiStage.addActor(silueta);
	     	            texto = new Texto("�Ja, ja, ja! �Justo en la cabeza! �Ser�s cabr�n!\n�Con ese van al menos cinco!", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==2 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("C�ntrate, Rudolf. Todav�a no hemos terminado.\nMataremos al menos a diez m�s antes de irnos.", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    			else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==3 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("�Ja! Si algo me gusta m�s que escuchar a los\njud�os gritar, ha de ser tu sadismo, amigo.", "talk1");
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    	        else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==4 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	        	texto.remove();
	    	        	silueta.remove();
	    	        	ruido.stop();
	    	            game.setScreen(new OverWorldScreen(game, musicaCiudad));
	    	            contador ++;
	    	        }
	    	        else if (button == Input.Buttons.LEFT && Parametros.frontera==true) {
	    	            periodicoActor.remove();
	    	            ResourceManager.musicaTitulo.stop();
	    	            Parametros.zoom=0.30f;
	    	            game.setScreen(new FrontierScreen(game));
	    	        }
	    	        break;
	    	        
	    		case 4:
	    			if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==0 && texto.completo==true) {
			        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	     	        	texto.remove();
	     	            texto = new Texto("Pienso sacar a mi hijo de dondequiera que est�...\nY lo har� a toda costa.", "talk1");
	     	            Parametros.zona=2;
	     	            this.uiStage.addActor(texto);
	     	            contador ++;
	     	        }
	    	        else if (button == Input.Buttons.LEFT && Parametros.frontera==false && contador==1 && texto.completo==true) {
	    	        	texto.remove();
	    	            game.setScreen(new OverWorldScreen(game, musicaCiudad));
	    	            contador ++;
	    	        }
	    	}
    	
		} else {
    		texto.completo=true;
            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
    	}
    	
        return false;
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        uiStage.act();
        
        if(ResourceManager.tiktak.isPlaying()) {
	        ResourceManager.tiktak.stop();
        }
        
        fadeTimer += delta;
        switch(Parametros.dia) {
        	case 1:
                if (fadeTimer < 1f) {
                    periodicoActor.getColor().a = fadeTimer;
                    desactivable = true;
                }
        		break;
        	case 2:
        		if(Parametros.frontera==true) {
	                if (fadeTimer < 1f) {
	                    periodicoActor.getColor().a = fadeTimer;
	                    desactivable = true;
	                }
        		}
        		break;
        	case 3:
        		if(Parametros.frontera==true) {
	                if (fadeTimer < 1f) {
	                    periodicoActor.getColor().a = fadeTimer;
	                    desactivable = true;
	                }
        		}
        	case 4:
        		if(Parametros.frontera==true) {
	                if (fadeTimer < 1f) {
	                    periodicoActor.getColor().a = fadeTimer;
	                    desactivable = true;
	                }
        		}
        		break;
        }
        
        batch.begin();
        batch.draw(blackBackground, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
        batch.end();

        uiStage.draw();
    }
}
