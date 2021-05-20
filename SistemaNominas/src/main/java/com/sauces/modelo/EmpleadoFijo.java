/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suaces.modelo;



/**
 *
 * @author daw1
 */
public class EmpleadoFijo extends Empleado  {
    private static final long serialVersionUID = 381312449141652051L;
    private float salario;

    /**
     *
     * @param dni
     * @param nombre
     * @param salario
     * @throws com.suaces.modelo.dniExepcion
     */
    public EmpleadoFijo( String dni, String nombre,float salario) throws dniExepcion {
        super(dni, nombre);
        this.salario = salario;
    }

    /**
     *
     * @param salario
     */
    public EmpleadoFijo(float salario) {
        this.salario = salario;
    }

    /**
     *
     * @return
     */
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString()+","+salario; 
    }
    
    /**
     *
     * @return
     */
    @Override
    public float ingresos() {
       return salario;
    }
    
}
