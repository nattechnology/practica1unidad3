package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText logCor, logCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logCor = (EditText) findViewById(R.id.logCorreo);
        logCon = (EditText) findViewById(R.id.logPass);

        SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        logCor.setText(pref.getString("correo",""));

        String datosN = logCor.getText().toString();
        String datosC = logCon.getText().toString();

        if(pref.contains("correo")){

            if(pref.contains("contrasena")) {

                String usua = pref.getString("correo", "");
                Toast.makeText(this, "Bienvenido " + usua, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity5.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public void iniciar(View view)
    {
        String datosN = logCor.getText().toString();
        String datosC = logCon.getText().toString();

        if(datosN.isEmpty()||datosC.isEmpty()){
            if(datosN.isEmpty()) {
                logCor.setError("Valor no ingresado");
                Toast.makeText(this, "Ingresa un nombre", Toast.LENGTH_LONG).show();
            }
            if(datosC.isEmpty()) {
                logCon.setError("Valor no ingresado");
                Toast.makeText(this, "Ingresa contraseña", Toast.LENGTH_LONG).show();
            }
        }else {
            //SharedPreferences preferences = getSharedPreferences("dato",0);
            SharedPreferences preferences1 = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences1.edit();

            //String Cont = con.getText().toString();
            editor.putString("correo", datosN);
            editor.putString("contrasena", datosC);
            editor.commit();

            String usua = preferences1.getString("correo", "");
            Toast.makeText(this, "Bienvenido " + usua, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity5.class);
            startActivity(intent);
            finish();
            //editor.clear();
        }
    }
}