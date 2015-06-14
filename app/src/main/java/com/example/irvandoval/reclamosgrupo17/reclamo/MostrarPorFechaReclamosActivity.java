package com.example.irvandoval.reclamosgrupo17.reclamo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.irvandoval.reclamosgrupo17.Controlador;
import com.example.irvandoval.reclamosgrupo17.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;


public class MostrarPorFechaReclamosActivity extends Activity {
    private static String urlHosting = "http://irvandoval.comxa.com/";
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<>();
    String year;
    String month;
    String day;
    File file;
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_por_fecha_reclamos);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }

        TextView txt = (TextView) findViewById(R.id.textoTitulo);
        txt.setText("Reclamos a partir del " + day +" de " + month + " de " + year);
    }

    public void createData() {
        Controlador parser = new Controlador();
        Intent intent = getIntent();
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        String url = urlHosting + "ws_reclamo_query_fecha.php?year=" + year + "&month=" + month + "&day=" + day;
        Calendar c = Calendar.getInstance();
        String json = parser.obtenerRespuestaDeURL(url,this);
        try{
            JSONArray reclamos = new JSONArray(json);
            File root = android.os.Environment.getExternalStorageDirectory();
            Toast.makeText(this,"directorio: "+ root, Toast.LENGTH_LONG).show();
            File dir = new File(root.getAbsolutePath() + "/download");
            dir.mkdirs();
            file = new File(dir, "reclamos.txt");
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("/*GENERADO: " + c.getTime() + "*/" +"\n");
            for(int i = 0; i< reclamos.length(); i++){
                JSONObject reclamo = reclamos.getJSONObject(i);
                Group group = new Group(reclamo.getString("TITULO"));
                group.children.add("ID Reclamo: " + Integer.toString(reclamo.getInt("ID_RECLAMO")));
                group.children.add("DUI: " + reclamo.getString("DUI"));
                group.children.add("ID Estado Reclamo: " + Integer.toString(reclamo.getInt("ID_ESTADO_RECLAMO")));
                group.children.add("ID Sucursal: " + Integer.toString(reclamo.getInt("ID_SUCURSAL")));
                group.children.add("ID Detalle: " + Integer.toString(reclamo.getInt("ID_DETALLE")));
                group.children.add("Motivo: " + reclamo.getString("MOTIVO_RECLAMO"));
                group.children.add("Fecha: " + reclamo.getString("FECHA_RECLAMO"));
                groups.append(i, group);
                try{
                    pw.println("Titulo: " + reclamo.getString("TITULO"));
                    pw.println("ID Reclamo:" + Integer.toString(reclamo.getInt("ID_RECLAMO")));
                    pw.println("DUI: " + reclamo.getString("DUI"));
                    pw.println("ID Estado Reclamo: " + Integer.toString(reclamo.getInt("ID_ESTADO_RECLAMO")));
                    pw.println("ID Sucursal: " + Integer.toString(reclamo.getInt("ID_SUCURSAL")));
                    pw.println("ID Detalle: " + Integer.toString(reclamo.getInt("ID_DETALLE")));
                    pw.println("Motivo: " + reclamo.getString("MOTIVO_RECLAMO"));
                    pw.println("Fecha: " + reclamo.getString("FECHA_RECLAMO"));
                    pw.println("/*********************************************/");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            pw.flush();
            pw.close();
            f.close();
        }catch(Exception e){
            Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Registros via Host Gratuito Extraidos correctamente", Toast.LENGTH_LONG).show();
    }

    public void sendViaBluetooth(View v) {

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if(btAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_LONG).show();
        } else {
            enableBluetooth();
        }
    }

    public void enableBluetooth() {

        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);

        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            //File file = new File(this.getFilesDir(),"reclamos.txt");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            PackageManager pm = getPackageManager();
            List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

            if(appsList.size() > 0) {
                String packageName = null;
                String className = null;
                boolean found = false;

                for(ResolveInfo info : appsList) {
                    packageName = info.activityInfo.packageName;
                    if(packageName.equals("com.android.bluetooth")) {
                        className = info.activityInfo.name;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    Toast.makeText(this, "Bluetooth havn't been found",
                            Toast.LENGTH_LONG).show();
                } else {
                    intent.setClassName(packageName, className);
                    startActivity(intent);
                }
            }
        } else {
            Toast.makeText(this, "Bluetooth is cancelled", Toast.LENGTH_LONG)
                    .show();
        }
    }


}
