package com.example.ejemplo5clase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore.Files;

public class ExternaActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_externa);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	// Método que guarda los datos en la memoria externa
	public void guardarDatos (View v)
	{
		File raiz;
		EditText txtNombreArchivo, txtContenidoArchivo;
		String textoNombreArchivo, textoContenidoArchivo;
		txtNombreArchivo = ((EditText)findViewById(R.id.nombreArchivo));
		txtContenidoArchivo = ((EditText)findViewById(R.id.contenidoArchivo));
		textoNombreArchivo = txtNombreArchivo.getText().toString();
		textoContenidoArchivo = txtContenidoArchivo.getText().toString();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			raiz = Environment.getExternalStorageDirectory();
			File directorio = new File(raiz.getAbsolutePath()+"/datos/miapp/loquequieras");
			if (directorio.mkdirs())
			{
				try {
					File archivo = new File(directorio, textoNombreArchivo);
					FileOutputStream fos;
					fos = new FileOutputStream(archivo);					
					PrintWriter pw = new PrintWriter(fos);
					pw.println(textoContenidoArchivo);
					pw.flush();
					pw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(this, "Error escribiendo: "+textoNombreArchivo, Toast.LENGTH_LONG).show();
					Toast.makeText(this, "Mensaje de Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		}
		else
		{
			Toast.makeText(this, "No se puede acceder a la memoria externa.", Toast.LENGTH_SHORT).show();
			raiz = null;
		}
	}

	public void leerDatos (View v)
	{
		File raiz;
		EditText txtNombreArchivo, txtContenidoArchivo;
		String textoNombreArchivo, textoContenidoArchivo, linea;
		
		linea = "";
		txtNombreArchivo = ((EditText)findViewById(R.id.nombreArchivo));
		textoNombreArchivo = txtNombreArchivo.getText().toString();
		txtContenidoArchivo = ((EditText)findViewById(R.id.contenidoArchivo));
		textoContenidoArchivo = txtContenidoArchivo.getText().toString();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			raiz = Environment.getExternalStorageDirectory();
			File directorio = new File(raiz.getAbsolutePath()+"/datos/miapp/loquequieras");
			if (directorio.isDirectory());
			{
				try {
					File archivo = new File(directorio, textoNombreArchivo);
					FileInputStream fis;
					fis = new FileInputStream(archivo);	
					// Para Lectura, en vez de printwriter utilizamos el InputStreamReader
					InputStreamReader isr = new InputStreamReader(fis);
					BufferedReader buf = new BufferedReader(isr);
					// limpiar el contenido a presentar antes de concantenar líneas nuevas
					txtContenidoArchivo.setText("");
					while (linea != null)
					{
						linea = buf.readLine();
						txtContenidoArchivo.append(linea);
					}
					isr.close();
					
					
/*					File ruta_sd = Environment.getExternalStorageDirectory();
					 
				    File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
				 
				    BufferedReader fin =
				        new BufferedReader(
				            new InputStreamReader(
				                new FileInputStream(f)));
				 
				    String texto = fin.readLine();
				    fin.close();*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(this, "Error de lectura: "+textoNombreArchivo, Toast.LENGTH_LONG).show();
					Toast.makeText(this, "Mensaje de Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		}
		else
		{
			Toast.makeText(this, "No se puede acceder a la memoria externa.", Toast.LENGTH_SHORT).show();
			raiz = null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.externa, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_externa,
					container, false);
			return rootView;
		}
	}

}
