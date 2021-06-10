/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.bancosauces;

import java.util.List;

/**
 *
 * @author daw1
 */
public class Cliente {
    private String dni;
    private String nombre;
    private List<Cuenta> cuentas;
    
    public Cliente(){
    }
    
    public Cliente(String dni){
    }
    
    public Cliente(String dni, String nombre){
    }
    
    public String getDni(){
        return null;
    }
    
    public String getNombre(){
        return null;
    }
    
    public List<Cuenta> getCuentas(){
        return null;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public boolean asignarCuenta(Cuenta cuenta){
        return false;
    }
    
    public Cuenta getCuenta(String codigo){
        return null;
    }
    
    public boolean cancelarCuenta(String codigo){
        return false;
    }
    
}
