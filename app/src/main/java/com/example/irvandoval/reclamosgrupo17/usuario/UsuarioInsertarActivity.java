package com.example.irvandoval.reclamosgrupo17.usuario;

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

public class UsuarioInsertarActivity extends ActionBarActivity {
    EditText nombreUsuario;
    EditText apellidoUsuario;
    EditText dui;
    EditText email;
    EditText telefono;
    EditText edad;
    EditText sexo;
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
            //implementar insercion
            ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(nuevoUsuario);
            hero.cerrar();
            if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
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
