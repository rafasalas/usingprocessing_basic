package com.rafasalas.usingprocessing_basic;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by salas on 29/05/2017.
 */

public class Sketch_1 extends PApplet{


    int Y_AXIS = 1;
    int X_AXIS = 2;

    int numeroparticulas, tipoparticulas, colorfondo;

    float Factor;


    int opacidad;
    Storsimple estorninos;
    float flujo =1;
    Atractor central, lateral1, lateral2, lateral3,lateral4;




    public void settings() {
        size (800,750);
        smooth(8);
        frameRate(60);

        numeroparticulas=1000;

        Factor=1;











        opacidad=255;

        estorninos=new Storsimple(numeroparticulas,tipoparticulas,0,height,0,width);




        central=new Atractor();
        lateral1=new Atractor();
        lateral2=new Atractor();
        lateral3=new Atractor();
        lateral4=new Atractor();
        central.posicion=new PVector(width/2, height/2);
        lateral1.posicion=new PVector(width/2, height/8);
        lateral2.posicion=new PVector(width/8, height/2);
        lateral3.posicion=new PVector(7*(width/8), height/2);
        lateral4.posicion=new PVector((width/2), 7*(height/8));
    }


    public void setup (){




    }


    public void draw() {


        background(0);

        noFill();
        central.sentido = -1 - flujo;
        lateral1.sentido = (float) -0.5 * flujo;
        lateral2.sentido = (float) -0.5 * flujo;
        lateral3.sentido = (float) -0.5 * flujo;
        lateral4.sentido = (float) -0.5 * flujo;
        for (int i = 0; i < 2; i++) {


            estorninos.aceleradorparticulas(central);

            estorninos.aceleradorparticulas(lateral1);
            estorninos.aceleradorparticulas(lateral2);
            estorninos.aceleradorparticulas(lateral3);
            estorninos.aceleradorparticulas(lateral4);

            estorninos.dibujaparticulas();

        }


    }



}












