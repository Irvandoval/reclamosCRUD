package com.example.irvandoval.reclamosgrupo17.usuario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
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
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UsuarioInsertarActivity extends ActionBarActivity {
    EditText nombreUsuario;
    EditText apellidoUsuario;
    EditText dui;
    EditText email;
    EditText telefono;
    EditText edad;
    EditText sexo;


    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/misfotos/";
    private File file = new File(ruta_fotos);
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_insertar);
        nombreUsuario = (EditText)findViewById(R.id.editUsuarioNombre);
        apellidoUsuario = (EditText)findViewById(R.id.editUsuarioApellido);
        dui =  (EditText)findViewById(R.id.editUsuarioDui);
        dui.addTextChangedListener( new MaskTextWatcher("########-#"));
        email = (EditText) findViewById(R.id.editUsuarioEmail);
        telefono = (EditText) findViewById(R.id.editUsuarioTelefono);
        telefono.addTextChangedListener( new MaskTextWatcher("####-####"));
        edad = (EditText) findViewById(R.id.editUsuarioEdad);
        sexo = (EditText) findViewById(R.id.editUsuarioSexo);




        //======== codigo nuevo ========
        boton = (Button) findViewById(R.id.mainbttomarfotousuario);
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
        getMenuInflater().inflate(R.menu.menu_usuario_insertar, menu);
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

    public void insertarUsuario(View v){
       Usuario nuevoUsuario = new Usuario();
        String res;
        if (!camposVacios()) {
            nuevoUsuario.setNombreUsuario(nombreUsuario.getText().toString());
            nuevoUsuario.setApellidoUsuario(apellidoUsuario.getText().toString());
            nuevoUsuario.setDui(dui.getText().toString());
            nuevoUsuario.setEmail(email.getText().toString());
            nuevoUsuario.setTelefono(telefono.getText().toString());
            nuevoUsuario.setEdad(Integer.parseInt(edad.getText().toString()));
            nuevoUsuario.setSexo(sexo.getText().toString());
            ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(nuevoUsuario);
            System.err.println("AL SALIR IMPRIME: " + res);
            if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void limpiarTexto(View v){
        nombreUsuario.setText("");
        apellidoUsuario.setText("");
        dui.setText("");
        email.setText("");
        telefono.setText("");
        edad.setText("");
        sexo.setText("");
    }

    public boolean camposVacios(){
        if(dui.getText().toString().equals("") || nombreUsuario.getText().toString().equals("")
                || apellidoUsuario.getText().toString().equals("") || email.getText().toString().equals("")
                || telefono.getText().toString().equals("") || edad.getText().toString().equals("") || sexo.getText().toString().equals(""))
            return true;
        else

            return false;
    }

}
