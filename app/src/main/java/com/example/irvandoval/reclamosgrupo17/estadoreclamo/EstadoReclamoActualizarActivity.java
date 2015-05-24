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

/**
 * Created by aspire e 14 on 19/05/2015.
 */
public class EstadoReclamoActualizarActivity extends ActionBarActivity {
    EditText idEstadoReclamo;
    EditText nombre_estado;
    EditText descripcion_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_reclamo_actualizar);
        idEstadoReclamo=(EditText) findViewById(R.id.editEstadoId);
        nombre_estado = (EditText) findViewById(R.id.editNombreEstado);
        descripcion_estado = (EditText) findViewById(R.id.editDescripcionEstado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estado_reclamo_actualizar, menu);
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

    public void actualizarEstadoReclamo(View v) {
        if(!camposVacios()) {
            ControlDB helper = new ControlDB(this);
            EstadoReclamo estado = new EstadoReclamo();
            estado.setIdEstadoReclamo(Integer.parseInt(idEstadoReclamo.getText().toString()));
            estado.setNombreEstado(nombre_estado.getText().toString());
            estado.setDescripcionEstado(descripcion_estado.getText().toString());
            helper.abrir();
            String estad1 = helper.actualizar(estado);
            helper.cerrar();
            Toast.makeText(this, estad1, Toast.LENGTH_SHORT).show();
        }

    }


    public void limpiarTexto(View v) {
        idEstadoReclamo.setText("");
        nombre_estado.setText("");
        descripcion_estado.setText("");

    }

    public boolean camposVacios(){
        if(idEstadoReclamo.getText().toString().equals("") || nombre_estado.getText().toString().equals("")|| descripcion_estado.getText().toString().equals("")) {
            Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        else

            return false;
    }
}



