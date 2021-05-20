/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suaces.modelo;

import com.suaces.sistemanominas.dao.DaoException;
import com.suaces.sistemanominas.dao.EmpleadoDao;
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

/**
 *
 * @author daw1
 */
public class EmpleadoCsv implements EmpleadoDao{
   private Path path;

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
    public void setPath(Path path) {
        this.path = path;
    }

    public EmpleadoCsv(String path) {
        this.path =Paths.get(path);
    }

    @Override
    public List<Empleado> Listar() throws DaoException{
        List<Empleado> listado=new ArrayList<>();
        String linea;
        Empleado empleado = null;
        String tipo,dni,nombre;
        Float salario = null,salariohoras;
        int horas;
        String[] tokens = null;
        try(BufferedReader fichero=Files.newBufferedReader(path))
        {
            linea=fichero.readLine();
            while(linea!=null)
            {
                tokens=linea.split(",");
                tipo=tokens[0];
                dni=tokens[1];
                nombre=tokens[2];
                switch(tipo){
                    case "EmpleadoFijo":
                        salario=Float.parseFloat(tokens[3]);
                        empleado=new EmpleadoFijo(dni, nombre, salario);
                        break;
                        
                    case "EmpleadoEventual":
                        salariohoras=Float.parseFloat(tokens[3]);
                        horas=Integer.parseInt(tokens[4]);
                        empleado=new EmpleadoEventual(dni, nombre, salario, horas);
                        break;
                }
                listado.add(empleado);
                linea=fichero.readLine();
            }
            
        }catch(NumberFormatException nu)
        {
            throw new DaoException("Formato incorrecta");
        }
        catch(NoSuchFileException io)
        {
            throw new DaoException("Error nombre del fichero");
        }catch(IOException io)
        {
            throw new DaoException("Error E / S");
        } catch (dniExepcion ex) {
          throw new DaoException(ex.getMessage());
        }
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException{
        int n=0;
        try(BufferedWriter fichero=Files.newBufferedWriter(path))
        {
            for(Empleado e :listado)
            {
               fichero.write(e.getClass().getSimpleName()+" , "+e.toString()) ;
               fichero.newLine();
               n++;
            }
            
        }catch(NumberFormatException nu)
        {
            throw new DaoException("Formato incorrecta");
        }
        catch(NoSuchFileException io)
        {
            throw new DaoException("Error nombre del fichero");
        }catch(IOException ie)
        {
            throw new DaoException("Error E / S");
        }
        return n;
    }
    
  

}
