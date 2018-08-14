package org.example.proyectoisw1213;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCorreo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearInt("email");
            }
        });

        findViewById(R.id.btnGoggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearInt("google");
            }
        });

        findViewById(R.id.btnTel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearInt("telefono");
            }
        });

    }


    public void crearInt(String tipo){
        Intent intent = IntentFactory.crear(this, tipo);
        startActivity(intent);
    }



}
