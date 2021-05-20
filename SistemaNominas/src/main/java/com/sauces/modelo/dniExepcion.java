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
public class dniExepcion extends Exception {

    public dniExepcion(String error_dni) {
        super(error_dni);
    }
    
}
