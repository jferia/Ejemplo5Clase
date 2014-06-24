package com.example.ejemplo5clase;

//import com.example.ejemplo5clase.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//recuperarConfiguracion(null);
		Intent intent = new Intent(this, ExternaActivity.class);
        startActivity(intent);
	}

  
	@Override
	protected void onStart ()
	{
		super.onStart();
		//recuperarConfiguracion( findViewById(R.id.button2));
		//recuperarConfiguracion(v);
		//recuperarConfiguracion(null);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	// Método que guarda la configuración
	public void borrarConfiguracion (View v)
	{
		SharedPreferences spConfiguracion = getSharedPreferences(getString(R.string.nombre_configuracion), Context.MODE_PRIVATE);
		spConfiguracion.edit().clear().commit();
	}
	
	// Método que guarda la configuración
	public void guardarConfiguracion (View v)
	{
		// MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
		SharedPreferences spConfiguracion = getSharedPreferences(getString(R.string.nombre_configuracion), Context.MODE_PRIVATE);
		
		SharedPreferences.Editor spEditor = spConfiguracion.edit();
		spEditor.putString("Usuario", ((EditText)findViewById(R.id.txtUsuario)).getText().toString());
		spEditor.putFloat("Max Intentos", Float.parseFloat(((EditText)findViewById(R.id.txtNumIntentos)).getText().toString()));
		spEditor.putBoolean("Repetir Color", ((CheckBox)findViewById(R.id.checkBox1)).isChecked());
		//spEditor.putString("nombre", "Prueba");
		spEditor.commit();
	}
	
	// Método que recupera la configuración
	public void recuperarConfiguracion (View v)
	{
		EditText textoUsuario = (EditText)findViewById(R.id.txtUsuario);
		EditText textoNumInentos = (EditText)findViewById(R.id.txtNumIntentos);
		CheckBox checkRepetir = (CheckBox)findViewById(R.id.checkBox1);
		
		SharedPreferences spConfiguracion = getSharedPreferences(getString(R.string.nombre_configuracion), Context.MODE_PRIVATE);

		textoUsuario.setText(spConfiguracion.getString("Usuario", ""));
		textoNumInentos.setText(Float.toString(spConfiguracion.getFloat("Max Intentos", 0)));
		checkRepetir.setChecked(spConfiguracion.getBoolean("Repetir Color", false));
	}
	
	// Método que guarda la configuración en memoria
	public void guardarMemoria (View v)
	{
		try {
			FileOutputStream nombreFichero = openFileOutput("Datos.txt", Context.MODE_PRIVATE);
			OutputStreamWriter FOUT = new OutputStreamWriter(nombreFichero);
			String cadena = ((EditText)findViewById(R.id.txtUsuario)).getText().toString();
			FOUT.write(cadena);
			FOUT.close();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Error Escribir Fichero: "+e.getMessage(), Toast.LENGTH_LONG);
		}
	}
	
	// Método que guarda la configuración en memoria
	public void recuperarMemoria (View v)
	{
		try {
			FileInputStream nombre = openFileInput("Datos.txt");
			InputStreamReader nombreFichero = new InputStreamReader(nombre);
			BufferedReader FIN = new BufferedReader(nombreFichero);
			
			EditText textoUsuario = (EditText)findViewById(R.id.txtUsuario);
			textoUsuario.setText(FIN.readLine());
			FIN.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Error al Leer Fichero: "+e.getMessage(), Toast.LENGTH_LONG);
		}
	}

}
