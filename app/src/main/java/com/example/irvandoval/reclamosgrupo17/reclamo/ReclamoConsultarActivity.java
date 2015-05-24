package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class ReclamoConsultarActivity extends ActionBarActivity {
    EditText titulo;
    EditText motivo;
    EditText fecha;
    EditText dui;
    EditText idDet;
    EditText idSucu;
    EditText idrecl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_consultar);
        titulo=(EditText)findViewById(R.id.TituloReclamo);
        motivo=(EditText)findViewById(R.id.MotivoReclamo);
        fecha=(EditText)findViewById(R.id.fechaReclamo);
        fecha.addTextChangedListener(new MaskTextWatcher("##/##/####"));
        dui=(EditText)findViewById(R.id.recDui);
        dui.addTextChangedListener(new MaskTextWatcher("########-#"));
        idDet=(EditText)findViewById(R.id.idDetalle);
        idrecl=(EditText)findViewById(R.id.idReclamo);
        idSucu=(EditText)findViewById(R.id.idSucursal);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reclamo_consultar, menu);
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
    public void consultarReclamo(View v){
        ControlDB hero;
        hero = new ControlDB(this);
        Reclamo Reca= new Reclamo();
        int idre=Integer.parseInt(idrecl.getText().toString());
        hero.abrir();
        Reca=hero.consultarReclamo(idre);
        hero.cerrar();
        if(Reca==null){
            Toast.makeText(this, "No existe reclamo con ese ID", Toast.LENGTH_SHORT).show();
        }else{
            titulo.setText(Reca.getTitulo());
            motivo.setText(Reca.getMotivoReclamo());
            dui.setText(Reca.getDui());
            fecha.setText(Reca.getFechaReclamo());
           idDet.setText(String.valueOf(Reca.getIdDetalle()));
            idSucu.setText(String.valueOf(Reca.getIdSucursal()));
            int cada=Integer.parseInt(String.valueOf(Reca.getIdEstadoReclamo()));

            Toast.makeText(this, "Reclamo Consultado", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto1(View v){
        titulo.setText("");
        motivo.setText("");
        fecha.setText("");
        dui.setText("");
        idSucu.setText("");
        idDet.setText("");
        idrecl.setText("");
        dui.setText("");

    }
}
