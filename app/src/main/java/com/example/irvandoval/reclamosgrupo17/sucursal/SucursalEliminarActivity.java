package com.example.irvandoval.reclamosgrupo17.sucursal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class SucursalEliminarActivity extends ActionBarActivity {
    private EditText idSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_eliminar);
        idSucursal = (EditText) findViewById(R.id.editIdSucursal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sucursal_eliminar, menu);
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

    public void eliminarSucursal(View v){
        Sucursal nuevaSucursal =  new Sucursal();
        String respuesta;
        nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
        ControlDB cdb =  new ControlDB(this);
        cdb.abrir();
        respuesta = cdb.eliminar(nuevaSucursal);
        cdb.cerrar();
        Toast.makeText(this, getResources().getString(R.string.filas_afectadas) + respuesta, Toast.LENGTH_SHORT).show();
    }
}
