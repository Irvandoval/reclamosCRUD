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

public class CategoriaProdServEliminarActivity extends ActionBarActivity {
    EditText IdCatPS;
    //EditText nombCatPS;
    //EditText descriCatPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_prod_serv_eliminar);
        IdCatPS = (EditText)findViewById(R.id.editText3);
        //nombCatPS = (EditText)findViewById(R.id.nombreCatPS);
        //descriCatPS = (EditText)findViewById(R.id.descripcionCatPS);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_prod_serv_eliminar, menu);
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


    public void eliminarCategoriaProdServ(View v){
        if(IdCatPS.getText().toString().equals("")) {
            ControlDB hero = new ControlDB(this);
            CategoriaProdServ herouser = new CategoriaProdServ();
            herouser.setIdCategoriaProdServ(Integer.parseInt(IdCatPS.getText().toString()));
            hero.abrir();
            String msg = hero.eliminar(herouser);
            hero.cerrar();
            Toast.makeText(this, getResources().getString(R.string.filas_afectadas) + msg, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto3(View v){
        IdCatPS.setText(" ");
        //nombCatPS.setText(" ");
        //descriCatPS.setText(" ");
    }
}
