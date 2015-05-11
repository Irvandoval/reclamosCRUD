package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.irvandoval.reclamosgrupo17.R;

public class CategoriaEmpresaMenuActivity extends ListActivity {
    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro", "ActualizarRegistro"};
            String[] activities={"CategoriaEmpresaInsertarActivity","CategoriaEmpresaEliminarActivity","CategoriaEmpresaConsultarActivity",
            "CategoriaActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_categoria_empresa_menu);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0, 0, 255));
        ArrayAdapter<String> adapter= new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);

    }


    @Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categoria_empresa_menu, menu);
        return true;
    }*/
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
        try{
            Class<?>
                    clase=Class.forName("com.example.irvandoval.reclamosgrupo17"+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
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
}
