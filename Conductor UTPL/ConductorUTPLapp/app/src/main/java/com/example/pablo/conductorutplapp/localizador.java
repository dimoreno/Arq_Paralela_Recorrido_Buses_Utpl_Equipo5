package com.example.pablo.conductorutplapp;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class localizador extends AppCompatActivity implements View.OnClickListener {
    Button localizador;
    TextView ruta;
    TextView latitud, longitud, nomLatitud, nomLong;
    Boolean hayRed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizador);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }//linea para error android os onmainTrhead
        ruta = (TextView) findViewById(R.id.textView4);
        latitud = (TextView) findViewById(R.id.textView6);
        longitud = (TextView) findViewById(R.id.textView8);
        nomLatitud = (TextView) findViewById(R.id.textView5);
        nomLong = (TextView) findViewById(R.id.textView7);
        localizador = (Button) findViewById(R.id.button);
        localizador.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String dato = extras.getString("Dato");
            ruta.setText(dato);
        }
        LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            final Location localizacion = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(localizacion!=null){
                latitud.setText(String.valueOf(localizacion.getLatitude()));
                longitud.setText(String.valueOf(localizacion.getLongitude()));
            }
        }catch (SecurityException e){

        }


        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitud.setText(String.valueOf(location.getLatitude()));
                longitud.setText(String.valueOf(location.getLongitude()));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        try {

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);
        }catch(SecurityException e){

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                /*nomLatitud.setText("Latitud:");
                nomLong.setText("Longitud:");
                LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                try {
                    final Location localizacion = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(localizacion!=null){
                        latitud.setText(String.valueOf(localizacion.getLatitude()));
                        longitud.setText(String.valueOf(localizacion.getLongitude()));
                    }
                }catch (SecurityException e){

                }


                LocationListener locListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitud.setText(String.valueOf(location.getLatitude()));
                        longitud.setText(String.valueOf(location.getLongitude()));
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                try {

                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);
                }catch(SecurityException e){

                }*/

                final DetectarRed dr;//objeto de la clase detectar red
                dr=new DetectarRed(getApplicationContext());
                hayRed=dr.estoyConectado();//llamo al metodo de la clase DetectarRed
                String r = ruta.getText().toString();
                System.out.println(r);
                String lat = latitud.getText().toString();
                System.out.println(lat);
                String longi = longitud.getText().toString();
                System.out.println(longi);
                if(hayRed){
                    //Toast.makeText(localizador.this,"s", Toast.LENGTH_LONG).show();



                    try {
                        String uri = "http://carbono.utpl.edu.ec:8080/smartlandiotv2/webresources/entidades.datos/insert?apikey=4816ac48c9bf57cb37d329da55d6010&trama={"
                                + "\"temperatura\":" + longi + ",\"hora\":\"" + lat + "\",\"fecha\":\"" + r + "\"}";

                        URL url = new URL(uri);
                        HttpURLConnection conex
                                = (HttpURLConnection) url.openConnection();
                        conex.setConnectTimeout(5000);//tiempo q durara la conexion
                        if (conex.getResponseCode() == 200) {
                            Toast.makeText(localizador.this, "Datos ingresados", Toast.LENGTH_LONG).show();
                        } else {
                            throw new IOException(conex.getResponseMessage());
                        }
                        conex.disconnect();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(localizador.this, "Mensaje: Error con la conexion\n" + ex, Toast.LENGTH_LONG).show();

                    }


                }else{
                    Toast.makeText(localizador.this,"Comprueba tu conexion de Datos/Internet", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

   /* private class Interno extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String r = ruta.getText().toString();
            String lat = latitud.getText().toString();
            String longi = longitud.getText().toString();


                try {
                    String uri = "http://carbono.utpl.edu.ec:8080/smartlandiotv2/webresources/entidades.datos/insert?apikey=4816ac48c9bf57cb37d329da55d6010&trama={"
                            + "\"temperatura\":" + r + ",\"hora\":\"" + lat + "\",\"fecha\":\"" + longi + "\"}";

                    URL url = new URL(uri);
                    HttpURLConnection conex
                            = (HttpURLConnection) url.openConnection();
                    conex.setConnectTimeout(5000);//tiempo q durara la conexion
                    if (conex.getResponseCode() == 200) {
                        Toast.makeText(localizador.this, "Datos ingresados", Toast.LENGTH_LONG).show();
                    } else {
                        throw new IOException(conex.getResponseMessage());
                    }
                    conex.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(localizador.this, "Mensaje: Error con la conexion\n" + ex, Toast.LENGTH_LONG).show();

                }


                return null;
            }

    }*/
}
