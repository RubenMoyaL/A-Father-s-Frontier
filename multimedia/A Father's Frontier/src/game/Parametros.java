package game;

public class Parametros {

 //Screen
 private static int anchoPantalla=800;
 private static int altoPantalla=600;
 public static float zoom=0.65f;
 public static int zona = 1;

 //Debug
 public static boolean debug=false;
 
 //Audio;
 public static float musicVolume = 1;
 public static float soundVolume = 1;
 
 //Estadísticas
 public static int dia = 1;
 public static int segundosFS = 100;
 public static int segundosOW = 180;
 public static int segundosRestantesOW = segundosOW;
 public static int minutosOW = 5;
 public static int dineroAnterior = 0;
 public static int dinero = 1;
 
 //Variables
 public static boolean frontera = false;
 public static boolean controlesActivos = true;
 public static boolean lockClick = false;
 public static boolean comienzo = false;
 public static boolean analizar = false;
 public static boolean analizado1 = false;
 public static boolean analizado2 = false;
 public static boolean analizado3 = false;
 public static boolean analizado4 = false;
 public static boolean correcto = false;
 public static boolean musicaUnaVez = true;
// public static boolean interactuandoNpc = false;
 
 //Misiones
 public static boolean haComidoHoy = false;
 public static boolean haPagadoDeuda = false;
 
 public static boolean mision_un_extrano_muneco = true;
 public static boolean mision_un_extrano_muneco_item = true;
 public static boolean mision_un_extrano_muneco_completada = true;
 public static boolean mision_un_extrano_muneco_finalizada = false;
 
 public static boolean mision_el_tesoro_perdido = true;
 public static boolean mision_el_tesoro_perdido_item = true;
 public static boolean mision_el_tesoro_perdido_completada = true;
 public static boolean mision_el_tesoro_perdido_finalizada = false;

 public static boolean mision_el_viejo_general = true;
 public static boolean mision_el_viejo_general_item = true;
 public static boolean mision_el_viejo_general_completada = true;
 public static boolean mision_el_viejo_general_finalizada = false;

 public static boolean mision_una_lettera_d_amore = true;
 
 public static boolean mision_buen_chico = true;
 public static boolean mision_buen_chico_item = true;
 public static boolean mision_buen_chico_completada = true;
 public static boolean mision_buen_chico_finalizada = false;

 public static boolean mision_malas_vistas = true;
 public static boolean mision_malas_vistas_item = true;
 public static boolean mision_malas_vistas_completada = true;
 public static boolean mision_malas_vistas_finalizada = false;

 public static boolean mision_busca_y_captura = true;
 public static boolean mision_busca_y_captura_item = true;
 public static boolean mision_busca_y_captura_completada = true;
 public static boolean mision_busca_y_captura_finalizada = false;
 
 public static boolean mision_3 = true;
 public static boolean mision_3_completada = true;
 
 public static boolean mision_4 = true;
 public static boolean mision_4_completada = true;
 
 public static boolean mision_5 = true;
 public static boolean mision_5_completada = true;
 
 public static boolean mision_6 = true;
 public static boolean mision_6_completada = true;
 
 public static boolean mision_7 = true;
 public static boolean mision_7_completada = true;
 
 public static boolean mision_8 = true;
 public static boolean mision_8_completada = true;
 
 public static boolean mision_9 = true;
 public static boolean mision_9_completada = true;
 
 public static boolean mision_10 = true;
 public static boolean mision_10_completada = true;
 
 public static boolean mision_11 = true;
 public static boolean mision_11_completada = true;

 
 public static boolean mision_un_encuentro_agridulce = true;
 public static boolean mision_un_encuentro_agridulce_completada = true;
 
 public static boolean mision_dein_kampf = true;
 public static boolean mision_dein_kampf_completada = true;
 
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
