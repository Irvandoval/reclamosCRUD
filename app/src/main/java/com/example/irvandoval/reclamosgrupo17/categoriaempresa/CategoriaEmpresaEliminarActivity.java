package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class CategoriaEmpresaEliminarActivity extends ActionBarActivity {
    EditText idce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_empresa_eliminar);
        idce=(EditText) findViewById(R.id.IDCE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_eliminar, menu);
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
    public void eliminarCE(View v){
        if(!idce.getText().toString().equals("")) {
            ControlDB hero = new ControlDB(this);
            CategoriaEmpresa CEhero= new CategoriaEmpresa();
            CEhero.setIdCategoriaEmp(Integer.parseInt(String.valueOf(idce.getText().toString())));
            hero.abrir();
            String msg = hero.eliminar(CEhero);
            hero.cerrar();
            Toast.makeText(this, getResources().getString(R.string.filas_afectadas) + msg, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarCE(View v){

        idce.setText("");

    }
}
