/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agenciaalquiler.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class VehiculoDaoCsv implements VehiculoDao {
    private Path path;

    public VehiculoDaoCsv(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
      
    @Override
    public List<Vehiculo> listar(){
        List<Vehiculo> vehiculos=new ArrayList<>();
        Vehiculo vehiculo=null;
        String linea;
        String matricula,grupo,tipo;
        int plazas;
        float capacidad;
        String[] tokens;
        try (BufferedReader fichero = Files.newBufferedReader(path)) {
            linea = fichero.readLine();
            while (linea != null) {
            tokens = linea.split(",");
            tipo=tokens[0];
            matricula=tokens[1];
            grupo=tokens[2];
            switch (tipo){
                case"turismo":
                    plazas=Integer.parseInt(tokens[4]);
                    vehiculo= new Turismo(matricula, Grupo.valueOf(grupo), plazas);
                    break;
                    case"furgoneta":
                        capacidad=Float.parseFloat(tokens[4]);
                        vehiculo=new Furgoneta(matricula,Grupo.valueOf(grupo), capacidad);
                        break;
            }
            vehiculos.add(vehiculo);
                linea = fichero.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return vehiculos;
    }

    @Override
        public int insertar(List<Vehiculo> listado) throws DaoException{
        int n=0;
        try(BufferedWriter fichero=Files.newBufferedWriter(path)){
            for(Vehiculo e: listado){
                fichero.write(getClass().getSimpleName()+","+e.toString());
                fichero.newLine();
                n++;
            }
        }catch(IOException ex){
            throw new DaoException(ex.toString());
        }
        return n;
    }
}
