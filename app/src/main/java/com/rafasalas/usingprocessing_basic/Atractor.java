package com.rafasalas.usingprocessing_basic;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by salas on 29/05/2017.
 */

public class Atractor extends PApplet{
    PVector posicion, origen_icono;
    float sentido;
    int tipo_atractor;
    int interaccion;

   public Atractor (){
        posicion=new PVector(random(width), random(height));
        interaccion=0;
        sentido=-1;
        tipo_atractor=1;
        origen_icono=new PVector (0,0);
    }
    PVector fuerza (PVector posicionobjeto){

        PVector f=posicionobjeto.get();
        f.sub(posicion);

        float modulo=f.mag();
        if (modulo <0) {f.mult(-1);}
        f.normalize();
        //noria

        //if (modulo<100){f.rotate(HALF_PI);}
        //noria
        switch(tipo_atractor) {
            case 1:
                f.mult(modulo/50);
                break;
            case 2:
                f.mult(150/modulo);
                break;
            case 3:
                f.mult(4);
                break;
            case 4:
                f.mult(150/modulo*modulo);
                break;
        }
        f.mult(sentido);
        return f;
    }
    void visible(){stroke (255,255,255);
        strokeWeight(1);
        //if (sentido>0) {fill(0,0,0);} else {fill(255,255,255);}
        noFill();
        ellipse (posicion.x, posicion.y, 10, 10);

    }

}
