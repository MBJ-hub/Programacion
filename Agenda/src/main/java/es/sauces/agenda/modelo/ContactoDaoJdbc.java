/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenda.modelo;

import java.util.List;

/**
 *
 * @author daw1
 */
public class ContactoDaoJdbc {
    private ConexionBD conexion;
    List<Contacto> listar;

    public ContactoDaoJdbc(ConexionBD conexion) {
        this.conexion = conexion;
    }
    
     public int insertar(Contacto contacto){
        return 0;
    }
     
    public int buscar(String nombre){
        return 0;
    }
    
    public int modificar(){
        return 0;
    }
    
    public int borrar(String nombre){
        return 0;
    }
   
}
