package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class ReclamoEliminarActivity extends ActionBarActivity {
    EditText titulo;
    EditText motivo;
    EditText fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_eliminar);
        titulo=(EditText)findViewById(R.id.TituloReclamo);
        motivo=(EditText)findViewById(R.id.MotivoReclamo);
        fecha=(EditText)findViewById(R.id.fechaReclamo);
        fecha.addTextChangedListener(new MaskTextWatcher("##/##/####"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reclamo_eliminar, menu);
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
    public void limpiarTexto1(View v){
        titulo.setText("");
        motivo.setText("");
        fecha.setText("");

    }
}
