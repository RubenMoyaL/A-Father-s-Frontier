package ow_elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.OverWorldScreen;

public class NpcMovil extends Element {
    private float velocidad;
    private int direccionMovimiento;
    private OverWorldScreen nivel;
    private int direccion;

    public NpcMovil(float x, float y, Stage s, OverWorldScreen nivel, String animacion, int direccion) {
        super(x, y, s);

        this.direccion = direccion;
        this.nivel = nivel;
        prepararAnimacion(animacion, true);
        this.setPolygon(8, 32, 23, 5, 5);

        if (direccion == 1) {
            direccionMovimiento = -1;
            velocidad = 70;
        } else if (direccion == 2) {
            direccionMovimiento = 1;
            velocidad = 70;
        }
    }

    public void act(float delta) {
        super.act(delta);
        if (direccion == 1) {
            moveBy(direccionMovimiento * velocidad * delta, 0);
        } else if (direccion == 2) {
            moveBy(0, direccionMovimiento * velocidad * delta);
        }
        animaciones();
        comprobarColisiones();
    }

    private void comprobarColisiones() {
        for (Solid solido : this.nivel.solidos) {
            if (this.overlaps(solido)) {
                if (direccion == 1) {
                    this.direccionMovimiento *= -1;
                } else if (direccion == 2) {
                    this.direccionMovimiento *= -1;
                }
            }
        }
        if (this.nivel.prota.overlaps(this)) {
            this.nivel.prota.preventOverlap(this);
        }
    }

    private void animaciones() {
        if (direccionMovimiento > 0) {
            if (direccion == 1) {
                this.setAnimation(this.derecha);
            } else if (direccion == 2) {
                this.setAnimation(this.espalda);
            }
        } else if (direccionMovimiento < 0) {
            if (direccion == 1) {
                this.setAnimation(this.izquierda);
            } else if (direccion == 2) {
                this.setAnimation(this.frente);
            }
        }
    }
}

