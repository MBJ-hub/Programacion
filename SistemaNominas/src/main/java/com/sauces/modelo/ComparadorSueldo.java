/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.util.Comparator;

/**
 *
 * @author daw1
 */
public class ComparadorSueldo implements Comparator<Empleado> {

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
      public int compare(Empleado o1, Empleado o2) {
                int salida = 0;
                float ingresos1,ingresos2;
                ingresos1=o1.ingresos();
                ingresos2=o1.ingresos();
               if(ingresos1<ingresos2)
               {
                   salida=-1;
               }else
               {
                   if(ingresos1>ingresos2)
                   {
                         salida=1;
                   }
               }

                return salida;
            }
        }
    

