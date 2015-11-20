package com.example.pablo.conductorutplapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l;
    String[] datos={"Pitas","Av.Cuxibamba","24_de_Mayo","Av.Occidental","Pradera","Los_Rosales","Av.Manuel_Agustin_Aguirre","Terminal","La_Banda"};
    Intent intent;
    String nombreRuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rutas,R.id.textView,datos);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout ll = (LinearLayout) view;
        TextView temp = (TextView) ll.findViewById(R.id.textView);
        nombreRuta=temp.getText().toString();
        Toast.makeText(this, nombreRuta, Toast.LENGTH_SHORT).show();
        intent = new Intent(MainActivity.this,localizador.class);
        intent.putExtra("Dato",nombreRuta);
        startActivity(intent);
    }
}
