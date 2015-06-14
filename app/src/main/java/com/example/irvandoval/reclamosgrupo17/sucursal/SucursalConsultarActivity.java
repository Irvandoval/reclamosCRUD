package com.example.irvandoval.reclamosgrupo17.sucursal;

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
import com.example.irvandoval.reclamosgrupo17.MapsActivity;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.SpeechRecognitionHelper;
import com.example.irvandoval.reclamosgrupo17.zona.Zona;

import java.util.ArrayList;

public class SucursalConsultarActivity extends ActionBarActivity {
    private EditText idSucursal;
    private EditText idEmpresa;
    private EditText idZona;
    private EditText nombreSucursal;
    private EditText jefeSucursal;
    private EditText direccionSucursal;
    private EditText telefonoSucursal;
    private Button boton;
    private Sucursal nuevaSucursal = new Sucursal();
    SpeechRecognitionHelper SRHelper;
    static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    Button btnConsultar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_consultar);
        idSucursal =  (EditText) findViewById(R.id.editIdSucursal);
        idEmpresa =  (EditText) findViewById(R.id.editIdEmpresa);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreSucursal = (EditText) findViewById(R.id.editNombreSucursal);
        jefeSucursal = (EditText) findViewById(R.id.editJefeSucursal);
        direccionSucursal = (EditText) findViewById(R.id.editDireccionSucursal);
        telefonoSucursal = (EditText) findViewById(R.id.editTelefonoSucursal);
        boton = (Button) findViewById(R.id.botonMapa);
        btnConsultar1 = (Button) findViewById(R.id.btnConsultar);
        boton.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sucursal_consultar, menu);
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

    public void consultarSucursal(View v){
        if(!camposVacios()) {

            nuevaSucursal.setIdSucursal(Integer.parseInt(idSucursal.getText().toString()));
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            nuevaSucursal = cdb.consultarSucursal(nuevaSucursal.getIdSucursal());
            if (nuevaSucursal == null) {
                Toast.makeText(this, getResources().getString(R.string.sucursal_noencontrada), Toast.LENGTH_SHORT).show();
            } else {
                idSucursal.setText(Integer.toString(nuevaSucursal.getIdSucursal()));
                idEmpresa.setText(Integer.toString(nuevaSucursal.getIdEmpresa()));
                idZona.setText(Integer.toString(nuevaSucursal.getIdZona()));
                nombreSucursal.setText(nuevaSucursal.getNombreSucursal());
                jefeSucursal.setText(nuevaSucursal.getJefeSucursal());
                direccionSucursal.setText(nuevaSucursal.getDireccionSucursal());
                telefonoSucursal.setText(nuevaSucursal.getTelefonoSucursal());
               Toast.makeText(this, getResources().getString(R.string.sucursal_consultada), Toast.LENGTH_SHORT).show();
                boton.setEnabled(true);
            }
            cdb.cerrar();
        }
    }
    public void limpiarTexto(View v){
        idSucursal.setText("");
        idEmpresa.setText("");
        idZona.setText("");
        nombreSucursal.setText("");
        jefeSucursal.setText("");
        direccionSucursal.setText("");
        telefonoSucursal.setText("");
        boton.setEnabled(false);

    }
    public boolean camposVacios(){
        if(idSucursal.getText().toString().equals("")){
            return true;
        }else
            return false;
    }

    public void consultarMapa(View v){
        Intent ni = new Intent(this, MapsActivity.class);
        ni.putExtra("longitud",nuevaSucursal.getLongitud());
        ni.putExtra("latitud",nuevaSucursal.getLatitud());
        ni.putExtra("direccion",nuevaSucursal.getDireccionSucursal());
        ni.putExtra("nombreSucursal","Sucursal " + nuevaSucursal.getNombreSucursal() + " en " + obtenerMunicipioDepto());
        startActivity(ni);
    }

    public String obtenerMunicipioDepto(){
        ControlDB cdb = new ControlDB(this);
        cdb.abrir();
        Zona zona = cdb.consultarZona(nuevaSucursal.getIdZona());
        cdb.cerrar();
        return   zona.getMunicipio() + ", " + zona.getDepartamento();

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
                idSucursal.setText(matches.get(0));//el resultado lo introducimos en el EditText del id Empresa
                btnConsultar1.performClick();//realizamos el click al boton de consultar
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}