/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.vista;

import com.sauces.modelo.Empleado;
import com.sauces.modelo.EmpleadoEventual;
import com.sauces.modelo.EmpleadoFijo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public class EmpleadoTableModel extends AbstractTableModel {

    private List<Empleado> listado;
    private String[] columnas;

    public EmpleadoTableModel() {
        listado = new ArrayList<>();
        columnas = new String[]{"DNI", "NOMBRE", "SALARIO", "HORAS", "INGRESOS"};
    }

    public List<Empleado> getListado() {
        return listado;
    }

    public void setListado(List<Empleado> listado) {
        this.listado = listado;
        this.fireTableDataChanged();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }

    @Override
    public int getRowCount() {
        return listado.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado emp = listado.get(rowIndex);
       
    
        Object o = null;
        switch (columnIndex) {
            case 0:
                o = emp.getDni().toString();
                break;

            case 1:
                o = emp.getNombre();
                break;

            case 2:
                if(emp instanceof EmpleadoFijo){
                    o = ((EmpleadoFijo) emp).getSalario();
                }else{
                    o = ((EmpleadoEventual)emp).getSalarioHora();
                }
                break;
            case 3:
                if(emp instanceof EmpleadoEventual){
                    o = ((EmpleadoEventual)emp).getHoras();
                }
                else{
                    o = 0;
                }
                break;
            case 4:
                o = emp.ingresos();
                break;

        }

        return o;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase = null;
        switch (columnIndex) {
            case 0:
                clase = String.class;
                break;
            case 1:
                clase = String.class;
                break;
            case 2:
                clase = Float.class;
                break;
            case 3:
                clase = Integer.class;
                break;
            case 4:
                clase = Float.class;
                break;
        }

        return clase;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
