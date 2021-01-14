package com.example.punto_de_venta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void agregar (View view){
        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void eliminar (View view){
        Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
        startActivityForResult(intent, 0);
    }

    public void mod_eli (View view){
        Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
        startActivityForResult(intent, 0);
    }

    public void busc (View view){
        Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
        startActivityForResult(intent, 0);
    }

}