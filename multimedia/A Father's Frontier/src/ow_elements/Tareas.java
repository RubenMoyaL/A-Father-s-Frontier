package ow_elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tareas extends Actor {

    private BitmapFont font;
    public static float tiempoRestante = 300;
    private String tiempoTexto;
    private String lista;
    private Texture tareas;

    public Tareas() {
        this.font = new BitmapFont();
        this.font.getData().setScale(1);
        this.tiempoTexto = "- LISTA DE TAREAS -";
        this.lista = "- A comer al supermercado."
        		 + "\n- He de pagar mis deudas."
        		 + "\n- Un extra�o mu�eco.";
        
        setBounds(10, 290, 200, 300);
        
        this.tareas = new Texture("Menu/menuBoton.png");
    }

    public void act(float delta) {
        super.act(delta);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(tareas, getX(), getY(), getWidth(), getHeight());
        font.draw(batch, tiempoTexto, 38, 577);
        font.draw(batch, lista, 22, 547);
    }
}