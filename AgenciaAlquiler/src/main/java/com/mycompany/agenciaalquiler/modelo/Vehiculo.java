
package com.mycompany.agenciaalquiler.modelo;

import java.util.Objects;

/**
 *
 * @author Mario
 */
public abstract class Vehiculo {

    
    private String matricula;
    Grupo grupo;

    public Vehiculo(String matricula, Grupo grupo) {
        this.matricula = matricula;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    @Override
    public String toString() {
        return matricula + ","+ grupo;
    }

    public int compareTo(Vehiculo o){
        return this.matricula.compareTo(o.matricula);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(this==obj){
            return true;
        }
        if(obj!=null){
            if(obj instanceof Vehiculo){
                Vehiculo v=(Vehiculo)obj;
                if(this.matricula.equals(v.matricula)){
                return true;
                }
            }
        }
        return false;
    }
    
    public abstract float getPrecioAlquiler();
        
    public float getPrecioAlquiler(int dias){
        float precioAlquilerDias;
     
        precioAlquilerDias=(int)((getGrupo().getPrecioA()+getGrupo().getPrecioB())*dias);
        return precioAlquilerDias;
    }
    
    static boolean remove(Vehiculo vehiculo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
