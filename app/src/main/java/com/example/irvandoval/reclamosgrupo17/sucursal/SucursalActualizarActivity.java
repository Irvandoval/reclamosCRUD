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
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class SucursalActualizarActivity extends ActionBarActivity {
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
        setContentView(R.layout.activity_sucursal_actualizar);
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
        getMenuInflater().inflate(R.menu.menu_sucursal_actualizar, menu);
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

    public void actualizarSucursal(View v){
        if(!camposVacios()) {
            Sucursal nuevaSucursal = new Sucursal();
            String respuesta;
            nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
            nuevaSucursal.setIdEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
            nuevaSucursal.setIdZona(Integer.parseInt(idZona.getText().toString()));
            nuevaSucursal.setNombreSucursal(nombreSucursal.getText().toString());
            nuevaSucursal.setJefeSucursal(jefeSucursal.getText().toString());
            nuevaSucursal.setTelefonoSucursal(telefonoSucursal.getText().toString());
            nuevaSucursal.setDireccionSucursal(direccionSucursal.getText().toString());
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            respuesta = cdb.actualizar(nuevaSucursal);
            cdb.cerrar();
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


    public void consultarSucursal(View v){
        if(!idSucursal.getText().toString().equals("")) {
            Sucursal nuevaSucursal = new Sucursal();
            nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            nuevaSucursal = cdb.consultarSucursal(nuevaSucursal.getIdSucursal());
            if (nuevaSucursal == null) {
                Toast.makeText(this, getResources().getString(R.string.sucursal_noencontrada), Toast.LENGTH_SHORT).show();
            } else {
                idSucursal.setText(Integer.toString(nuevaSucursal.getIdSucursal()));
                idEmpresa.setText(Integer.toString(nuevaSucursal.getIdEmpresa()));
                idZona.setText(Integer.toString(nuevaSucursal.getIdZona()));
                nombreSucursal.setText(nuevaSucursal.getNombreSucursal());
                jefeSucursal.setText(nuevaSucursal.getJefeSucursal());
                direccionSucursal.setText(nuevaSucursal.getDireccionSucursal());
                telefonoSucursal.setText(nuevaSucursal.getTelefonoSucursal());
                Toast.makeText(this, getResources().getString(R.string.sucursal_consultada), Toast.LENGTH_SHORT).show();
            }
            cdb.cerrar();
        }
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
