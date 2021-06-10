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
public interface ClienteDao {
    public void insertar(Cliente cliente) throws DaoException;
    public void modificar(Cliente cliente) throws DaoException;
    public void borrar(String dni) throws DaoException;
    public Cliente buscar(String dni) throws DaoException;
    public List<Cliente> listar() throws DaoException;
}
