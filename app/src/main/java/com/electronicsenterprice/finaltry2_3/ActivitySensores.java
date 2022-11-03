package com.electronicsenterprice.finaltry2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.electronicsenterprice.finaltry2_3.db.ConfiguracionesValues;
import com.electronicsenterprice.finaltry2_3.db.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ActivitySensores extends AppCompatActivity {
    ProgressBar PBar1, PBar2, PBar3, PBar4, PBar5;
    TextView txtPBar1, txtPBar2, txtPBar3, txtPBar4, txtPBar5;
    TextView Sensor1, Sensor2, Sensor3, Sensor4, Sensor5;

    Button btnActualizar;
    public String URL;
    public String URLIp;
    public String URLHost;
    public String URLDatabase;
    public String URLTabla;
    public String URLUsername;
    public String URLPassword;

    public double Float_1;
    public double Float_2;
    public double Float_3;
    public double Float_4;
    public double Float_5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        PBar1 = findViewById(R.id.PBar1);
        PBar2 = findViewById(R.id.PBar2);
        PBar3 = findViewById(R.id.PBar3);
        PBar4 = findViewById(R.id.PBar4);
        PBar5 = findViewById(R.id.PBar5);

        txtPBar1 = findViewById(R.id.txtPBar1);
        txtPBar2 = findViewById(R.id.txtPBar2);
        txtPBar3 = findViewById(R.id.txtPBar3);
        txtPBar4 = findViewById(R.id.txtPBar4);
        txtPBar5 = findViewById(R.id.txtPBar5);

        Sensor1 = findViewById(R.id.textView);
        Sensor2 = findViewById(R.id.textView4);
        Sensor3 = findViewById(R.id.textView5);
        Sensor4 = findViewById(R.id.textView3);
        Sensor5 = findViewById(R.id.textView7);

        btnActualizar = findViewById(R.id.Actualizar);
        btnActualizar.setOnClickListener(view -> {
            mostrarConfigs();
            URL = "http://"+ URLIp +"/Select_datos3.php?hostname=" + URLHost + "&datebase=" + URLDatabase + "&tabla=" + URLTabla + "&username=" + URLUsername + "&password=" + URLPassword;
            /*/?hostname=" + URLHost + "&datebase=" + URLDatabase + "&username=" + URLUsername + "&password=" + URLPassword + "&tabla=" + URLTabla*/
            getData();
        });

        SetTextTo();
    }

    private void SetTextTo(){
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor Fila;
        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                if(Fila.getString(15) != null){Sensor1.setText(Fila.getString(15));}
                if(Fila.getString(16) != null){Sensor2.setText(Fila.getString(16));}
                if(Fila.getString(17) != null){Sensor3.setText(Fila.getString(17));}
                if(Fila.getString(18) != null){Sensor4.setText(Fila.getString(18));}
                if(Fila.getString(19) != null){Sensor5.setText(Fila.getString(19));}

            } while (Fila.moveToNext());
        }
        Fila.close();
    }


    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ActivitySensores.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
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
                        Float_1 = (responseObj.getInt("Float_1"))/10d;
                        Float_2 = (responseObj.getInt("Float_2"))/10d;
                        Float_3 = (responseObj.getInt("Float_3"))/10d;
                        Float_4 = (responseObj.getInt("Float_4"))/10d;
                        Float_5 = (responseObj.getInt("Float_5"))/10d;
                        ActualizarFloats();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarConfigs();
                getData();

            }
        });
        queue.add(jsonArrayRequest);
    }

    @SuppressLint("SetTextI18n")
    private void ActualizarFloats() {
        PBar1.setProgress((int)Float_1);
        PBar2.setProgress((int)Float_2);
        PBar3.setProgress((int)Float_3);
        PBar4.setProgress((int)Float_4);
        PBar5.setProgress((int)Float_5);

        txtPBar1.setText(String.valueOf(Float_1) + " Cº");
        txtPBar2.setText(String.format("%s%%", String.valueOf(Float_2)));
        txtPBar3.setText(String.valueOf(Float_3) + " Cº");
        txtPBar4.setText(String.format("%s%%", String.valueOf(Float_4)));
        txtPBar5.setText(String.format("%s%%", String.valueOf(Float_5)));
    }

    public void mostrarConfigs() {

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ConfiguracionesValues configuracionesValues;
        Cursor Fila;

        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                configuracionesValues = new ConfiguracionesValues();

                configuracionesValues.setId(Fila.getInt(0));
                String IP = configuracionesValues.setIP(Fila.getString(1));
                String Host = configuracionesValues.setHost(Fila.getString(2));
                String DBase = configuracionesValues.setDBase(Fila.getString(3));
                String Tabla = configuracionesValues.setTable(Fila.getString(4));
                String Username = configuracionesValues.setUsername(Fila.getString(5));
                String Password = configuracionesValues.setPassword(Fila.getString(6));
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

}