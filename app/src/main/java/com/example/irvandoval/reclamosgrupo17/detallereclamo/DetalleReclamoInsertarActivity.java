package com.example.irvandoval.reclamosgrupo17.detallereclamo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    Button TomarFoto;


    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/misfotos/";
    private File file = new File(ruta_fotos);
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo_insertar);
        helper = new ControlDB(this);
        detalle_id= (EditText) findViewById(R.id.editDetalle_id);
        prod_ser= (EditText) findViewById(R.id.idProdServ);
        descripcion_detalle = (EditText) findViewById(R.id.editDescripcionDetalle);









        //======== codigo nuevo ========
        boton = (Button) findViewById(R.id.mainbttomarfotodetarecl);
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
