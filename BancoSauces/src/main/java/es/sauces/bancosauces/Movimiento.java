/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.bancosauces;

import java.time.LocalDate;

/**
 *
 * @author daw1
 */
public class Movimiento {
    private LocalDate fecha;
    private TipoMovimiento tipo;
    private float cantidad;
    private float saldo;

    public Movimiento() {
    }

    public Movimiento(LocalDate fecha, TipoMovimiento tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return super.toString() + fecha + "," + tipo + "," + cantidad +  "," + saldo;
    }
    
    
    
}
