package com.example.utplbus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Diego Israel on 11/10/2015.
 */
public class Inicio extends AppCompatActivity{
    TextView tvPorcentaje;
    TextView tvCarga;
    ProgressBar pbSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        tvPorcentaje=(TextView)findViewById(R.id.tvPorcentaje);
        tvCarga=(TextView)findViewById(R.id.tvCarga);
        pbSaludo=(ProgressBar)findViewById(R.id.pbSaludo);
        pbSaludo.setMax(100);
        pbSaludo.setBackgroundColor(Color.TRANSPARENT);
        pbSaludo.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);//color a la linea del pbSaludo
        pbSaludo.setProgress(0);

        Hilo cargaDatos=new Hilo(this);
        cargaDatos.execute();
    }
    public class Hilo extends AsyncTask<Void, Integer, Void> {
        Context contexto;
        public Hilo(Context mContexto){
            contexto=mContexto;
        }
        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            publishProgress(0);
            for(int i=0;i<100;i++){
                try {
                    Thread.sleep(25);
                    publishProgress(i+1);

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... value) {
            // TODO Auto-generated method stub
            tvPorcentaje.setText(value[0]+"%");
            pbSaludo.setProgress(value[0]);
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            contexto.startActivity(new Intent(contexto,Rutas.class));
            finish();
        }

    }
}
