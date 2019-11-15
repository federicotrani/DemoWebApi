package com.example.demowebapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demowebapi.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtLoginEmail, edtLoginPassword;
    Button btnLoginIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Control de Ingreso");

        findViewsById();
    }

    private void findViewsById(){
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);

        btnLoginIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ListaAlumnoActivity.class);
                startActivity(intent);
            }
        });
    }
}
