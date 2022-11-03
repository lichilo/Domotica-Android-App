package com.electronicsenterprice.finaltry2_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.electronicsenterprice.finaltry2_3.db.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch c1, c2, c3, c4, c5, c6, c7, c8;
    Button btnActualizar;

    public String URL;
    public String URLIp = "";
    public String URLHost = "";
    public String URLDatabase = "";
    public String URLTabla = "";
    public String URLUsername = "";
    public String URLPassword = "";
    public String Val_1="1";
    public String Val_2 ="1";
    public String Val_3 ="1";
    public String Val_4 ="1";
    public String Val_5 ="1";
    public String Val_6 ="1";
    public String Val_7 ="1";
    public String Val_8 ="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.checkBox1);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);
        c7 = findViewById(R.id.checkBox7);
        c8 = findViewById(R.id.checkBox8);

        SetTextTo();
        cargarConfigs();
        btnActualizar = findViewById(R.id.btnMySQL);
        btnActualizar.setOnClickListener(view -> {
            cargarDatos();
            URL = "http://"+ URLIp + "/Insertar_datos2.php?hostname=" + URLHost + "&datebase=" + URLDatabase + "&tabla=" + URLTabla + "&username=" + URLUsername + "&password=" + URLPassword;
            ejecutarServicio(URL);
            //Toast.makeText(MainActivity.this, "" + URL, Toast.LENGTH_SHORT).show();
        });


        btnActualizar = findViewById(R.id.btnUpdate);
        btnActualizar.setOnClickListener(view -> {
            cargarDatos();
            URL = "http://"+ URLIp +"/UpdateSwitch.php?hostname=" + URLHost + "&datebase=" + URLDatabase + "&tabla=" + URLTabla + "&username=" + URLUsername + "&password=" + URLPassword;
            updateSwitchs(URL);
            //Toast.makeText(MainActivity.this, "" + URL, Toast.LENGTH_SHORT).show();
        });

        cargarDatos();
        URL = "http://"+ URLIp +"/UpdateSwitch.php?hostname=" + URLHost + "&datebase=" + URLDatabase + "&tabla=" + URLTabla + "&username=" + URLUsername + "&password=" + URLPassword;
        updateSwitchs(URL);

    }



    private void updateSwitchs(String URL2) {

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);

                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.

                        if(responseObj.getInt("Bool_1") == 1){
                            c1.setChecked(true);
                        }else{
                            c1.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_2") == 1){
                            c2.setChecked(true);
                        }else{
                            c2.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_3") == 1){
                            c3.setChecked(true);
                        }else{
                            c3.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_4") == 1){
                            c4.setChecked(true);
                        }else{
                            c4.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_5") == 1){
                            c5.setChecked(true);
                        }else{
                            c5.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_6") == 1){
                            c6.setChecked(true);
                        }else{
                            c6.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_7") == 1){
                            c7.setChecked(true);
                        }else{
                            c7.setChecked(false);
                        }
                        if(responseObj.getInt("Bool_8") == 1){
                            c8.setChecked(true);
                        }else{
                            c8.setChecked(false);
                        }

                        //ActualizarFloats();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                updateSwitchs(URL);
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void SetTextTo(){
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor Fila;
        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                if(Fila.getString(7) != null){c1.setText(Fila.getString(7));}
                if(Fila.getString(8) != null){c2.setText(Fila.getString(8));}
                if(Fila.getString(9) != null){c3.setText(Fila.getString(9));}
                if(Fila.getString(10) != null){c4.setText(Fila.getString(10));}
                if(Fila.getString(11) != null){c5.setText(Fila.getString(11));}
                if(Fila.getString(12) != null){c6.setText(Fila.getString(12));}
                if(Fila.getString(13) != null){c7.setText(Fila.getString(13));}
                if(Fila.getString(14) != null){c8.setText(Fila.getString(14));}


            } while (Fila.moveToNext());
        }

        Fila.close();
    }

    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            //Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();

        }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Bool_1", Val_1);
                parametros.put("Bool_2", Val_2);
                parametros.put("Bool_3", Val_3);
                parametros.put("Bool_4", Val_4);
                parametros.put("Bool_5", Val_5);
                parametros.put("Bool_6", Val_6);
                parametros.put("Bool_7", Val_7);
                parametros.put("Bool_8", Val_8);
                return parametros;
            }
        };
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuConfiguraciones:
                cambiarConfiguracion();
                return true;
            case R.id.menuExtras:
                cambiarConfiguracionextras();
                return true;
            case R.id.menuSensores:
                SensoresMenu();
                return true;
            case R.id.menuCamera:
                SensoresCamera();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cambiarConfiguracion(){
        Intent intent = new Intent(this, ActivityConfiguraciones.class);
        startActivity(intent);
    }

    public void SensoresMenu(){
        Intent intent = new Intent(this,ActivitySensores.class);
        startActivity(intent);
    }
    public void cambiarConfiguracionextras(){
        Intent intent = new Intent(this, menuExtras.class);
        startActivity(intent);
    }


    public void SensoresCamera(){
        Intent intent = new Intent(this, ActivityCamara.class);
        startActivity(intent);
    }



    public void cargarConfigs() {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor Fila;
        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                String IP = Fila.getString(1);
                String Host = Fila.getString(2);
                String DBase = Fila.getString(3);
                String Tabla = Fila.getString(4);
                String Username = Fila.getString(5);
                String Password = Fila.getString(6);
                URLIp = IP;
                URLHost = Host;
                URLDatabase = DBase;
                URLTabla = Tabla;
                URLUsername = Username;
                URLPassword = Password;

            } while (Fila.moveToNext());
        }
        Fila.close();
    }

    public void cargarDatos(){
        if(c1.isChecked()){
            Val_1="1";
        }else {
            Val_1="0";
        }

        if(c2.isChecked()){
            Val_2 ="1";
        }else {
            Val_2 ="0";
        }

        if(c3.isChecked()){
            Val_3 ="1";
        }else {
            Val_3 ="0";
        }

        if(c4.isChecked()){
            Val_4 ="1";
        }else {
            Val_4 ="0";
        }

        if(c5.isChecked()){
            Val_5 ="1";
        }else {
            Val_5 ="0";
        }

        if(c6.isChecked()){
            Val_6 ="1";
        }else {
            Val_6 ="0";
        }

        if(c7.isChecked()){
            Val_7 ="1";
        }else {
            Val_7 ="0";
        }

        if(c8.isChecked()){
            Val_8 ="1";
        }else {
            Val_8 ="0";
        }
    }
}