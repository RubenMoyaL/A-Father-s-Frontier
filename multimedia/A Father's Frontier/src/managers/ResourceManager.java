package managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public final class ResourceManager {
	private ResourceManager() {}
	public static AssetManager assets=new AssetManager();
	public static LabelStyle buttonStyle;
	public static TextButtonStyle textButtonStyle;
	public BitmapFont fuentePropia;
	public static Music musicaTitulo;
	
	public static void loadAllResources(){
		
		//Mapas
		assets.setLoader(TiledMap.class, new TmxMapLoader());
		assets.load("Mapas/PapersBackground.tmx", TiledMap.class);
		assets.load("Mapas/OverWorld.tmx", TiledMap.class);
		assets.load("Mapas/CasaProtagonista.tmx", TiledMap.class);
		assets.load("Mapas/SuperUKMarket.tmx", TiledMap.class);
		
        //Men�
		assets.load("Menu/PapersBackgraundTsx.png", Texture.class);
        assets.load("Menu/titleBackground.jpg", Texture.class);
        assets.load("Menu/titleTitle.png", Texture.class);
        assets.load("Menu/blackBackground.png", Texture.class);
        assets.load("Menu/menuBoton.png", Texture.class);
        assets.load("Menu/lluvia.png", Texture.class);
        assets.load("Menu/raindrop.png", Texture.class);
        assets.load("Menu/niebla.png", Texture.class);
        assets.load("Menu/periodico.0_0.png", Texture.class);
        assets.load("Menu/periodico.1_0.png", Texture.class);
        assets.load("Menu/periodico.2_0.png", Texture.class);
        assets.load("Menu/periodico.3_0.png", Texture.class);
        assets.load("Menu/mapaEsquema.png", Texture.class);
        assets.load("Menu/dialogoOW.png", Texture.class);
        
        //Pasaportes      
        assets.load("01-FS/Objetos/pasaporte.1_1.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.1_2.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.1_3.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.1_4.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.2_1.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.2_2.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.2_3.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.2_4.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.3_1.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.3_2.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.3_3.png", Texture.class);
        assets.load("01-FS/Objetos/pasaporte.3_4.png", Texture.class);
        
        //Reglas
        assets.load("01-FS/Objetos/reglas.1_0.png", Texture.class);
        assets.load("01-FS/Objetos/reglas.2_0.png", Texture.class);
        assets.load("01-FS/Objetos/reglas.2_1.png", Texture.class);
        assets.load("01-FS/Objetos/reglas.3_0.png", Texture.class);
        assets.load("01-FS/Objetos/reglas.3_1.png", Texture.class);
        assets.load("01-FS/Objetos/reglas.3_2.png", Texture.class);
        
        //Permisos
        assets.load("01-FS/Objetos/permiso.3_1.png", Texture.class);
        assets.load("01-FS/Objetos/permiso.3_2.png", Texture.class);
        assets.load("01-FS/Objetos/permiso.3_4.png", Texture.class);
        
        //Objetos est�ticos
        assets.load("01-FS/Objetos/botonRojo.png", Texture.class);
        assets.load("01-FS/Objetos/botonRojo_Pressed.png", Texture.class);
        assets.load("01-FS/Objetos/botonVerde.png", Texture.class);
        assets.load("01-FS/Objetos/botonVerde_Pressed.png", Texture.class);
        assets.load("01-FS/Objetos/mesa.png", Texture.class);
        assets.load("01-FS/Objetos/maletin.png", Texture.class);
        
        //Objetos
        assets.load("01-FS/Objetos/objeto.pistola.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.botella.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.carta.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.cuchillo.png", Texture.class);assets.load("01-FS/Objetos/objeto.cuchillo.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.duxer.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.hucha.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.llave.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.pintalabios.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.platano.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.sombrero.png", Texture.class);
        assets.load("01-FS/Objetos/objeto.sopa.png", Texture.class);
        
        //Personajes FS
        assets.load("01-FS/Personajes/FS.1_1.png", Texture.class);
        assets.load("01-FS/Personajes/FS.1_2.png", Texture.class);
        assets.load("01-FS/Personajes/FS.1_3.png", Texture.class);
        assets.load("01-FS/Personajes/FS.1_4.png", Texture.class);
        assets.load("01-FS/Personajes/FS.2_1.png", Texture.class);
        assets.load("01-FS/Personajes/FS.2_2.png", Texture.class);
        assets.load("01-FS/Personajes/FS.2_3.png", Texture.class);
        assets.load("01-FS/Personajes/FS.2_4.png", Texture.class);
        assets.load("01-FS/Personajes/FS.3_1.png", Texture.class);
        assets.load("01-FS/Personajes/FS.3_2.png", Texture.class);
        assets.load("01-FS/Personajes/FS.3_3.png", Texture.class);
        assets.load("01-FS/Personajes/FS.3_4.png", Texture.class);
        
        //Personajes OW
        assets.load("02-OW/Personajes/personaje.protagonista_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.ahorcado.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.chica_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.chica1_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.dependiente_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.detective_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.evans_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.extra_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.extra1_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.extra2_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.extra3_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.extra4_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.herido_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.perro_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.policia_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.protagonista.final_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.secuestrador_ow.png", Texture.class);
        assets.load("02-OW/Personajes/personaje.viejo_ow.png", Texture.class);
        
        //Sonidos
        assets.load("01-FS/Audio/sounds/boton.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/correcto.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/incorrecto.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/boton.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/menuBoton.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/papeles.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/botella.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/pistola.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/hucha.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/sombrero.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/lata.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/banana.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/duxer.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/llaves.wav", Sound.class);
        assets.load("01-FS/Audio/sounds/maletin.wav", Sound.class);
        assets.load("02-OW/Audio/sounds/pasos.wav", Sound.class);
        
        //M�sica
        assets.load("01-FS/Audio/music/maletin_por_favor.wav", Music.class);
        assets.load("01-FS/Audio/music/final.wav", Music.class);
        assets.load("01-FS/Audio/music/lluvia.wav", Music.class);
        assets.load("01-FS/Audio/music/title_theme.wav", Music.class);
        assets.load("02-OW/Audio/music/ciudad_dia1.wav", Music.class);
        assets.load("02-OW/Audio/music/ambiente.wav", Music.class);
	}
	
	public static boolean update(){
		return assets.update();
	}
	public static void botones() {
		
		//estilo para botones
        FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
		FreeTypeFontParameter ftfp= new FreeTypeFontParameter();
		
		ftfp.size=36;
		ftfp.color=Color.WHITE;
		ftfp.borderWidth=2;
		
		BitmapFont fuentePropia=ftfg.generateFont(ftfp);
		buttonStyle=new LabelStyle();
		buttonStyle.font=fuentePropia;
		textButtonStyle=new TextButtonStyle();
		Texture buttonText = ResourceManager.getTexture("Menu/menuBoton.png");
		NinePatch buttonPatch = new NinePatch(buttonText);
		textButtonStyle.up=new NinePatchDrawable(buttonPatch);
		textButtonStyle.font=fuentePropia;
		
		musicaTitulo = Gdx.audio.newMusic(Gdx.files.internal("01-FS/Audio/music/title_theme.wav"));
		musicaTitulo.play();
	    musicaTitulo.setVolume(0.5f);
		musicaTitulo.setLooping(true);
	}
	
	/*public static TextureAtlas getAtlas(String path){
		return assets.get(path, TextureAtlas.class);
		
	}*/
	
	public static Texture getTexture(String path) {
		return assets.get(path, Texture.class);
	}
	
	public static Music getMusic(String path){
		return assets.get(path, Music.class);
	}

	public static Sound getSound(String path)
	{
		return assets.get(path, Sound.class);
	}
	
	public static TiledMap getMap(String path){
		return assets.get(path, TiledMap.class);
	}

	public static void dispose(){
		assets.dispose();
	}
}
