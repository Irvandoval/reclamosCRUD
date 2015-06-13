package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
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
import java.util.Calendar;

public class CategoriaEmpresaInsertarActivity extends ActionBarActivity {
    EditText nombCE;
    EditText descCE;
    EditText idCE;


    Button TomarFoto;
    ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_empresa_insertar);
        nombCE = (EditText)findViewById(R.id.NombreCE);
        descCE = (EditText)findViewById(R.id.DescripcionCE);
        idCE = (EditText)findViewById(R.id.IDCE);

        TomarFoto = (Button) findViewById(R.id.mainbttomarfoto);
        //image = (ImageView) findViewById(R.id.mainimage);
        TomarFoto.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }
    }



    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);

    }

    View.OnClickListener onClick=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
// TODO Auto-generated method stub
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photo =new File(Environment.getExternalStorageDirectory(),String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg");
            file=Uri.fromFile(photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent,FOTOGRAFIA);
        }
    };
    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        if (RequestCode==FOTOGRAFIA){
            if(ResultCode == RESULT_OK){
                image.setImageURI(file);
            }
            else{
                Toast.makeText(getApplicationContext(),"fotografia No tomada", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_insertar, menu);
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
    public void insertarCatEmpresa(View v){
        CategoriaEmpresa CEhero=new CategoriaEmpresa();
        String res;
        if (!camposVacios()) {
            CEhero.setNombreCategoriaEmp(nombCE.getText().toString());
            CEhero.setDescripcionCategoriaEmp(descCE.getText().toString());
           CEhero.setIdCategoriaEmp(Integer.parseInt(idCE.getText().toString()));
            CEhero.setCantidadEmpresas(0);
           // CEhero.setIdCategoriaEmp(1);
             ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(CEhero);
            hero.cerrar();

           if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void limpiarCE(View v){
        nombCE.setText("");
        descCE.setText("");
        idCE.setText("");

    }
    public boolean camposVacios(){
        if(nombCE.getText().toString().equals("") || descCE.getText().toString().equals("") || idCE.getText().toString().equals(""))
            return true;
        else

            return false;
    }

}
