/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author daw1
 */
public class VehiculoDaoObj implements VehiculoDao {
    private Path path;

    public VehiculoDaoObj(Path path) {
        this.path = path;
    }
    
    
    @Override
    public List<Vehiculo> listar() {
        
        
        
        return null;
    }

    @Override
       public int insertar(List<Vehiculo> listado){
        int n=0;
        try(ObjectOutputStream fichero=new ObjectOutputStream(Files.newOutputStream(path))){
            for(Vehiculo e: listado){
                fichero.writeObject(e);
                n++;
            }
        } catch (IOException ex) {
         System.out.println(ex);
     }
        return n; 
    }
    
}
