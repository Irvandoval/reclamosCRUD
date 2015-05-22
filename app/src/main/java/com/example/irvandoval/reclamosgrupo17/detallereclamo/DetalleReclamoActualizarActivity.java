package com.example.irvandoval.reclamosgrupo17.detallereclamo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

/**
 * Created by aspire e 14 on 19/05/2015.
 */
public class DetalleReclamoActualizarActivity extends ActionBarActivity {
    ControlDB helper;
    EditText editDetalle_id;
    EditText descripcion_detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_actualizar);
        helper = new ControlDB(this);
        descripcion_detalle = (EditText) findViewById(R.id.editDescripcionDetalle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_reclamo_actualizar, menu);
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

    public void actualizarDetalle(View v) {
        DetalleReclamo detalle = new DetalleReclamo();
        detalle.setIdDetalle(Integer.parseInt(editDetalle_id.getText().toString()));
        detalle.setDescripcionDetalle(descripcion_detalle.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(detalle);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }



    public void limpiarTexto(View v) {
        editDetalle_id.setText("");
        descripcion_detalle.setText("");

    }


}
