package com.example.pablo.conductorutplapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//Clase para saber si hay o no red (wifi-datos-etc) en el telefono
public class DetectarRed {
	private Context _context;
	public DetectarRed(Context context){
		this._context=context;
	}
	public boolean estoyConectado(){
		ConnectivityManager conectado=(ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectado!=null){
			NetworkInfo[] info=conectado.getAllNetworkInfo();
			if(info!=null)
				for(int i=0;i<info.length;i++)
					if(info[i].getState()==NetworkInfo.State.CONNECTED){
						return true;
					}
		}
		return false;		
	}
}
