/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.agenda.controladorAgenda;

import es.sauces.agenda.modelo.ContactoDao;
import es.sauces.agenda.vista.VentanaAgenda;

/**
 *
 * @author daw1
 */
public class ControladorAgenda {
    private VentanaAgenda ventana;
    private ContactoDao contactoDao;

    public ControladorAgenda(VentanaAgenda ventana, ContactoDao contactoDao) {
        this.ventana = ventana;
        this.contactoDao = contactoDao;
    }
    
    public void crear(){
    
    }
    public void editar(){
    
    }
    public void borrar(){}
    public void buscar(){}
    public void listar(){}
    public void exportar(){}
    public void importar(){}
    public void iniciar(){}
}
