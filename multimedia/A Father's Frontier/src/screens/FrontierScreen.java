package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fs_elements.Boton;
import fs_elements.FS1_1;
import fs_elements.FS1_2;
import fs_elements.FS1_3;
import fs_elements.FS1_4;
import fs_elements.FS2_1;
import fs_elements.FS2_2;
import fs_elements.FS2_3;
import fs_elements.FS2_4;
import fs_elements.FS3_1;
import fs_elements.FS3_2;
import fs_elements.FS3_3;
import fs_elements.FS3_4;
import fs_elements.Lluvia;
import fs_elements.Maletin;
import fs_elements.Mesa;
import fs_elements.Objeto;
import fs_elements.Reglas;
import fs_elements.Reloj;
import fs_elements.Texto;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

/**
 * La clase principal de la Frontier Screen.
 * @author Rub�n Moya
 */
public class FrontierScreen extends BScreen implements InputProcessor{

//Indispensables
Stage mainStage;
OrthographicCamera camara;
private OrthogonalTiledMapRenderer renderer;
private TiledMap map;
public float mouseX;
public float mouseY;

//Escenario
private Texture blackBackground;
private Texture backgroundTexture;
private Texture periodicoTexture;
private Actor backgroundActor;
private Actor blackBackgroundActor;
private Actor periodicoActor;
private Lluvia lluvia;
private Mesa mesa;
private Boton botonRojo;
private Boton botonVerde;
private Maletin maletin;

//M�sica
private Music musica;
private Music musica2;
private Music tiktak;

//Sistema
private int contador = 0;
float tiempo = 0f;
public boolean terminado = false;
private boolean periodicoActivo=false;
@SuppressWarnings("unused")
private boolean controlesSiNo=false;

//Personajes
private FS1_1 fs1_1;
private FS1_2 fs1_2;
private FS1_3 fs1_3;
private FS1_4 fs1_4;
private FS2_1 fs2_1;
private FS2_2 fs2_2;
private FS2_3 fs2_3;
private FS2_4 fs2_4;
private FS3_1 fs3_1;
private FS3_2 fs3_2;
private FS3_3 fs3_3;
private FS3_4 fs3_4;

//Interfaz
public static Reloj reloj;
private Texto texto;

//Objetos
private Objeto pasaporte;
private Objeto permiso;
private Reglas reglas;
private Objeto botella;
private Objeto pistola;
private Objeto cuchillo;
private Objeto carta;
private Objeto pintalabios;
private Objeto sombrero;
private Objeto hucha;
private Objeto sopa;
private Objeto platano;
private Objeto llave;
private Objeto duxer;

/**
 * Constructor de la Frontier Screen, en el que se declaran todos
 * los actores, el mapa y otros elementos, adem�s de ejecutar la
 * progresi�n del juego.
 * @param game El par�metro game genera un nuevo juego.
 */
	public FrontierScreen(Demo game) {
		super(game);
		mainStage = new Stage();
		lluvia = new Lluvia();
		
		//Dinero
		Parametros.dineroAnterior = Parametros.dinero;
		
		//Fondos
		backgroundTexture = new Texture(Gdx.files.internal("Menu/PapersBackgraundTsx.png"));
		backgroundActor = new Image(backgroundTexture);
		blackBackground = new Texture(Gdx.files.internal("Menu/blackBackground.png"));
		blackBackgroundActor = new Image(blackBackground);
		
		//M�sica
		musica = Gdx.audio.newMusic(Gdx.files.internal("01-FS/Audio/music/maletin_por_favor.wav"));
        musica.setVolume(0.3f);
		musica.setLooping(true);
		musica2 = Gdx.audio.newMusic(Gdx.files.internal("01-FS/Audio/music/lluvia.wav"));
		musica2.play();
        musica2.setVolume(0.4f);
		musica2.setLooping(true);
		tiktak = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/tiktak.wav"));
		
	    //Mapa
		map=ResourceManager.getMap("Mapas/PapersBackground.tmx");
		renderer=new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
		camara=(OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom, Parametros.getAltoPantalla()*Parametros.zoom);
		renderer.setView(camara);

		//Actores
		mainStage.addActor(backgroundActor);
		backgroundActor.setSize(240,180);
		mainStage.addActor(lluvia);

		//Par�metros
		Parametros.controlesActivos = false;  
		Parametros.comienzo = false;       
		Parametros.analizar = false;          
		Parametros.analizado1 = false;        
		Parametros.analizado2 = false;        
		Parametros.analizado3 = false;        
		Parametros.analizado4 = false;        
		Parametros.correcto = false;          
		
		/**
		 * Este switch es el encargado de comenzar la progresi�n
		 * del juego.
		 * @param Parametros.dia Este par�metro es el encargado de
		 * identificar en qu� d�a se encuentra la partida actual.
		 */
		switch (Parametros.dia) {
			case 1:
				
				//Personajes
				fs1_1 = new FS1_1(mainStage);
				mainStage.addActor(fs1_1);
				fs1_1.hide();
				
				fs1_2 = new FS1_2(mainStage);
				mainStage.addActor(fs1_2);
				fs1_2.hide();
				
				fs1_3 = new FS1_3(mainStage);
				mainStage.addActor(fs1_3);
				fs1_3.hide();
				
				fs1_4 = new FS1_4(mainStage);
				mainStage.addActor(fs1_4);
				fs1_4.hide();
				
				//Otros Actores
				mesa = new Mesa();
				mainStage.addActor(mesa);
				
				botonRojo = new Boton(165, "botonRojo.png", "botonRojo_pressed.png");
				mainStage.addActor(botonRojo);
				
				botonVerde = new Boton(200, "botonVerde.png", "botonVerde_pressed.png");
				mainStage.addActor(botonVerde);
				
				maletin = new Maletin();
				mainStage.addActor(maletin);
				maletin.hide();
				
				reglas = new Reglas(170, 50, 60, 74, "reglas.1_0.png", null, null);
				mainStage.addActor(reglas);
				
				reloj = new Reloj();
				mainStage.addActor(reloj);
	
				periodicoTexture = new Texture(Gdx.files.internal("Menu/periodico.1_0.png"));
				periodicoActor = new Image(periodicoTexture);
				
				//Di�logo
				texto = new Texto("�?: Que pase el primero,\n"
								+ "por favor.", 7, 154);
				mainStage.addActor(texto);
			       
				//Variables
				Parametros.comienzo=true;
				contador++;
				break;
				
			case 2:
				
				//Personajes
				fs2_1 = new FS2_1(mainStage);
				mainStage.addActor(fs2_1);
				fs2_1.hide();          
				
				fs2_2 = new FS2_2(mainStage);
				mainStage.addActor(fs2_2);
				fs2_2.hide();          
				
				fs2_3 = new FS2_3(mainStage);
				mainStage.addActor(fs2_3);
				fs2_3.hide();        
				
				fs2_4 = new FS2_4(mainStage);
				mainStage.addActor(fs2_4);
				fs2_4.hide();    
				
				//Otros Actores
				mesa = new Mesa();
				mainStage.addActor(mesa);
				
				botonRojo = new Boton(165, "botonRojo.png", "botonRojo_pressed.png");
				mainStage.addActor(botonRojo);
				
				botonVerde = new Boton(200, "botonVerde.png", "botonVerde_pressed.png");
				mainStage.addActor(botonVerde);
				
				maletin = new Maletin();
				mainStage.addActor(maletin);
				maletin.hide();
				
				reglas = new Reglas(161, 50, 69, 74, "reglas.2_0.png", "reglas.2_1.png", null);
				mainStage.addActor(reglas);
				
				reloj = new Reloj();
				mainStage.addActor(reloj);
	
				periodicoTexture = new Texture(Gdx.files.internal("Menu/periodico.2_0.png"));
				periodicoActor = new Image(periodicoTexture);
				
				//Di�logo
				texto = new Texto("�?: Que pase el si-\n"
								+ "guiente, por favor.", 7, 154);
				mainStage.addActor(texto);
			       
				//Variables
				Parametros.comienzo=true;
				contador++;
				break;
				
			case 3:
				
				//Personajes
				fs3_1 = new FS3_1(mainStage);
				mainStage.addActor(fs3_1);
				fs3_1.hide();          
				
				fs3_2 = new FS3_2(mainStage);
				mainStage.addActor(fs3_2);
				fs3_2.hide();          
				
				fs3_3 = new FS3_3(mainStage);
				mainStage.addActor(fs3_3);
				fs3_3.hide();        
				
				fs3_4 = new FS3_4(mainStage);
				mainStage.addActor(fs3_4);
				fs3_4.hide();    
				
				//Otros Actores
				mesa = new Mesa();
				mainStage.addActor(mesa);
				
				botonRojo = new Boton(165, "botonRojo.png", "botonRojo_pressed.png");
				mainStage.addActor(botonRojo);
				
				botonVerde = new Boton(200, "botonVerde.png", "botonVerde_pressed.png");
				mainStage.addActor(botonVerde);
				
				maletin = new Maletin();
				mainStage.addActor(maletin);
				maletin.hide();
				
				reglas = new Reglas(161, 50, 69, 74, "reglas.3_0.png", "reglas.3_1.png", "reglas.3_2.png");
				mainStage.addActor(reglas);
				
				reloj = new Reloj();
				mainStage.addActor(reloj);

				periodicoTexture = new Texture(Gdx.files.internal("Menu/periodico.3_0.png"));
				periodicoActor = new Image(periodicoTexture);
				
				//Di�logo
				texto = new Texto("�?: Siguiente...\n"
								+ "Vamos...", 7, 154);
				mainStage.addActor(texto);
			       
				//Variables
				Parametros.comienzo=true;
				contador++;
				break;
				
		default:
			break;
			
		}
		
	}
	
