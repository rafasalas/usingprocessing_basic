package com.rafasalas.usingprocessing_basic;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by salas on 29/05/2017.
 */

public class Sketch_1 extends PApplet {
    int numeroparticulas, tipoparticulas, colorfondo;

    float Factor=1;


    int opacidad;
    ArrayList<Storsimple> estorninos;
    public float flujo =1;
    Atractor central, lateral1, lateral2, lateral3,lateral4;

    public void settings() {

        size(displayWidth, displayHeight);
        //fullScreen();
    }

    public void setup (){
        //smooth(8);
        frameRate(60);

        numeroparticulas=90;

        opacidad=255;

        estorninos=new ArrayList<Storsimple>() ;
        for(int i=0; i<2; i++){if (i==0){tipoparticulas=2;}else{tipoparticulas=2;}
            estorninos.add(new Storsimple(numeroparticulas,0,height,0,width));
            //estorninos.get(i).colorea(125,255,0,1,0,1, 50,180);

        }

        central=new Atractor(1);
        lateral1=new Atractor(1);
        lateral2=new Atractor(1);
        lateral3=new Atractor(1);
        lateral4=new Atractor(1);
        central.posicion=new PVector(width/2, height/2);
        lateral1.posicion=new PVector(width/2, height/8);
        lateral2.posicion=new PVector(width/8, height/2);
        lateral3.posicion=new PVector(7*(width/8), height/2);
        lateral4.posicion=new PVector((width/2), 7*(height/8));


    }


    public void draw(){
        background(0);

        noFill();
        central.sentido=-1-flujo;
        lateral1.sentido=(float)-0.5*flujo;
        lateral2.sentido=(float)-0.5*flujo;
        lateral3.sentido=(float)-0.5*flujo;
        lateral4.sentido=(float)-0.5*flujo;
        for (int i = 0; i < 2; i++) {
            Storsimple s = estorninos.get(i);

            s.aceleradorparticulas(central);
            if (i==0){
                s.aceleradorparticulas(lateral1);
                s.aceleradorparticulas(lateral2);}
            else{
                s.aceleradorparticulas(lateral3);
                s.aceleradorparticulas(lateral4);
            }
            s.dibujaparticulas();

        }

    }

   class Particula {

        int r, g, b, a;
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
            limsup = 0;
            liminf = height;
            limizq = 0;
            limder = width;
            posicion = new PVector(random(limder - limizq), random(liminf - limsup));
            velocidad = new PVector(0, 0);
            aceleracion = new PVector(0, 0);
            gravedad = new PVector(0, (float) 0.02);
            limite = 10;
            masa = random(3, 18);
            resistencia = false;
            r = (int) (random(0, 255));
            g = (int) (random(0, 255));
            b = (int) (random(0, 255));
            a = (int) (random(0, 255));
            lifespan = 255;
            eterna = false;
            decay = 2;
            //masa=30;
        }

        void acelerar(PVector acelerador) {
            PVector a = PVector.div(acelerador, masa);
            aceleracion.add(a);
        }

        void caer() {
            velocidad.add(gravedad);
        }

        void resistencia(float coeficiente) {
            resistencia = true;
            coefroz = coeficiente;
        }

        boolean muerta() {
            if (lifespan < 0) {
                return true;
            } else {
                return false;
            }


        }

        public void actualizar() {
            if (eterna == false) {
                lifespan -= decay;
            }
            velocidad.add(aceleracion);
            if (resistencia) {
                PVector friccion = velocidad.get();

                friccion.normalize();
                friccion.mult(-1 * coefroz);
                velocidad.add(friccion);
            }
            velocidad.limit(limite);
            posicion.add(velocidad);


            aceleracion.mult(0);

            if (posicion.x > limder) {
                velocidad.x = velocidad.x * -1;
                posicion.x = limder;
            }
            if (posicion.x < limizq) {
                velocidad.x = velocidad.x * -1;
                posicion.x = limizq;
            }
            if (posicion.y > liminf) {
                velocidad.y = velocidad.y * -1;
                posicion.y = liminf;
            }
            if (posicion.y < limsup) {
                velocidad.y = velocidad.y * -1;
                posicion.y = limsup;
            }
        }

