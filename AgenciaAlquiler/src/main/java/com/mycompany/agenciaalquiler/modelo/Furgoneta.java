
package com.mycompany.agenciaalquiler.modelo;

/**
 *
 * @author Mario
 */
public class Furgoneta extends Vehiculo{
    private float capacidad;
    
    public Furgoneta(String matricula, Grupo grupo, float capacidad) {
        super(matricula, grupo);
        this.capacidad= capacidad;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }

    
    @Override
    public String toString() {
        return super.toString()+ "," +capacidad;
    }
    
    @Override
    public float getPrecioAlquiler() {
       float precioAlquiler=0;
       precioAlquiler=getGrupo().getPrecioA()+getGrupo().getPrecioB()*capacidad;
       return precioAlquiler;
    }
    
    @Override
    public float getPrecioAlquiler(int dias){
        float precioAlquilerDias;
        
        precioAlquilerDias=(int)((getGrupo().getPrecioA()+getGrupo().getPrecioB()*capacidad)*dias);
        return precioAlquilerDias;
    }
}
