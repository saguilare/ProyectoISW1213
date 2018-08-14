package org.example.proyectoisw1213;

import android.content.Context;
import android.content.Intent;


public class IntentFactory {

    public static Intent crear(Context contexto, String tipo){

        if (tipo == "email"){
            return new Intent(contexto,LoginActivity.class);
        }else if (tipo == "google"){
            return new Intent(contexto,LoginGoogleActivity.class);
        }else if (tipo == "telefono"){
            return new Intent(contexto,LoginTelefonoActivity.class);
        }else{
            return new Intent(contexto,LoginActivity.class);
        }

    }
}
