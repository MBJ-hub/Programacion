package com.sauces.sistemanominas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sauces.modelo.SistemaNominas;
import com.sauces.controlador.Controlador;
import com.sauces.vista.Ventana;

/**
 *
 * @author daw1
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SistemaNominas modelo = new SistemaNominas();
        Ventana vista = new Ventana();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.iniciar();

    }

}
