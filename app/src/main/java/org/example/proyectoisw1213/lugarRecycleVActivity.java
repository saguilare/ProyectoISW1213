package org.example.proyectoisw1213;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class lugarRecycleVActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private AdminBD data;
    private SQLiteDatabase conn;

    String usuario;
    String lugar ;
    String tipo ;
    String ubicacion ;
    String gps ;
    Bitmap foto;
    String comentario ;
    Float  rating;
    List<Lugar> lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugarrecycleview);
        data= new AdminBD(this,"datos",null,1);


        // Inicializar
        List<Lugar> items = new ArrayList<>();

        try{
            obtenerLugares();
            if (lugares != null)

            for (Lugar item : lugares) {
                items.add(new Lugar(R.drawable.example_appwidget_preview, item.getlugar().toString(), 230));
            }



        }catch(Exception e){
            Toast.makeText(lugarRecycleVActivity.this, "Error al cargar datos de la BD", Toast.LENGTH_SHORT).show();
        }





        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new LugarAdapter(items);
        recycler.setAdapter(adapter);


    }

    private void deleteDB(){
        conn=data.getReadableDatabase();
        conn.rawQuery("delete from favoritos ",null);
        conn.close();
    }
    public void obtenerLugares() {
        conn=data.getReadableDatabase();


        //get user  from session
        //int codigo = Integer.parseInt(((EditText)findViewById(R.id.etCodigo)).getText().toString());
        usuario = "testuser";
        //Cursor fila = conn.rawQuery("select * from favoritos where user="+usuario.toString(),null);
        Cursor fila = conn.rawQuery("select * from favoritos ",null);
        if (fila.moveToFirst()){

            lugares = new ArrayList<Lugar>();
            do {
                // Passing values
                String u = fila.getString(0);
                lugar = fila.getString(1);
                tipo = fila.getString(2);
                ubicacion = fila.getString(3);
                gps = fila.getString(4);
                byte[] bfoto = fila.getBlob(5);
                comentario = fila.getString(6);
                rating = fila.getFloat(7);

                foto = BitmapFactory.decodeByteArray(bfoto, 0, bfoto.length);
                // Do something Here with values
                Lugar l = new Lugar(u, lugar, tipo,  ubicacion, gps, foto, comentario, rating  );
                lugares.add(l);

            } while(fila.moveToNext());
            conn.close();
        }else {
            Toast.makeText(lugarRecycleVActivity.this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }

    }
}

