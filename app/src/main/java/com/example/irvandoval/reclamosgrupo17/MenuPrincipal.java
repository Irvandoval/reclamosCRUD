package com.example.irvandoval.reclamosgrupo17;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MenuPrincipal extends ListActivity {
    //Elementos de la lista a mostrar
    String[] menu = {"Tabla Usuario", "Tabla Reclamo", "Tabla Detalle Reclamo"
            , "Tabla Estado Reclamo", "Tabla Producto/Servicio", "Tabla Categoria Producto/Servicio"
            , "Tabla Empresa", "Tabla Categoria Empresa", "Tabla Sucursal", "Tabla Zona", "Llenar DB"};
    // Elementos de las actividades a invocar
    String[] activities = {"UsuarioMenuActivity", "ReclamoMenuActivity", "DetalleReclamoMenuActivity"
            , "EstadoReclamoMenuActivity", "ProdServMenuActivity", "CategoriaProdServMenuActivity"
            , "EmpresaMenuActivity", "CategoriaEmpresaMenuActivity", "SucursalMenuActivity", "ZonaMenuActivity"};
    // Elementos de los paquetes a que pertenecen las actividades
    String[] packages = {"usuario","reclamo","detallereclamo","estadoreclamo","prodserv","categoriaprodserv","empresa"
                        ,"categoriaempresa","sucursal","zona"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        } else {// si se ha elegido Llenar DB
            //aqui hay que implementar el llenado de datos.
        }
    }
}
