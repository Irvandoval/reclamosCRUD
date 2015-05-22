package com.example.irvandoval.reclamosgrupo17.categoriaprodserv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.usuario.Usuario;

public class CategoriaProdServInsertarActivity extends ActionBarActivity {
 EditText IdCatPS;
 EditText nombCatPS;
 EditText descriCatPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_prod_serv_insertar);
        IdCatPS = (EditText)findViewById(R.id.id_CategoriaPS);
        nombCatPS = (EditText)findViewById(R.id.nombreCatPS);
        descriCatPS = (EditText)findViewById(R.id.descripcionCatPS);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_prod_serv_insertar, menu);
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


    public void insertarCategoriaprodServInsertar(View v){
        CategoriaProdServ nuevoCategoriaProdServ = new CategoriaProdServ();
        String res;
        if (!camposVacios()) {
            nuevoCategoriaProdServ.setIdCategoriaProdServ(Integer.parseInt(IdCatPS.getText().toString()));
            nuevoCategoriaProdServ.setNombreCategoriaPs(nombCatPS.getText().toString());
            nuevoCategoriaProdServ.setDescripcionCategoriaPs(descriCatPS.getText().toString());
            nuevoCategoriaProdServ.setCantidadProductos(0);
            ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(nuevoCategoriaProdServ);
            System.err.println("AL SALIR IMPRIME: " + res);
            if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void limpiarTexto3(View v){

        nombCatPS.setText("");
        descriCatPS.setText(" ");
    }

    public boolean camposVacios(){
        if(IdCatPS.getText().toString().equals("") || nombCatPS.getText().toString().equals("") || descriCatPS.getText().toString().equals(""))
            return true;
        else
            return false;
    }
}
