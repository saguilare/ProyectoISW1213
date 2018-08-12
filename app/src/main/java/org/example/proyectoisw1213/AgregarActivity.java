package org.example.proyectoisw1213;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class AgregarActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST =1;
    EditText ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ubicacion = (EditText) findViewById(R.id.etUbicacion);
    }


    public void seleccionarUbicacion(View view){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try{
            startActivityForResult(builder.build(AgregarActivity.this),PLACE_PICKER_REQUEST);
        }catch(GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }catch(GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int codigoRequest, int codigoResult, Intent data){
        if (codigoRequest == PLACE_PICKER_REQUEST){
            if(codigoResult == RESULT_OK){
                Place place = PlacePicker.getPlace(AgregarActivity.this,data);
                ubicacion.setText(place.getAddress());
            }
        }
    }
}
