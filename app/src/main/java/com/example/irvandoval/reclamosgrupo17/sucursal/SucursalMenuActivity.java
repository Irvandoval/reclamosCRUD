package com.example.irvandoval.reclamosgrupo17.sucursal;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.irvandoval.reclamosgrupo17.R;

public class SucursalMenuActivity extends ListActivity {
    String[] menu;
    String activities[] = {"SucursalInsertarActivity","SucursalEliminarActivity"
            ,"SucursalConsultarActivity","SucursalActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.GREEN);
        setMenu(getResources()); // esto nos sirve para llenar el menu con opciones desde resources (string.xml)
        ArrayAdapter<String> adapter= new  ArrayAdapter<>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.YELLOW);
        try{
            Class<?> clase=Class.forName("com.example.irvandoval.reclamosgrupo17.sucursal." + nombreValue);
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