	/**
	 * Este m�todo identifica si se ha pulsado el click izquierdo
	 * para despu�s actuar en consecuencia, dependiendo de en qu�
	 * punto del juego se encuentre el jugador.
	 * @param screenX Lee la posici�n X de la pantalla.
	 * @param screenY Lee la posici�n Y de la pantalla.
	 * @param pointer Lee la posici�n del cursor en la pantalla.
	 * @param button Se declara un bot�n para identificar si este
	 * ha sido pulsado.
	 */
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if(texto.completo==true) {
			switch (Parametros.dia) {
				case 1:
					
			        if (button == Input.Buttons.LEFT && Parametros.comienzo==true && Parametros.lockClick==false) {
			        	
			        	//FS.1_1
			        	
			        	if(contador==1) {
			        		
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			fs1_1.show();
			    			texto = new Texto("�?: El pasaporte y el\n"
			    							+ "equipaje, por favor.", 7, 154);
			    			mainStage.addActor(texto);
			    			
			    			contador++;
			    			
			            } else if (contador==2) {
	
			        		musica.play();
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Comprobar� las reglas\n"
			    							+ "y decidir� qu� hacer)", 7, 154);
			    			mainStage.addActor(texto);
			    			maletin.show();
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.1_1.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			pistola = new Objeto(108, 26, 48, 28, "objeto.pistola.png", "pistola.wav");
			    			mainStage.addActor(pistola);
			    			
			    			reloj.start();
			    			contador++;
			    			
			            } else if (contador==3) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
	
			    			contador=0;
			    			Parametros.controlesActivos=true;
			    			Parametros.analizar=true;
			    			
			            } else if (Parametros.analizado1==true) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			            	FS1_1.texto.remove();
			            	
			    			texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
	
			            	fs1_1.fadeOut();
			            	pasaporte.remove();
			            	maletin.hide();
			            	pistola.remove();
			            	
			            	Parametros.controlesActivos=false;
			    			Parametros.analizar=false;
			    			Parametros.analizado1=false;
			    			contador=4;
			    			
			    		//FS.1_2
			    			
			            } else if (contador==4) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Que pase el\n"
											+ "siguiente, gracias.", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			    			
				        } else if (contador==5) {
			            	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
			    			texto = new Texto("�?: El pasaporte y el\n"
			    							+ "equipaje sobre la mesa.", 7, 154);
			    			mainStage.addActor(texto);
			    			
			    			fs1_2.show();
			    			
			    			contador++;
			    			
			            } else if (contador==6) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Puedo... Debo hacer\n"
			    							+ "esto bien...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.1_2.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			botella = new Objeto(76, 26, 36, 43, "objeto.botella.png", "botella.wav");
			    			mainStage.addActor(botella);
			    			
			    			contador++;
			    			
				        } else if (contador==7) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado2==true) {
			
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS1_2.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs1_2.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	botella.remove();
				        	
			    			Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado2=false;
							contador=8;
							
						//FS.1_3	
							
			            } else if (contador==8) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("Siguiente por aqu�,\n"
											+ "si es tan amable.", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==9) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
							
			    			texto = new Texto("Pasaporte y equipaje,\n"
											+ "sobre la mesa.", 7, 154);
							mainStage.addActor(texto);
							
							fs1_3.show();
							
							contador++;
							
			            } else if (contador==10) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Recuerda por qui�n\n"
			    							+ "est�s aqu�...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.1_3.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			cuchillo = new Objeto(125, 32, 30, 38, "objeto.cuchillo.png", "pistola.wav");
			    			mainStage.addActor(cuchillo);
			    			botella = new Objeto(76, 26, 36, 43, "objeto.botella.png", "botella.wav");
			    			mainStage.addActor(botella);
			    			
			    			contador++;
			    			
				        } else if (contador==11) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado3==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS1_3.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
											+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs1_3.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	botella.remove();
				        	cuchillo.remove();
				        	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado3=false;
							contador=12;
							
						//FS 1_4
							
			            } else if (contador==12) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Por aqu� si es\n"
											+ "tan amable, se�orita.", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==13) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
							texto = new Texto("�?: Pasaporte y objetos\n"
											+ "sobre la mesa, gracias.", 7, 154);
							mainStage.addActor(texto);
							
							fs1_4.show();
							
							contador++;
							
			            } else if (contador==14) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(No puedo dejar que\n"
			    							+ "el miedo me supere...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.1_4.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			pintalabios = new Objeto(130, 30, 22, 32, "objeto.pintalabios.png", "pistola.wav");
			    			mainStage.addActor(pintalabios);
			    			carta = new Objeto(78, 30, 50, 31, "objeto.carta.png", "papeles.wav");
			    			mainStage.addActor(carta);
			    			
			    			contador++;
			    			
				        } else if (contador==15) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado4==true && Parametros.correcto==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS1_4.texto.remove();
				        	
				        	texto = new Texto("por favor, lleva esta.\n"
		        							+ "carta a mi marido...", 7, 154);
				        	mainStage.addActor(texto);
				        	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado4=false;
							Parametros.correcto=false;
				        	contador=16;
							
				        } else if (contador==16) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
				        	texto = new Texto("Es un hombre italiano\n"
				        					+ "de buen vestir, estoy", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
							
				        } else if (contador==17) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
				        	texto = new Texto("segura de que podr�s\n"
				        					+ "encontrarlo...", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (contador==18) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
				        	texto = new Texto("Desiree: Much�simas\n"
				        					+ "gracias...", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (Parametros.analizado4==true && Parametros.correcto==false || contador==19) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("�?: ...\n"
			        						+ "(Seguir� ma�ana...)", 7, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs1_4.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	pintalabios.remove();
				        	
				        	if(contador != 19) {carta.remove();}
				        	else {Parametros.mision_una_lettera_d_amore=false;}
				        	
							Parametros.analizar=false;
							Parametros.analizado4=false;
							terminado = true;	
							contador=0;
							
						//Final del d�a 1
							
				        } else if (terminado==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	
							Parametros.dia++;
							Parametros.frontera=false;
							game.setScreen(new StatsScreen(game));
						    musica.stop();
						    musica2.stop();
						    
				        }
			        	
			        }
			        	break;
	        	
		        case 2:
	
			        if (button == Input.Buttons.LEFT && Parametros.comienzo==true) {
			        	
			        	//FS 2_1
			        	
			        	if(contador==1) {
	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("�?: Su pasaporte y\n"
			    							+ "sus pertenencias.", 7, 154);
			    			mainStage.addActor(texto);
			    			
			    			fs2_1.show();
			    			
			    			contador++;
			    			
			            } else if (contador==2) {
	
			        		musica.play();
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Estoy agotado... Pero\n"
			    							+ "no debo detenerme...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.2_1.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			pistola = new Objeto(92, 33, 37, 23, "objeto.pistola.png", "pistola.wav");
			    			mainStage.addActor(pistola);
			    			sombrero = new Objeto(85, 31, 70, 40, "objeto.sombrero.png", "sombrero.wav");
			    			mainStage.addActor(sombrero);
			    			
			    			reloj.start();
			    			contador++;
			    			
			            } else if (contador==3) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			Parametros.controlesActivos=true;
			    			Parametros.analizar=true;
			    			contador=0;
			    			
			            } else if (Parametros.analizado1==true) {
			        		
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			            	FS2_1.texto.remove();
			            	
			    			texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs2_1.fadeOut();
			            	pasaporte.remove();
			            	maletin.hide();
			            	pistola.remove();
			            	sombrero.remove();
			            	
			            	Parametros.controlesActivos=false;
			    			Parametros.analizar=false;
			    			Parametros.analizado1=false;
			    			contador=4;
			    			
			    		//FS.2_2
			    			
			            } else if (contador==4) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Siguiente, por\n"
											+ "favor...", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			        		
				        } else if (contador==5) {
			            	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
			    			texto = new Texto("�?: Si es tan amable, su\n"
			    							+ "pasaporte y equipaje.", 7, 154);
			    			mainStage.addActor(texto);
	
			    			fs2_2.show();
			    			
			    			contador++;
			    			
			            } else if (contador==6) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(No puedo quit�rmelo\n"
			    							+ "de la cabeza...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.2_2.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			sopa = new Objeto(84, 28, 26, 30, "objeto.sopa.png", "lata.wav");
			    			mainStage.addActor(sopa);
			    			hucha = new Objeto(111, 32, 50, 41, "objeto.hucha.png", "hucha.wav");
			    			mainStage.addActor(hucha);
			    			
			    			contador++;
			    			
				        } else if (contador==7) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado2==true) {
			
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS2_2.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs2_2.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	hucha.remove();
				        	sopa.remove();
				        	
			    			Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado2=false;
							contador=8;
							
						//FS.2_3
							
			            } else if (contador==8) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: �Usted es el si-\n"
											+ "guiente? Pase por aqu�.", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==9) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
							texto = new Texto("�?: Su pasaporte y sus\n"
											+ "bienes sobre la mesa.", 7, 154);
							mainStage.addActor(texto);
							
							fs2_3.show();
							
							contador++;
							
			            } else if (contador==10) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Cada segundo, esto\n"
			    							+ "solo empeora...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.2_3.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			platano = new Objeto(119, 29, 34, 36, "objeto.platano.png", "banana.wav");
			    			mainStage.addActor(platano);
			    			cuchillo = new Objeto(85, 32, 30, 38, "objeto.cuchillo.png", "pistola.wav");
			    			mainStage.addActor(cuchillo);
			    			
			    			contador++;
			    			
				        } else if (contador==11) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado3==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS2_3.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
											+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs2_3.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	platano.remove();
				        	cuchillo.remove();
				        	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado3=false;
							contador=12;
						
						//FS.2_4
							
			            } else if (contador==12) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Si... Siguiente,\n"
											+ "por aqu�.", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==13) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: E... Equipaje y...\n"
											+ "Pasaporte, por favor.", 7, 154);
							mainStage.addActor(texto);
	
							fs2_4.show();
							
							contador++;
							
			            } else if (contador==14) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Esta chica... Me\n"
			    							+ "resulta familiar...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.2_4.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			sopa = new Objeto(84, 28, 26, 30, "objeto.sopa.png", "lata.wav");
			    			mainStage.addActor(sopa);
			    			botella = new Objeto(113, 28, 36, 43, "objeto.botella.png", "botella.wav");
			    			mainStage.addActor(botella);
			    			
			    			contador++;
			    			
				        } else if (contador==15) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado4==true) {
	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS2_4.texto.remove();
				        	
						    musica.stop();
			            	fs2_4.fadeOut();
				        	
				        	texto = new Texto("(La chica ha salido\n"
		        							+ "corriendo con una ", 7, 154);
				        	mainStage.addActor(texto);
	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado4=false;
				        	contador=16;
							
				        } else if (contador==16) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	
				        	texto = new Texto("expresi�n terrible en\n"
				        					+ "su rostro...)", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	reloj.stop();
				        	contador++;
							
				        } else if (contador==17) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
	
				        	texto = new Texto("(No s� qu� est� su-\n"
											+ "cediendo... Pero", 7, 154);
				    		
				    		mainStage.addActor(blackBackgroundActor);
				    		blackBackgroundActor.setSize(240,180);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (contador==18) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
				        	texto = new Texto("debo averiguar qu�\n"
				        					+ "se esconde detr�s", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (contador==19) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("de todo esto... Cues-\n"
			        						+ "te lo que cueste...)", 7, 154);
			        		mainStage.addActor(texto);
			        		
				        	pasaporte.remove();
				        	maletin.hide();
				        	sopa.remove();
				        	botella.remove();
	
							Parametros.mision_un_encuentro_agridulce=false;
							Parametros.analizar=false;
							Parametros.analizado4=false;	
							terminado = true;
							contador=0;
						
						//Final del d�a 2
							
				        } else if (terminado==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
	
							Parametros.dia++;
							Parametros.frontera=false;
							game.setScreen(new StatsScreen(game));
						    musica2.stop();
						    
				        }
			        }
			        
		        	break;
		        
		        case 3:
	
			        if (button == Input.Buttons.LEFT && Parametros.comienzo==true) {
			        	
			        	//FS 3_1
			        	
			        	if(contador==1) {
	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("�?: Equipaje y \n"
			    							+ "documentaci�n...", 7, 154);
			    			mainStage.addActor(texto);
			    			
			    			fs3_1.show();
			    			
			    			contador++;
			    			
			            } else if (contador==2) {
	
			        		musica.play();
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Hoy es el �ltimo d�a...\n"
			    							+ "he de darlo todo...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(12, 41, 60, 83, "pasaporte.3_1.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			permiso = new Objeto(4, 7, 69, 33, "permiso.3_1.png", "papeles.wav");
			    			mainStage.addActor(permiso);
			    			sopa = new Objeto(81, 28, 26, 30, "objeto.sopa.png", "lata.wav");
			    			mainStage.addActor(sopa);
			    			sombrero = new Objeto(110, 31, 50, 28, "objeto.sombrero.png", "sombrero.wav");
			    			mainStage.addActor(sombrero);
			    			
			    			reloj.start();
			    			contador++;
			    			
			            } else if (contador==3) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			Parametros.controlesActivos=true;
			    			Parametros.analizar=true;
			    			contador=0;
			    			
			            } else if (Parametros.analizado1==true) {
			        		
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			            	FS3_1.texto.remove();
			            	
			    			texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs3_1.fadeOut();
			            	permiso.remove();
			            	pasaporte.remove();
			            	maletin.hide();
			            	sopa.remove();
			            	sombrero.remove();
			            	
			            	Parametros.controlesActivos=false;
			    			Parametros.analizar=false;
			    			Parametros.analizado1=false;
			    			contador=4;
			    			
			    		//FS.3_2
			    			
			            } else if (contador==4) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Usted, el que\n"
											+ "est� ah�...", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			        		
				        } else if (contador==5) {
			            	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
			    			texto = new Texto("�?: Pasaporte en regla\n"
			    							+ "y equipaje, vamos...", 7, 154);
			    			mainStage.addActor(texto);
	
			    			fs3_2.show();
			    			
			    			contador++;
			    			
			            } else if (contador==6) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			texto = new Texto("(Eso es... �Un preser-\n"
			    							+ "vativo?...)", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(12, 41, 60, 83, "pasaporte.3_2.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			permiso = new Objeto(4, 7, 69, 33, "permiso.3_2.png", "papeles.wav");
			    			mainStage.addActor(permiso);
			    			duxer = new Objeto(120, 28, 26, 26, "objeto.duxer.png", "duxer.wav");
			    			mainStage.addActor(duxer);
			    			cuchillo = new Objeto(85, 31, 30, 38, "objeto.cuchillo.png", "pistola.wav");
			    			mainStage.addActor(cuchillo);
			    			
			    			contador++;
			    			
				        } else if (contador==7) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado2==true) {
			
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS3_2.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
			        						+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs3_2.fadeOut();
				        	pasaporte.remove();
				        	permiso.remove();
				        	maletin.hide();
				        	duxer.remove();
				        	cuchillo.remove();
				        	
			    			Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado2=false;
							contador=8;
							
						//FS.3_3
							
			            } else if (contador==8) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Por aqu�, es por\n"
											+ "aqu�, por favor...", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==9) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
							texto = new Texto("�?: Todo lo que tenga\n"
											+ "sobre la mesa.", 7, 154);
							mainStage.addActor(texto);
							
							fs3_3.show();
							
							contador++;
							
			            } else if (contador==10) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			    			if (Parametros.dinero>=14) {
			    				texto = new Texto("(Seg�n mis c�lculos...\n"
		    							+ "Todav�a puedo conseguirlo)", 7, 154);
			    			}
			    			else {
			    				texto = new Texto("(Seg�n mis c�lculos...\n"
		    									+ "Estoy perdido...)", 7, 154);
			    			}
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(10, 30, 60, 83, "pasaporte.3_3.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			hucha = new Objeto(78, 29, 50, 41, "objeto.hucha.png", "hucha.wav");
			    			mainStage.addActor(hucha);
			    			cuchillo = new Objeto(122, 32, 30, 38, "objeto.cuchillo.png", "pistola.wav");
			    			mainStage.addActor(cuchillo);
			    			
			    			contador++;
			    			
				        } else if (contador==11) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado3==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS3_3.texto.remove();
				        	
							texto = new Texto("LLAMAR AL SIGUIENTE\n"
											+ "(Pulsa CLICK)", 10, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs3_3.fadeOut();
				        	pasaporte.remove();
				        	maletin.hide();
				        	hucha.remove();
				        	cuchillo.remove();
				        	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado3=false;
							contador=12;
						
						//FS.3_4
							
			            } else if (contador==12) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
			    			
			        		texto = new Texto("�?: Siguien...\n"
			        						+ " ", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			           
			        	} else if (contador==13) {
			        	
			        		AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
						    musica.stop();
						    
			        		texto = new Texto("Rudolf: ...\n"
			        						+ " ", 7, 154);
							mainStage.addActor(texto);
	
							fs3_4.show();
							
							contador++;
							
			            } else if (contador==14) {
			            	
			            	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
			    			texto.remove();
	
			        		texto = new Texto("�?: ...\n"
			        						+ " ", 7, 154);
			    			mainStage.addActor(texto);
			    			
			        		AudioManager.playSound("01-FS/Audio/sounds/maletin.wav");
			    			maletin.show();
			    			pasaporte = new Objeto(12, 41, 60, 83, "pasaporte.3_4.png", "papeles.wav");
			    			mainStage.addActor(pasaporte);
			    			permiso = new Objeto(4, 7, 69, 33, "permiso.3_4.png", "papeles.wav");
			    			mainStage.addActor(permiso);
			    			llave = new Objeto(84, 34, 25, 25, "objeto.llave.png", "llaves.wav");
			    			mainStage.addActor(llave);
			    			botella = new Objeto(113, 28, 36, 43, "objeto.botella.png", "botella.wav");
			    			mainStage.addActor(botella);
			    			
			    			contador++;
			    			
				        } else if (contador==15) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							texto.remove();
							
							Parametros.controlesActivos=true;
							Parametros.analizar=true;
							contador=0;
							
				        } else if (Parametros.analizado4==true) {
	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	FS3_4.texto.remove();
				        	
						    musica.stop();
				        	
				        	texto = new Texto("...Volker Schwartz?\n"
		        							+ " ", 7, 154);
				        	mainStage.addActor(texto);
	
							Parametros.controlesActivos=false;
							Parametros.analizar=false;
							Parametros.analizado4=false;
				        	reloj.stop();
				        	contador=16;
							
				        } else if (contador==16) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	
				        	texto = new Texto("�?:...\n"
				        					+ " ", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
							
				        } else if (contador==17) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
	
				        	texto = new Texto("Rudolf: No hay tiempo,\n"
											+ "Te sacar� de esta.", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (contador==18) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
				        	texto = new Texto("Toma esta llave, y\n"
				        					+ "dir�gete al sur de", 7, 154);
				        	mainStage.addActor(texto);
				        	
				        	contador++;
				        	
				        } else if (contador==19) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("Londres... All� encon-\n"
			        						+ "trar�s lo que", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			        		
				        } else if (contador==20) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("necesitas para acabar\n"
			        						+ "con todo esto...)", 7, 154);
			        		mainStage.addActor(texto);
			        		
			        		contador++;
			        		
				        } else if (contador==21) {
				        
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("...Hail Hitler...\n"
			        						+ " ", 7, 154);
			        		mainStage.addActor(texto);
			        		
			            	fs3_4.fadeOut();
				        	pasaporte.remove();
				        	permiso.remove();
				        	maletin.hide();
				        	botella.remove();
			        		
			        		contador++;
			        	
				        } else if (contador==22) {
					        
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
				        	texto.remove();
				        	
			        		texto = new Texto("Volker: ...\n"
			        						+ "Voy por t�, Evans...", 7, 154);
			        		
			        		mainStage.addActor(texto);
	
							Parametros.mision_dein_kampf=false;
							Parametros.analizar=false;
							Parametros.analizado4=false;	
							terminado = true;
							contador=0;
						
						//Final del d�a 3
							
				        } else if (terminado==true) {
				        	
				        	AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
							Parametros.dia++;
							Parametros.frontera=false;
							game.setScreen(new StatsScreen(game));
						    musica2.stop();
						    
				        }
			        }
			        
		        	break;
		        	
		        default:
		        	System.out.println("Error. Ning�n d�a seleccionado.");
		        	break;
	        }

		} else {
			texto.completo=true;
	        AudioManager.playSound("01-FS/Audio/sounds/menuBoton.wav");
		}
	        return false;
	    }


	
	/**
	 * El m�todo render se ejecuta en cada frame, es el
	 * encargado de renderizar el juego, y en este caso,
	 * tambi�n de comprobar la posici�n del rat�n en
	 * pantalla, adem�s de comprobar si el reloj ha
	 * llegado a cero.
	 * @param delta Proporcionamos el deltaTime al m�todo
	 * para que pueda saber cu�ntos segundos han pasado
	 * desde que se ejecut� el �ltimo frame.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
	    mainStage.act();
	    mainStage.draw();
	    
	    mouseX = Gdx.input.getX();
	    mouseY = Gdx.input.getX();
	    
	    renderer.render();
	    mainStage.draw();
	    
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
        	if(periodicoActivo==false) {
            	AudioManager.playSound("01-FS/Audio/sounds/papeles.wav");
        		mainStage.addActor(periodicoActor);
        		periodicoActor.setSize(240,180);
        		if (Parametros.controlesActivos==true) {
        			controlesSiNo=true;
        		}
    			Parametros.controlesActivos=false;
    			Parametros.lockClick=true;
       			periodicoActivo=true;
        	}
        	else if (periodicoActivo==true) {
            	AudioManager.playSound("01-FS/Audio/sounds/papeles.wav");
        		periodicoActor.remove();
       			if(controlesSiNo=true) {
       				Parametros.controlesActivos=true;
       				controlesSiNo=false;
       			}
    			Parametros.lockClick=false;
       			periodicoActivo=false;
        	}
    	}
        
        /* ---	CONTROLES ---
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
        	if(activo==false) {
        		mainStage.addActor(periodicoActor);
       			activo=true;
        	}
        	else if (activo==true) {
        		periodicoActor.remove();
       			activo=false;
        	}
    	}
    	
    	*/

	    if(reloj.tiempoRestante<=10) {
			tiktak.play();
	    }
	    
	    if(reloj.tiempoRestante==0) {
			Parametros.dia++;	
			contador=0;
			game.setScreen(new StatsScreen(game));
			tiktak.stop();
		    musica.stop();
		    musica2.stop();
	    }
	    
	}
}
