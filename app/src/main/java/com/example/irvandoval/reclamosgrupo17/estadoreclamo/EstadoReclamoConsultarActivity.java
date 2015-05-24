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
public class EstadoReclamoConsultarActivity extends ActionBarActivity {
    ControlDB helper;
    EditText idEstadoReclamo;
    EditText nombre_estado;
    EditText descripcion_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_reclamo_consultar);
        helper = new ControlDB(this);
        idEstadoReclamo = (EditText) findViewById(R.id.editEstadoId);
        nombre_estado = (EditText) findViewById(R.id.editNombreEstado);
        descripcion_estado = (EditText) findViewById(R.id.editDescripcionEstado);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estado_reclamo_consultar, menu);
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

    public void consultarEstadoReclamo(View v) {
        if(!camposVacios()) {
            helper.abrir();
            EstadoReclamo estado =
                    helper.consultarEstadoReclamo(Integer.parseInt(idEstadoReclamo.getText().toString()));
            helper.cerrar();
            if (estado == null)
                Toast.makeText(this, "Estado con el ID " +
                        idEstadoReclamo.getText().toString() +
                        " no encontrado", Toast.LENGTH_LONG).show();
            else {
                nombre_estado.setText(estado.getNombreEstado());
                descripcion_estado.setText(estado.getDescripcionEstado());

            }

        }
    }

    public void limpiarTexto(View v) {
        idEstadoReclamo.setText("");
        nombre_estado.setText("");
        descripcion_estado.setText("");
    }

    public boolean camposVacios(){
        if(idEstadoReclamo.getText().toString().equals("") )
        {Toast.makeText(this, "Ha dejado campos vacios",
                Toast.LENGTH_LONG).show();
            return true;}
        else

            return false;
    }
}



