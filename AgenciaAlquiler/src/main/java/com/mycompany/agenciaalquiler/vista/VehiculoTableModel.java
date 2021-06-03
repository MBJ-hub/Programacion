/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.vista;

import com.mycompany.agenciaalquiler.modelo.Furgoneta;
import com.mycompany.agenciaalquiler.modelo.Turismo;
import com.mycompany.agenciaalquiler.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public class VehiculoTableModel extends AbstractTableModel{
   private List<Vehiculo> listadoVehiculos;
   private String[] columnas;

    
    public VehiculoTableModel() {
        listadoVehiculos  = new ArrayList<>();
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
   
   @Override
    public int getRowCount(){
       return listadoVehiculos.size();
    }
   
   @Override
     public int getColumnCount(){
       return columnas.length;
    }
   
   @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Vehiculo v=listadoVehiculos.get(rowIndex);
        Object o=null;
        switch(columnIndex){
           case 0:o=v.getMatricula().toString();
            break;
           case 1:if(v instanceof Turismo){
               o=((Turismo)v).getClass();
                } else{
                    o=((Furgoneta)v).getClass();
                }
            break;
           case 2:o=v.getGrupo().toString();
           break;
           case 3: if(v instanceof Turismo){
               o=((Turismo) v).getPlazas();
           }
           break;
           case 4: if(v instanceof Furgoneta){
                o=((Furgoneta)v).getCapacidad();
           }else{
               o=0.0f;
           }
           break;
           case 5: o=v.getPrecioAlquiler();
        }
        return o;
    }
   
   @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
       return false;
    }
   
   @Override
    public Class<?> getColumnClass(int columnIndex){
       Class<?> clase=null;
        switch(columnIndex){
            case 0: clase=String.class;
            break;
            case 1: clase=String.class;
            break;
            case 2: clase=String.class;
            break;
            case 3:clase=Integer.class;
            break;
            case 4:clase=Float.class;
            break;
            case 5:clase=Float.class;
            break;
        }
               return clase;
    }
   
   @Override
    public String getColumnName(int column){
        return columnas[column];
    }
}
