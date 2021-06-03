/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenda.modelo;

import java.sql.Connection;

/**
 *
 * @author daw1
 */
class ConexionBD {
    private String url; 
    private String usuario;
    private String password;

    public ConexionBD(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }
    
    public Connection getConnection(){
        return null;
    }
    
}
