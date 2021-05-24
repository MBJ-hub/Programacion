/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.util.Objects;
/**
 *
 * @author daw1
 */
public class Matricula {
    private String matricula;

    public Matricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return super.toString() + matricula;
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
        final Matricula other = (Matricula) obj;
        return Objects.equals(this.matricula, other.matricula);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.matricula);
        return hash;
    }
    
    public int compareTo(Matricula m){
        return this.matricula.compareTo(m.matricula);    
    }
    
    private boolean esValida(String matricula) {
        boolean esValida = false;
        
        if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z && [^AEIOUQ]]{3}$")){
        return true;
        }else{
            return false;
        } 
    }
    
}
