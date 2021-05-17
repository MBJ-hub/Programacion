package com.mycompany.banco.appBanco;

/**
 *
 * @author Mario Badallo Jiménez
 */
import com.mycompany.banco.modelo.Banco;
import com.mycompany.banco.modelo.Cuenta;
import java.util.Scanner;

public class AppBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion, opcion2;
        String codigo, titular;
        float saldo, cantidad;
        Cuenta cuenta;
        Banco banco = new Banco("Banco Sauces");

        do {
            System.out.println("1.- Abrir cuenta");
            System.out.println("2.- Operar con cuenta");
            System.out.println("3.- Cancelar cuenta");
            System.out.println("4.- Listar cuentas");
            System.out.println("5.- Consultar total depósitos");
            System.out.println("0.- Salir");
            System.out.println("Introduzca opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Abrir cuenta");
                    System.out.println("Introduzca codigo");
                    codigo = teclado.nextLine();
                    System.out.println("Introduzca titular de la cuenta");
                    titular = teclado.nextLine();
                    System.out.println("Introduzca saldo inicial");
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                    if (banco.abrirCuenta(codigo, titular, saldo)) {
                        System.out.println("Cuenta abierta");
                    } else {
                        System.out.println("No se ha podido abrir la cuenta");
                    }
                    break;

                case 2:
                    System.out.println("Operar con cuenta");
                    System.out.println("Introduzca codigo: ");
                    codigo = teclado.nextLine();

                    cuenta = banco.getCuenta(codigo);
                    if (cuenta != null) {
                        System.out.println("1.- Ingresar dinero");
                        System.out.println("2.- Retirar dinero");
                        System.out.println("3.- Consultar saldo");
                        System.out.println("4.- Realizar transferencia");
                        System.out.println("5.- Consultar movimientos");
                        System.out.println("Introduzca opción: ");
                        opcion2 = teclado.nextInt();

                        switch (opcion2) {
                            case 1:
                                System.out.println("Introduzca cantidad a ingresar:");
                                cantidad = teclado.nextFloat();
                                teclado.nextLine();
                                cuenta.ingresar(cantidad);
                                System.out.println("Ingreso realizado");
                                break;
                            case 2:
                                System.out.println("Introduzca cantidad a retirar:");
                                cantidad = teclado.nextFloat();
                                teclado.nextLine();
                                cuenta.reintegrar(cantidad);
                                System.out.println("Reintegro realizado");
                                break;
                            case 3:
                                System.out.println("Su saldo es: " + cuenta.getSaldo());
                                break;
                            case 4:
                                System.out.println("Introduzca cuenta: ");
                                codigo = teclado.nextLine();
                                System.out.println("Introduzca cantidad: ");
                                cantidad = teclado.nextFloat();
                                teclado.nextLine();
                                cuenta.realizarTransferencia(banco.getCuenta(codigo), cantidad);
                                System.out.println("Transferencia realizada");
                                break;
                            case 5:
                                System.out.println(cuenta.listarMovimientos());
                                break;
                        }
                    } else {
                        System.out.println("No existe una cuenta con ese codigo");
                    }
                    break;
                case 3:
                    System.out.println("Cancelar cuenta: ");
                    System.out.println("Introduzca codigo: ");
                    codigo = teclado.nextLine();
                    if (banco.cancelarCuenta(codigo)) {
                        System.out.println("Cuenta cancelada");
                    } else {
                        System.out.println("No se ha podido cancelar la cuenta");
                    }
                    break;
                case 4:
                    for (Cuenta c : banco.getCuentas()) {
                        System.out.println(c.toString());
                    }
                    break;
                case 5:
                    System.out.println("Total depositos: " + banco.getTotalDepositos());
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        } while (opcion != 0);
    }
}
