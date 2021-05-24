/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.vista;

import com.mycompany.agenciaalquiler.modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author daw1
 */
public class VehiculoTableModel {
   private List<Vehiculo> listadoVehiculos;
   private String[] columnas;

    public VehiculoTableModel(List<Vehiculo> listadoVehiculos, String[] columnas) {
        this.listadoVehiculos = listadoVehiculos;
        this.columnas = new String[]{"MATRICULA","TIPO","GRUPO","PLAZAS","CAPACIDAD","PRECIO ALQUILER"};
    }
   
   
    public void setListadoVehiculos(List<Vehiculo> listado){
       this.listadoVehiculos = listado;
       
    }

    public List<Vehiculo> getListadoVehiculos() {
        return listadoVehiculos;
    }

    public String[] getColumnas() {
        return columnas;
    }
   
    public int getRowCount(){
       return listadoVehiculos.size();
    }
   
     public int getColumnCount(){
       return columnas.length;
    }
   
    public Object getValueAt(int rowIndex, int columnIndex){
       return null;
    }
   
    public boolean isCellEditable(int rowIndex, int columnIndex){
       return false;
    }
   
    public Class<?> getColumnClass(int columnIndex){
       return null;
    }
   
    public String getColumnName(int column){
        return columnas[column];
    }
}
