package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

import android.content.res.Resources;
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
    String[] menu;
            String[] activities={"CategoriaEmpresaInsertarActivity","CategoriaEmpresaEliminarActivity","CategoriaEmpresaConsultarActivity",
            "CategoriaEmpresaActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0, 0, 255));
        setMenu(getResources());
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);

    }


    @Override
    protected void onListItemClick(ListView l,View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
        try{
            Class<?> clase=Class.forName("com.example.irvandoval.reclamosgrupo17.categoriaempresa."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void setMenu(Resources res){
        menu = new String[] {res.getString(R.string.insertar),res.getString(R.string.eliminar)
                ,res.getString(R.string.consultar),res.getString(R.string.actualizar)};
    }

}
