/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author daw1
 */
public class VehiculoDaoJson implements VehiculoDao{
    private Path path;

    public VehiculoDaoJson(Path path) {
        this.path = path;
    }
    
    @Override
    public List<Vehiculo> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int insertar(List<Vehiculo> listado) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
