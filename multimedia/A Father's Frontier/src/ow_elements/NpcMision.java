package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import game.Parametros;
import managers.AudioManager;
import screens.OverWorldScreen;

public class NpcMision extends Element {
	private OverWorldScreen nivel;
	private Texto interaccion;
	private int siguienteInteraccion = 0;
	private Image bocadillo;
	private float elapsedTime = 0;
	private Element dialogBox;
	private String dialogo1;
	private String dialogo2;
	private String dialogo3;
	private String dialogo4;
	private String dialogo5;
	private TarjetaDeZona tarjeta;
	private int mision;

	public NpcMision(float x, float y, Stage s, OverWorldScreen nivel, String animacion, String direccion, String dialogo1, String dialogo2, String dialogo3, String dialogo4, String dialogo5, int mision) {
		super(x, y, s);
		this.nivel = nivel;
		this.dialogo1 = dialogo1;
		this.dialogo2 = dialogo2;
		this.dialogo3 = dialogo3;
		this.dialogo4 = dialogo4;
		this.dialogo5 = dialogo5;
		this.mision = mision;
		
		prepararAnimacion(animacion, false);
		switch (direccion) {
		case "frente":
			setAnimation(idleFrente);
			break;
		case "izquierda":
			setAnimation(getIdleIzquierda());
			break;
		case "derecha":
			setAnimation(idleDerecha);
			break;
		case "espaldas":
			setAnimation(idleEspaldas);
			break;
		}
		
		this.setPolygon(8, 32, 23, 5, 5);
		
		dialogBox=new Element(0,0,s,this.getWidth(),this.getHeight());
		dialogBox.setPosition(this.getX(),this.getY());
		dialogBox.setRectangle();
	}

	public void act(float delta) {
	    super.act(delta);
	    float distanciaX = Math.abs(nivel.prota.getX() - getX());
	    float distanciaY = Math.abs(nivel.prota.getY() - getY());

	    comprobarColisiones();
	    bocadillo(distanciaX, distanciaY);
	    
	    if(bocadillo!=null) {
		    if (elapsedTime < 1) {
		        bocadillo.setY(bocadillo.getY() + 3 * delta);
		    } else if (elapsedTime < 3) {
		    	bocadillo.setY(bocadillo.getY() - 3 * delta);
		    }

		    if (elapsedTime > 2) {
		        elapsedTime = 0;
		    } else {
		        elapsedTime += delta;
		    }
	    }
	    
	    if(mision == 3 && Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == false && Parametros.mision_el_viejo_general_item==false && Parametros.mision_el_viejo_general_finalizada == true) {
	    	this.remove();
	    }
	    
	    if(siguienteInteraccion==0) {
		    if (Gdx.input.justTouched()) {
		        Vector2 clickCoords = nivel.mainStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		        if (distanciaX < 60 && distanciaY < 60 && dialogBox.getBoundaryPolygon().contains(clickCoords.x, clickCoords.y)) {
		            interactuar();
		        }
		    }
	    }
	    else {
	    	if (Gdx.input.justTouched()) {
		    		if(interaccion.completo==true) {
			    		interactuar();
		    		}
		    		else {
		    			interaccion.completo=true;
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		    		}
	    		}
	    }
	}

	private void comprobarColisiones() {
		if (this.nivel.prota.overlaps(this)) {
			this.nivel.prota.preventOverlap(this);
		}
	}
	
