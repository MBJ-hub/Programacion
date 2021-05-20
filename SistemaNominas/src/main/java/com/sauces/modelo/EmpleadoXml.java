/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import com.suaces.sistemanominas.dao.DaoException;
import com.suaces.sistemanominas.dao.EmpleadoDao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.DOMException;

/**
 *
 * @author daw1
 */
public class EmpleadoXml implements EmpleadoDao {

    private Path path;

    public EmpleadoXml(String path) {
        this.path = Paths.get(path);
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public Path getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Empleado> Listar() throws DaoException {
        List<Empleado> listado = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);

        try ( BufferedReader fichero = Files.newBufferedReader(path);) {
            listado = (List<Empleado>) xstream.fromXML(fichero);
        } catch (StreamException str) {
            throw new DaoException("formato archivo incorrecto ");
        }catch (IOException ex) {
           throw new DaoException("Error E / S");
        }
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        int n = 0;
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);

        try ( BufferedWriter fichero = Files.newBufferedWriter(path)) {
            xstream.toXML(listado, fichero);
            n = listado.size();
        }catch(NoSuchFileException io)
        {
            throw new DaoException("Error nombre del fichero");
        }
        catch (IOException ex) {
            throw new DaoException("Error E / S");
        }
        return n;
    }

}
