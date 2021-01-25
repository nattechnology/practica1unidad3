package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText eli_cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        eli_cod = (EditText) findViewById(R.id.bajCodigo);
    }

    public void eliminar (View view){

        String icod = eli_cod.getText().toString();

        if(icod.isEmpty()){
            if(icod.isEmpty()) {
                eli_cod.setError("Valor no ingresado");
                Toast.makeText(this, "Ingresa el código", Toast.LENGTH_LONG).show();
            }
        }else {
            BaseD db = new BaseD(this,"db1",null,1);
            SQLiteDatabase conex = db.getWritableDatabase();

            if(!icod.isEmpty()) {
                Cursor fila = conex.rawQuery("select nombre, precioprov, preciouni from productos where codigo= " +icod, null);
                if(fila.moveToFirst()) {
                    conex.delete("productos", "codigo = " + icod, null);
                    conex.close();
                    eli_cod.setText("");
                    Toast.makeText(this, "Producto eliminado", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "El producto no existe ", Toast.LENGTH_LONG).show();
                    eli_cod.setText("");
                    conex.close();
                }
            }
        }
    }

    public void menu (View view){
        Toast.makeText(this, "Producto eliminado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
        startActivityForResult(intent, 0);
    }
}