	private void bocadillo(float distanciaX, float distanciaY) {
		if (distanciaX < 60 && distanciaY < 60 &&
		        ((nivel.prota.getX() < getX() && nivel.prota.getY() < getY()) ||
		         (nivel.prota.getX() >= getX() && nivel.prota.getY() < getY()) ||
		         (nivel.prota.getX() < getX() && nivel.prota.getY() >= getY()) ||
		         (nivel.prota.getX() >= getX() && nivel.prota.getY() >= getY()))) {
		    // el personaje est� cerca y en la direcci�n correcta, creamos y agregamos el actor bocadillo
		    if (bocadillo == null) {
		    	
		    	switch(this.mision) {
			    	case 0:
				    	if(Parametros.mision_un_extrano_muneco == true && Parametros.mision_un_extrano_muneco_completada == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 1:
				    	if(Parametros.mision_el_tesoro_perdido == true && Parametros.mision_el_tesoro_perdido_completada == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 2:
				    	if(Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 3:
				    	if(Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 4:
				    	if(Parametros.mision_una_lettera_d_amore == true) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 5:
				    	if(Parametros.mision_buen_chico == true) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 6:
				    	if(Parametros.mision_malas_vistas == true && Parametros.mision_malas_vistas_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 7:
				    	if(Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 8:
				    	if(Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 9:
				    	if(Parametros.mision_magia_blanca == true && Parametros.mision_magia_blanca_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 10:
				    	if(Parametros.mision_un_glamuroso_collar == true && Parametros.mision_un_glamuroso_collar_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 11:
				    	if(Parametros.mision_caos_en_la_ciudad == true && Parametros.mision_caos_en_la_ciudad_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
			    	case 12:
				    	if(Parametros.mision_caos_en_la_ciudad == true && Parametros.mision_caos_en_la_ciudad_item == false) {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_ow.png"));
				    	}
				    	else {
				    		bocadillo = new Image(new Texture("02-OW/Personajes/bocadillo_mision_ow.png"));
				    	}
				    	break;
		    	}
		    	
		    	switch(this.mision) {
		    		case 6:
	    				bocadillo.setPosition(getX()+17, getY()-40 + getHeight() + 10); // ajustar la posici�n del bocadillo
	    				break;
		    		default:
		        		switch(this.dialogo1) {
		        		case "Jericho: Esoy arruinado... Todos piensan que\nestoy loco... �Ahora dicen que rapto ni�os!":
			        		bocadillo.setPosition(getX()+18, getY()-30 + getHeight() + 10); // ajustar la posici�n del bocadillo
		        			break;
		        		default:
			        		bocadillo.setPosition(getX()+17, getY()-10 + getHeight() + 10); // ajustar la posici�n del bocadillo
		        			break;
		        		}
		    			break;
		    	}
		        bocadillo.setName("bocadillo");
		    	nivel.mainStage.addActor(bocadillo);
		    }
		} else {
		    // el personaje se alej� o no est� en la direcci�n correcta, eliminamos el actor bocadillo
		    if (bocadillo != null) {
		        bocadillo.remove();
		        bocadillo = null;
		    }
		}
	}
	
	private void interactuar() {
	    Parametros.controlesActivos = false; // se desactivan los controles del personaje
	    
	    switch(this.mision) {
	    
    	// MISI�N 0
	    
	    	case 0:
	    	    if(Parametros.mision_un_extrano_muneco == true && Parametros.mision_un_extrano_muneco_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Un extra�o mu�eco -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_un_extrano_muneco = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_un_extrano_muneco == false && Parametros.mision_un_extrano_muneco_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_extrano_muneco == false && Parametros.mision_un_extrano_muneco_completada == false && Parametros.mision_un_extrano_muneco_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_un_extrano_muneco = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_un_extrano_muneco_item=false;
	    	            Parametros.dinero+=3;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_un_extrano_muneco_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_extrano_muneco == true && Parametros.mision_un_extrano_muneco_completada == false && Parametros.mision_un_extrano_muneco_item==false && Parametros.mision_un_extrano_muneco_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_un_extrano_muneco = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.dinero+=3;
	    	            Parametros.mision_un_extrano_muneco_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_extrano_muneco == true && Parametros.mision_un_extrano_muneco_completada == false && Parametros.mision_un_extrano_muneco_item==false && Parametros.mision_un_extrano_muneco_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	
	    	// MISI�N 1
	    	
	    	case 1:
	    	    if(Parametros.mision_el_tesoro_perdido == true && Parametros.mision_el_tesoro_perdido_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- El tesoro perdido -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_el_tesoro_perdido = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_el_tesoro_perdido == false && Parametros.mision_el_tesoro_perdido_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_tesoro_perdido == false && Parametros.mision_el_tesoro_perdido_completada == false && Parametros.mision_el_tesoro_perdido_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_el_tesoro_perdido = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/item.wav");
	    	            Parametros.mision_el_tesoro_perdido_item=false;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_el_tesoro_perdido_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_tesoro_perdido == true && Parametros.mision_el_tesoro_perdido_completada == false && Parametros.mision_el_tesoro_perdido_item==false && Parametros.mision_el_tesoro_perdido_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_el_tesoro_perdido = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/item.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_el_tesoro_perdido_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_tesoro_perdido == true && Parametros.mision_el_tesoro_perdido_completada == false && Parametros.mision_el_tesoro_perdido_item==false && Parametros.mision_el_tesoro_perdido_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
		    	    }
	    		}
	    		    break;
	    	    
	    	//MISION 2
	    	    
	    	case 2:
	    	    if(Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- El viejo general -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_el_viejo_general = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_el_viejo_general == false && Parametros.mision_el_viejo_general_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_viejo_general == false && Parametros.mision_el_viejo_general_completada == false && Parametros.mision_el_viejo_general_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_el_viejo_general = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.dinero+=2;
	    	            Parametros.mision_el_viejo_general_item=false;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_el_viejo_general_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == false && Parametros.mision_el_viejo_general_item==false && Parametros.mision_el_viejo_general_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_el_viejo_general = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_el_viejo_general_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == false && Parametros.mision_el_viejo_general_item==false && Parametros.mision_el_viejo_general_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    break;
	    	case 3:
		    	switch (siguienteInteraccion) {
		        case 0:
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo1, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 1:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo2, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 2:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo3, "talk2");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 3:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion.hide();
		            if (Parametros.mision_el_viejo_general == true && Parametros.mision_el_viejo_general_completada == false && Parametros.mision_el_viejo_general_item==false && Parametros.mision_el_viejo_general_finalizada == false) {}
		            else {AudioManager.playSound("02-OW/Audio/sounds/item.wav");}
		            Parametros.mision_el_viejo_general_completada = false;
		            Parametros.mision_el_viejo_general_item = false;
		            Parametros.controlesActivos = true; // se activan los controles del personaje
		            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
		            break;
	    		}
	    		break;
	    		
	    	case 4:
	    	    if(Parametros.mision_una_lettera_d_amore == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- �Misi�n completada! -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.dinero+=2;
	    	            Parametros.mision_una_lettera_d_amore=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_una_lettera_d_amore == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    break;
	    	    
	    	case 5:
	    	    if(Parametros.mision_buen_chico == true && Parametros.mision_buen_chico_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Buen chico -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_buen_chico = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_buen_chico == false && Parametros.mision_buen_chico_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_buen_chico == false && Parametros.mision_buen_chico_completada == false && Parametros.mision_buen_chico_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_buen_chico = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_buen_chico_item=false;
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_buen_chico_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_buen_chico == true && Parametros.mision_buen_chico_completada == false && Parametros.mision_buen_chico_item==false && Parametros.mision_buen_chico_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_buen_chico = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.dinero+=2;
	    	            Parametros.mision_buen_chico_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_buen_chico == true && Parametros.mision_buen_chico_completada == false && Parametros.mision_buen_chico_item==false && Parametros.mision_buen_chico_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    break;
	    	    
	    	case 6:
	    	    if(Parametros.mision_malas_vistas == true && Parametros.mision_malas_vistas_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Malas vistas -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_malas_vistas = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_malas_vistas == false && Parametros.mision_malas_vistas_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_malas_vistas == false && Parametros.mision_malas_vistas_completada == false && Parametros.mision_malas_vistas_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_malas_vistas = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_malas_vistas_item=false;
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_malas_vistas_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_malas_vistas == true && Parametros.mision_malas_vistas_completada == false && Parametros.mision_malas_vistas_item==false && Parametros.mision_malas_vistas_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_malas_vistas = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.dinero+=2;
	    	            Parametros.mision_malas_vistas_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_malas_vistas == true && Parametros.mision_malas_vistas_completada == false && Parametros.mision_malas_vistas_item==false && Parametros.mision_malas_vistas_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	case 7:
	    	    if(Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 5:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto("degenerado, ya que cambia de localizaci�n\ndtodos los d�as.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 6:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto("�Qu� me dice? �Lo encontrar� por m�?\nLe recompensar� con cuatro jugosas libras.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 7:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Elemental, querido Simon -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_elemental_mi_querido_simon = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_elemental_mi_querido_simon == false && Parametros.mision_elemental_mi_querido_simon_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Simon: �D�nde estar� ese anciano loco?\nEspero que lo encuentre pronto, amigo.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_elemental_mi_querido_simon == false && Parametros.mision_elemental_mi_querido_simon_completada == false && Parametros.mision_elemental_mi_querido_simon_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Simon: Ese viejo secuestra-ni�os tiene\nque andar por alguna parte...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Lo ha encontrado ya? �Jericho?\n�Detr�s del UK Market? �BUENA PERSONA?", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Fingir� que no ha dicho eso �ltimo...\nUn placer hacer negocios con usted, amigo.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�El gran Simon Sinclair lo ha vuelto\n a hacer! �AJ�!", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_elemental_mi_querido_simon = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_elemental_mi_querido_simon_item=false;
	    	            Parametros.dinero+=4;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_elemental_mi_querido_simon_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_completada == false && Parametros.mision_elemental_mi_querido_simon_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Simon: Ese viejo secuestra-ni�os tiene\nque andar por alguna parte...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Lo ha encontrado ya? �Jericho?\n�Detr�s del UK Market? �BUENA PERSONA?", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Fingir� que no ha dicho eso �ltimo...\nUn placer hacer negocios con usted, amigo.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�El gran Simon Sinclair lo ha vuelto\n a hacer! �AJ�!", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_elemental_mi_querido_simon = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_elemental_mi_querido_simon_item=false;
	    	            Parametros.dinero+=4;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_elemental_mi_querido_simon_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_completada == false && Parametros.mision_elemental_mi_querido_simon_item==false && Parametros.mision_elemental_mi_querido_simon_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Simon: Podr�a haberlo hecho sin usted,\nsin embargo, he de admitir que ha ayudado.", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	case 8:
		    	switch (siguienteInteraccion) {
		        case 0:
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo1, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 1:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo2, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 2:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo3, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 3:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo4, "talk2");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 4:	
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo5, "talk2");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 5:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion.hide();
		            if (Parametros.mision_elemental_mi_querido_simon == true && Parametros.mision_elemental_mi_querido_simon_completada == false && Parametros.mision_elemental_mi_querido_simon_item==false && Parametros.mision_elemental_mi_querido_simon_finalizada == false) {}
		            else {AudioManager.playSound("02-OW/Audio/sounds/item.wav");}
		            Parametros.mision_elemental_mi_querido_simon_completada = false;
		            Parametros.mision_elemental_mi_querido_simon_item = false;
		            Parametros.controlesActivos = true; // se activan los controles del personaje
		            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
		            break;
	    		}
	    		break;
	    	
	    	case 9:
	    	    if(Parametros.mision_magia_blanca == true && Parametros.mision_magia_blanca_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Magia blanca -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_magia_blanca = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_magia_blanca == false && Parametros.mision_magia_blanca_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_magia_blanca == false && Parametros.mision_magia_blanca_completada == false && Parametros.mision_magia_blanca_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Esa es mi...! Digo... Esa es mi bolsa...\nMuchas gracias, amigo... No lo cuente...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_magia_blanca = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_magia_blanca_item=false;
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_magia_blanca_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_magia_blanca == true && Parametros.mision_magia_blanca_completada == false && Parametros.mision_magia_blanca_item==false && Parametros.mision_magia_blanca_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Esa es mi...! Digo... Esa es mi bolsa...\nMuchas gracias, amigo... No lo cuente...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_magia_blanca = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.dinero+=2;
	    	            Parametros.mision_magia_blanca_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_magia_blanca == true && Parametros.mision_magia_blanca_completada == false && Parametros.mision_magia_blanca_item==false && Parametros.mision_magia_blanca_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Ni una palabra a nadie de lo que ha\nsucedido entre nosotros, �Entendido?", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	case 10:
	    	    if(Parametros.mision_un_glamuroso_collar == true && Parametros.mision_un_glamuroso_collar_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo3, "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo4, "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Un glamuroso collar -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_un_glamuroso_collar = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_un_glamuroso_collar == false && Parametros.mision_un_glamuroso_collar_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo5, "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_glamuroso_collar == false && Parametros.mision_un_glamuroso_collar_completada == false && Parametros.mision_un_glamuroso_collar_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Es ese mi collar? �Entr�guemelo de inmediato!\nAqu� tiene su recompensa, no se muera de hambre.", "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_un_glamuroso_collar = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_un_glamuroso_collar_item=false;
	    	            Parametros.dinero+=4;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_un_glamuroso_collar_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_glamuroso_collar == true && Parametros.mision_un_glamuroso_collar_completada == false && Parametros.mision_un_glamuroso_collar_item==false && Parametros.mision_un_glamuroso_collar_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Es ese mi collar? �Entr�guemelo de inmediato!\nTome su recompensa, no muera de hambre.", "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_un_glamuroso_collar = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.dinero+=4;
	    	            Parametros.mision_un_glamuroso_collar_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_un_glamuroso_collar == true && Parametros.mision_un_glamuroso_collar_completada == false && Parametros.mision_un_glamuroso_collar_item==false && Parametros.mision_un_glamuroso_collar_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Haber encontrado este collar no le hace menos\npordiosero de lo que es, �Sabe?", "talk2");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	case 11:
	    	    if(Parametros.mision_caos_en_la_ciudad == true && Parametros.mision_caos_en_la_ciudad_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	        
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto(this.dialogo1, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo2, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo3, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 3:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo4, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 4:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto(this.dialogo5, "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 5:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            tarjeta = new TarjetaDeZona("- Caos en la ciudad -",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/mision.wav");
	    	            Parametros.mision_caos_en_la_ciudad = false;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if(Parametros.mision_caos_en_la_ciudad == false && Parametros.mision_caos_en_la_ciudad_completada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("Espero que no le moleste que no vaya a verle...\n�Ha ido a avisarle ya? Puedo recomensarle...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_caos_en_la_ciudad == false && Parametros.mision_caos_en_la_ciudad_completada == false && Parametros.mision_caos_en_la_ciudad_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Ha hablado con mi \"amigo\"?\nS-se ha enfadado, �Verdad?", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto("E-en f-f-f-fin... No pasa nada...\nA-aqu� tiene su recompensa...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_caos_en_la_ciudad = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_caos_en_la_ciudad_item=false;
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_caos_en_la_ciudad_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_caos_en_la_ciudad == false && Parametros.mision_caos_en_la_ciudad_completada == false && Parametros.mision_caos_en_la_ciudad_finalizada == false) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("�Ha hablado con mi \"amigo\"?\nS-se ha enfadado, �Verdad?", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            interaccion = new Texto("E-en f-f-f-fin... No pasa nada...\nA-aqu� tiene su recompensa...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 2:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.mision_caos_en_la_ciudad = true;
	    	            tarjeta = new TarjetaDeZona("�Misi�n completada!",1);
	    	            nivel.uiStage.addActor(tarjeta);
	    	            AudioManager.playSound("02-OW/Audio/sounds/comprar.wav");
	    	            Parametros.mision_caos_en_la_ciudad_item=false;
	    	            Parametros.dinero+=2;
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            Parametros.mision_caos_en_la_ciudad_finalizada=true;
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	    
	    	    else if (Parametros.mision_caos_en_la_ciudad == true && Parametros.mision_caos_en_la_ciudad_completada == false && Parametros.mision_caos_en_la_ciudad_item==false && Parametros.mision_caos_en_la_ciudad_finalizada == true) {
	    		    switch (siguienteInteraccion) {
	    	        case 0:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion = new Texto("C-creo que soy hombre muerto...\nDeb� haberme presentado...", "talk1");
	    	            this.nivel.uiStage.addActor(interaccion);
	    	            siguienteInteraccion++;
	    	            break;
	    	        case 1:
	    	            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	    	            interaccion.hide();
	    	            Parametros.controlesActivos = true; // se activan los controles del personaje
	    	            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
	    	            break;
	    		    }
	    	    }
	    	break;
	    	case 12:
		    	switch (siguienteInteraccion) {
		        case 0:
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo1, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 1:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo2, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 2:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo3, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 3:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion = new Texto(this.dialogo4, "talk1");
		            this.nivel.uiStage.addActor(interaccion);
		            siguienteInteraccion++;
		            break;
		        case 4:
		        	interaccion.remove();
		            AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		            interaccion.hide();
		            if (Parametros.mision_caos_en_la_ciudad == true && Parametros.mision_caos_en_la_ciudad_completada == false && Parametros.mision_caos_en_la_ciudad_item==false && Parametros.mision_caos_en_la_ciudad_finalizada == false) {}
		            else {AudioManager.playSound("02-OW/Audio/sounds/item.wav");}
		            Parametros.mision_caos_en_la_ciudad_completada = false;
		            Parametros.mision_caos_en_la_ciudad_item = false;
		            Parametros.controlesActivos = true; // se activan los controles del personaje
		            siguienteInteraccion = 0; // se reinicia el contador despu�s de la �ltima interacci�n
		            break;
	    		}
	    		break;
	    }
	}
}
