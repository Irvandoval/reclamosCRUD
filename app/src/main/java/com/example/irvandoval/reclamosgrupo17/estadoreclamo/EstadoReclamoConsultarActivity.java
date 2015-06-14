package com.example.irvandoval.reclamosgrupo17.estadoreclamo;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.SpeechRecognitionHelper;

import java.util.ArrayList;


/**
 * Created by aspire e 14 on 19/05/2015.
 */
public class EstadoReclamoConsultarActivity extends ActionBarActivity {
    ControlDB helper;
    EditText idEstadoReclamo;
    EditText nombre_estado;
    EditText descripcion_estado;
    SpeechRecognitionHelper SRHelper;
    static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_reclamo_consultar);
        helper = new ControlDB(this);
        idEstadoReclamo = (EditText) findViewById(R.id.editEstadoId);
        nombre_estado = (EditText) findViewById(R.id.editNombreEstado);
        descripcion_estado = (EditText) findViewById(R.id.editDescripcionEstado);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);

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
    public void busquedaPorVoz(View v){
        SRHelper.run(this);
    }

    // Activity Results handler
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

// if it’s speech recognition results
// and process finished ok
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

// receiving a result in string array
// there can be some strings because sometimes speech recognizing inaccurate
// more relevant results in the beginning of the list
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

// in “matches” array we holding a results... let’s show the most relevant
            if (matches.size() > 0) {
                // Toast.makeText(this, matches.get(0), Toast.LENGTH_LONG).show();
                idEstadoReclamo.setText(matches.get(0));//el resultado lo introducimos en el EditText del id Empresa
                btnConsultar.performClick();//realizamos el click al boton de consultar
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}



