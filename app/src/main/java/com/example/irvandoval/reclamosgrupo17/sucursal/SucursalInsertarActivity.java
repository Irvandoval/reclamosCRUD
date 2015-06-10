package com.example.irvandoval.reclamosgrupo17.sucursal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.MapsActivity;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class SucursalInsertarActivity extends ActionBarActivity {
    private EditText idSucursal;
    private EditText idEmpresa;
    private EditText idZona;
    private EditText nombreSucursal;
    private EditText jefeSucursal;
    private EditText direccionSucursal;
    private EditText telefonoSucursal;
    private double longitud = 0;
    private double latitud = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_insertar);
        idSucursal =  (EditText) findViewById(R.id.editIdSucursal);
        idEmpresa =  (EditText) findViewById(R.id.editIdEmpresa);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreSucursal = (EditText) findViewById(R.id.editNombreSucursal);
        jefeSucursal = (EditText) findViewById(R.id.editJefeSucursal);
        direccionSucursal = (EditText) findViewById(R.id.editDireccionSucursal);
        telefonoSucursal = (EditText) findViewById(R.id.editTelefonoSucursal);
        telefonoSucursal.addTextChangedListener( new MaskTextWatcher("####-####"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sucursal_insertar, menu);
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

    public void insertarSucursal(View v){
        if(!camposVacios()) {
            Sucursal nuevaSucursal = new Sucursal();
            String respuesta;
            nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
            nuevaSucursal.setIdEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
            nuevaSucursal.setIdZona(Integer.parseInt(idZona.getText().toString()));
            nuevaSucursal.setNombreSucursal(nombreSucursal.getText().toString());
            nuevaSucursal.setJefeSucursal(jefeSucursal.getText().toString());
            nuevaSucursal.setDireccionSucursal(direccionSucursal.getText().toString());
            nuevaSucursal.setTelefonoSucursal(telefonoSucursal.getText().toString());
            nuevaSucursal.setLongitud(longitud);
            nuevaSucursal.setLatitud(latitud);
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            respuesta = cdb.insertar(nuevaSucursal);
            if (respuesta.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.cantidad_insertados) + respuesta, Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
        }
    }
    public boolean camposVacios(){
        if(idSucursal.getText().toString().equals("") || idEmpresa.getText().toString().equals("")
                || idZona.getText().toString().equals("") || telefonoSucursal.getText().toString().equals("")
                || nombreSucursal.getText().toString().equals("") || direccionSucursal.getText().toString().equals("")
                ||jefeSucursal.getText().toString().equals("")){
            return true;
        }else
            return false;
    }
    public void limpiarTexto(View v){
        idSucursal.setText("");
        idEmpresa.setText("");
        idZona.setText("");
        nombreSucursal.setText("");
        jefeSucursal.setText("");
        direccionSucursal.setText("");
        telefonoSucursal.setText("");
    }

    public void iniciarMapa(View v){
        Intent i  = new Intent( this, MapsActivity.class);
        startActivityForResult(i, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            longitud = data.getDoubleExtra("latitude", 0);
            latitud = data.getDoubleExtra("longitude", 0);
            Toast.makeText(this, Double.toString(longitud), Toast.LENGTH_LONG).show();
            Toast.makeText(this, Double.toString(latitud), Toast.LENGTH_LONG).show();
        }
    }
}