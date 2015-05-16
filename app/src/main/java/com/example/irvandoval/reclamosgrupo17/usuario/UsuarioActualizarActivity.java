package com.example.irvandoval.reclamosgrupo17.usuario;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.majoramask.MaskTextWatcher;

public class UsuarioActualizarActivity extends ActionBarActivity {
    EditText nombreUsuario;
    EditText apellidoUsuario;
    EditText dui;
    EditText email;
    EditText telefono;
    EditText edad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
        nombreUsuario = (EditText)findViewById(R.id.editUsuarioNombre);
        apellidoUsuario = (EditText)findViewById(R.id.editUsuarioApellido);
        dui =  (EditText)findViewById(R.id.EditUsuarioDui);
        dui.addTextChangedListener(new MaskTextWatcher("########-#"));
        email = (EditText) findViewById(R.id.editUsuarioEmail);
        telefono = (EditText) findViewById(R.id.editUsuarioTelefono);
        telefono.addTextChangedListener( new MaskTextWatcher("####-####"));
        edad = (EditText) findViewById(R.id.editUsuarioEdad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario_actualizar, menu);
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

    public void actualizarUsuario(View v){
    }
    public void limpiarTexto(View v){
        nombreUsuario.setText("");
        apellidoUsuario.setText("");
        dui.setText("");
        email.setText("");
        telefono.setText("");
        edad.setText("");
    }

}
