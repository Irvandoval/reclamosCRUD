package com.example.irvandoval.reclamosgrupo17.sucursal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class SucursalConsultarActivity extends ActionBarActivity {
    private EditText idSucursal;
    private EditText idEmpresa;
    private EditText idZona;
    private EditText nombreSucursal;
    private EditText jefeSucursal;
    private EditText direccionSucursal;
    private EditText telefonoSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_consultar);
        idSucursal =  (EditText) findViewById(R.id.editIdSucursal);
        idEmpresa =  (EditText) findViewById(R.id.editIdEmpresa);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreSucursal = (EditText) findViewById(R.id.editNombreSucursal);
        jefeSucursal = (EditText) findViewById(R.id.editJefeSucursal);
        direccionSucursal = (EditText) findViewById(R.id.editDireccionSucursal);
        telefonoSucursal = (EditText) findViewById(R.id.editTelefonoSucursal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sucursal_consultar, menu);
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

    public void consultarSucursal(View v){
        Sucursal nuevaSucursal =  new Sucursal();
        String respuesta;
        nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
        ControlDB cdb =  new ControlDB(this);
        cdb.abrir();
        nuevaSucursal = cdb.consultarSucursal(nuevaSucursal.getIdSucursal());
        if(nuevaSucursal == null){
            Toast.makeText(this, getResources().getString(R.string.sucursal_noencontrada), Toast.LENGTH_SHORT).show();
        }else{
            idSucursal.setText(nuevaSucursal.getIdSucursal());
            idEmpresa.setText(nuevaSucursal.getIdEmpresa());
            idZona.setText(nuevaSucursal.getIdZona());
            nombreSucursal.setText(nuevaSucursal.getNombreSucursal());
            jefeSucursal.setText(nuevaSucursal.getJefeSucursal());
            telefonoSucursal.setText(nuevaSucursal.getTelefonoSucursal());
            Toast.makeText(this, getResources().getString(R.string.sucursal_consultada), Toast.LENGTH_SHORT).show();
        }
        cdb.cerrar();

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
}
