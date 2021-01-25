package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText mod_cod, mod_nomb, mod_preP, mod_preU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mod_cod = (EditText) findViewById(R.id.modCodigo);
        mod_nomb = (EditText) findViewById(R.id.modNombre);
        mod_preP = (EditText) findViewById(R.id.modPrecioP);
        mod_preU = (EditText) findViewById(R.id.modPrecioU);
    }

    public void modificar (View view){
        BaseD db = new BaseD(this,"db1",null,1);
        SQLiteDatabase conex = db.getWritableDatabase();

        String icod = mod_cod.getText().toString();
        String Snom = mod_nomb.getText().toString();
        String ipreP = mod_preP.getText().toString();
        String ipreU = mod_preU.getText().toString();
        if(Snom.isEmpty()||icod.isEmpty()||ipreP.isEmpty()||ipreU.isEmpty()){
            if(Snom.isEmpty())
                mod_nomb.setError("Valor no ingresado");
            if(icod.isEmpty())
                mod_cod.setError("Valor no ingresado");
            if(ipreP.isEmpty())
                mod_preP.setError("Valor no ingresado");
            if(ipreU.isEmpty())
                mod_preU.setError("Valor no ingresado");
        }else {

            ContentValues reg = new ContentValues();
            reg.put("codigo", icod);
            reg.put("nombre", Snom);
            reg.put("precioprov", ipreP);
            reg.put("preciouni", ipreU);

            conex.update("productos", reg, "codigo=" + icod, null);
            conex.close();

            Toast.makeText(this, "Producto modificado ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
            startActivityForResult(intent, 0);
            finish();
        }
    }

    public void consul (View view){
        BaseD db = new BaseD(this,"Base1",null,1);
        SQLiteDatabase conex = db.getReadableDatabase();

        String icod = mod_cod.getText().toString();
        if(icod.isEmpty()){
            if(icod.isEmpty()) {
                mod_cod.setError("Valor no ingresado");
                Toast.makeText(this, "Ingresa el código", Toast.LENGTH_LONG).show();
            }
        }else {
            if (!icod.isEmpty()) {
                Cursor fila = conex.rawQuery("select nombre, precioprov, preciouni from productos where codigo=" + icod, null);
                if (fila.moveToFirst()) {
                    mod_nomb.setText(fila.getString(0));
                    mod_preP.setText(fila.getString(1));
                    mod_preU.setText(fila.getString(2));
                    conex.close();
                } else {
                    Toast.makeText(this, "El producto no existe ", Toast.LENGTH_LONG).show();
                    limp();
                    conex.close();
                }
            }
        }
    }

    public void limp (){
        mod_nomb.setText("");
        mod_cod.setText("");
        mod_preP.setText("");
        mod_preU.setText("");
    }

    public void limpiar(View view){
        limp();
    }
}