package org.example.proyectoisw1213;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferenciasFragment extends PreferenceFragment {

    // Esto se copio del slide
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
    //fin copia

}
