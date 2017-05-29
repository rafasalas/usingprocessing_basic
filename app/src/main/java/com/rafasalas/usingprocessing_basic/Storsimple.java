package com.rafasalas.usingprocessing_basic;

/**
 * Created by salas on 29/05/2017.
 */
import java.util.ArrayList;

import processing.core.PVector;

public class Storsimple
{        float magbrowniano;
    float numeroparticulas, masaparticula;
    int claseparticula;
    PVector velocidadinicial;
    PVector origen;
    PVector browniano;
    int limsup, liminf, limizq, limder;

    boolean esbrowniano;
    //float magbrowniano;
    ArrayList<Particula> particulas;


  public  Storsimple(float numpart,  int claspart, int sup, int inf, int izq, int der){
        limsup=sup;
        liminf=inf;
        limizq=izq;
        limder=der;
        numeroparticulas=numpart;
        claseparticula=claspart;
        particulas=new ArrayList<Particula>() ;



        //origen=new PVector((width/2)+30, (height/2)+30);
        esbrowniano=true;
        magbrowniano=(float).8;

        for(int i=0; i<numeroparticulas; i++){

            particulas.add(new Particula());


            particulas.get(i).liminf=inf;
            particulas.get(i).eterna=true;
            particulas.get(i).liminf=inf;
            particulas.get(i).limsup=sup;
            particulas.get(i).limizq=izq;
            particulas.get(i).limder=der;
        }


    }

    //fin constructor Storsimple


   public  void aceleradorparticulas(Atractor a){
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

            p.caer();
            p.lanzar();



        }


    }
}
