/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suaces.modelo;

import com.suaces.sistemanominas.dao.DaoException;
import com.suaces.sistemanominas.dao.EmpleadoDao;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author OUTMANE BOUHOU
 */
public class SistemaNominas {

    private List<Empleado> empleados;

    private EmpleadoDao empleadoDao;

    /**
     * Get the value of empleadoDao
     *
     * @return the value of empleadoDao
     */
    public EmpleadoDao getEmpleadoDao() {
        return empleadoDao;
    }

    /**
     * Set the value of empleadoDao
     *
     * @param empleadoDao new value of empleadoDao
     */
    public void setEmpleadoDao(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }

    /**
     *
     */
    public SistemaNominas() {
        empleados = new ArrayList<>();
    }

    /**
     * Permite incuir un empleado en el sistema de nominas
     *
     * @param empleado
     * @return true si el empleado incluido en el sistema y falso en otro caso.
     */
    public boolean incluirEmpleado(Empleado empleado) {
        boolean incluido = false;
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
            incluido = true;
        }

        return incluido;
    }
    

    /**
     *
     * @param dni
     * @return
     */
    public Empleado getEmpleado(String dni) throws dniExepcion {
        Empleado empleado = null;

        Iterator<Empleado> iterador = empleados.iterator();

        while (iterador.hasNext()) {
            empleado = iterador.next();
              
           /* if (empleado.getDni().equals(new Dni(dni)))*/
           if (empleado.getDni().getDni().equals(dni)){
                return empleado;
            }
        }
        return null;
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean eliminarEmpleado(Empleado e) {
        return empleados.remove(e);
    }

    /**
     *
     * @return
     */
    public List<Empleado> listarEmpleados() {
        List<Empleado> listado = new ArrayList<>(empleados);
        Collections.sort(listado);
        return listado;
    }

    /**
     *
     * @return
     */
    public List<Empleado> ListarEmpleadosPorSueldo() {
        List<Empleado> listado = new ArrayList<>();

        Collections.copy(empleados, listado);
        Collections.sort(listado, new ComparadorSueldo());

        return listado;
    }

    /**
     *
     * @return
     */
    public float getTotalSalarios() {
        float acumulador = 0;
        Iterator<Empleado> iterador = empleados.iterator();
        while (iterador.hasNext()) {

            acumulador += iterador.next().ingresos();
        }
        return acumulador;
    }

    public int guardarEmpleados() throws DaoException {
        if (empleadoDao != null) {
            return empleadoDao.insertar(empleados);
        }
        return 0;
    }

    public int cargarEmpleados() throws DaoException {
        int n = 0;
        if (empleadoDao != null) {

            List<Empleado> listado = empleadoDao.Listar();
            for (Empleado e : listado) {
                if (this.incluirEmpleado(e)) {
                    n++;
                }

            }
            
         /*  empleados=empleadoDao.Listar();*/
        }
        
        return n;
    }
}
