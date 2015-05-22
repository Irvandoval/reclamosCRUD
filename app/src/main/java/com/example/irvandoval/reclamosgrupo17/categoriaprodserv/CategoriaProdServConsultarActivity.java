package com.example.irvandoval.reclamosgrupo17.categoriaprodserv;

import android.content.Intent;
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

public class CategoriaProdServConsultarActivity extends ActionBarActivity {
    EditText IdCatPS;
    EditText nombCatPS;
    EditText descriCatPS;
    EditText cantCatPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_prod_serv_consultar);
        IdCatPS = (EditText)findViewById(R.id.idCategoriaPS);
        nombCatPS = (EditText)findViewById(R.id.nombreCatPS);
        descriCatPS = (EditText)findViewById(R.id.editText2);
        cantCatPS = (EditText)findViewById(R.id.editTextCantidad);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_prod_serv_consultar, menu);
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

    public void consultarCategoriaProductoServ(View v){
        ControlDB hero=new ControlDB(this);
        CategoriaProdServ herouser;
        hero.abrir();
        herouser=hero.consultarCategoriaProdServ(Integer.parseInt(IdCatPS.getText().toString()));
        if(herouser==null){
            Toast.makeText(this, getResources().getString(R.string.noenc_ConsultaCatProdServ), Toast.LENGTH_SHORT).show();
        }else{
            nombCatPS.setText(herouser.getNombreCategoriaPs());
            descriCatPS.setText(herouser.getDescripcionCategoriaPs());
            cantCatPS.setText(Integer.toString(herouser.getCantidadProductos()));
            Toast.makeText(this, getResources().getString(R.string.ConsultaCatProdServ), Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto3(View v){
        nombCatPS.setText(" ");
        descriCatPS.setText(" ");
        cantCatPS.setText(" ");
    }
}
