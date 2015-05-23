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

public class EmpresaEliminarActivity extends ActionBarActivity {
    EditText idEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_eliminar);
        idEmpresa = (EditText) findViewById(R.id.idEmpresa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empresa_eliminar, menu);
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
    public  void eliminarEmpresa(View v){
        ControlDB cdb = new ControlDB(this);
        Empresa empresa = new Empresa();
        cdb.abrir();
        empresa.setIdEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
        String msg = cdb.eliminar(empresa);
        cdb.cerrar();
        Toast.makeText(this, msg+"Empresa eliminada correctamente", Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        idEmpresa.setText("");
    }
}
