package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.OverWorldScreen;

public class Objeto extends Element {
	private OverWorldScreen nivel;
	private Texto interaccion;
	private int siguienteInteraccion = 0;
	private Element dialogBox;
	private String dialogo1;
	private String sprite2;
	private String sprite1;
	private int mision;
	
	
	public Objeto(float x, float y, Stage s, OverWorldScreen nivel, String sprite1, String sprite2, String dialogo1, int mision) {
		super(x, y, s);
		this.nivel = nivel;
		this.dialogo1 = dialogo1;
		this.sprite2 = sprite2;
		this.sprite1 = sprite1;
		this.mision = mision;
		
		loadFullAnimation(sprite1, 1, 1, 0.1f,false);
		
		dialogBox=new Element(0,0,s,this.getWidth(),this.getHeight());
		dialogBox.setPosition(this.getX(),this.getY());
		dialogBox.setRectangle();
	}

	public void act(float delta) {
	    super.act(delta);
	    float distanciaX = Math.abs(nivel.prota.getX() - getX());
	    float distanciaY = Math.abs(nivel.prota.getY() - getY());

	    glow();
	    
	    if (Gdx.input.justTouched()) {
	        Vector2 clickCoords = nivel.mainStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
	        if (distanciaX < 60 && distanciaY < 60 && dialogBox.getBoundaryPolygon().contains(clickCoords.x, clickCoords.y)) {
	            interactuar();
	        }
	    }
	}
	
	private void glow() {
	    Vector2 cursorCoords = nivel.mainStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
	    if (this.getBoundaryPolygon().contains(cursorCoords.x, cursorCoords.y)) {
	        loadFullAnimation(this.sprite2, 1, 1, 0.1f, false);
	    } else {
	        loadFullAnimation(this.sprite1, 1, 1, 0.1f, false);
	    }
	}

	
	private void interactuar() {
	    Parametros.controlesActivos = false; // se desactivan los controles del personaje
		
	    switch(this.mision) {
	    	case 0:
		    	switch (siguienteInteraccion) {
		        case 0:
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo1);
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 1:
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion.hide();
		            Parametros.mision_un_extrano_muneco_completada = false;
		            Parametros.controlesActivos = true; // se activan los controles del personaje
		            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
		            this.remove();
		            break;
			    }
		    	break;
	    }
	}
}