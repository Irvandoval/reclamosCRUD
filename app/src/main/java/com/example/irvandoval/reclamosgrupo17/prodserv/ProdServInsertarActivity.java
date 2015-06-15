package com.example.irvandoval.reclamosgrupo17.prodserv;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.irvandoval.reclamosgrupo17.ControlDB;
import com.example.irvandoval.reclamosgrupo17.R;
import com.example.irvandoval.reclamosgrupo17.prodserv.ProdServ;

import java.io.File;
import java.util.Calendar;

public class ProdServInsertarActivity extends ActionBarActivity {
    EditText IdProdServ;
    EditText IdCategoriaProdServ;
    EditText nombProdServ;
    EditText descriProdServ;

    Button TomarFoto;
    ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_serv_insertar);
        IdProdServ = (EditText)findViewById(R.id.idProdServ);
        IdCategoriaProdServ = (EditText)findViewById(R.id.IdCategoriaPServ);
        nombProdServ = (EditText)findViewById(R.id.nombreProdServ);
        descriProdServ = (EditText)findViewById(R.id.descripcionProdServ);




        TomarFoto = (Button) findViewById(R.id.mainbttomarfotoprodserv);
        //image = (ImageView) findViewById(R.id.mainimage);
        TomarFoto.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState.getString("Foto")));
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
        getMenuInflater().inflate(R.menu.menu_prod_serv_insertar, menu);
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


    public void insertarProdServInsertar(View v){
        ProdServ nuevoProdServ = new ProdServ();
        String res;
        if (!camposVacios()) {
            nuevoProdServ.setIdProdServ(Integer.parseInt(IdProdServ.getText().toString()));
            nuevoProdServ.setIdCategoriaProd(Integer.parseInt(IdCategoriaProdServ.getText().toString()));
            nuevoProdServ.setNombreProdServ(nombProdServ.getText().toString());
            nuevoProdServ.setDescripcionProdServ(descriProdServ.getText().toString());
            ControlDB hero;
            hero = new ControlDB(this);
            hero.abrir();
            res = hero.insertar(nuevoProdServ);
            System.err.println("AL SALIR IMPRIME: " + res);
            if (res.equals("error_insertar")) {
                Toast.makeText(this, getResources().getString(R.string.error_insertar), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.cantidad_insertados) + res, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void limpiarTexto4(View v){
        IdProdServ.setText(" ");
        nombProdServ.setText(" ");
        descriProdServ.setText(" ");
    }


    public boolean camposVacios(){
        if( IdProdServ.getText().toString().equals("") || nombProdServ.getText().toString().equals("") || descriProdServ.getText().toString().equals(""))
            return true;
        else
            return false;
    }
}
