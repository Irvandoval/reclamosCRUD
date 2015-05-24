package com.example.irvandoval.reclamosgrupo17.prodserv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.categoriaprodserv.CategoriaProdServ;

public class ProdServEliminarActivity extends ActionBarActivity {
    EditText IdProdServ;
   // EditText nombProdServ;
    //EditText descriProdServ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_serv_insertar);
        IdProdServ = (EditText)findViewById(R.id.nombreProdServ);
        //descriProdServ = (EditText)findViewById(R.id.descripcionProdServ);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prod_serv_insertar, menu);
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

    public void eliminarProdServ(View v) {
        if (!IdProdServ.getText().toString().equals("")) {
            ControlDB hero = new ControlDB(this);
            ProdServ herouser = new ProdServ();
            herouser.setIdProdServ(Integer.parseInt(IdProdServ.getText().toString()));
            hero.abrir();
            String msg = hero.eliminar(herouser);
            hero.cerrar();
            Toast.makeText(this, getResources().getString(R.string.filas_afectadas) + msg, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto4(View v){

        //nombProdServ.setText(" ");
        IdProdServ.setText(" ");
    }
}
