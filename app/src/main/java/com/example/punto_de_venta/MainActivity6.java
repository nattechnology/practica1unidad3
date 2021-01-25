package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {

    EditText bus_cod;
    TextView bus_nom, bus_preP, bus_preU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        bus_cod = (EditText) findViewById(R.id.busCodigo);
        bus_nom = (TextView) findViewById(R.id.busNombre);
        bus_preP = (TextView) findViewById(R.id.busPrecioP);
        bus_preU = (TextView) findViewById(R.id.busPrecioU);
    }

    public void consul (View view){
        BaseD db = new BaseD(this,"Base1",null,1);
        SQLiteDatabase conex = db.getReadableDatabase();

       //int icod = Integer.parseInt(bus_cod.getText().toString());
       String icod = bus_cod.getText().toString();

        if(icod.isEmpty()){
            if(icod.isEmpty()) {
                bus_cod.setError("Valor no ingresado");
                Toast.makeText(this, "Ingresa el código", Toast.LENGTH_LONG).show();
            }
        }else {
            if (!icod.isEmpty()) {
                Cursor fila = conex.rawQuery("select nombre, precioprov, preciouni from productos where codigo= " + icod, null);
                if (fila.moveToFirst()) {
                    bus_nom.setText(fila.getString(0));
                    bus_preP.setText(fila.getString(1));
                    bus_preU.setText(fila.getString(2));
                    conex.close();
                    bus_cod.setText("");
                } else {
                    Toast.makeText(this, "El producto no existe ", Toast.LENGTH_LONG).show();
                    bus_nom.setText("");
                    bus_preP.setText("");
                    bus_preU.setText("");
                    conex.close();
                }
            }
        }
    }

    public void menu (View view){
        Toast.makeText(this, "Producto encontrado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity6.this, MainActivity5.class);
        startActivityForResult(intent, 0);
    }
}