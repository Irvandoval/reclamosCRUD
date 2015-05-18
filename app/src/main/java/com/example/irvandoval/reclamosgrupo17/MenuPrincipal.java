package com.example.irvandoval.reclamosgrupo17;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MenuPrincipal extends ListActivity {
    //Elementos de la lista a mostrar
    String[] menu;
    // Elementos de las actividades a invocar
    String[] activities = {"UsuarioMenuActivity", "ReclamoMenuActivity", "DetalleReclamoMenuActivity",
                            "EstadoReclamoMenuActivity", "ProdServMenuActivity", "CategoriaProdServMenuActivity",
                            "EmpresaMenuActivity", "CategoriaEmpresaMenuActivity", "SucursalMenuActivity", "ZonaMenuActivity"};
    // Elementos de los paquetes a que pertenecen las actividades
    String[] packages = {"usuario","reclamo","detallereclamo","estadoreclamo","prodserv","categoriaprodserv","empresa"
                        ,"categoriaempresa","sucursal","zona"};
    ControlDB BDHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        setMenu(res);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position != 10) { //si se ha elegido un elemento de tabla
            String nombreValue = activities[position];
            String paquete = packages[position];
            try {
                Class<?> clase = Class.forName("com.example.irvandoval.reclamosgrupo17." + paquete + "." + nombreValue);
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            BDHelper = new ControlDB(getApplicationContext());
            BDHelper.abrir();
            String toast = BDHelper.llenarDB();
            BDHelper.cerrar();
            Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Llena el Array de menu con el contenido de strings.xml
     * @param res el resource de la aplicacion
     */
    private void setMenu(Resources res){
        menu = new String[] {res.getString(R.string.title_activity_usuario_menu),
                            res.getString(R.string.title_activity_reclamo_menu),
                            res.getString(R.string.title_activity_detalle_reclamo_menu),
                            res.getString(R.string.title_activity_estado_reclamo_menu),
                            res.getString(R.string.title_activity_prod_serv_menu),
                            res.getString(R.string.title_activity_categoria_prod_serv_menu),
                            res.getString(R.string.title_activity_empresa_menu),
                            res.getString(R.string.title_activity_categoria_empresa_menu),
                            res.getString(R.string.title_activity_sucursal_menu),
                            res.getString(R.string.title_activity_zona_menu),
                            res.getString(R.string.fill_db)};
    }
}
