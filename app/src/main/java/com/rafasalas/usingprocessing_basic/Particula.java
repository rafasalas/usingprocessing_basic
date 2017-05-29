package com.rafasalas.usingprocessing_basic;

/**
 * Created by salas on 29/05/2017.
 */
import processing.core.PApplet;
import processing.core.PVector;

import static android.R.attr.width;
import static com.rafasalas.usingprocessing_basic.R.attr.height;

public class Particula extends PApplet {

        int r,g,b,a;
        PVector posicion, velocidad, aceleracion, gravedad;
        float limite;
        float masa;
        boolean resistencia;
        float coefroz;
        float lifespan;
        boolean eterna;
        int decay;
        int limsup, liminf, limizq, limder;
       public Particula() {
            limsup=0;
            liminf=height;
            limizq=0;
            limder=width;
            posicion=new PVector(random(limder-limizq), random(liminf-limsup));
            velocidad=new PVector (0, 0);
            aceleracion=new PVector (0, 0);
            gravedad=new PVector (0, (float)0.02);
            limite=15;
            masa=random(3, 18);
            resistencia=false;
            r=(int)(random(0,255));
            g=(int)(random(0,255));
            b=(int)(random(0,255));
            a=(int)(random(0,255));
            lifespan=255;
            eterna=false;
            decay=2;
            //masa=30;
        }

        void acelerar(PVector acelerador) {
            PVector a=PVector.div(acelerador, masa);
            aceleracion.add(a);
        }
        void caer() {
            velocidad.add(gravedad);
        }
        void resistencia(float coeficiente) {
            resistencia=true;
            coefroz=coeficiente;
        }

        boolean muerta(){
            if (lifespan<0){return true;}else{return false;}


        }

    public void actualizar() {
            if (eterna==false){lifespan-=decay;}
            velocidad.add(aceleracion);
            if (resistencia) {
                PVector friccion=velocidad.get();

                friccion.normalize();
                friccion.mult(-1*coefroz);
                velocidad.add(friccion);
            }
            velocidad.limit(limite);
            posicion.add(velocidad);



            aceleracion.mult(0);

            if (posicion.x > limder ) {
                velocidad.x = velocidad.x*-1;
                posicion.x=limder;
            }
            if ( posicion.x < limizq) {
                velocidad.x = velocidad.x*-1;
                posicion.x=limizq;
            }
            if (posicion.y > liminf ) {
                velocidad.y = velocidad.y*-1;
                posicion.y=liminf;
            }
            if (posicion.y < limsup) {
                velocidad.y = velocidad.y*-1;
                posicion.y=limsup;
            }
        }
        public void mostrar() {
            if (eterna==false){ a=(int)(lifespan);}
          stroke (r,g,b,a);
            strokeWeight(masa);

            point (posicion.x, posicion.y);
            strokeWeight(10);
            stroke(0,0,0,255);
            point(30, 30);

        }

    public  void lanzar(){
            actualizar();
            mostrar();



        }

    }


