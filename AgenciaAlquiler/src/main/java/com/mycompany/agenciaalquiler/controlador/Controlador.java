/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.controlador;

import com.mycompany.agenciaalquiler.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler.vista.Ventana;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private AgenciaAlquiler agenciaAlquiler;


    public Controlador(Ventana vista, AgenciaAlquiler agenciaAlquiler){
        this.vista = vista;
        this.agenciaAlquiler =agenciaAlquiler;
    }
    
    public void crearVehiculo(){
    
    }
    
    public void borrarVehiculo(){}
    
    public void buscarVehiculo(){}
    
    public void modificarVehiculo(){}
    
    public void listarVehiculos(){}
    
    public void guardarVehiculos(){}
    
    public void cargarVehiculos(){}
    
    public void buscarVehiculoMasBarato(){}

    public void buscarVehiculoMasCaro(){}
    
    public void iniciar(){
    }
}