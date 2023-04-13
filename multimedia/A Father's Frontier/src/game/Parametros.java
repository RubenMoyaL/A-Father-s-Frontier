package game;

public class Parametros {

 //Screen
 private static int anchoPantalla=800;
 private static int altoPantalla=600;
 public static float zoom=0.30f;

 //Debug
 public static boolean debug=true;
 
 //Audio;
 public static float musicVolume = 1;
 public static float soundVolume = 1;
 
 //Estadísticas
 public static int dia = 1;
 public static int segundos = 1000;
 public static int dineroAnterior = 0;
 public static int dinero = 1;
 
 //Variables
 public static boolean controlesActivos = false;
 public static boolean comienzo = false;
 public static boolean analizar = false;
 public static boolean analizado1 = false;
 public static boolean analizado2 = false;
 public static boolean analizado3 = false;
 public static boolean analizado4 = false;
 public static boolean correcto = false;
 
 //Misiones
 public static boolean mision_carta = true;
 
 
 
 
 
 
public static int getAnchoPantalla() {
	return anchoPantalla;
}

public static void setAnchoPantalla(int anchoPantalla) {
	Parametros.anchoPantalla = anchoPantalla;
}

public static int getAltoPantalla() {
	return altoPantalla;
}

public static void setAltoPantalla(int altoPantalla) {
	Parametros.altoPantalla = altoPantalla;
}

public static float getMusicVolume() {
	return musicVolume;
}

public static void setMusicVolume(float musicVolume) {
	Parametros.musicVolume = musicVolume;
}




 
}
