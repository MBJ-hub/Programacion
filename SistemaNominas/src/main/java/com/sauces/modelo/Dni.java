/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author daw1
 */
public class Dni implements Serializable,Comparable<Dni>{

    private static final long serialVersionUID = 3997526007634875572L;
    private String dni;

    public Dni(String dni) throws dniExepcion {
        if(!esValido(dni))
        {
            throw new dniExepcion("DNI INCORRECTO");
        }
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.dni);
        return hash;
    }

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
        final Dni other = (Dni) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) throws dniExepcion {
         if(!esValido(dni))
        {
            throw new dniExepcion("DNI INCORRECTO");
        }
        this.dni = dni;
    }

    @Override
    public String toString() {
        return dni;
    }

    @Override
    public int compareTo(Dni dni) {
      return this.dni.compareTo(dni.dni);
    }
    private boolean esValido(String dni)
    {
        boolean esValido=false;
        int resto;
        int s;
        char letra;
        String letras="TRWAGMYFPDXBNJZSQVHLCKE";
        String expreg="([0-9]{8})([A-Z])";
        Pattern p =Pattern.compile(expreg);
        Matcher m =p.matcher(dni);
        
       if(m.matches())
       {   s=Integer.parseInt(m.group(1));
           resto=s % 23;
           letra=letras.charAt(resto);
           if(letra==dni.charAt(8))
           {
               esValido=true;
           }
       }
        return esValido;
    }


}
