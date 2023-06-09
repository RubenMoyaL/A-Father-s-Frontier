package ow_elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import managers.ResourceManager;

/**
 * Carga la niebla de la Ciudad.
 * @author Rub�n Moya
 */
public class Niebla extends Actor {

  private Texture texture;
  private float x;
  private float y;
  private float elapsedTime; // variable para almacenar el tiempo transcurrido
  private String textura;
  
  public Niebla(float width, float height, String textura) {
	this.textura = textura;
    texture = ResourceManager.getTexture(this.textura); // Aseg�rate de tener una textura con el efecto de niebla
    setWidth(width);
    setHeight(height);
  }

  @Override
  public void act(float delta) {
    elapsedTime += delta;
    if (elapsedTime < 2f) {
      x += delta * 10f;
      y += delta * 5f;
    } else {
      x -= delta * 10f;
      y -= delta * 5f;
      if(elapsedTime > 4f) {
    	  elapsedTime=0;
      }
    }
  }

  /**
  * Mueve la niebla de un lado a otro.
  * @author Rub�n Moya
  */
  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.setColor(1, 1, 1, 0.3f);
    batch.draw(texture, getX()-19 + x, getY()-16 + y, getWidth()+50, getHeight()+50);
    batch.setColor(1, 1, 1, 1);
  }

  public void dispose() {
    texture.dispose();
  }

}