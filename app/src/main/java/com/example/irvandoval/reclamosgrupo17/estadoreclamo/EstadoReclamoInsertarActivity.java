package com.example.irvandoval.reclamosgrupo17.estadoreclamo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

import java.util.ResourceBundle;

/**
 * Created by aspire e 14 on 19/05/2015.
 */
public class EstadoReclamoInsertarActivity  extends ActionBarActivity{
    ControlDB helper;
    EditText idEstadoReclamo;
    EditText nombre_estado;
    EditText descripcion_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_reclamo_insertar);
        helper = new ControlDB (this);
        nombre_estado = (EditText) findViewById(R.id.editNombreEstado);
        descripcion_estado = (EditText) findViewById(R.id.editDescripcionEstado);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estado_reclamo_insertar, menu);
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

    public void insertarEstadoReclamo(View v) {
        String regInsertados;
        EstadoReclamo estado=new EstadoReclamo ();
        estado.setIdEstadoReclamo(Integer.parseInt(idEstadoReclamo.getText().toString()));
        estado.setNombreEstado(nombre_estado.getText().toString());
        estado.setDescripcionEstado(descripcion_estado.getText().toString());
        helper.abrir();
        regInsertados=helper.insertar(estado);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


    }









    public void limpiarTexto(View v) {
        nombre_estado.setText("");
        descripcion_estado.setText("");
    }



}


