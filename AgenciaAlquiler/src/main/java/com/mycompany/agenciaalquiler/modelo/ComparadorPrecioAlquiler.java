
package com.mycompany.agenciaalquiler.modelo;

import java.util.Comparator;

/**
 *
 * @author daw1
 */
public class ComparadorPrecioAlquiler implements Comparator <Vehiculo>{
    
    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        int salida = 0;
        float alquiler1;
        float alquiler2;

        alquiler1 = v1.getPrecioAlquiler();
        alquiler2 = v2.getPrecioAlquiler();

        if (alquiler1 < alquiler2) {
            salida = -1;
        } else {
            if (alquiler1 > alquiler2) {
                salida = 1;
            }
        }
        return salida;

    }
}
