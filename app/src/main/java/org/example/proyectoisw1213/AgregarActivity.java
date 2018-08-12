package org.example.proyectoisw1213;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.FileNotFoundException;

public class AgregarActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST =1;
    int PHOTO_PICKER_REQUEST =2;

    EditText ubicacion;
    Button btnCargarImagen;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ubicacion = (EditText) findViewById(R.id.etGps);
        btnCargarImagen = (Button)findViewById(R.id.btnAgregarFoto);


    }

    public void seleccionarFoto(View view){
        try{
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PHOTO_PICKER_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
        }
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
        }else if(codigoRequest == PHOTO_PICKER_REQUEST){
            Uri targetUri = data.getData();
            EditText etFoto = (EditText)findViewById(R.id.etFoto);
            etFoto.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                ImageView ivFoto = (ImageView)findViewById(R.id.ivFoto);
                ivFoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
