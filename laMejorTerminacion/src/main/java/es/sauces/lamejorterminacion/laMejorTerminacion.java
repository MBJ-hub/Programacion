/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.lamejorterminacion;

/**
 *
 * @author daw1
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class laMejorTerminacion {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int casos, max;
        String[] boletos;
        HashMap<Character, Integer> registro;
        char terminacion = '0';
        boolean varios;

        while (true) {

            casos = s.nextInt();
            if (casos == 0) {
                break;
            }

            boletos = new String[casos];
            registro = new HashMap<>();

            for (int i = 0; i < casos; i++) {
                boletos[i] = s.next();
                if (registro.containsKey(boletos[i].charAt(4))) {
                    registro.put(boletos[i].charAt(4), registro.get(boletos[i].charAt(4)) + 1);
                } else {
                    registro.put(boletos[i].charAt(4), 1);
                }
            }
            max = 0;
            varios = false;
            for (Map.Entry<Character, Integer> entry : registro.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    terminacion = entry.getKey();
                    varios = false;
                } else if (entry.getValue() == max) {
                    varios = true;
                }
            }

            System.out.println((varios) ? "VARIAS" : terminacion);
        }
    }
}
