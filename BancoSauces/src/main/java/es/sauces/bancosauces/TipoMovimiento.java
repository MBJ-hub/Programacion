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
public enum TipoMovimiento {
    INGRESO,
    REINTEGRO,
    TRANSFERENCIA;

    
    
    
    
    
    
    public static TipoMovimiento getINGRESO() {
        return INGRESO;
    }

    public static TipoMovimiento getREINTEGRO() {
        return REINTEGRO;
    }

    public static TipoMovimiento getTRANSFERENCIA() {
        return TRANSFERENCIA;
    }
    
    
    
}
