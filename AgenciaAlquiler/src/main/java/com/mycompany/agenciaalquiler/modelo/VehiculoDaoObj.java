/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    public VehiculoDaoObj(String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); 
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
       
    @Override
        public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        Vehiculo vehiculo;
        try(InputStream inputS=Files.newInputStream(path);
                ObjectInputStream entrada = new ObjectInputStream(inputS);){
            while(inputS.available() > 0){
                vehiculo=(Vehiculo)entrada.readObject();
                lista.add(vehiculo);
            }
        }catch(EOFException eofe){
            System.out.println("Fin de fichero");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Objeto no esperado");
        }catch(FileNotFoundException fnfe){
            System.out.println("No existe el fichero");
        }catch(IOException ioe){
            System.out.println("Error de entrada/salida");
        }  
        return lista;    
        }
    
}