        public void mostrar() {
            if (eterna == false) {
                a = (int) (lifespan);
            }
            stroke(r, g, b, a);
            strokeWeight(masa);

            point(posicion.x, posicion.y);
            strokeWeight(1);

            /*float angular;
            if (eterna == false) {
                a = (int) (lifespan);
            }
            stroke (r,g,b,a);
            strokeWeight(1);
            fill(r,g,b,a);
            //angular=atan2(velocidad.y,velocidad.x);
            angular=velocidad.heading()+(PI);
            //angular=constrain (angular,-0.1,0.1);


            rectMode (CENTER);
            pushMatrix();
            translate(posicion.x, posicion.y);
            rotate(angular);
            rect (0, 0, masa,masa);
            popMatrix();*/
        }

        public void lanzar() {
            actualizar();
            mostrar();


        }

    }
    class Storsimple {


        float magbrowniano;
        float numeroparticulas, masaparticula;
        int claseparticula;
        PVector velocidadinicial;
        PVector origen;
        PVector browniano;
        int limsup, liminf, limizq, limder;

        boolean esbrowniano;
        //float magbrowniano;
        ArrayList<Particula> particulas;


        Storsimple(float numpart,  int sup, int inf, int izq, int der){
            limsup=sup;
            liminf=inf;
            limizq=izq;
            limder=der;
            numeroparticulas=numpart;

            particulas=new ArrayList<Particula>() ;


            origen=new PVector(random(width), random(height));
            //origen=new PVector((width/2)+30, (height/2)+30);
            esbrowniano=true;
            magbrowniano=(float).8;

            for(int i=0; i<numeroparticulas; i++){
                //velocidadinicial=new PVector (0,50+random(-10,10));
                velocidadinicial=new PVector (random (width),random(height));
                //velocidadinicial=new PVector (random (-15,15),random(-15,15));
                //velocidadinicial=new PVector (10+random(-3,3),10+random(-3,3));
                masaparticula=random (3,10);

                particulas.add(new Particula());

                particulas.get(i).eterna=true;
                particulas.get(i).liminf=inf;
                particulas.get(i).limsup=sup;
                particulas.get(i).limizq=izq;
                particulas.get(i).limder=der;
            }


        }

        //fin constructor Storsimple


        void aceleradorparticulas(Atractor a){
            for (int i = 0; i < particulas.size(); i++) {
                Particula p = particulas.get(i);
                p.acelerar(a.fuerza(p.posicion));
                if(esbrowniano==true){
                    browniano=new PVector (0, magbrowniano);
                    browniano.rotate(p.velocidad.heading());
                    p.acelerar(browniano);
                }
            }

        }






        void dibujaparticulas(){

            for (int i = 0; i < particulas.size(); i++) {
                Particula p = particulas.get(i);
                //noFill();
                //stroke (p.r,p.g,p.b,55);
                //if (p.posicion.x>(limder-limizq)/2){factor=5;}else{factor=-5;}

                //bezier(p.posicion.x, p.posicion.y, p.posicion.x+factor*10, p.posicion.y+180, (limder-limizq)/2+factor, (liminf-limsup)/2,(limder-limizq)/2,(liminf-limsup));
                p.caer();
                p.lanzar();



            }


        }




    }//fin class Storsimple

    class Atractor {
        PVector posicion, origen_icono;
        float sentido;
        int tipo_atractor;
        int interaccion;

        Atractor (int clase){
            posicion=new PVector(random(width), random(height));
            interaccion=0;
            sentido=-1;
            tipo_atractor=clase;
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


}












