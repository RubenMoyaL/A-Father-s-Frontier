package screens;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import ow_elements.Solid;
import ow_elements.Niebla;
import ow_elements.RainDrop;
import ow_elements.Protagonista;
import game.Demo;
import game.Parametros;
import managers.ResourceManager;

public class OverWorldScreen extends BScreen{
	
Stage mainStage;

Solid end;
OrthographicCamera camara;
private TiledMap map;
private OrthogonalTiledMapRenderer renderer;
public Array<Solid> solidos;
public float mouseX;
public float mouseY;
private Vector3 m3d;
private Niebla niebla;
private Music musica;
private Music ambiente;

private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles,mapWidthInPixels, mapHeightInPixels;
private ActorComparator comparator;
	   
public Protagonista prota;

	public OverWorldScreen(Demo game) {
		
		super(game);
		
		map=ResourceManager.getMap("Mapas/OverWorld.tmx");
		comparator=new ActorComparator();
		mainStage=new Stage();

		m3d=new Vector3();
		renderer=new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/ciudad_dia1.wav"));
		musica.play();
        musica.setVolume(0.2f);
		musica.setLooping(true);
		
		ambiente = Gdx.audio.newMusic(Gdx.files.internal("02-OW/Audio/music/ambiente.wav"));
		ambiente.play();
		ambiente.setVolume(0.8f);
		ambiente.setLooping(true);
		
		MapProperties props;
		props=map.getProperties();
		tileWidth=props.get("tilewidth",Integer.class);
		tileHeight=props.get("tileheight",Integer.class);
		mapWidthInTiles=props.get("width", Integer.class);
		mapHeightInTiles=props.get("height", Integer.class);
		mapWidthInPixels=tileWidth*mapWidthInTiles;
		mapHeightInPixels=tileHeight*mapHeightInTiles;
		
	    niebla = new Niebla(mapWidthInPixels, mapHeightInPixels);
	    mainStage.addActor(niebla);
		
		ArrayList<MapObject> elementos;		
		
		elementos=getRectangleList("Solid");
		solidos = new Array<Solid>();
		
		Solid solido;
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"), (float)props.get("y"), mainStage,
					(float)props.get("width"), (float)props.get("height"));
			solidos.add(solido);
		}
		for(Polygon poli:getPolygonList("poligono")) {
			solidos.add(new Solid(poli.getX(), poli.getY(), mainStage, poli));
		}
		
		float inicioX;
		float inicioY;
		elementos=getRectangleList("Start");
		inicioX=(float)props.get("x");
		inicioY=(float)props.get("y");
		
		
		camara=(OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom, Parametros.getAltoPantalla()*Parametros.zoom);
		renderer.setView(camara);
		
		prota=new Protagonista(inicioX-100,inicioY+96,mainStage, this);
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	     mainStage.act();

			createRainDrops();
			
	     uiStage.act();
	    colide();
	    
	   centrarCamara();
	   
	   m3d.x=Gdx.input.getX();
	   m3d.y=Gdx.input.getY();
	   m3d.z=0;
	   camara.unproject(m3d);
	   mouseX=m3d.x;
	   mouseY=m3d.y;
	   //actualizarInterfaz();
	    renderer.setView(camara);
	    renderer.render();
	    mainStage.getActors().sort(comparator);
	        mainStage.draw();
	}
	
	public void colide() {
		for(Solid s:solidos) {
			if(s.overlaps(prota)) {
				prota.preventOverlap(s);
			}
		}
	}
	
	public void centrarCamara() {
		
		this.camara.position.x=MathUtils.clamp(prota.getX()+15,this.camara.viewportWidth/2,this.mapWidthInPixels-this.camara.viewportWidth/2);
		this.camara.position.y=MathUtils.clamp(prota.getY()+30,this.camara.viewportHeight/2,this.mapHeightInPixels-this.camara.viewportHeight/2);
		camara.update();
		
	}
	public void avanzarNivel() {
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
		ArrayList<Polygon> list =new ArrayList<Polygon>();
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
}
