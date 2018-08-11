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
    }

    public void AbrirLoginEmail(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void AbrirLoginTelefono(View view){
        Intent intent = new Intent(this,LoginTelefonoActivity.class);
        startActivity(intent);
    }

    public void AbrirLoginGoogle(View view){
        Intent intent = new Intent(this,LoginGoogleActivity.class);
        startActivity(intent);
    }
}
