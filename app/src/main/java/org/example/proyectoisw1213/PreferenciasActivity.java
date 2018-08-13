package org.example.proyectoisw1213;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();

    }
}
