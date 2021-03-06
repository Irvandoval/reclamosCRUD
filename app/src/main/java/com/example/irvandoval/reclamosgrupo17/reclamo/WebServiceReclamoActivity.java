package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.Controlador;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

import org.json.JSONArray;
import org.json.JSONObject;

public class WebServiceReclamoActivity extends ActionBarActivity {
   private static String urlHostingGratuito = "http://irvandoval.comxa.com/";
    private static String urlHostingLocal="http://192.168.0.100:8080/WSG17/webresources/g17.entidad.reclamo/all/";
    EditText fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service__reclamo_);
        fecha = (EditText) findViewById(R.id.editFecha);
        fecha.addTextChangedListener(new MaskTextWatcher("####-##-##"));
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_service__reclamo_, menu);
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


    public void reclamosPorFechaHosting(View v){
       if(!fecha.getText().toString().equals("")) {
           Intent intent = new Intent(this, MostrarPorFechaReclamosActivity.class);
           intent.putExtra("year", fecha.getText().toString().substring(0, 4));
           intent.putExtra("month", fecha.getText().toString().substring(5, 7));
           intent.putExtra("day", fecha.getText().toString().substring(8, 10));
           startActivity(intent);
       }
    }
    public void llenarReclamosHosting(View v){
        Controlador parser = new Controlador();
        String url = urlHostingGratuito + "ws_reclamo_all.php";
        String json = parser.obtenerRespuestaDeURL(url,this);

        ControlDB controlDB = new ControlDB(this);
        controlDB.abrir();

        try{
            JSONArray reclamos = new JSONArray(json);
            Reclamo nuevoReclamo = new Reclamo();
            for(int i = 0; i< reclamos.length(); i++){
                JSONObject reclamo = reclamos.getJSONObject(i);
                nuevoReclamo.setIdReclamo(reclamo.getInt("ID_RECLAMO"));
                nuevoReclamo.setDui(reclamo.getString("DUI"));
                nuevoReclamo.setIdEstadoReclamo(reclamo.getInt("ID_ESTADO_RECLAMO"));
                nuevoReclamo.setIdSucursal(reclamo.getInt("ID_SUCURSAL"));
                nuevoReclamo.setIdDetalle(reclamo.getInt("ID_DETALLE"));
                nuevoReclamo.setTitulo(reclamo.getString("TITULO"));
                nuevoReclamo.setMotivoReclamo(reclamo.getString("MOTIVO_RECLAMO"));
                nuevoReclamo.setFechaReclamo(reclamo.getString("FECHA_RECLAMO"));
                controlDB.insertar(nuevoReclamo);
            }
        }catch(Exception e){
            Toast.makeText(this, "Error de conexion 4", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
         controlDB.cerrar();
        Toast.makeText(this, "Registros via Host Gratuito Insertados Correctamente al DB Local", Toast.LENGTH_LONG).show();
    }

    public void llenarReclamosLocal(View v){
        Controlador parser = new Controlador();
        String url = urlHostingLocal ;//"ws_reclamo_all.php"
        String json = parser.obtenerRespuestaDeURL(url,this);

        ControlDB controlDB = new ControlDB(this);
        controlDB.abrir();

        try{
            JSONArray reclamos = new JSONArray(json);
            Reclamo nuevoReclamo = new Reclamo();
            for(int i = 0; i< reclamos.length(); i++){
                JSONObject reclamo = reclamos.getJSONObject(i);
                nuevoReclamo.setIdReclamo(reclamo.getInt("idReclamo"));
                nuevoReclamo.setDui(reclamo.getJSONObject("dui").getString("dui"));
                nuevoReclamo.setIdEstadoReclamo(reclamo.getJSONObject("idEstadoReclamo").getInt("idEstadoReclamo"));
                nuevoReclamo.setIdSucursal(reclamo.getJSONObject("idSucursal").getInt("idSucursal"));
                nuevoReclamo.setIdDetalle(reclamo.getJSONObject("idDetalle").getInt("idDetalle"));
                nuevoReclamo.setTitulo(reclamo.getString("titulo"));
                nuevoReclamo.setMotivoReclamo(reclamo.getString("motivoReclamo"));
                nuevoReclamo.setFechaReclamo(reclamo.getString("fechaReclamo"));

                controlDB.insertar(nuevoReclamo);
            }
        }catch(Exception e){
            Toast.makeText(this, "Error de conexion 4", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        controlDB.cerrar();
        Toast.makeText(this, "Registros via Host Local Insertados Correctamente al DB Local", Toast.LENGTH_LONG).show();
    }
}
