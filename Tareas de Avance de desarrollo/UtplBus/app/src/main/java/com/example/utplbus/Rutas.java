package com.example.utplbus;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Diego Israel on 11/10/2015.
 */
public class Rutas extends ListActivity{
    private MiAdaptador miAdaptador=null;
    //Paso 2
    public class Nodo{
        public String nodoTitulo;
        public String nodoDescripcion;
        public Integer nodoImagen;
    }
    //PASO 3 creo un arraylist de tipo nodo para agregar titulo,descripcion e img
    private static ArrayList<Nodo> mArray=new ArrayList<Nodo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDatos();//sirve para mostrar los datos
        //PASO 5
        miAdaptador=new MiAdaptador(this);
        setListAdapter(miAdaptador);
    }
    //PASO final
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        switch (position) {
            case 0:
                Toast.makeText(this, "Espera Por Favor", Toast.LENGTH_SHORT).show();
                Intent p0=new Intent(Rutas.this,Ubicacion.class);
                startActivity(p0);
                break;
            case 1:
                Toast.makeText(this, "Proximamenre", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent p2=new Intent(Rutas.this,Ubicacion.class);
                startActivity(p2);
                break;
            case 3:
                Toast.makeText(this, "Proximamenre", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Proximamenre", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "Proximamenre", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void setDatos() {
        // PASO 4
        mArray.clear();
        //item 1
        Nodo miNodo=new Nodo();
        miNodo.nodoTitulo=this.getResources().getString(R.string.titulo1);
        miNodo.nodoDescripcion="Seleciona la ruta";
        miNodo.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo);
        //item 2
        Nodo miNodo2=new Nodo();
        miNodo2.nodoTitulo=this.getResources().getString(R.string.titulo2);
        miNodo2.nodoDescripcion="Seleciona la ruta";
        miNodo2.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo2);
        //item 3
        Nodo miNodo3=new Nodo();
        miNodo3.nodoTitulo=this.getResources().getString(R.string.titulo3);
        miNodo3.nodoDescripcion="Seleciona la ruta";
        miNodo3.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo3);
        //item 4
        Nodo miNodo4=new Nodo();
        miNodo4.nodoTitulo=this.getResources().getString(R.string.titulo4);
        miNodo4.nodoDescripcion="Seleciona la ruta";
        miNodo4.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo4);
        //item 5
        Nodo miNodo5=new Nodo();
        miNodo5.nodoTitulo=this.getResources().getString(R.string.titulo5);
        miNodo5.nodoDescripcion="Seleciona la ruta";
        miNodo5.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo5);
        //item 6
        Nodo miNodo6=new Nodo();
        miNodo6.nodoTitulo=this.getResources().getString(R.string.titulo6);
        miNodo6.nodoDescripcion="Seleciona la ruta";
        miNodo6.nodoImagen=R.mipmap.parada;
        mArray.add(miNodo6);
    }
    //PASO 1
    public static class MiAdaptador extends BaseAdapter{
        //Constructor para pasar el contexto donde dice miAdaptador(this)
        private Context context;
        public MiAdaptador(Context c){
            context=c;
        }
        @Override
        public int getCount() {
            // Sirve para retornar el nro de item de la lista o fila
            return mArray.size();
        }

        @Override
        public Object getItem(int position) {
            // Devuelve un item para q en caso de q se pida un item se devuelve la img y texto
            return mArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // PASO penultimo Devuelve la vista del item a visualizar
            //devuelve el layout para ver
            View view=null;
            if(convertView==null){
                //si la vista es nula se genera una nueva lista
                LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.rutas, null);
            }else{
                //sino quiere decir q la vista esta generada
                view=convertView;
            }
            //obtener la imagen y se rellena la vista
            ImageView img=(ImageView)view.findViewById(R.id.imagen);
            img.setImageDrawable(context.getResources().getDrawable(mArray.get(position).nodoImagen));
            TextView titulo=(TextView)view.findViewById(R.id.titulo);
            titulo.setText(mArray.get(position).nodoTitulo);
            TextView descripcion=(TextView)view.findViewById(R.id.descripcion);
            descripcion.setText(mArray.get(position).nodoDescripcion);
            return view;
        }

    }
}
