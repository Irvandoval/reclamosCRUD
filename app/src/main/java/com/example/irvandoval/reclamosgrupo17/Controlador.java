package com.example.irvandoval.reclamosgrupo17;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by irvandoval on 06-13-15.
 */
public class Controlador {
    public static String informacionError = "Conexion Exitosa";

    public String obtenerRespuestaDeURL(String url,Context ctx) {
        String respuesta = " ";
        try {
            HttpParams params = new BasicHttpParams();
            int timeoutConnection = 3000;
            HttpConnectionParams.setConnectionTimeout(params,
                    timeoutConnection);
            int timeoutSocket = 5000;
            HttpConnectionParams.setSoTimeout(params, timeoutSocket);
            HttpClient httpClient = new DefaultHttpClient(params);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            respuesta = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(ctx, "Error de conexion 1", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Toast.makeText(ctx, "Error de conexion 2", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(ctx, "Error de conexion 3", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return respuesta;
    }
}
