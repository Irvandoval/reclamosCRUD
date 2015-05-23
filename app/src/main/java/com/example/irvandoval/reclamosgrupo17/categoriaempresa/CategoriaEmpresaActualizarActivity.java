package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class CategoriaEmpresaActualizarActivity extends ActionBarActivity {
    EditText nombCE;
    EditText descCE;
    EditText idCE;
    EditText canempres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_empresa_actualizar);
        idCE=(EditText)findViewById(R.id.IDCE);
        nombCE = (EditText)findViewById(R.id.NombreCE);
        descCE = (EditText)findViewById(R.id.DescripcionCE);
        canempres=(EditText)findViewById(R.id.cantEmpresas );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_actualizar, menu);
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
    public void actualizarCE(View v){
        CategoriaEmpresa CEhero=new CategoriaEmpresa();
        String res;
        ControlDB hero;
        hero = new ControlDB(this);
        hero.abrir();
        int idce=Integer.parseInt(idCE.getText().toString());
       CEhero=hero.consultarCategoriaEmpresa(idce);
       nombCE.setText(CEhero.getNombreCategoriaEmp());
        descCE.setText(CEhero.getDescripcionCategoriaEmp());
      canempres.setText(CEhero.getCantidadEmpresas());
        res=hero.actualizar(CEhero);
        hero.cerrar();

    }
    public void limpiarCE(View v){
        nombCE.setText("");
        descCE.setText("");
        idCE.setText("");
        canempres.setText("");

    }
}
