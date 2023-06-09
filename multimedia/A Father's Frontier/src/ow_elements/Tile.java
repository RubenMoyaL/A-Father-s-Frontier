package ow_elements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import screens.OverWorldScreen;

/**
 * Establece las tiles que se superpondr�n al jugador en la Ciudad.
 * @author Rub�n Moya
 */
public class Tile extends Element {
	private OverWorldScreen nivel;
	private String tile;
	
	public Tile(float x, float y, Stage s, OverWorldScreen nivel, String tile, float separacion) {
		super(x, y, s);
		this.nivel = nivel;
		this.tile = tile;
		
		loadFullAnimation(tile, 1, 1, 0.1f,false);
		this.setRectangle(31, 31, separacion, 0);
	}

	public void act(float delta) {
	    super.act(delta);
	    switch(this.tile) {
	    	case "farolaA":
	    		break;
	    	case "farolaB":
	    		break;
	    	case "fuente":
	    		break;
	    	case "coche":
	    		break;
	    	default:
	    		comprobarColisiones();
	    		break;
	    }
	}

	/**
	 * Comprueba las colisiones de la Tile con el protagonista.
	 */
	private void comprobarColisiones() {
		if (this.nivel.prota.overlaps(this)) {
			this.nivel.prota.preventOverlap(this);
		}
	}
}