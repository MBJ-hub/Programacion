/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.controlador;

import com.mycompany.agenciaalquiler.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler.modelo.Furgoneta;
import com.mycompany.agenciaalquiler.modelo.Grupo;
import com.mycompany.agenciaalquiler.modelo.Turismo;
import com.mycompany.agenciaalquiler.modelo.Vehiculo;
import com.mycompany.agenciaalquiler.modelo.VehiculoDao;
import com.mycompany.agenciaalquiler.modelo.VehiculoDaoCsv;
import com.mycompany.agenciaalquiler.modelo.VehiculoDaoJson;
import com.mycompany.agenciaalquiler.modelo.VehiculoDaoObj;
import com.mycompany.agenciaalquiler.modelo.VehiculoDaoXml;
import com.mycompany.agenciaalquiler.modelo.DaoException;
import com.mycompany.agenciaalquiler.vista.Ventana;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private AgenciaAlquiler agenciaAlquiler;


    public Controlador(Ventana vista, AgenciaAlquiler agenciaAlquiler){
        this.vista = vista;
        this.agenciaAlquiler =agenciaAlquiler;
    }
    

    public Ventana getVista() {
        return vista;
    }

    public void setVista(Ventana vista) {
        this.vista = vista;
    }

    public AgenciaAlquiler getAgenciaAlquiler() {
        return agenciaAlquiler;
    }

    public void setAgenciaAlquiler(AgenciaAlquiler agenciaAlquiler) {
        this.agenciaAlquiler = agenciaAlquiler;
    }
     
    public void crearVehiculo(){
        Vehiculo v=null;
        String matricula=vista.getMatricula();
        Grupo grupo=Grupo.valueOf(vista.getGrupo());
        String tipo=vista.getTipo();
        
        if(tipo.equals("TURISMO")){
            
                int plazas=vista.getPlazas();
                v=new Turismo(matricula,grupo,plazas);
        }    
        else{
                float capacidad=vista.getCapacidad();
                v=new Furgoneta(matricula,grupo,capacidad);
            
        }
        vista.actualizarTabla();
        if(agenciaAlquiler.incluirVehiculo(v)){
            vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            vista.mostrarMensaje("Vehículo incluido");
        }
        else{
            vista.mostrarMensaje("No se ha podido incluir el vehículo");
        }
    }

    public void borrarVehiculo(){
    String matricula = vista.getMatricula();
        Vehiculo v;
        v = agenciaAlquiler.consultarVehiculo(matricula);
        if (v != null) {
            if (agenciaAlquiler.eliminarVehiculo(v)) {
                vista.mostrarMensaje("Vehiculo Eliminado");
            } else {
                vista.mostrarMensaje("No se ha podido eliminar el vehiculo");
            }
        }else{
            vista.mostrarMensaje("No existe el vehiculo");
        }
    }
    
    public void buscarVehiculo(){
        String matricula=null;
        Vehiculo v;
        JOptionPane.showInputDialog(null,"Introduce Matricula",matricula);
        v=agenciaAlquiler.consultarVehiculo(matricula);
        vista.mostrarMensaje(v.toString());
    }
    
    public void modificarVehiculo(){
        try{
        String matricula=vista.getMatricula();
        Vehiculo v=agenciaAlquiler.consultarVehiculo(matricula);
        v.setGrupo(Grupo.valueOf(vista.getGrupo()));
        
        
                if(v instanceof Turismo){
                    ((Turismo) v).setPlazas(vista.getPlazas());
                    vista.mostrarPlazas(((Turismo) v).getPlazas());

                }
                if(v instanceof Furgoneta){
                    ((Furgoneta) v).setCapacidad(vista.getCapacidad());
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
                vista.mostrarMensaje("Vehiculo modificado");
                this.listarVehiculos();
        } catch (NumberFormatException nfe){
                vista.mostrarMensaje("Error en los datos introducidos");
        }
    
    }
    
   public void listarVehiculos() {
       List<Vehiculo> listado=null;
        switch(vista.getOrden().toUpperCase()){
            case "MATRÍCULA":listado=agenciaAlquiler.listarVehiculos(Grupo.A);
                break;
            case "PRECIO ALQUILER":listado=agenciaAlquiler.listarVehiculosPorPrecio();
                break;
        }
        switch(vista.getFiltroTipo()){
            case "TURISMO":
                break;
            case "FURGONETA":
                break;
        }
        switch(vista.getFiltroGrupo()){
            case "TODOS":
                break;
            case "A": agenciaAlquiler.listarVehiculos(Grupo.A);
                break;
            case "B": agenciaAlquiler.listarVehiculos(Grupo.B);
                break;
            case "C": agenciaAlquiler.listarVehiculos(Grupo.C);
                break;
        }
        if(listado!=null){
            vista.mostrarVehiculos(listado);
        }
        vista.actualizarTabla();
    }
    
    public void guardarVehiculos(){
        String archivo;
        List<Vehiculo> listado;
        VehiculoDao vehiculoDao;
        int n = 0;
        archivo = vista.getArchivo();
        vehiculoDao = getDao(archivo);

        if (vehiculoDao != null) {
            try {
                agenciaAlquiler.setVehiculoDao(vehiculoDao);
                n = agenciaAlquiler.guardarVehiculos();
                vista.mostrarMensaje("se ha guardado " + n + " Empleados");
            } catch (DaoException ex) {
                vista.mostrarMensaje(ex.getMessage());
            }
        }
    }
    
    public void cargarVehiculos(){
        String archivo;
        List<Vehiculo> listado;
        VehiculoDao vehiculoDao;
        int n = 0;
        archivo = vista.getArchivo();
        vehiculoDao = getDao(archivo);

        if (vehiculoDao != null) {
            agenciaAlquiler.setVehiculoDao(vehiculoDao);
            n =agenciaAlquiler.cargarVehiculos();
            vista.mostrarMensaje("se ha cargado " + n + " Empleados");
        }
    }
    
    public void buscarVehiculoMasBarato(){
        Vehiculo v=agenciaAlquiler.getVehiculoMasBarato();
        if(v!=null){
             vista.mostrarMatricula(v.getMatricula());
             vista.mostrarGrupo(v.getGrupo().toString());
             vista.mostrarTipo(v.getClass().getSimpleName());

             if(v instanceof Turismo){
                 vista.mostrarPlazas(((Turismo) v).getPlazas());
                 vista.mostrarCapacidad(0);
             }
             else if(v instanceof Furgoneta){
                 vista.mostrarPlazas(0);
                 vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
             }

             vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());     
        }
    }

    public void buscarVehiculoMasCaro(){
        List<Vehiculo> listado=null;
        Vehiculo v=listado.get(listado.lastIndexOf(this));
        if(v!=null){
             vista.mostrarMatricula(v.getMatricula());
             vista.mostrarGrupo(v.getGrupo().toString());
             vista.mostrarTipo(v.getClass().getSimpleName());

             if(v instanceof Turismo){
                 vista.mostrarPlazas(((Turismo) v).getPlazas());
                 vista.mostrarCapacidad(0);
             }
             else if(v instanceof Furgoneta){
                 vista.mostrarPlazas(0);
                 vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
             }

             vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());     
        }
    }
    
    public void iniciar(){
        vista.mostrar();
    }

    private VehiculoDao getDao(String archivo) {
        String extension = archivo.substring(archivo.lastIndexOf(".") + 1);
        switch (extension) {
            case "obj":
                return (new VehiculoDaoObj(archivo));
            case "csv":
                return (new VehiculoDaoCsv(archivo));
            case "json":
                return (new VehiculoDaoJson(archivo));
            case "xml":
                return (new VehiculoDaoXml(archivo));
        }
        return null;
    }
}
