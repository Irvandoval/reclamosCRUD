package com.example.irvandoval.reclamosgrupo17.empresa;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

public class EmpresaConsultarActivity extends ActionBarActivity {
    EditText nombreEmpres;
    EditText idEmpresa;
    EditText idCategoriaEmpresa;
    EditText cantidadSucursales;
    SpeechRecognitionHelper SRHelper;
    static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    Button btnConsultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_consultar);
        nombreEmpres = (EditText)findViewById(R.id.nombreEmpresa);
        idEmpresa = (EditText) findViewById(R.id.idEmpresa);
        idCategoriaEmpresa = (EditText) findViewById(R.id.idCategoriaEmp);
        cantidadSucursales = (EditText) findViewById(R.id.cantidadSucursales);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empresa_consultar, menu);
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

    public void consultarEmpresa(View v){
        ControlDB cdb = new ControlDB(this);
        Empresa empresa;
        cdb.abrir();
        empresa = cdb.consultarEmpresa(Integer.parseInt(idEmpresa.getText().toString()));
        cdb.cerrar();
        if(empresa == null){
            Toast.makeText(this, "Empresa no encontrada", Toast.LENGTH_SHORT).show();
        }else{
            idEmpresa.setText(Integer.toString(empresa.getIdEmpresa()));
            idCategoriaEmpresa.setText(Integer.toString(empresa.getIdCategoriaEmp()));
            nombreEmpres.setText(empresa.getNombreEmpresa());
            cantidadSucursales.setText(Integer.toString(empresa.getCantidadSucursales()));
            Toast.makeText(this, "Empresa consultada", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        nombreEmpres.setText("");
        idEmpresa.setText("");
        cantidadSucursales.setText("");
        idCategoriaEmpresa.setText("");
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
                idEmpresa.setText(matches.get(0));//el resultado lo introducimos en el EditText del id Empresa
                btnConsultar.performClick();//realizamos el click al boton de consultar
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
