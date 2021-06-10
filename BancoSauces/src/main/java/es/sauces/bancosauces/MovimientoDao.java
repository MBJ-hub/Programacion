/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.bancosauces;

import java.util.List;

/**
 *
 * @author daw1
 */
public interface MovimientoDao {
    public void insertar(Movimiento movimiento,String codigoCuenta) throws DaoException;
    public List<Movimiento> listar() throws DaoException;
    public List<Movimiento> listar(String codigoCuenta) throws DaoException;
}
