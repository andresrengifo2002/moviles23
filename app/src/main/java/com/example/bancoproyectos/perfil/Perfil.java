package com.example.bancoproyectos.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bancoproyectos.MainActivitylistado;
import com.example.bancoproyectos.MenuPrincipal;
import com.example.bancoproyectos.R;
import com.example.bancoproyectos.api.PerfilApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {


    private TextView T1, T2, T3;
    private Retrofit retrofit;
    private List<Perfils> proyectosList = new ArrayList<>();
    private static final String TAG = "perfiles";




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        T2=findViewById(R.id.text2);
        Intent intent=getIntent();
        String accessT=intent.getStringExtra("token");
        T2.setText(accessT);


        T1 = (TextView) findViewById(R.id.text1);
        T2 = (TextView) findViewById(R.id.text2);
        T3 = (TextView) findViewById(R.id.text3);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://lexa2334.pythonanywhere.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();

        // Your other code goes here
    }

    private void obtenerDatos() {
        PerfilApiService service = retrofit.create(PerfilApiService.class);

        Call<List<Perfils>> productsRespuestaCall = service.groupList();
        productsRespuestaCall.enqueue(new Callback<List<Perfils>>() {
            @Override
            public void onResponse(Call<List<Perfils>> call, Response<List<Perfils>> response) {
                if (response.isSuccessful()) {
                    List<Perfils> perfil = response.body();
                    if (perfil != null) {
                        proyectosList.addAll(perfil);
                        for (Perfils p : perfil) {
                            Log.e(TAG, "products: " + p.getUsuario());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Perfils>> call, Throwable t) {
                // Handle failure here
            }
        });



    }

    public void onClick(View view) {


            Intent intent = new Intent(Perfil.this, MainActivitylistado.class);
            startActivity(intent);
        }
    }
