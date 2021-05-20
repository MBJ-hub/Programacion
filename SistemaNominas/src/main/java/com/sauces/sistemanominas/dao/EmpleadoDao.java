/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas.dao;

import com.sauces.modelo.Empleado;
import java.util.List;

/**
 *
 * @author daw1
 */
public interface EmpleadoDao {
    List<Empleado> Listar() throws DaoException;
    int insertar(List<Empleado> listado) throws DaoException;
    
}
