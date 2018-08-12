package org.example.proyectoisw1213;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminBD extends SQLiteOpenHelper {
    public AdminBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Se ejecuta sólo una vez... ojo con no cambiar el nombre o la versión si se hacen cambios en la estructura
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favoritos("
                + "user text, "
                + "lugar text, "
                + "tipo text, "
                + "ubicacion text, "
                + "gps text, "
                + "foto blob, "
                + "comentario text, "
                + "rating real);");

    }
    //Se ejecuta sólo si se cambia la versión de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE favoritos("
                + "user text, "
                + "lugar text, "
                + "tipo text, "
                + "ubicacion text, "
                + "gps text, "
                + "foto blob, "
                + "comentario text, "
                + "rating real);");
    }
}
