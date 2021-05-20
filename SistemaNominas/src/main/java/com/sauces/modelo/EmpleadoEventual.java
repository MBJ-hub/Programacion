/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;


/**
 *
 * @author daw1
 */
public class EmpleadoEventual extends Empleado   {

    private static final long serialVersionUID = 63760230934661934L;
    private float salarioHora;
    private int horas;

    /**
     *
     * @param dni
     * @param nombre
     * @param salarioHora
     * @param horas
     */
    public EmpleadoEventual(String dni, String nombre, float salarioHora, int horas) throws dniExepcion {
        super(dni, nombre);
        this.salarioHora = salarioHora;
        this.horas = horas;
    }

    /**
     *
     * @param salarioHora
     * @param horas
     */
    public EmpleadoEventual(float salarioHora, int horas) {
        this.salarioHora = salarioHora;
        this.horas = horas;
    }

    /**
     *
     * @return
     */
    public float getSalarioHora() {
        return salarioHora;
    }

    /**
     *
     * @return
     */
    public int getHoras() {
        return horas;
    }

    /**
     *
     * @param salarioHora
     */
    public void setSalarioHora(float salarioHora) {
        this.salarioHora = salarioHora;
    }

    /**
     *
     * @param horas
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "," + salarioHora + "," + horas;
    }

    /**
     *
     * @return
     */
    @Override
    public float ingresos() {
        return salarioHora * horas;
    }

}
