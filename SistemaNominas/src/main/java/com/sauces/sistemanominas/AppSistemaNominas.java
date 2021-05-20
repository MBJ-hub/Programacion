/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.sistemanominas;

import com.sauces.modelo.EmpleadoObj;
import com.sauces.modelo.dniExepcion;
import com.sauces.modelo.EmpleadoEventual;
import com.sauces.sistemanominas.dao.EmpleadoDao;
import com.sauces.modelo.SistemaNominas;
import com.sauces.modelo.EmpleadoCsv;
import com.sauces.modelo.EmpleadoFijo;
import com.sauces.modelo.Empleado;
import com.sauces.modelo.EmpleadoXml;
import com.sauces.sistemanominas.dao.DaoException;
import com.sauces.modelo.EmpleadoJson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;
import java.util.Scanner;
import java.time.Duration;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Corregir error equals
 * @author daw1
 */
public class AppSistemaNominas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        SistemaNominas sn = new SistemaNominas();
        int opcion1 = 0, opcion2 = 0,hora;
        EmpleadoDao empleadoDao=null;
        String nombre,dni,nombrearchivo=null,extension=null,usuario,pass;
        float salario,salarioHora;
        Empleado empleado=null;
        LocalDate fechaentrada,fechasalida;
        LocalTime horaentrada,horasalida;
        Properties properties = new Properties();
        Properties properties2 = new Properties();
        Duration use;
        String propuser="",proppass="";
        
        int posicionpunto;
        int n;
        
        
          try ( InputStream fichero = ClassLoader.getSystemClassLoader().getResourceAsStream("acceso.properties");
                 ) {
            properties.load(fichero);
            propuser=properties.getProperty("usuario");
            proppass=properties.getProperty("password");
           
        } catch (IOException ioe) {
            System.out.println("Error de E/S");
           
        }
         
        System.out.println("Introduzca el usuario");
        usuario=teclado.nextLine();
        System.out.println("Introduzca el password");
        pass=teclado.nextLine();
        
        if(usuario.equals(propuser)&&pass.equals(proppass)){
            fechaentrada=LocalDate.now();
            horaentrada=LocalTime.now();
        
        do {
            try {
                System.out.println("1-Crear empleado fijo");
                System.out.println("2-Crear empleado eventual");
                System.out.println("3-Consultar empleado");
                System.out.println("4-Eliminar empleado");
                System.out.println("5-Listar empleados");
                System.out.println("6-Listar empleados por sueldo");
                System.out.println("7-Consultar total salarios");
                System.out.println("8-Listar empleados fijos");
                System.out.println("9-Guardar empleados ");
                System.out.println("10-Cargar empleados ");
                System.out.println("0-Salir");
                System.out.println("Introduzca opcion :");
                opcion1 = teclado.nextInt();
                 
        
                switch (opcion1) {
                    
                    case 1:
                        teclado.nextLine();
                        System.out.println("Introduzca DNI :");
                        dni = teclado.nextLine();
                        System.out.println("Introduzca nombre :");
                        nombre=teclado.nextLine();
                        System.out.println("Introduzca salario");
                        salario = teclado.nextFloat();
                        teclado.nextLine();
                        try{
                            empleado=new EmpleadoFijo(dni,nombre,salario);
                            if(sn.incluirEmpleado(empleado))
                            {
                                System.out.println("Empleado incluido en la sistema");
                            }
                            else
                            {
                                System.out.println("No se ha podido incluir al empeado el sistema");
                            }
                        }catch(dniExepcion de)
                        {
                            System.out.println(de);
                        }
                        break;
                    case 2:
                        teclado.nextLine();
                        System.out.println("Introduzca DNI :");
                        dni= teclado.nextLine();
                        System.out.println("Introduzca nombre :");
                        nombre=teclado.nextLine();
                        System.out.println("Introduzca salario por Hora");
                        salarioHora = teclado.nextFloat();
                        System.out.println("Introduzca horas trabajadas");
                        hora=teclado.nextInt();
                        teclado.nextLine();
                        try{
                            
                            
                            empleado=new EmpleadoEventual(dni, nombre, salarioHora, hora);
                            if(sn.incluirEmpleado(empleado))
                            {
                                System.out.println("Empleado incluido en la sistema");
                            }
                            else
                            {
                                System.out.println("No se ha podido incluir al empeado el sistema");
                            }
                        }catch(dniExepcion dr)
                        {
                            System.out.println(dr.getMessage());
                        }
                        
                        break;
                    case 3:
                        teclado.nextLine();
                        System.out.println("Introduzca DNI del empleado :");
                        dni=teclado.nextLine();
                        empleado=sn.getEmpleado(dni);
                        if(empleado!=null)
                        {
                            System.out.println(empleado);
                        }
                        else
                        {
                            System.out.println("No exsiste un empleado con ese DNI");
                        }
                        
                        break;
                    case 4:
                        teclado.nextLine();
                        System.out.println("Introduzca DNI del empleado :");
                        dni=teclado.nextLine();
                        empleado=sn.getEmpleado(dni);
                        if(empleado!=null)
                        {
                            sn.eliminarEmpleado(empleado);
                            System.out.println("Empleado Eliminado del sistema");
                            
                        }
                        else
                        {
                            System.out.println("No exsiste un empleado con ese DNI");
                        }
                        
                        break;
                    case 5:
                        for(Empleado e :sn.listarEmpleados())
                        {
                            System.out.println(e);
                        }
                        break;
                    case 6:
                        for(Empleado e :sn.ListarEmpleadosPorSueldo())
                        {
                            System.out.println(e);
                        }
                        
                        break;
                    case 7:System.out.println("TOTAL :"+sn.getTotalSalarios());break;
                    case 8:System.out.println("Listado de empleados fijos");
                    for(Empleado e :sn.listarEmpleados())
                    { if(e instanceof EmpleadoFijo)
                    {
                        System.out.println(e);
                    }
                    
                    }
                    break;
                    
                    case 9:
                        teclado.nextLine();
                        System.out.println("Introduzca nombre Archivo");
                        nombrearchivo=teclado.nextLine();
                        posicionpunto=nombrearchivo.lastIndexOf('.');
                        if(posicionpunto!=-1)
                        {
                            extension=nombrearchivo.substring(posicionpunto);
                        }
                        
                        switch(extension)
                        {
                            case ".csv":
                                empleadoDao=new EmpleadoCsv(nombrearchivo);
                                break;
                            case ".obj":
                                empleadoDao=new EmpleadoObj(nombrearchivo);
                                break;
                            case ".json":
                                empleadoDao=new EmpleadoJson(nombrearchivo);
                                break;
                            case ".xml":
                                empleadoDao=new EmpleadoXml(nombrearchivo);
                                break;
                            default: System.out.println("Extencion incorrecta");
                        }
                        if(empleadoDao!=null)
                        {
                            try{
                                sn.setEmpleadoDao(empleadoDao);
                                n=sn.guardarEmpleados();
                                System.out.println("se ha almacenado "+n+" empleados");
                            }catch(DaoException ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                            
                        }
                        break;
                    case 10:
                        teclado.nextLine();
                        System.out.println("Introduzca nombre Archivo");
                        nombrearchivo=teclado.nextLine();
                        posicionpunto=nombrearchivo.lastIndexOf('.');
                        if(posicionpunto!=-1)
                        {
                            extension=nombrearchivo.substring(posicionpunto);
                        }
                        switch(extension)
                        {
                            case ".csv":
                                empleadoDao=new EmpleadoCsv(nombrearchivo);
                                break;
                            case ".obj":
                                empleadoDao=new EmpleadoObj(nombrearchivo);
                                break;
                            case ".json":
                                empleadoDao=new EmpleadoJson(nombrearchivo);
                                break;
                            case ".xml":
                                empleadoDao=new EmpleadoXml(nombrearchivo);
                                break;
                            default: System.out.println("Extencion incorrecta");
                            
                        }
                        if(empleadoDao!=null)
                        {
                            try{
                                sn.setEmpleadoDao(empleadoDao);
                                n=sn.cargarEmpleados();
                                System.out.println("se han cargado  "+n+"  empleados");
                            }catch(DaoException ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                            
                        }
                        break;
                    case 0:System.out.println("Adios");
                    fechasalida=LocalDate.now();
                    horasalida=LocalTime.now();
                    use=Duration.between(LocalDateTime.of(fechaentrada,horaentrada),LocalDateTime.of(fechasalida,horasalida));
                    System.out.println("Fecha entrada :"+fechaentrada);
                     System.out.println("hora salida :"+horasalida);
                     System.out.println(use);
                       
                      try ( BufferedWriter wr = Files.newBufferedWriter(Paths.get("Config.properties"))) {
                       properties2.setProperty("fechaUltimoAcceso" ,fechaentrada.toString() ); 
                       properties2.setProperty("fechaUltimoAcceso" ,fechaentrada.toString() );
                       
                       properties.store(wr, "se ha cambiado dates");
                        } catch (IOException ioe) {
                            System.out.println("Error de E/S");
                        } 
                             break;
                    default: System.out.println("ERROR EN LA SELECCION");
                }
            } catch (dniExepcion ex) {
                Logger.getLogger(AppSistemaNominas.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (opcion1 != 0);
       }
    }

}
