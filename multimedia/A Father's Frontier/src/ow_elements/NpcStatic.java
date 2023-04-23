package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.OverWorldScreen;

public class NpcStatic extends Element{

    private OverWorldScreen nivel;
    private Texto interaccion;
    private int siguienteInteraccion = 0;
    private boolean esperaClick = false;
    private boolean unaVez=true;
    
    public NpcStatic(float x, float y, Stage s, OverWorldScreen nivel, String animacion, String direccion) {
        super(x, y, s);
        this.nivel = nivel;
        
        prepararAnimacion(animacion, false);
        
        switch (direccion) {
            case "frente":
                setAnimation(idleFrente);
                break;
            case "izquierda":
                setAnimation(idleIzquierda);
                break;
            case "derecha":
                setAnimation(idleDerecha);
                break;
            case "espaldas":
                setAnimation(idleEspaldas);
                break;
        }

        this.setPolygon(8, 32, 23, 5, 5);
    }

    private void comprobarColisiones() {
        if (this.nivel.prota.overlaps(this)) {
            this.nivel.prota.preventOverlap(this);
            
            if(unaVez==true) {
            	interactuar();
            }
            
            else {
            	
            }
        }
        else {
        	unaVez=true;
        }
    }
    
    public void act(float delta) {
        super.act(delta);
        comprobarColisiones();
    }
    
    private void interactuar() {
        Parametros.controlesActivos = false; // se desactivan los controles del personaje
        
        if (!esperaClick) {
            switch (siguienteInteraccion) {
                case 0:
	            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
                    interaccion = new Texto("El r�o T�mesis esconde grandes secretos\nen su interior...");
                    interaccion.setPosition(getX() + 50, getY() + 50);
                    this.nivel.uiStage.addActor(interaccion);
                    siguienteInteraccion++;
                    esperaClick = true; // Se espera un clic antes de avanzar a la siguiente interacci�n
                    break;
                
                case 1:
	            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
                    interaccion = new Texto("En tiempos de guerra, la muerte puede\nsentirse desde su rivera...");
                    this.nivel.uiStage.addActor(interaccion);
                    siguienteInteraccion++;
                    esperaClick = true; // Se espera un clic antes de reiniciar el contador
                    break;
                
                case 2:
	            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	            	unaVez=false;
                    break;
            }
        } else {
            // Se espera un clic para continuar
            if (Gdx.input.justTouched()) {
                esperaClick = false; // Se reinicia la variable para esperar un clic
                interaccion.hide();
                if (siguienteInteraccion == 2) {
                    siguienteInteraccion = 0; // Se reinicia el contador despu�s de la �ltima interacci�n
                    Parametros.controlesActivos = true; // se activan los controles del personaje
                }
            }
        }
    }
    
}