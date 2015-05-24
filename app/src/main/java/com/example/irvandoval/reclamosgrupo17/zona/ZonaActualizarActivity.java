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

public class ZonaActualizarActivity extends ActionBarActivity {
	private EditText idZona;
	private EditText nombreZona;
	private EditText municipio;
	private EditText departamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_actualizar);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreZona = (EditText) findViewById(R.id.editNombreZona);
        municipio = (EditText) findViewById(R.id.editMunicipio);
        departamento =  (EditText) findViewById(R.id.editDepartamento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zona_actualizar, menu);
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

    public void actualizarZona(View v){
        if(!camposVacios()) {
            Zona nuevaZona = new Zona();
            String respuesta;
            nuevaZona.setIdZona(Integer.parseInt(idZona.getText().toString()));
            nuevaZona.setNombreZona(nombreZona.getText().toString());
            nuevaZona.setMunicipio(municipio.getText().toString());
            nuevaZona.setDepartamento(departamento.getText().toString());
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            respuesta = cdb.actualizar(nuevaZona);
            cdb.cerrar();
            Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarZona(View v){

        if (!idZona.getText().toString().equals("")) {
            ControlDB cdb = new ControlDB(this);
            Zona nuevaZona;
            cdb.abrir();
            nuevaZona = cdb.consultarZona(Integer.parseInt(idZona.getText().toString()));
            if (nuevaZona == null) {
                Toast.makeText(this, getResources().getString(R.string.zona_noencontrada), Toast.LENGTH_SHORT).show();
            } else {
                idZona.setText(Integer.toString(nuevaZona.getIdZona()));
                nombreZona.setText(nuevaZona.getNombreZona());
                municipio.setText(nuevaZona.getMunicipio());
                departamento.setText(nuevaZona.getDepartamento());
                Toast.makeText(this, getResources().getString(R.string.zona_consultada), Toast.LENGTH_SHORT).show();
            }
            cdb.cerrar();
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
