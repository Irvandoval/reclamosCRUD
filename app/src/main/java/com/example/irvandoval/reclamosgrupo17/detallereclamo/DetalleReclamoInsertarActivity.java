package com.example.irvandoval.reclamosgrupo17.detallereclamo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.irvandoval.reclamosgrupo17.R;
/**
 * Created by aspire e 14 on 19/05/2015.
 */
public class DetalleReclamoInsertarActivity  extends ActionBarActivity {
    EditText descripcion_detalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_insertar);
        descripcion_detalle = (EditText) findViewById(R.id.editDescripcionDetalle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_reclamo_insertar, menu);
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

    public void insertarEstado(View v) {
        DetalleReclamo nuevoDetalle= new DetalleReclamo();
        nuevoDetalle.setDescripcionDetalle(descripcion_detalle.getText().toString());

    }


    public void limpiarTexto(View v) {
        descripcion_detalle.setText("");

    }
}
