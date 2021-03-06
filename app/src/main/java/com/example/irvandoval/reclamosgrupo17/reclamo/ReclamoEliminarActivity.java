package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

public class ReclamoEliminarActivity extends ActionBarActivity {
    EditText idrec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_eliminar);
        idrec=(EditText)findViewById(R.id.idReclamo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reclamo_eliminar, menu);
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
    public void eliminarreclamo(View v){
        ControlDB hero;
        hero = new ControlDB(this);
        Reclamo reca=new Reclamo();
        reca.setIdReclamo(Integer.parseInt(idrec.getText().toString()));
        hero.abrir();
        String res=hero.eliminar(reca);
        hero.cerrar();
        Toast.makeText(this, getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto1(View v){
    idrec.setText("");

    }
}
