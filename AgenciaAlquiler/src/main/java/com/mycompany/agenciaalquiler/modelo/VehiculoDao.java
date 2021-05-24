/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.util.List;

/**
 *
 * @author daw1
 */
public interface VehiculoDao {
    List<Vehiculo> listar();
    int insertar(List<Vehiculo> listado)throws DaoException;
}
