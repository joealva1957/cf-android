package com.example.user.registro.Modelo;

import java.util.ArrayList;

/**
 * Created by User on 2/4/2018.
 */

public class Registro {
    ArrayList<Estudiante> listaEstudiantes;

    public Registro() {
        listaEstudiantes= new ArrayList<Estudiante>();
    }//Fin del constructor
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public String agregarEstudiante(Estudiante estudiante){
        if(estudiante!=null){
            listaEstudiantes.add(estudiante);
            return "Transporte agregado correctamente.";
        }
        return "Error al cargar Transporte";
    }//Fin del metodo agregar estudiante
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int buscarEstudiante(String carnet){
        if(carnet!=null){
            for(int i=0;i<listaEstudiantes.size();i++){
                if(listaEstudiantes.get(i).getCarnet().equalsIgnoreCase(carnet)) {
                    return i;
                }
            }
        }
        return -1;
    }//Fin del metodo buscarEstudiante
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public String modificarEstudiante(Estudiante estudiante, int posicion){
        if(estudiante!=null && posicion!=-1){
            listaEstudiantes.get(posicion).setNombre(estudiante.getNombre());
            listaEstudiantes.get(posicion).setCarnet(estudiante.getCarnet());
            listaEstudiantes.get(posicion).setCarrera(estudiante.getCarrera());
            listaEstudiantes.get(posicion).setImgUser(estudiante.getImgUser());
            return "Transporte modificado correctamente.";
        }
        return "Error al modificar Transporte.";
    }//Fin del metodo modificarEstudiante
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public String eliminarEstudiante(int posicion){
        if(posicion!=-1){
            listaEstudiantes.remove(posicion);
            return "Transporte eliminado correctamente.";
        }
        return "Error al eliminar Transporte.";
    }//Fin del metodo eliminarEstudiante
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public String getInfoEstudiante(int posicion){
        return listaEstudiantes.get(posicion).toString();
    }//Fin del metodo getInfoEstdudiante

    public Estudiante devolverEstudiante(int posicion){
        return listaEstudiantes.get(posicion);
    }

    public ArrayList<Estudiante> devolverLista(){
        return listaEstudiantes;
    }
}//Fin de la clase Registro
