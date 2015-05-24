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

public class EmpresaInsertarActivity extends ActionBarActivity {
    EditText idEmpresa;
    EditText idCategoriaEmpresa;
    EditText nombreEmpres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_insertar);
        nombreEmpres = (EditText)findViewById(R.id.nombreEmpresa);
        idEmpresa =  (EditText) findViewById(R.id.idEmpresa);
        idCategoriaEmpresa = (EditText) findViewById(R.id.idCategoriaEmp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empresa_insertar, menu);
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
    public void insertarEmpresa(View v){
        Empresa empresa = new Empresa();
        String res;
        if(!camposVacios()){
            empresa.setIdEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
            empresa.setIdCategoriaEmp(Integer.parseInt(idCategoriaEmpresa.getText().toString()));
            empresa.setNombreEmpresa(nombreEmpres.getText().toString());
            empresa.setCantidadSucursales(0);//siempre se inicializara con 0
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            res= cdb.insertar(empresa);
            if(res.equals("error_insertar")){
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, res+"empresa registrada", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void limpiarTexto(View v){
        nombreEmpres.setText("");
        idCategoriaEmpresa.setText("");
        idEmpresa.setText("");
    }

    public boolean camposVacios(){
        if(nombreEmpres.getText().toString().equals("") || idCategoriaEmpresa.getText().toString().equals("") ||
                idEmpresa.getText().toString().equals(""))
        return true;
        else
            return false;
    }
}
