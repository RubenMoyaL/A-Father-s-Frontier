package screens;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import ow_elements.NpcStatic;
import ow_elements.NpcMovil;
import ow_elements.Solid;
import ow_elements.Element;
import ow_elements.Niebla;
import ow_elements.RainDrop;
import ow_elements.Protagonista;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class OverWorldScreen extends BScreen{
	
Stage mainStage;

Solid frontera;
OrthographicCamera camara;
private TiledMap map;
private OrthogonalTiledMapRenderer renderer;
public Array<Solid> solidos;
public Array<Element> enemigos;
public float mouseX;
public float mouseY;
private Vector3 m3d;
private Niebla niebla;
private Music musica;
private Music ambiente;
private static boolean musicaUnaVez = true;

private Texture planoTexture;
private Actor planoActor;
private boolean planoActivo=false;

private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles,mapWidthInPixels, mapHeightInPixels;
private ActorComparator comparator;

public Protagonista prota;
private Solid solido;
private Solid casa;
private Solid superUk;
private Solid owTp;
private Solid superUk2;
private Solid ow1;
private Solid ow2;
@SuppressWarnings("unused")
private Solid start;

//TP
private static boolean CasaACalle = false;
private static boolean CalleASuper1 = false;
private static boolean CalleASuper2 = false;

	public OverWorldScreen(Demo game) {
		
		super(game);

		comparator=new ActorComparator();
		mainStage=new Stage();
		ambiente = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/ambiente.wav"));
		musica = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/ciudad_dia1.wav"));
		planoTexture = new Texture(Gdx.files.internal("Menu/mapaEsquema.png"));
		planoActor = new Image(planoTexture);
		
		if(musicaUnaVez==true) {
			musica.play();
	        musica.setVolume(0.2f);
			musica.setLooping(true);
			musicaUnaVez=false;
		}
		
		switch (Parametros.zona) {
			case 1:
				map=ResourceManager.getMap("Mapas/CasaProtagonista.tmx");
				break;
			case 2: 
				map=ResourceManager.getMap("Mapas/OverWorld.tmx");
				ambiente.play();
				ambiente.setVolume(0.8f);
				ambiente.setLooping(true);
				break;
			case 3:
				map=ResourceManager.getMap("Mapas/SuperUKMarket.tmx");
				break;
		}
		

		m3d=new Vector3();
		renderer=new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
	    
		MapProperties props;
		props=map.getProperties();
		tileWidth=props.get("tilewidth",Integer.class);
		tileHeight=props.get("tileheight",Integer.class);
		mapWidthInTiles=props.get("width", Integer.class);
		mapHeightInTiles=props.get("height", Integer.class);
		mapWidthInPixels=tileWidth*mapWidthInTiles;
		mapHeightInPixels=tileHeight*mapHeightInTiles;
		
		if(Parametros.zona==2) {
			enemigos=new Array<Element>();
			for(MapObject ene:getEnemyList()) {
				props=ene.getProperties();
				
				//Dibujar NPC
				switch(props.get("npc").toString()) {
				case "staticNpc":
					//Declaraciones de NPC
					NpcStatic testStaticNpc=new NpcStatic((float)props.get("x"), (float)props.get("y"),mainStage, this,
							"02-OW/Personajes/personaje.extra4_ow.png", "frente");
					enemigos.add(testStaticNpc);
					break;
					
				case "movilNpc":
					//Declaraciones de NPC
					NpcMovil testMovilNpc=new NpcMovil((float)props.get("x"), (float)props.get("y"),mainStage, this,
							"02-OW/Personajes/personaje.extra1_ow.png");
					enemigos.add(testMovilNpc);
					break;
				case "misionNpc":
					break;
				}
			}
		}
		
		ArrayList<MapObject> elementos;		

		if(Parametros.zona==2) {
			niebla = new Niebla(mapWidthInPixels, mapHeightInPixels);
		    mainStage.addActor(niebla);
		}
		    
		elementos=getRectangleList("Solid");
		solidos = new Array<Solid>();
		
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"), (float)props.get("y"), mainStage,
					(float)props.get("width"), (float)props.get("height"));
			solidos.add(solido);
		}
		for(Polygon poli:getPolygonList("poligono")) {
			solidos.add(new Solid(poli.getX(), poli.getY(), mainStage, poli));
		}
		
		float inicioX = 0;
		float inicioY = 0;
		
		if(Parametros.zona==1) {
			if(CasaACalle==false) {
				elementos=getRectangleList("Start");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	
			}
			else {
				elementos=getRectangleList("StartCalle");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	

				CasaACalle=false;
			}
		}
		
		else if(Parametros.zona==2) {
			if (CalleASuper1==true){
				elementos=getRectangleList("StartSuper1");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	

				CalleASuper1=false;
			}
			else if (CalleASuper2==true){
				elementos=getRectangleList("StartSuper2");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	

				CalleASuper2=false;
			}
			else {
				elementos=getRectangleList("Start");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	
				
				CasaACalle=false;
			}
		}
		
		else if (Parametros.zona==3) {
			if (CalleASuper1==true){
				elementos=getRectangleList("Start1");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	

				CalleASuper1=false;
			}
			else if (CalleASuper2==true){
				elementos=getRectangleList("Start2");
				props=elementos.get(0).getProperties();
				start=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
				(float)props.get("width"),(float) props.get("height"));
				inicioX=(float)props.get("x");
				inicioY=(float)props.get("y");	

				CalleASuper2=false;
			}
		}
		
		if(Parametros.zona==1) {
			elementos=getRectangleList("Ow");
			props=elementos.get(0).getProperties();
			owTp=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
			(float)props.get("width"),(float) props.get("height"));
		}
		else if(Parametros.zona==2) {
			elementos=getRectangleList("frontera");
			props=elementos.get(0).getProperties();
			frontera=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
					(float)props.get("width"),(float) props.get("height"));
			
			elementos=getRectangleList("casa");
			props=elementos.get(0).getProperties();
			casa=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
					(float)props.get("width"),(float) props.get("height"));
			
			elementos=getRectangleList("superUk");
			props=elementos.get(0).getProperties();
			superUk=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
					(float)props.get("width"),(float) props.get("height"));
			
			elementos=getRectangleList("superUk2");
			props=elementos.get(0).getProperties();
			superUk2=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
					(float)props.get("width"),(float) props.get("height"));
		}
		else if(Parametros.zona==3) {
			elementos=getRectangleList("ow1");
			props=elementos.get(0).getProperties();
			ow1=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
			(float)props.get("width"),(float) props.get("height"));
			
			elementos=getRectangleList("ow2");
			props=elementos.get(0).getProperties();
			ow2=new Solid((float)props.get("x"), (float)props.get("y"), mainStage, 
			(float)props.get("width"),(float) props.get("height"));
		}
		
		camara=(OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom, Parametros.getAltoPantalla()*Parametros.zoom);
		renderer.setView(camara);
		
			prota=new Protagonista(inicioX-5,inicioY,mainStage, this);
			uiStage=new Stage();
	}

	
	public void colide() {
		for(Solid s:solidos) {
			if(s.overlaps(prota)) {
				prota.preventOverlap(s);
			}
		}
		
		if(Parametros.zona==1) {
			if(this.owTp.overlaps(this.prota)) {
				CasaACalle=true;
				teletransporte(2);
			}
		}
		
		else if(Parametros.zona==2) {
			if(this.frontera.overlaps(this.prota)) {
				teletransporteFrontera();
			}
			if(this.casa.overlaps(this.prota)) {
				CasaACalle=true;
				teletransporte(1);
			}
		
			if(this.superUk.overlaps(this.prota)) {
				CalleASuper1=true;
				teletransporte(3);
			}
			
			if(this.superUk2.overlaps(this.prota)) {
				CalleASuper2=true;
				teletransporte(3);
			}
		}
		
		else if(Parametros.zona==3) {
			if(this.ow1.overlaps(this.prota)) {
				CalleASuper1=true;
				teletransporte(2);
			}
			
			if(this.ow2.overlaps(this.prota)) {
				CalleASuper2=true;
				teletransporte(2);
			}
		}
	}
	
	public void centrarCamara() {
		
		this.camara.position.x=MathUtils.clamp(prota.getX()+15,this.camara.viewportWidth/2,this.mapWidthInPixels-this.camara.viewportWidth/2);
		this.camara.position.y=MathUtils.clamp(prota.getY()+30,this.camara.viewportHeight/2,this.mapHeightInPixels-this.camara.viewportHeight/2);
		camara.update();
		
	}
	
	public void teletransporteFrontera() {
		musica.stop(); 
		ambiente.stop();
		prota.pasos.stop();
		Parametros.frontera=true;
		game.setScreen(new StartScreen(game));
	}
	
	public void teletransporte(int zona) {
		ambiente.stop();
		prota.pasos.stop();
		Parametros.zona=zona;
		game.setScreen(new OverWorldScreen(game));
	}
	
	public ArrayList<MapObject> getRectangleList(String propertyName){
		ArrayList<MapObject> list =new ArrayList<MapObject>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				if(!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props= obj.getProperties();
				if(props.containsKey("name") &&  props.get("name").equals(propertyName))
				{
					list.add(obj);
				}	
			}
		}
		
		return list;
	}
	
	private void createRainDrops() {
	    for (int i = 0; i < 10; i++) {
	        float x = MathUtils.random(0, mapWidthInPixels + 1500);
	        float y = MathUtils.random(mapHeightInPixels, mapHeightInPixels + 200);
	        float speed = MathUtils.random(200, 400);
	        RainDrop drop = new RainDrop(x, y, speed);
	        mainStage.addActor(drop);
	    }
	}
	
	public ArrayList<Polygon> getPolygonList(String propertyName){
		
		Polygon poly;
		ArrayList<Polygon> list = new ArrayList<Polygon>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				
				
				if(!(obj instanceof PolygonMapObject))
					continue;
				MapProperties props= obj.getProperties();
				if(props.containsKey("name") &&  props.get("name").equals(propertyName))
				{			
					poly=((PolygonMapObject)obj).getPolygon();
					list.add(poly);
				}
			}
		}
		
		return list;
	
	}
	
	public ArrayList<MapObject> getEnemyList(){
		ArrayList<MapObject> list =new ArrayList<MapObject>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				if(!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props= obj.getProperties();
				
				
				TiledMapTileMapObject tmtmo=(TiledMapTileMapObject) obj;
				TiledMapTile t=tmtmo.getTile();
				MapProperties defaultProps=t.getProperties();
				if(defaultProps.containsKey("npc")) {
					list.add(obj);
				}
				
				Iterator<String> propertyKeys=defaultProps.getKeys();
				while(propertyKeys.hasNext()) {
					String key =propertyKeys.next();
					
					if(props.containsKey(key))
						continue;
					else {
						Object value=defaultProps.get(key);
						props.put(key, value);
					}
						
				}
				
			}
			
		}
		
		return list;
	}
	
	public class ActorComparator implements Comparator<Actor>{

		@Override
		public int compare(Actor a1, Actor a2) {
			if(a1.getY()==a2.getY()) {
				return 0;
			}
			if(a1.getY()>a2.getY()) {
				return -1;
			}
			return 1;
			
		}
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
	   mainStage.act();
	   uiStage.act();
	   colide();
	   
	   centrarCamara();
	   
	   m3d.x=Gdx.input.getX();
	   m3d.y=Gdx.input.getY();
	   m3d.z=0;
	   camara.unproject(m3d);
	   mouseX=m3d.x;
	   mouseY=m3d.y;
	   
	   if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
		   planoActor.setSize(Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	       	if(planoActivo==false) {
	           	AudioManager.playSound("01-FS/Audio/sounds/papeles.wav");
	       		uiStage.addActor(planoActor);
	   			Parametros.controlesActivos=false;
	      			planoActivo=true;
	       	}
	       	else if (planoActivo==true) {
	           	AudioManager.playSound("01-FS/Audio/sounds/papeles.wav");
	       		planoActor.remove();
	      				Parametros.controlesActivos=true;
	      				planoActivo=false;
	      	}
	    }
	   
	    renderer.setView(camara);
	    renderer.render(new int[]{0, 1, 2, 3});
	    mainStage.getActors().sort(comparator);
	        mainStage.draw();
		    renderer.render(new int[]{4});
		    
			if(Parametros.zona==2) {
			    createRainDrops();
			}
			
	        uiStage.draw();
	}
}