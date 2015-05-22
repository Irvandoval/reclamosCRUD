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
 * Created by aspire e 14 on 17/05/2015.
 */
public class DetalleReclamoConsultarActivity extends ActionBarActivity {
    ControlDB helper;
    EditText editDetalle_id;
    EditText descripcion_detalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_consultar);
        helper = new ControlDB(this);
        descripcion_detalle = (EditText) findViewById(R.id.editDescripcionDetalle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_reclamo_consultar, menu);
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

    public void consultarDetalle(View v) {
        helper.abrir();
        DetalleReclamo detalle =
                helper.consultarDetalleReclamo(Integer.parseInt(editDetalle_id.getText().toString()));
        helper.cerrar();
        if(detalle == null)
            Toast.makeText(this, "Detalle con el ID " +
                    editDetalle_id.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editDetalle_id.setText(detalle.getDescripcionDetalle());

        }
    }




    public void limpiarTexto(View v) {
        descripcion_detalle.setText("");

    }
}

