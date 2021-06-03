/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class VehiculoDaoXml implements VehiculoDao{
Path path;

    public VehiculoDaoXml(String path) {
        this.path = Paths.get(path);
    }
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    @Override
     public List<Vehiculo> listar() {
        List<Vehiculo> listado=new ArrayList<>();
        XStream xstream=new XStream(new DomDriver());
        
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(Turismo.class);
        xstream.allowTypeHierarchy(Furgoneta.class);
        try(BufferedReader archivo=Files.newBufferedReader(path)){
            listado=(List<Vehiculo>)xstream.fromXML(archivo);
        } catch (NoSuchFileException nsfe){
            try {
                throw new DaoException("Error en el nombre del fichero");
            } catch (DaoException ex) {
                Logger.getLogger(VehiculoDaoXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(StreamException se){
            try {
                throw new DaoException("Formato de datos incorrecto");
            } catch (DaoException ex) {
                Logger.getLogger(VehiculoDaoXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (IOException ex) {
            Logger.getLogger(VehiculoDaoXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) {
       int n=0;
        XStream xstream=new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(Furgoneta.class);
        xstream.allowTypeHierarchy(Turismo.class);
        try(BufferedWriter fichero=Files.newBufferedWriter(path)){
        xstream.toXML(vehiculos,fichero);
                n=vehiculos.size();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return n; 
    }
    
}
