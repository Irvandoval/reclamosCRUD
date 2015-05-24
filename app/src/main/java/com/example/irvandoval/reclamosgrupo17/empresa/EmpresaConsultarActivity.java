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

public class EmpresaConsultarActivity extends ActionBarActivity {
    EditText nombreEmpres;
    EditText idEmpresa;
    EditText idCategoriaEmpresa;
    EditText cantidadSucursales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_consultar);
        nombreEmpres = (EditText)findViewById(R.id.nombreEmpresa);
        idEmpresa = (EditText) findViewById(R.id.idEmpresa);
        idCategoriaEmpresa = (EditText) findViewById(R.id.idCategoriaEmp);
        cantidadSucursales = (EditText) findViewById(R.id.cantidadSucursales);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empresa_consultar, menu);
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

    public void consultarEmpresa(View v){
        ControlDB cdb = new ControlDB(this);
        Empresa empresa;
        cdb.abrir();
        empresa = cdb.consultarEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
        cdb.cerrar();
        if(empresa == null){
            Toast.makeText(this, "Empresa no encontrada", Toast.LENGTH_SHORT).show();
        }else{
            idEmpresa.setText(Integer.toString(empresa.getIdEmpresa()));
            idCategoriaEmpresa.setText(Integer.toString(empresa.getIdCategoriaEmp()));
            nombreEmpres.setText(empresa.getNombreEmpresa());
            cantidadSucursales.setText(Integer.toString(empresa.getCantidadSucursales()));
            Toast.makeText(this, "Empresa consultada", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        nombreEmpres.setText("");
        idEmpresa.setText("");
        cantidadSucursales.setText("");
        idCategoriaEmpresa.setText("");
    }
}
