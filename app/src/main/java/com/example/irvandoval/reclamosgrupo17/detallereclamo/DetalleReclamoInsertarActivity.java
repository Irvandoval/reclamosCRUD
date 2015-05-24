package com.example.irvandoval.reclamosgrupo17.detallereclamo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
public class DetalleReclamoInsertarActivity  extends ActionBarActivity {
    ControlDB helper;
    EditText detalle_id;
    EditText prod_ser;
    EditText descripcion_detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_insertar);
        helper = new ControlDB(this);
        detalle_id= (EditText) findViewById(R.id.editDetalle_id);
        prod_ser= (EditText) findViewById(R.id.idProdServ);
        descripcion_detalle = (EditText) findViewById(R.id.editDescripcionDetalle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_reclamo_insertar, menu);
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

    public void insertarEstado(View v) {

        if(!camposVacios()) {
            String regInsertados;
            DetalleReclamo nuevoDetalle = new DetalleReclamo();
            nuevoDetalle.setIdDetalle(Integer.parseInt(detalle_id.getText().toString()));
            nuevoDetalle.setIdProdServ(Integer.parseInt(prod_ser.getText().toString()));
            nuevoDetalle.setDescripcionDetalle(descripcion_detalle.getText().toString());
            helper.abrir();
            regInsertados = helper.insertar(nuevoDetalle);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public boolean camposVacios(){
        if(detalle_id.getText().toString().equals("") || prod_ser.getText().toString().equals("")
                || descripcion_detalle.getText().toString().equals(""))
        {Toast.makeText(this, "Ha dejado campos vacios",
                Toast.LENGTH_LONG).show();
            return true;}
        else

            return false;
    }


    public void limpiarTexto(View v) {
        detalle_id.setText("");
        prod_ser.setText("");
        descripcion_detalle.setText("");

    }
}
