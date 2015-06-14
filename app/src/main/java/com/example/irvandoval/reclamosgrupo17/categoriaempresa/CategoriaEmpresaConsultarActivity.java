package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

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

public class CategoriaEmpresaConsultarActivity extends ActionBarActivity {
    EditText nombCE;
    EditText descCE;
    EditText idCE;
    EditText canempres;
    SpeechRecognitionHelper SRHelper;
    static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_empresa_consultar);
        idCE=(EditText)findViewById(R.id.IDCE);
        nombCE = (EditText)findViewById(R.id.NombreCE);
        descCE = (EditText)findViewById(R.id.DescripcionCE);
        canempres=(EditText)findViewById(R.id.cantEmpresas );
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_consultar, menu);
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
    public void consultarCatEmpresa(View v){
        CategoriaEmpresa CEhero=new CategoriaEmpresa();
        String res;
        ControlDB hero;
        hero = new ControlDB(this);
        hero.abrir();
        int idce=Integer.parseInt(idCE.getText().toString());
        CEhero=hero.consultarCategoriaEmpresa(idce);
        hero.cerrar();
        if(CEhero==null){
            Toast.makeText(this, getResources().getString(R.string.categoria_empresa_noencontrada), Toast.LENGTH_SHORT).show();
        }else{
            nombCE.setText(CEhero.getNombreCategoriaEmp());
            descCE.setText(CEhero.getDescripcionCategoriaEmp());
            canempres.setText(String.valueOf(CEhero.getCantidadEmpresas()));

            Toast.makeText(this, getResources().getString(R.string.categoria_empresa_consultada), Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarCE(View v){
        nombCE.setText("");
        descCE.setText("");
        idCE.setText("");
        canempres.setText("");

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
                idCE.setText(matches.get(0));//el resultado lo introducimos en el EditText del id Empresa
                btnConsultar.performClick();//realizamos el click al boton de consultar
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
