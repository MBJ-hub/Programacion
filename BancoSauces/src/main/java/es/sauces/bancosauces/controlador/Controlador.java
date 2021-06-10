/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.bancosauces.controlador;

import es.sauces.bancosauces.GestorDao;
import es.sauces.bancosauces.vista.Ventana;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana ventana;
    private GestorDao gestorDao;
    
    public Controlador(Ventana ventana, GestorDao gestorDao) {
        this.ventana = ventana;
        this.gestorDao = gestorDao;
    }
    
    public void crearCliente(){
    
    
    }
    
    public void modificarCliente(){}
    
    public void eliminarCliente(){}
    
    public void buscarCliente(){}
    
    public void abrirCuenta(){}
    
    public void ingresar(){}
    
    public void reintegrar(){}
    
    public void cancelarCuenta(){}
    
    public void buscarCuenta(){}
    
    public void listarCuentasCliente(){}
    
    public void listarMovimientosCuenta(){}
	
	public void iniciar(){}
}
