package com.example.irvandoval.reclamosgrupo17.zona;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.irvandoval.reclamosgrupo17.R;

public class ZonaMenuActivity extends ListActivity {
    private String[] menu;
    private String[] activities = {"ZonaInsertarActivity","ZonaEliminarActivity"
            ,"ZonaConsultarActivity","ZonaActualizarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setBackgroundColor(Color.MAGENTA);
        setMenu(getResources()); // esto nos sirve para llenar el menu con opciones desde resources (string.xml)
        ArrayAdapter<String> adapter= new  ArrayAdapter<>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(66, 165, 245));
        try{
            Class<?> clase=Class.forName("com.example.irvandoval.reclamosgrupo17.zona." + nombreValue); //usuario se cambia por el que esten haciendo
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zona_menu, menu);
        return true;
    }



    /**
     * Llena el Array de menu con el contenido de strings.xml
     * @param res el resource de la aplicacion
     */
    private void setMenu(Resources res){
        menu = new String[] {res.getString(R.string.insertar),res.getString(R.string.eliminar)
                ,res.getString(R.string.consultar),res.getString(R.string.actualizar)};
    }

}
