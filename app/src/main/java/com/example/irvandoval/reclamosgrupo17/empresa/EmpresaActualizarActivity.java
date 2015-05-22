package com.example.irvandoval.reclamosgrupo17.empresa;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class EmpresaActualizarActivity extends ActionBarActivity {
    EditText nombreEmpres;
    EditText idEmpresa;
    EditText idCategoriaEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_actualizar);
        nombreEmpres = (EditText)findViewById(R.id.nombreEmpresa);
        idEmpresa  = (EditText) findViewById(R.id.idEmpresa);
        idCategoriaEmpresa = (EditText) findViewById(R.id.idCategoriaEmp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empresa_actualizar, menu);
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

    public void actualizarEmpresa(View v){
        Empresa empresa = new Empresa();
        Empresa empresaAux;
        empresa.setIdEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
        empresa.setIdCategoriaEmp(Integer.parseInt(idCategoriaEmpresa.getText().toString()));
        empresa.setNombreEmpresa(nombreEmpres.getText().toString());
        ControlDB cdb = new ControlDB(this);
        cdb.abrir();
        empresaAux = cdb.consultarEmpresa(empresa.getIdEmpresa());//para obtener la cantidad de sucursales
        empresa.setCantidadSucursales(empresaAux.getCantidadSucursales());
        String msg = cdb.actualizar(empresa);
        cdb.cerrar();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        nombreEmpres.setText("");
    }
}
