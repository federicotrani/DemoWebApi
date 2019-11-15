package com.example.demowebapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demowebapi.GetDataApiService;
import com.example.demowebapi.R;
import com.example.demowebapi.RetrofitClientInstance;
import com.example.demowebapi.models.Alumno;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAlumnoActivity extends AppCompatActivity {
    private List<Alumno> listaAlumnos;
    private AlumnoAdapter adapter;
    private ListView lsvListaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumno);

        getSupportActionBar().setTitle("Lista de Alumnos");

        this.findViewsById();

        // inicializamos LISTA
        this.listaAlumnos = new ArrayList<Alumno>();
        // llenamos LISTA con consulta al API
        this.cargarListaApiCompleta();
        // asignamos lista al Adapter para armar la VISTA
        adapter = new AlumnoAdapter(listaAlumnos);
        // asignamos el adapter al LISTVIEW
        lsvListaAlumnos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alumnos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.action_lista_alumno_agregar:
                Intent intent = new Intent(this,DetalleAlumnoActivity.class);
                startActivity(intent);
                break;
            case R.id.action_lista_alumno_refrescar:
                this.cargarListaApiCompleta();
                adapter.notifyDataSetChanged();
                break;
            case R.id.action_lista_alumno_favoritos:
                this.cargarListaApiFavoritos();
                adapter.notifyDataSetChanged();
                break;
        }

        return true;
    }

    private void findViewsById(){
        lsvListaAlumnos = findViewById(R.id.lsvListaAlumnos);

        lsvListaAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaAlumnoActivity.this, DetalleAlumnoActivity.class);
                intent.putExtra("KEY_ID_ALUMNO", (int)adapter.getItemId(position));
                startActivity(intent);
            }
        });
    }


    private void cargarListaApiCompleta(){

        final List<Alumno> lista = new ArrayList<Alumno>();

        GetDataApiService api = RetrofitClientInstance.getRetrofitInstance().create(GetDataApiService.class);
        Call<List<Alumno>> call = api.GetAlumnosAll();

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                for(Alumno alumno : response.body()){
                    lista.add(alumno);
                }

                cargarVistaListaAlumnos(lista);
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Toast.makeText(ListaAlumnoActivity.this,"Error, algo salio mal",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cargarListaApiFavoritos(){

        final List<Alumno> lista = new ArrayList<Alumno>();

        GetDataApiService api = RetrofitClientInstance.getRetrofitInstance().create(GetDataApiService.class);
        Call<List<Alumno>> call = api.GetAlumnosFavs();

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                for(Alumno alumno : response.body()){
                    lista.add(alumno);
                    // listaAlumnos.add(alumno);
                }

                cargarVistaListaAlumnos(lista);
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Toast.makeText(ListaAlumnoActivity.this,"Error, algo salio mal",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cargarListaDesdeApiDummy(){
        listaAlumnos.clear();

        listaAlumnos.add(new Alumno("Luis Perez","luis@gmail.com","Belgrano 556",1,0,""));
        listaAlumnos.add(new Alumno("Maria Soto","maria@gmail.com","Las Herras 739",1,0,""));
        listaAlumnos.add(new Alumno("Esteban Suarez","esteban@gmail.com","Congreso 421",1,0,""));

    }

    private void cargarVistaListaAlumnos(List<Alumno> lista){
        // asignamos lista al Adapter para armar la VISTA
        adapter = new AlumnoAdapter(lista);
        // asignamos el adapter al LISTVIEW
        lsvListaAlumnos.setAdapter(adapter);
    }
}
