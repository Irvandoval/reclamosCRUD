package com.example.irvandoval.reclamosgrupo17.zona;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class ZonaInsertarActivity extends ActionBarActivity {
        EditText idZona;
        EditText nombreZona;
        EditText municipio;
        EditText departamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_insertar);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreZona = (EditText) findViewById(R.id.editNombreZona);
        municipio = (EditText) findViewById(R.id.editMunicipio);
        departamento =  (EditText) findViewById(R.id.editDepartamento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zona_insertar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insertarZona(View v){
        if(!camposVacios()) {
            Zona nuevaZona = new Zona();
            String respuesta;
            nuevaZona.setIdZona(Integer.parseInt(idZona.getText().toString()));
            nuevaZona.setNombreZona(nombreZona.getText().toString());
            nuevaZona.setMunicipio(municipio.getText().toString());
            nuevaZona.setDepartamento(departamento.getText().toString());
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            respuesta = cdb.insertar(nuevaZona);
            cdb.cerrar();
            Toast.makeText(this, getResources().getString(R.string.cantidad_insertados) + respuesta, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        idZona.setText("");
        nombreZona.setText("");
        municipio.setText("");
        departamento.setText("");
    }
    public boolean camposVacios(){
        if(idZona.getText().toString().equals("") || nombreZona.getText().toString().equals("")
                || municipio.getText().toString().equals("") || departamento.getText().toString().equals("")){
            return true;
        }else
            return false;
    }
}
