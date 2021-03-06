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
        CEhero.setNombreCategoriaEmp(nombCE.getText().toString());
        CEhero.setDescripcionCategoriaEmp(descCE.getText().toString());
        CEhero.setCantidadEmpresas(Integer.parseInt(canempres.getText().toString()));
        CEhero.setIdCategoriaEmp(Integer.parseInt(idCE.getText().toString()));
        res=hero.actualizar(CEhero);
        hero.cerrar();
        Toast.makeText(this, getResources().getString(R.string.categoria_empresa_consultada)+res, Toast.LENGTH_SHORT).show();
        limpiarCE(v);

    }
    public void consultarCatEmpresa(View v){
        CategoriaEmpresa CEhero=new CategoriaEmpresa();
        String res;
        ControlDB hero;
        hero = new ControlDB(this);
        hero.abrir();
        int idce=Integer.parseInt(idCE.getText().toString());
        if(idCE.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el ID", Toast.LENGTH_SHORT).show();
        }else{
            CEhero=hero.consultarCategoriaEmpresa(idce);
            hero.cerrar();
            if(CEhero==null){
                Toast.makeText(this, getResources().getString(R.string.categoria_empresa_noencontrada), Toast.LENGTH_SHORT).show();
            }else{
                nombCE.setText(CEhero.getNombreCategoriaEmp());
                descCE.setText(CEhero.getDescripcionCategoriaEmp());
                canempres.setText(String.valueOf(CEhero.getCantidadEmpresas()));

                Toast.makeText(this, getResources().getString(R.string.categoria_empresa_consultada), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void limpiarCE(View v){
        nombCE.setText("");
        descCE.setText("");
        idCE.setText("");
        canempres.setText("");

    }
}
