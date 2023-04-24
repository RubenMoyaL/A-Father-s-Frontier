
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fs_elements.Lluvia;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class TitleScreen extends BScreen{
private Table tabla;
private OrthographicCamera camera;
private Batch batch;
private Texture background;
private Actor titleActor;
private Texture title;
private Lluvia lluvia;
private float elapsedTime=0;

	public TitleScreen(Demo game) {
	    super(game);
		
	    Parametros.dia=1;
	    Parametros.controlesActivos=true;
		Parametros.zona=1;
	    
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, Parametros.getAltoPantalla(), Parametros.getAltoPantalla());
	    
	    batch = new SpriteBatch();
	    background = new Texture("Menu/titleBackground.jpg");
	    title = new Texture("Menu/titleTitle.png");
		titleActor = new Image(title);
		lluvia = new Lluvia();
		
		this.uiStage.addActor(lluvia);
		lluvia.setScaleY(Parametros.getAltoPantalla()/125);
		lluvia.setScaleX(Parametros.getAnchoPantalla()/125);
		this.uiStage.addActor(titleActor);
		titleActor.setBounds(0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
		
		tabla = new Table();
		tabla.setFillParent(true);
		
		tabla.setPosition(0, -170);	
		tabla.pad(60);
		
		TextButton boton=new TextButton("Jugar",ResourceManager.textButtonStyle);
		
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				this.dispose();

				game.setScreen(new StartScreen(game));
				
				return false;
				});
		TextButton botonOpciones=new TextButton("Dificultad",ResourceManager.textButtonStyle);
		botonOpciones.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				this.dispose();

				game.setScreen(new OptionsScreen(game));
				
				return false;
				});
		TextButton botonSalir=new TextButton("Salir", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				this.dispose();
			Gdx.app.exit();
				return false;
				});
		
		tabla.add(botonOpciones).padRight(10).space(10).width(200).height(110);
		tabla.add(boton).padRight(10).width(200).height(110);
		tabla.add(botonSalir).space(10).width(200).height(110);

		this.uiStage.addActor(tabla);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	    Pixmap pixmap = new Pixmap(Gdx.files.internal("01-FS/Objetos/cursor.png"));
		int xHotspot = 15, yHotspot = 15;
		Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
		Gdx.graphics.setCursor(cursor);
		
		super.render(delta);
		
	    // Mueve el actor titleActor en funci�n del tiempo transcurrido
	    if (elapsedTime < 1) {
	        titleActor.setY(titleActor.getY() + 7 * delta);
	    } else if (elapsedTime < 4) {
	        titleActor.setY(titleActor.getY() - 7 * delta);
	    }

	    if (elapsedTime > 2) {
	        elapsedTime = 0;
	    } else {
	        elapsedTime += delta;
	    }
	    
	     uiStage.act();
	     batch.begin();
	     batch.draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	     batch.end();
	     uiStage.draw();	    
	}
}