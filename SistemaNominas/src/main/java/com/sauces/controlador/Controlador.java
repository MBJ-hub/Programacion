/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.controlador;

import com.sauces.modelo.Dni;
import com.sauces.modelo.Empleado;
import com.sauces.modelo.EmpleadoCsv;
import com.sauces.modelo.EmpleadoEventual;
import com.sauces.modelo.EmpleadoFijo;
import com.sauces.modelo.EmpleadoJson;
import com.sauces.modelo.EmpleadoObj;
import com.sauces.modelo.EmpleadoXml;
import com.sauces.modelo.SistemaNominas;
import com.sauces.modelo.dniExepcion;
import com.sauces.sistemanominas.dao.DaoException;
import com.sauces.sistemanominas.dao.EmpleadoDao;
import com.sauces.vista.Ventana;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class Controlador {

    private Ventana vista;

    private SistemaNominas modelo;

    public Controlador(Ventana vista, SistemaNominas modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public Ventana getVista() {
        return vista;
    }

    public void setVista(Ventana vista) {
        this.vista = vista;
    }

    public SistemaNominas getModelo() {
        return modelo;
    }

    public void setModelo(SistemaNominas modelo) {
        this.modelo = modelo;
    }

    public void crearEmpleado() {
        String dni, nombre, tipo;
        Empleado emp = null;
        float salario;
        int horas;
        dni = vista.getDni();
        nombre = vista.getNombre();
        tipo = vista.getTipo();
        salario = vista.getSalario();
        try {
            switch (tipo) {
                case "Fijo":
                    emp = new EmpleadoFijo(dni, nombre, salario);
                    break;
                case "Eventual":
                    horas = vista.getHoras();
                    emp = new EmpleadoEventual(dni, nombre, salario, horas);
                    break;

            }
            vista.mostrarIngresos(emp.ingresos());

            if (modelo.incluirEmpleado(emp)) {
                vista.listarEmpleados(modelo.listarEmpleados());
                vista.mostrarMensaje("Empleado " + tipo + " Incluido");
            } else {
                vista.mostrarMensaje("No se ha podido incluir");
            }
        } catch (dniExepcion exs) {
            vista.mostrarMensaje("Error :" + exs);
        }

    }

    public void buscarEmpleado() throws dniExepcion {
        Empleado emp;
        String dni;
        dni = vista.getDni();
        emp = modelo.getEmpleado(dni);
        if (emp != null) {
            vista.mostrarMensaje("El Dni Existe");

            vista.mostrarNombre(emp.getNombre());
            if (emp instanceof EmpleadoFijo) {
                vista.mostrarTipo("Fijo");
                vista.mostrarSalario(((EmpleadoFijo) emp).getSalario());
            } else {
                vista.mostrarTipo("Eventual");
                vista.mostrarSalario(((EmpleadoEventual) emp).getHoras());

            }
            vista.mostrarIngresos(emp.ingresos());

        } else {
            vista.mostrarMensaje("No existe empleado con ese Dni");

        }
    }

    public void eliminarEmpleado() throws dniExepcion {
        Empleado emp;
        String dni;
        dni = vista.getDni();
        emp = modelo.getEmpleado(dni);
        if (emp != null) {
            if(modelo.eliminarEmpleado(emp)){;
                vista.mostrarMensaje("Empleado eliminado ");
            }else {
                vista.mostrarMensaje("no ha podido eliminar el empleado");
            }
        } else {
            vista.mostrarMensaje("No existe ese empleado");
        }
    }

    public void modificarEmpleado() throws dniExepcion {
        Empleado emp;
        String dni;
        Object o = null;
        dni = vista.getDni();
        emp = modelo.getEmpleado(dni);
        if (emp != null) {
            //*Pedimos los datos y los cambiamos.
            emp.setDni(new Dni(vista.getDni()));
            emp.setNombre(vista.getNombre());
            if (emp instanceof EmpleadoFijo) {
                //*Casting.
                ((EmpleadoFijo) emp).setSalario(vista.getSalario());
            } else {
                ((EmpleadoEventual) emp).setHoras(vista.getHoras());
            }
            //*¡Dar a listar empleados en la interfaz para que funcione!.
            vista.mostrarIngresos(emp.ingresos());
            vista.mostrarMensaje("Empleado modificado");

        } else {
            vista.mostrarMensaje("No existe empleado con ese Dni");
        }
    }
    
    public void listarEmpleados() {
        vista.listarEmpleados(modelo.listarEmpleados());

    }
    
    public void listarEmpleados1() {
        vista.listarEmpleados(modelo.listarEmpleados());
        List<Empleado> listado = null;
        String orden = vista.getOrden();
        switch (orden) {
            case "DNI": listado = modelo.listarEmpleados();
                break;
            case "Ingresos": listado = modelo.ListarEmpleadosPorSueldo();
                break;
            case "nombre": listado = modelo.listarEmpleados();
                    Collections.sort(listado, new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado e1, Empleado e2) {
                        return e1.getNombre().compareTo(e2.getNombre());
                    }
                });
                
        }
        //*if(listado!=null){}
    }

    public void guardarEmpleados() {
        String archivo;
        List<Empleado> listado;
        EmpleadoDao empleadoDao;
        int n = 0;
        archivo = vista.getArchivo();
        empleadoDao=getDao(archivo);
        
        if (empleadoDao != null) {
            try {
                modelo.setEmpleadoDao(empleadoDao);
                n=  modelo.guardarEmpleados();
               
                vista.mostrarMensaje("se ha guardado "+n+" Empleados");
                vista.listarEmpleados(modelo.listarEmpleados());
            } catch (DaoException ex) {
                vista.mostrarMensaje(ex.getMessage());
            }

        }

    }

    public void cargarEmpleados() {
        String archivo;
        List<Empleado> listado;
        EmpleadoDao empleadoDao;
        int n = 0;
        archivo = vista.getArchivo();
        empleadoDao=getDao(archivo);
        
        if (empleadoDao != null) {
            try {
                modelo.setEmpleadoDao(empleadoDao);
                n=  modelo.cargarEmpleados();
               
                vista.mostrarMensaje("se ha cargado "+n+" Empleados");
                vista.listarEmpleados(modelo.listarEmpleados());
            } catch (DaoException ex) {
                vista.mostrarMensaje(ex.getMessage());
            }

        }
    }

    public void iniciar() {
        vista.mostrar();
    }
    private EmpleadoDao getDao(String nombrearchivo)
    { int posicionpunto=nombrearchivo.lastIndexOf('.');
    EmpleadoDao empleadoDao=null;
        String extension;
                        if(posicionpunto!=-1)
                        {
                            extension=nombrearchivo.substring(posicionpunto);
                        
                        
                        switch(extension)
                        {
                            case ".csv":
                                empleadoDao=new EmpleadoCsv(nombrearchivo);
                                break;
                            case ".obj":
                                empleadoDao=new EmpleadoObj(nombrearchivo);
                                break;
                            case ".json":
                                empleadoDao=new EmpleadoJson(nombrearchivo);
                                break;
                            case ".xml":
                                empleadoDao=new EmpleadoXml(nombrearchivo);
                                break;
                            default: System.out.println("Extencion incorrecta");
                        }}
                  return empleadoDao;      
    }
}
