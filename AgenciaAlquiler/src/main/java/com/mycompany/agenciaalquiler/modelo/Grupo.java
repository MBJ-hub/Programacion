
package com.mycompany.agenciaalquiler.modelo;

/**
 *
 * @author Mario
 */
public enum Grupo {

    A (50, 1.5f, 5),
    B (55, 2f, 10),
    C (60, 2.5f, 15); 

    private final float precioBase;
    private final float factorTurismo;
    private final float factorFurgoneta;

    private Grupo(float precioBase, float factorTurismo, float factorFurgoneta) {
        this.precioBase = precioBase;
        this.factorTurismo = factorTurismo;
        this.factorFurgoneta = factorFurgoneta;
    }

    public static Grupo getA() {
        return A;
    }

    public static Grupo getB() {
        return B;
    }

    public static Grupo getC() {
        return C;
    }

    public float getPrecioA() {
        return precioBase;
    }

    public float getPrecioB() {
        return factorTurismo;
    }

    public float getPrecioC() {
        return factorFurgoneta;
    }
}
