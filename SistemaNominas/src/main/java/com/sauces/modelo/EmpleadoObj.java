/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suaces.modelo;

import com.suaces.sistemanominas.dao.DaoException;
import com.suaces.sistemanominas.dao.EmpleadoDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class EmpleadoObj implements EmpleadoDao {

    private Path path;

    public EmpleadoObj(String path) {
        this.path = Paths.get(path);
    }

    public Path getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public List<Empleado> Listar() throws DaoException {
        List<Empleado> listado = new ArrayList<>();
        Empleado e = null;
        try ( InputStream is = Files.newInputStream(path);  ObjectInputStream fichero = new ObjectInputStream(is)) {
            while (is.available() > 0) {
                e = (Empleado) fichero.readObject();
                listado.add(e);
            }

        } catch (NoSuchFileException io) {
            throw new DaoException("Error nombre del fichero");
        } catch (NumberFormatException nu) {
            throw new DaoException("Formato incorrecta");
        } catch (IOException ex) {
            throw new DaoException("Error E / S");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        int n = 0;
        try ( ObjectOutputStream fichero = new ObjectOutputStream(Files.newOutputStream(path))) {
            for (Empleado e : listado) {
                fichero.writeObject(e);
                n++;
            }

        } catch (NoSuchFileException io) {
            throw new DaoException("Error nombre del fichero");
        } catch (NumberFormatException nu) {
            throw new DaoException("Formato incorrecta");
        } catch (IOException ex) {
            throw new DaoException("Error E / S");
        }

        return n;
    }
}
