package ow_elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.OverWorldScreen;

public class NpcMision extends Element{
	
	private OverWorldScreen nivel;

	public NpcMision(float x, float y, Stage s, OverWorldScreen nivel, String animacion, String direccion) {
		super(x,y,s);
		this.nivel=nivel;
		
        prepararAnimacion(animacion, false);
        
        switch(direccion) {
	        case "frente" :
		        setAnimation(idleFrente);
	        break;
	        case "izquierda" :
		        setAnimation(idleIzquierda);
	        break;
	        case "derecha" :
		        setAnimation(idleDerecha);
	        break;
	        case "espaldas" :
		        setAnimation(idleEspaldas);
	        break;
        }
        
        this.setPolygon(8, 32, 23, 5, 5);
	}
	
	private void comprobarColisiones() {
		if(this.nivel.prota.overlaps(this)) {
			this.nivel.prota.preventOverlap(this);
		}
	}
	
	public void act(float delta) {
		super.act(delta);
        comprobarColisiones();
	}
}
