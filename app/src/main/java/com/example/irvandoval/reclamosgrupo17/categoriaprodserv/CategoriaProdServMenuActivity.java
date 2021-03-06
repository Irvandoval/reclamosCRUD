package com.example.irvandoval.reclamosgrupo17.categoriaprodserv;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.irvandoval.reclamosgrupo17.R;

public class CategoriaProdServMenuActivity extends ListActivity {
    String menu[]; // que opciones contendra el menu?
    String activities[] = {"CategoriaProdServInsertarActivity","CategoriaProdServEliminarActivity"
            ,"CategoriaProdServConsultarActivity","CategoriaProdServActualizarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.GRAY);
        setMenu(getResources()); // esto nos sirve para llenar el menu con opciones desde resources (string.xml)
        ArrayAdapter<String> adapter= new  ArrayAdapter<>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.GRAY);
        try{
            Class<?> clase=Class.forName("com.example.irvandoval.reclamosgrupo17.categoriaprodserv." + nombreValue); //usuario se cambia por el que esten haciendo
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
