package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Parametros;

public class Tareas extends Actor {

    private BitmapFont font;
    public static float tiempoRestante = 300;
    private String titulo;
    private String lista = "";
    private Texture tareas;
    
    private String[] tareasStrings = {"He de comer algo.",
                                      "He de pagar mis deudas.",
                                      "Un extra�o mu�eco.",
                                      "El tesoro perdido.",
                                      "El viejo general.",
                                      "Un encuentro agridulce.",
                                      "Dein Kampf.",
                                      "Lettera d'amore."};
    
    private static boolean[] tareasBoolean = {Parametros.haComidoHoy,
                                              Parametros.haPagadoDeuda,
                                              Parametros.mision_un_extrano_muneco,
                                              Parametros.mision_el_tesoro_perdido,
                                              Parametros.mision_el_viejo_general,
                                              Parametros.mision_un_encuentro_agridulce,
                                              Parametros.mision_dein_kampf,
                                              Parametros.mision_una_lettera_d_amore};

    public Tareas() {
        // Cargar la fuente desde el archivo Peepo.ttf
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Peepo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 17; // Tama�o de la fuente
        this.font = generator.generateFont(parameter);
        generator.dispose();
        
        // Configurar el resto de los atributos
        this.titulo = "- LISTA DE TAREAS -";
        setBounds(10, 300, 232, 290);
        this.tareas = new Texture("Menu/listaTareas.png");
        
        // Actualizar la lista con las tareas que est�n en true
        actualizarLista();
    }
    
    private void actualizarLista() {
        lista = "";
        boolean hayMisiones = false;
        for (int i = 0; i < tareasBoolean.length; i++) {
            if (tareasBoolean[i] == false) {
                lista += tareasStrings[i] + "\n";
                hayMisiones = true;
            }
        }
        if (!hayMisiones) {
            lista = "No tienes m�s misiones...";
        }
    }

    public void act(float delta) {
        super.act(delta);
        // Actualizar la lista si hay cambios en las tareas
        for (int i = 0; i < tareasBoolean.length; i++) {
            boolean tareaActual = tareasBoolean[i];
            tareasBoolean[i] = obtenerValorBooleano(i);
            if (tareasBoolean[i] != tareaActual) {
                actualizarLista();
                break;
            }
        }
    }
    
    private boolean obtenerValorBooleano(int indice) {
        boolean valor = true ;
        switch (indice) {
            case 0: valor = Parametros.haComidoHoy; break;
            case 1: valor = Parametros.haPagadoDeuda; break;
            case 2: valor = Parametros.mision_un_extrano_muneco; break;
            case 3: valor = Parametros.mision_el_tesoro_perdido; break;
            case 4: valor = Parametros.mision_el_viejo_general; break;
            case 5: valor = Parametros.mision_un_encuentro_agridulce; break;
            case 6: valor = Parametros.mision_dein_kampf; break;
            case 7: valor = Parametros.mision_una_lettera_d_amore; break;
            default: break;
        }
        return valor;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(tareas, getX(), getY(), getWidth(), getHeight());
        font.draw(batch, titulo, 43, 569);
        font.draw(batch, lista, 22, 527);
    }
} 