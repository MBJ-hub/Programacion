/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.modelo;

import com.suaces.sistemanominas.dao.DaoException;
import com.suaces.sistemanominas.dao.EmpleadoDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class EmpleadoJson implements EmpleadoDao {

    private Path path;

    public EmpleadoJson(String path) {
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
        //deberos definir un tipo:
        Type tipo = new TypeToken<List<Empleado>>() {}.getType();
        //En el caso de que haya herencia habrá que registrar las clases hijas:
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");
        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "EmpleadoFijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "EmpleadoEventual");
        //crearemos un objeto de la clase GsonBuilder y lo configuraremos
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        //creamos un objeto de la clase Gson
        Gson gson = builder.create();

        try ( BufferedReader fichero = Files.newBufferedReader(path)) {
            listado = gson.fromJson(fichero, tipo);
        }catch(NoSuchFileException io)
        {
            throw new DaoException("Error nombre del fichero");
        }
        catch(JsonSyntaxException js)
        {
           throw new DaoException("formato archivo incorrecto");  
        }
        catch (IOException ex) {
          throw new DaoException("Error E / S");
        }

        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        int n = 0;
        //deberos definir un tipo:
        Type tipo = new TypeToken<List<Empleado>>() {
        }.getType();
        //En el caso de que haya herencia habrá que registrar las clases hijas:
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");
        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "EmpleadoFijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "EmpleadoEventual");
        //crearemos un objeto de la clase GsonBuilder y lo configuraremos
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapterFactory(empleadoAdapter);
        //creamos un objeto de la clase Gson
        Gson gson = builder.create();

        try ( BufferedWriter fichero = Files.newBufferedWriter(path)) {
            gson.toJson(listado, tipo, fichero);
            n = listado.size();
        }catch(NoSuchFileException io)
        {
            throw new DaoException("Error nombre del fichero");
        }
        catch(JsonSyntaxException js)
        {
           throw new DaoException("formato archivo incorrecto");  
        }
        catch (IOException ex) {
          throw new DaoException("Error E / S");
        }
        return n;
    }

}
