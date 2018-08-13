package org.example.proyectoisw1213;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class AgregarActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST =1;
    int PHOTO_PICKER_REQUEST =2;

    EditText ubicacion;
    Button btnCargarImagen;
    RatingBar rb;
    Float rating;
    Bitmap bitmap;

    private FirebaseAuth mAuth;
    private AdminBD data;
    private SQLiteDatabase conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        data= new AdminBD(this,"datos",null,1);
        //this.deleteDatabase("datos");

        ubicacion = (EditText) findViewById(R.id.etGps);
        btnCargarImagen = (Button)findViewById(R.id.btnAgregarFoto);
        rb = (RatingBar)findViewById(R.id.ratingBar);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar,float rate,boolean fromUser){
                rating = rate;

            }
        });
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser()!=null){
            FirebaseUser fbuser = mAuth.getCurrentUser();
            Toast.makeText(AgregarActivity.this, "Su accion se ejecuto con exito"+fbuser.getEmail(), Toast.LENGTH_SHORT).show();

        }


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
            //EditText etFoto = (EditText)findViewById(R.id.etFoto);
            //etFoto.setText(targetUri.toString());

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

    public void insertar(View view) {
        try{
            conn=data.getWritableDatabase();

            byte[] foto = getBitmapAsByteArray(bitmap);

            String usuario = "testuser";
            String lugar = ((EditText)findViewById(R.id.etNombre)).getText().toString();
            String tipo = ((EditText)findViewById(R.id.etTipo)).getText().toString();
            String ubicacion = ((EditText)findViewById(R.id.etUbicacion)).getText().toString();
            String gps = ((EditText)findViewById(R.id.etGps)).getText().toString();

            String comentario = ((EditText)findViewById(R.id.etComentarios)).getText().toString();


            ContentValues registro = new ContentValues();
            registro.put("user",usuario);
            registro.put("lugar",lugar);
            registro.put("tipo",tipo);
            registro.put("ubicacion",ubicacion);
            registro.put("gps",gps);
            registro.put("foto",foto);
            registro.put("comentario",comentario);
            registro.put("rating",rating);

            conn.insert("favoritos",null,registro);
            conn.close();
            Toast.makeText(AgregarActivity.this, "Su accion se ejecuto con exito", Toast.LENGTH_SHORT).show();

            //((EditText)findViewById(R.id.etCodigo)).setText("");
            //((EditText)findViewById(R.id.etDescripcion)).setText("");
            //((EditText)findViewById(R.id.etPrecio)).setText("");
        }catch(Exception e){
            Toast.makeText(AgregarActivity.this, "Error: Su accion no se ejecuto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}

