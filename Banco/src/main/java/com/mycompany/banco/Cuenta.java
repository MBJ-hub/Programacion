package com.mycompany.banco.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mario Badallo Jiménez
 */
public class Cuenta {

    private String codigo;
    private String titular;
    private float saldo;
    private List<Movimiento> movimientos;

    /**
     *
     * @param codigo
     * @param titular
     * @param saldo
     */
    public Cuenta(String codigo, String titular, float saldo) {
        if (saldo >= 0) {
            this.codigo = codigo;
            this.titular = titular;
            this.saldo = saldo;
            movimientos = new ArrayList<>();
            movimientos.add(new Movimiento(LocalDate.now(), 'I', saldo, this.saldo));
        }
    }

    /**
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public String getTitular() {
        return titular;
    }

    /**
     *
     * @param titular
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     *
     * @return
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     *
     * @return
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     *
     * @param desde
     * @param hasta
     * @return
     */
    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta) {
        List<Movimiento> listado = new ArrayList<>();

        for (Movimiento m : movimientos) {
            if (m.getFecha().isAfter(desde) && m.getFecha().isBefore(hasta)) {
                listado.add(m);
            }
        }
        return listado;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    /**
     * Permite ingresar dinero en una cuenta. Incrementa el saldo de una cuenta.
     * Si la cantidad es negativa el ingreso no sera realizado.
     *
     * @param cantidad cantidad a ingresar.
     */
    public void ingresar(float cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'I', cantidad, saldo));
        }
    }

    /**
     *
     * @param cantidad
     */
    public void reintegrar(float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'R', -cantidad, saldo));
        }
    }

    /**
     *
     * @param destino
     * @param cantidad
     */
    public void realizarTransferencia(Cuenta destino, float cantidad) {
        if (destino != null && cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            destino.saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'T', -cantidad, saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(), 'T', cantidad, destino.saldo));
        }
    }

    /**
     * Devuelve un string con un listado de los movimientos de la cuenta.
     *
     * @return Cadena con los movimientos de la cuenta.
     */
    public String listarMovimientos() {
        StringBuilder salida = new StringBuilder();

        for (Movimiento m : movimientos) {
            salida.append(m.toString());
            salida.append("\n");
        }

        return salida.toString();
    }
}
