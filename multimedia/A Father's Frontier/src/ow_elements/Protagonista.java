package ow_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.ResourceManager;
import screens.OverWorldScreen;

    public class Protagonista extends Element{
        private boolean unaVez = false;
        public Sound pasos;

        public Protagonista(float x, float y, Stage s, OverWorldScreen nivel) {
            super(x, y, s);
            pasos=ResourceManager.getSound("02-OW/Audio/sounds/pasos.wav");

            prepararAnimacion("02-OW/Personajes/personaje.protagonista_ow.png", true);

            setAnimation(idleFrente);
            this.setPolygon(8, 32, 23, 5, 5);
        }

        @Override
        public void act(float delta) {
            super.act(delta);    
        	if(Parametros.controlesActivos=true) {
	            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
	                Vector2 clickPos = getStage().screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
	                Vector2 direction = new Vector2(clickPos).sub(getX(), getY());
	                direction.nor();
	                this.velocity.set(direction.scl(130));
	                if (unaVez==true) {
	                    pasos.play();
	                    pasos.loop();    
	                    unaVez=false;
	                }
	            } else {
	                pasos.stop();
	                this.velocity.y=0;
	                this.velocity.x=0;
	                unaVez=true;
	            }
	            animaciones();
	            this.applyPhysics(delta);
        	}
        }
        
        private void animaciones() {
        		if (this.velocity.isZero()) {
                    Vector2 clickPos = getStage().screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
                    Vector2 direction = new Vector2(clickPos).sub(getX(), getY());
                    Vector2 up = new Vector2(0, 1);
                    float angleRad = direction.angleRad(up);

                    if (angleRad < -MathUtils.PI / 4f && angleRad > -3f * MathUtils.PI / 4f) {
                        this.setAnimation(idleIzquierda);
                    } else if (angleRad > MathUtils.PI / 4f && angleRad < 3f * MathUtils.PI / 4f) {
                        this.setAnimation(idleDerecha);
                    } else if (angleRad >= 3f * MathUtils.PI / 4f || angleRad <= -3f * MathUtils.PI / 4f) {
                        this.setAnimation(idleFrente);
                    } else {
                        this.setAnimation(idleEspaldas);
                    }
                } else {
                    float angle = MathUtils.atan2(this.velocity.y, this.velocity.x) * MathUtils.radiansToDegrees;
                    if (angle < 0) {
                        angle += 360;
                    }
                    if (angle >= 45 && angle < 135) {
                        this.setAnimation(espalda);
                    } else if (angle >= 135 && angle < 225) {
                        this.setAnimation(izquierda);
                    } else if (angle >= 225 && angle < 315) {
                        this.setAnimation(frente);
                    } else {
                        this.setAnimation(derecha);
                    }
                }
        	}
    	}