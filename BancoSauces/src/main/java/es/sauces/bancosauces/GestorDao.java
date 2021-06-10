/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.bancosauces;

/**
 *
 * @author daw1
 */
public class GestorDao {
    private ConexionBD conexion;
    private ClienteDao clienteDao;
    private CuentaDao cuentaDao;
    private MovimientoDao movimientoDao;

    public GestorDao(ConexionBD conexion) {
        this.conexion = conexion;
        clienteDao=(ClienteDao) new ClienteDaoJdbc(conexion);
        cuentaDao=(CuentaDao) new CuentaDaoJdbc(conexion);
        movimientoDao=(MovimientoDao) new MovimientoDaoJdbc(conexion);
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public CuentaDao getCuentaDao() {
        return cuentaDao;
    }

    public MovimientoDao getMovimientoDao() {
        return movimientoDao;
    }
}
