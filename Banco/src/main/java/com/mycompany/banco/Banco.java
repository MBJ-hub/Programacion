package com.mycompany.banco.modelo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Mario Badallo Jiménez
 */
public class Banco {

    private String nombre;
    private Set<Cuenta> cuentas;

    /**
     *
     * @param nombre
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public List<Cuenta> getCuentas() {
        return List.copyOf(cuentas);
    }

    /**
     *
     * @param codigo
     * @param titular
     * @param saldo
     * @return
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo) {
        return cuentas.add(new Cuenta(codigo, titular, saldo));
    }

    /**
     *
     * @param codigo
     * @return
     */
    public boolean cancelarCuenta(String codigo) {
        return cuentas.remove(new Cuenta(codigo, null, 0));
    }

    /**
     *
     * @return
     */
    public float getTotalDepositos() {
        float total = 0;

        for (Cuenta c : cuentas) {
            total += c.getSaldo();
        }

        return total;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public Cuenta getCuenta(String codigo) {
        Cuenta c = null, cuenta;
        Iterator<Cuenta> iterador = cuentas.iterator();

        while (iterador.hasNext() && c == null) {
            cuenta = iterador.next();
            if (cuenta.getCodigo().equals(codigo)) {
                c = cuenta;
            }
        }

        return c;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return nombre;
    }
}
