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
public class Cuenta {
    private String codigo;
    private Cliente titular;
    private float saldo;
    private List<Movimiento> movimientos;

    public Cuenta() {
    }

    public Cuenta(String codigo) {
        this.codigo = codigo;
    }

    public Cuenta(String codigo, Cliente titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public Movimiento ingresar(float cantidad){
        return null;
    }
    
    public Movimiento reintegrar(float cantidad){
        return null;
    }
    
    public Movimiento realizarTransferencia(Cuenta destino, float cantidad){
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + codigo + "," + titular + "," + saldo + "," + movimientos;   
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }
    
}
