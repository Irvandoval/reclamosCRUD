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

public class UsuarioConsultarActivity extends ActionBarActivity {
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
        setContentView(R.layout.activity_usuario_consultar);
        nombreUsuario = (EditText)findViewById(R.id.editUsuarioNombre);
        apellidoUsuario = (EditText)findViewById(R.id.editUsuarioApellido);
        dui =  (EditText)findViewById(R.id.editUsuarioDui);
        dui.addTextChangedListener(new MaskTextWatcher("########-#"));
        email = (EditText) findViewById(R.id.editUsuarioEmail);
        telefono = (EditText) findViewById(R.id.editUsuarioTelefono);
        telefono.addTextChangedListener( new MaskTextWatcher("####-####"));
        edad = (EditText) findViewById(R.id.editUsuarioEdad);
        sexo = (EditText) findViewById(R.id.editUsuarioSexo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario_consultar, menu);
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

    public void consultarUsuario(View v){
       if(!camposVacios()) {
           ControlDB hero = new ControlDB(this);
           Usuario herouser;
           hero.abrir();
           herouser = hero.consultarUsuario(dui.getText().toString());
           hero.cerrar();
           if (herouser == null) {
               Toast.makeText(this, getResources().getString(R.string.usuario_noencontrado), Toast.LENGTH_SHORT).show();
           } else {
               nombreUsuario.setText(herouser.getNombreUsuario());
               apellidoUsuario.setText(herouser.getApellidoUsuario());
               email.setText(herouser.getEmail());
               telefono.setText(herouser.getTelefono());
               edad.setText(String.valueOf(herouser.getEdad()));
               sexo.setText(herouser.getSexo());
               Toast.makeText(this, getResources().getString(R.string.usuario_consultado), Toast.LENGTH_SHORT).show();
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
    }
    public boolean camposVacios(){
        if(dui.getText().toString().equals("") )
            return true;
        else

            return false;
    }
}
