 package com.suaces.modelo;


import java.io.Serializable;
import java.util.Objects;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author outmane bouhou
 */
public abstract class Empleado implements Comparable<Empleado>,Serializable {
private static final long serialVersionUID = 7360821926849314799L;
    private Dni dni;
    private String nombre;

    /**
     *
     * @param dni
     * @param nombre
     */
    public Empleado(String dni, String nombre) throws dniExepcion {
     
        this.dni = new Dni(dni);
        this.nombre = nombre;
    }

    /**
     *
     */
    public Empleado() {
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  dni + "," + nombre ;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
     
        final Empleado other = (Empleado) obj;
        if (this.dni.equals(other.dni)) {
            return true;
        }
        return false;
    }
    

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public Dni getDni() {
        return  dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(Dni dni) {
        this.dni = dni;
    }

    /**
     *
     * @param 
     * @return
     */
    @Override
    public int compareTo(Empleado o) {
     
      return this.dni.compareTo(o.dni);
    }

    /**
     *
     * @return
     */
    public abstract float ingresos();

}
