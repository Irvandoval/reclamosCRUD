package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class ReclamoInsertarActivity extends ActionBarActivity {
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
        setContentView(R.layout.activity_reclamo_insertar);
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
        getMenuInflater().inflate(R.menu.menu_reclamo_insertar, menu);
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
    public void insertarReclamo(View v){
        ControlDB hero;
        hero = new ControlDB(this);
        hero.abrir();
        Reclamo Reca=new Reclamo();
        Reca.setIdReclamo(Integer.parseInt(idrecl.getText().toString()));
        Reca.setIdDetalle(Integer.parseInt(idDet.getText().toString()));
        Reca.setIdSucursal(Integer.parseInt(idSucu.getText().toString()));
        Reca.setIdEstadoReclamo(1);
        Reca.setDui(dui.getText().toString());
        Reca.setFechaReclamo(fecha.getText().toString());
        Reca.setTitulo(titulo.getText().toString());
        Reca.setMotivoReclamo(motivo.getText().toString());
        String res=hero.insertar(Reca);
        hero.cerrar();
        Toast.makeText(this, getResources().getString(R.string.cantidad_insertados)+ res, Toast.LENGTH_SHORT).show();
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
    public boolean camposVacios(){
        if(dui.getText().toString().equals("") || titulo.getText().toString().equals("")
                || motivo.getText().toString().equals("") ||fecha.getText().toString().equals("")
                || idSucu.getText().toString().equals("") || idDet.getText().toString().equals("") || idrecl.getText().toString().equals(""))
            return true;
        else

            return false;
    }

}
