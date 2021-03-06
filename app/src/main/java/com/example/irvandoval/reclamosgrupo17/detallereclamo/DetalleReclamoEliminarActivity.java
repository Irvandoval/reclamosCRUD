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
public class DetalleReclamoEliminarActivity extends ActionBarActivity {

    EditText id_detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_eliminar);
        id_detalle = (EditText) findViewById(R.id.editDetalle_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_reclamo_eliminar, menu);
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


    public void eliminarDetalle(View v) {
        if (!id_detalle.getText().toString().equals("")) {
            ControlDB helper = new ControlDB(this);
            String regEliminadas;
            DetalleReclamo detalle = new DetalleReclamo();
            detalle.setIdDetalle(Integer.parseInt(id_detalle.getText().toString()));
            helper.abrir();
            regEliminadas = helper.eliminar(detalle);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
            // implementar eliminacion
        }
        else Toast.makeText(this, "Ha dejado campos vacios",
                Toast.LENGTH_LONG).show();
    }
    public void limpiarTexto(View v) {
        id_detalle.setText("");
    }
}



