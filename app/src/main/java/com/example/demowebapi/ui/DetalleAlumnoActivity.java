package com.example.demowebapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.demowebapi.GetDataApiService;
import com.example.demowebapi.R;
import com.example.demowebapi.RetrofitClientInstance;
import com.example.demowebapi.models.Alumno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleAlumnoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtDetalleNombre, edtDetalleEmail, edtDetalleDomicilio;
    private Button btnDetalleGrabar, btnDetalleCancelar;
    private Switch swtDetalleFavorito;
    private int id_alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alumno);

        this.getViewsById();

        Intent intent = getIntent();

        //Bundle bundle = intent.getExtras();
        // this.id_alumno = bundle.getInt("KEY_ID_ALUMNO",0);

        this.id_alumno = intent.getIntExtra("KEY_ID_ALUMNO",0);

        if(this.id_alumno!=0)
        {
            getSupportActionBar().setTitle("Modificando Alumno #"+id_alumno);

            this.cargarItemDesdeApi(id_alumno);
        }

    }

    private void cargarItemDesdeApi(int id_alumno) {


        GetDataApiService api = RetrofitClientInstance.getRetrofitInstance().create(GetDataApiService.class);
        Call<Alumno> call = api.GetAlumnosById(id_alumno);

        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Alumno alumno = response.body();

                cargarVistaDetalleAlumno(alumno);
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                Toast.makeText(DetalleAlumnoActivity.this, "Error, algo salió mal!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarVistaDetalleAlumno(Alumno alumno){
        edtDetalleNombre.setText(alumno.getNombre());
        edtDetalleEmail.setText(alumno.getEmail());
        edtDetalleDomicilio.setText(alumno.getDomicilio());

        swtDetalleFavorito.setChecked(alumno.getFavorito()==1?true:false);

    }

    private void getViewsById() {
        edtDetalleDomicilio = findViewById(R.id.edtDetalleDomicilio);
        edtDetalleNombre = findViewById(R.id.edtDetalleNombre);
        edtDetalleEmail = findViewById(R.id.edtDetalleEmail);

        btnDetalleCancelar = findViewById(R.id.btnDetalleCancelar);
        btnDetalleGrabar = findViewById(R.id.btnDetalleGrabar);

        btnDetalleGrabar.setOnClickListener(this);
        btnDetalleCancelar.setOnClickListener(this);

        swtDetalleFavorito = findViewById(R.id.swtDetalleFavorito);
    }

    private boolean validarDatos(Alumno alumno){


        if(edtDetalleNombre.getText().toString().isEmpty())
        {
            edtDetalleNombre.setError("Este campo es obligatorio!!");
            return false;
        }

        if(edtDetalleDomicilio.getText().toString().isEmpty())
        {
            edtDetalleDomicilio.setError("Este campo es obligatorio!!");
            return false;
        }

        if(edtDetalleEmail.getText().toString().isEmpty())
        {
            edtDetalleEmail.setError("Este campo es obligatorio!!");
            return false;
        }


        return true;
    }

    private void grabarDatos(){
        Alumno alumno = new Alumno();
        alumno.setNombre(edtDetalleNombre.getText().toString());
        alumno.setEmail(edtDetalleEmail.getText().toString());
        alumno.setDomicilio(edtDetalleDomicilio.getText().toString());
        alumno.setFavorito(swtDetalleFavorito.isChecked()?1:0);

        // TODO: falta lógica de carga de foto del alumno

        if(this.validarDatos(alumno))
        {
            // TODO: procedimiento grabado registro alumnos
            GetDataApiService api = RetrofitClientInstance.getRetrofitInstance().create(GetDataApiService.class);
            Call<Alumno> call = api.agregarAlumno(alumno);

            call.enqueue(new Callback<Alumno>() {
                @Override
                public void onResponse(Call<Alumno> call, Response<Alumno> response) {

                    if(response.isSuccessful()){
                        Toast.makeText(DetalleAlumnoActivity.this, "Status Code: "
                                +response.code(), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<Alumno> call, Throwable t) {
                    Toast.makeText(DetalleAlumnoActivity.this, "Error, algo salió mal!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDetalleCancelar:
                finish();
                break;
            case R.id.btnDetalleGrabar:
                this.grabarDatos();
                break;
        }
    }
}
