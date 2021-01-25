package com.example.punto_de_venta;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseD extends SQLiteOpenHelper{

    private static final int VER_BD = 1;
    private static final String NOM_BD = "Base1";
    private static final String TABLA = "CREATE TABLE productos(codigo INTEGER  PRIMARY KEY, nombre TEXT, precioprov TEXT, preciouni TEXT)";


    public BaseD(@Nullable Context context,
                 @Nullable String name,
                 @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOM_BD, null, VER_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS productos");
        onCreate(db);
    }

}



