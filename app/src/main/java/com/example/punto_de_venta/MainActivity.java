package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText alt_cod, alt_nom, alt_preU, alt_preP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alt_nom = (EditText) findViewById(R.id.alNombre);
        alt_cod = (EditText) findViewById(R.id.alCodigo);
        alt_preP = (EditText) findViewById(R.id.alPrecioP);
        alt_preU = (EditText) findViewById(R.id.alPrecioU);
    }

    public void alta (View view){

        String Snom = alt_nom.getText().toString();
        String Scod = alt_cod.getText().toString();
        String ipreP = alt_preP.getText().toString();
        String ipreU = alt_preU.getText().toString();

        if(Snom.isEmpty()||Scod.isEmpty()||ipreP.isEmpty()||ipreU.isEmpty()){
            if(Snom.isEmpty())
                alt_nom.setError("Valor no ingresado");
            if(Scod.isEmpty())
                alt_cod.setError("Valor no ingresado");
            if(ipreP.isEmpty())
                alt_preP.setError("Valor no ingresado");
            if(ipreU.isEmpty())
                alt_preU.setError("Valor no ingresado");
        }else {
            BaseD db = new BaseD(this,"Base1",null,1);
            SQLiteDatabase conex = db.getWritableDatabase();
                ContentValues reg = new ContentValues();
                reg.put("codigo", Scod);
                reg.put("nombre", Snom);
                reg.put("precioprov", ipreP);
                reg.put("preciouni", ipreU);

                conex.insert("productos", null, reg);
                conex.close();
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_LONG).show();
                limp();
        }
    }

    public void limp (){
        alt_nom.setText("");
        alt_cod.setText("");
        alt_preP.setText("");
        alt_preU.setText("");
    }
}