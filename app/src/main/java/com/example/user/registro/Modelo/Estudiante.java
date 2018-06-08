package com.example.user.registro.Modelo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2/4/2018.
 */

public class Estudiante implements Parcelable {
    private String nombre;
    private String carnet;
    private String carrera;
    private Uri imgUser;

    public Estudiante(String nombre, String carnet, String carrera,Uri imgUser) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.carrera = carrera;
        this.imgUser = imgUser;
    }

    public Estudiante() {
        nombre="";
        carnet="";
        carrera="";
        imgUser=null;
    }

    protected Estudiante(Parcel in) {
        nombre = in.readString();
        carnet = in.readString();
        carrera = in.readString();
        imgUser = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };

    public Uri getImgUser() {
        return imgUser;
    }

    public void setImgUser(Uri imgUser) {
        this.imgUser = imgUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante " +
                "nombre='" + nombre + '\'' +
                ", carnet='" + carnet + '\'' +
                ", carrera='" + carrera + '\'' +
                ", imgUser=" + imgUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(carnet);
        dest.writeString(carrera);
        dest.writeParcelable(imgUser, flags);
    }
}//Fin de la clase
