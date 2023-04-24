package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class DeathScreen extends BScreen{
private OrthographicCamera camera;
private Batch batch;
private Texture background;
private Label causaDeLaMuerte;
private Music musica;
private Label fin;
private boolean estadisticasHabilitada = false;
private boolean diaHabilitada = false;

	public DeathScreen(Demo game) {
	    super(game);
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/muerte.wav"));
		musica.play();
	    musica.setVolume(0.2f);
	    musica.setLooping(true);
	    
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, Parametros.getAltoPantalla(), Parametros.getAltoPantalla());
	    
	    batch = new SpriteBatch();
	    background = new Texture("Menu/blackBackground.png");
	    
	    if(Parametros.haComidoHoy==false) {
			causaDeLaMuerte=new Label("EST� MUERTO\r\n\n"
					+ "El hambre se ha vuelto tan\n"
					+ "insoportable que le ha ido matando\n"
					+ "lentamente como un cancer...\n"
					+ "Su remordimiento antes de morir,\n"
					+ "nunca pudo volver a ver a su hijo.\n\n"
					+ "Pulse ENTER para volver"
					, uiStyle);
	    }
		causaDeLaMuerte.setPosition(75,50);
	    causaDeLaMuerte.setAlignment(1, 2);
	    
		fin=new Label("FINAL", uiStyle);
		fin.setPosition(350,280);
		fin.setAlignment(1, 2);
	    
		this.uiStage.addActor(causaDeLaMuerte);
		estadisticasHabilitada=true;
	}
	
	@Override
	public void render(float delta) {
		
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && estadisticasHabilitada) {
        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			causaDeLaMuerte.remove();
    		this.uiStage.addActor(fin);
    		diaHabilitada=true;
    		estadisticasHabilitada=false;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && (diaHabilitada)) {
        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			fin.remove();
			musica.stop();
			ResourceManager.musicaTitulo.play();
			game.setScreen(new TitleScreen(game));
        }
        
		 super.render(delta);
	    
	     uiStage.act();
	     batch.begin();
	     batch.draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	     batch.end();
	     uiStage.draw();

	}
	
}
