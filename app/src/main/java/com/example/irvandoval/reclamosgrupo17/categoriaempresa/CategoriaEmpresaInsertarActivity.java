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

public class CategoriaEmpresaInsertarActivity extends ActionBarActivity {
    EditText nombCE;
    EditText descCE;
    EditText idCE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_empresa_insertar);
        nombCE = (EditText)findViewById(R.id.NombreCE);
        descCE = (EditText)findViewById(R.id.DescripcionCE);
        idCE = (EditText)findViewById(R.id.IDCE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_insertar, menu);
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
    public void insertarCatEmpresa(View v){
        CategoriaEmpresa CEhero=new CategoriaEmpresa();
        String res;
        if (!camposVacios()) {
            CEhero.setNombreCategoriaEmp(nombCE.getText().toString());
            CEhero.setDescripcionCategoriaEmp(descCE.getText().toString());
           CEhero.setIdCategoriaEmp(Integer.parseInt(idCE.getText().toString()));
            CEhero.setCantidadEmpresas(0);
           // CEhero.setIdCategoriaEmp(1);
             ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(CEhero);
            hero.cerrar();

           if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void limpiarCE(View v){
        nombCE.setText("");
        descCE.setText("");
        idCE.setText("");

    }
    public boolean camposVacios(){
        if(nombCE.getText().toString().equals("") || descCE.getText().toString().equals("") || idCE.getText().toString().equals(""))
            return true;
        else

            return false;
    }

}
