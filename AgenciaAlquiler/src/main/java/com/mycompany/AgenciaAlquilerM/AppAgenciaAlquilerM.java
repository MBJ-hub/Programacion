/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.AgenciaAlquilerM;

import com.mycompany.agenciaalquiler.controlador.Controlador;
import com.mycompany.agenciaalquiler.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler.vista.Ventana;
/**
 *
 * @author daw1
 */
public class AppAgenciaAlquilerM {
    
/**
 *
 * @author daw1
 */
/**
 * @param args the command line arguments
*/
    public static void main(String[] args) {
        
        // TODO code application logic here 
        AgenciaAlquiler aa = new AgenciaAlquiler();
        Ventana vista=new Ventana();
        Controlador controlador=new Controlador(vista, aa);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
}

