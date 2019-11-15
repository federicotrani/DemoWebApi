package com.example.demowebapi;

import com.example.demowebapi.models.Alumno;
import com.example.demowebapi.models.Usuario;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataApiService {
    @GET("alumnos/ListarTodos")
    Call<List<Alumno>> GetAlumnosAll();

    @GET("alumnos/ListarSoloFavoritos")
    Call<List<Alumno>> GetAlumnosFavs();

    @GET("alumnos/ListarPorId/{id}")
    Call<Alumno> GetAlumnosById(@Path("id") int id);

    @GET("usuarios/Login")
    Call<Boolean> getLogin(
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("alumnos/Agregar")
    Call<Alumno> agregarAlumno(@Body Alumno alumno);
}
