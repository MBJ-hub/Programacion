package com.mycompany.agenciaalquiler.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mario
 */
public class AgenciaAlquiler {

    private String nombre;
    private List<Vehiculo> flota;
    private VehiculoDao vehiculoDao;

    /**
     *
     * @param flota
     * @param nombre
     */
    public AgenciaAlquiler(List<Vehiculo> flota, String nombre) {
        this.nombre = nombre;
        this.flota = flota;
        
    }

    public AgenciaAlquiler() {
        flota=new ArrayList<>();
    }

    public VehiculoDao getVehiculoDao(){
        return vehiculoDao;
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFlota(List<Vehiculo> flota) {
        this.flota = flota;
    }

    public void setVehiculoDao(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }
    
    public void setVehiculos(List<Vehiculo> vehiculos){
        this.flota = vehiculos; 
    }
    
    public boolean incluirVehiculo(Vehiculo vehiculo) {
        boolean incluido = false;
        if (!flota.contains(vehiculo)) {
            flota.add(vehiculo);
            incluido = true;
        }
        return incluido;
    }

    public Vehiculo consultarVehiculo(String matricula) {
        Vehiculo v = null;
        Iterator<Vehiculo> iterador = flota.iterator();
        while (iterador.hasNext()) {
            v = iterador.next();

            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        return flota.remove(vehiculo);
    }

    public List<Vehiculo> listarVehiculosPorPrecio() {
        List<Vehiculo> listadoP = new ArrayList<>();

        Collections.copy(flota, listadoP);
        Collections.sort(listadoP, new ComparadorPrecioAlquiler());
        return listadoP;

    }

    public List<Vehiculo> listarVehiculos(Grupo grupo) {
        List<Vehiculo> listadoG = new ArrayList<>();

        flota.stream().filter(v -> (grupo.equals(v.getGrupo()))).forEachOrdered(v -> {
            listadoG.add(v);
        });
        return listadoG;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Vehiculo> getVehiculos() {
        return flota;
    }

    public Vehiculo getVehiculoMasBarato() {
        Vehiculo v = null;
        return Collections.min(flota, new ComparadorPrecioAlquiler());
    }
    
    public int guardarVehiculos() throws DaoException{
        int n=0;
        if(vehiculoDao!=null){
            return vehiculoDao.insertar(flota);
        }
        return n;
    }
    
    public int cargarVehiculos(){
        int n=0;
        if(vehiculoDao!=null){
            List<Vehiculo> listado=vehiculoDao.listar();
            n = listado.stream().map(v -> {
                if(this.incluirVehiculo(v));
                return v;
            }).map(_item -> 1).reduce(n, Integer::sum);
        }
         return n;
    }
    
}
