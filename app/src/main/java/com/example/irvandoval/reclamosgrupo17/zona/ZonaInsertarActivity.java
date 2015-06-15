package com.example.irvandoval.reclamosgrupo17.zona;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZonaInsertarActivity extends ActionBarActivity {
        EditText idZona;
        EditText nombreZona;
        EditText municipio;
        EditText departamento;

    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/misfotos/";
    private File file = new File(ruta_fotos);
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_insertar);
        idZona = (EditText) findViewById(R.id.editIdZona);
        nombreZona = (EditText) findViewById(R.id.editNombreZona);
        municipio = (EditText) findViewById(R.id.editMunicipio);
        departamento =  (EditText) findViewById(R.id.editDepartamento);






        //======== codigo nuevo ========
        boton = (Button) findViewById(R.id.mainbttomarfotozona);
        //Si no existe crea la carpeta donde se guardaran las fotos
        file.mkdirs();
        //accion para el boton
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String file = ruta_fotos + getCode() + ".jpg";
                File mi_foto = new File( file );
                try {
                    mi_foto.createNewFile();
                } catch (IOException ex) {
                    Log.e("ERROR ", "Error:" + ex);
                }
                //
                Uri uri = Uri.fromFile( mi_foto );
                //Abre la camara para tomar la foto
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Guarda imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //Retorna a la actividad
                startActivityForResult(cameraIntent, 0);
            }

        });
        //====== codigo nuevo:end ======
    }

    /**
     60  * Metodo privado que genera un codigo unico segun la hora y fecha del sistema
     61  * @return photoCode
     62  * */
    @SuppressLint("SimpleDateFormat")
    private String getCode()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "pic_" + date;
        return photoCode;
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zona_insertar, menu);
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

    public void insertarZona(View v){
        if(!camposVacios()) {
            Zona nuevaZona = new Zona();
            String respuesta;
            nuevaZona.setIdZona(Integer.parseInt(idZona.getText().toString()));
            nuevaZona.setNombreZona(nombreZona.getText().toString());
            nuevaZona.setMunicipio(municipio.getText().toString());
            nuevaZona.setDepartamento(departamento.getText().toString());
            ControlDB cdb = new ControlDB(this);
            cdb.abrir();
            respuesta = cdb.insertar(nuevaZona);
            cdb.cerrar();
            Toast.makeText(this, getResources().getString(R.string.cantidad_insertados) + respuesta, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        idZona.setText("");
        nombreZona.setText("");
        municipio.setText("");
        departamento.setText("");
    }
    public boolean camposVacios(){
        if(idZona.getText().toString().equals("") || nombreZona.getText().toString().equals("")
                || municipio.getText().toString().equals("") || departamento.getText().toString().equals("")){
            return true;
        }else
            return false;
    }
}
