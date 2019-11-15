package com.example.demowebapi.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demowebapi.R;
import com.example.demowebapi.models.Alumno;

import java.util.List;
import java.util.zip.Inflater;

public class AlumnoAdapter extends BaseAdapter {

    List<Alumno> listaAlumnos;

    public AlumnoAdapter(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public int getCount() {
        return listaAlumnos.size();
    }

    @Override
    public Alumno getItem(int position) {
        return listaAlumnos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaAlumnos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista;

        if(convertView == null){
            vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_alumnos,null);
        }else{
            vista = convertView;
        }

        Alumno alumno = getItem(position);

        TextView txtDetalleAlumnoEmail = vista.findViewById(R.id.txtDetalleAlumnoEmail);
        TextView txtDetalleAlumnoNombre = vista.findViewById(R.id.txtDetalleAlumnoNombre);
        TextView txtDetalleAlumnoDomicilio = vista.findViewById(R.id.txtDetalleAlumnoDomicilio);
        ImageView imvDetalleAlumnoFavorito = vista.findViewById(R.id.imvDetalleAlumnoFavorito);

        txtDetalleAlumnoNombre.setText(alumno.getNombre());
        txtDetalleAlumnoDomicilio.setText(alumno.getDomicilio());
        txtDetalleAlumnoEmail.setText(alumno.getEmail());

        if(alumno.getFavorito()==1){
            imvDetalleAlumnoFavorito.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else{
            imvDetalleAlumnoFavorito.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        return vista;
    }
}
