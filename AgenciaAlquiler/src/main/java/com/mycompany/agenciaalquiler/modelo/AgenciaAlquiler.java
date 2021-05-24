package com.mycompany.agenciaalquiler.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import com.mycompany.agenciaalquiler.modelo.VehiculoDao;

/**
 *
 * @author Mario
 */
public class AgenciaAlquiler {

    private String nombre;
    private List<Vehiculo> flota;

    public AgenciaAlquiler(String nombre, List<Vehiculo> flota) {
        this.nombre = nombre;
        this.flota = flota;
    }

    public boolean incluirVehiculo(Vehiculo vehiculo) {
        boolean incluido = false;
        if (!flota.contains(vehiculo)) {
            flota.add(vehiculo);
            incluido = true;
        }
        return incluido;
    }

    public Vehiculo consultarVehiculo(Matricula matricula) {
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

        for (Vehiculo v : flota) {
            if (grupo.equals(v.getGrupo())) {
                listadoG.add(v);
            }
        }
        return listadoG;
    }

    public Vehiculo getVehiculoMasBarato() {
        Vehiculo v = null;
        return Collections.min(flota, new ComparadorPrecioAlquiler());
    }
    
    public int guardarVehiculos(){
        return 0;
    }
    
    public int cargarVehiculos(){
        return 0;
    }
    
}
