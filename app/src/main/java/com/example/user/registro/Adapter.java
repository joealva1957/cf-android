package com.example.user.registro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.registro.Modelo.Estudiante;

import java.util.ArrayList;

/**
 * Created by ucr on 13/4/2018.
 */

public class Adapter extends BaseAdapter {
    //Definimos las variables del contexto y de la lista de estudiantes
    Context context;
    ArrayList<Estudiante> lista;

    public Adapter(Context context, ArrayList<Estudiante> lista) {
        this.context = context;
        this.lista = lista;
    }

    //Este método nos proporciona el tamaño de los elementos que contiene el arrayList
    @Override
    public int getCount() {
        return lista.size();
    }//Fin del getCount()

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    //Este método es el encargado de inflar o personalizar nuestra lista
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService
                    (context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lyt_item, null);
        }
        ImageView imgUser = convertView.findViewById(R.id.imgUser);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtCarnet = convertView.findViewById(R.id.txtCarnet);
        TextView txtCarrera = convertView.findViewById(R.id.txtCarrera);

        imgUser.setImageURI(lista.get(position).getImgUser());
        txtNombre.setText(lista.get(position).getNombre());
        txtCarnet.setText(lista.get(position).getCarnet());
        txtCarrera.setText(lista.get(position).getCarrera());

        return convertView;
    }
}
