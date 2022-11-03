package com.electronicsenterprice.finaltry2_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.electronicsenterprice.finaltry2_3.db.ConfiguracionesValues;
import com.electronicsenterprice.finaltry2_3.db.DbConfiguraciones;
import com.electronicsenterprice.finaltry2_3.db.DbHelper;

import java.util.ArrayList;

public class ActivityConfiguraciones extends AppCompatActivity {

    EditText txtIp,txtHostname,txtDatabase, txtTabla,txtUsername,txtPassword;
    Button Save, Mostrar;

    String Bool1 = "";
            String Bool2 = "";
    String Bool3 = "";
            String Bool4 = "";
    String Bool5 = "";
            String Bool6 = "";
    String Bool7 = "";
            String Bool8 = "";
    String Float1 = "";
            String Float2 = "";
    String Float3 = "";
            String Float4 = "";
    String Float5 = "";
            String URL =  "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        txtIp = findViewById(R.id.txtIp);
        txtHostname = findViewById(R.id.txtHostname);
        txtDatabase = findViewById(R.id.txtDatabase);
        txtTabla = findViewById(R.id.txtTabla);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        Save = (Button) findViewById(R.id.btnGuardar2);
        Mostrar = (Button) findViewById(R.id.btnMostrar);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtIp.getText().toString().equals("") && !txtHostname.getText().toString().equals("") && !txtDatabase.getText().toString().equals("") && !txtTabla.getText().toString().equals("")) {
                    long id = 0;
                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    Cursor Fila;

                    Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

                    if (Fila.moveToFirst()) {
                        do {

                            Bool1 = Fila.getString(7);
                            Bool2 = Fila.getString(8);
                            Bool3 = Fila.getString(9);
                            Bool4 = Fila.getString(10);
                            Bool5 = Fila.getString(11);
                            Bool6 = Fila.getString(12);
                            Bool7 = Fila.getString(13);
                            Bool8 = Fila.getString(14);
                            Float1 = Fila.getString(15);
                            Float2 = Fila.getString(16);
                            Float3 = Fila.getString(17);
                            Float4 = Fila.getString(18);
                            Float5 = Fila.getString(19);
                            URL = Fila.getString(20);

                        } while (Fila.moveToNext());
                    }

                    Fila.close();



                    DbConfiguraciones dbConfiguraciones = new DbConfiguraciones(ActivityConfiguraciones.this);
                    id = dbConfiguraciones.insertarConfiguracion(txtIp.getText().toString(), txtHostname.getText().toString(), txtDatabase.getText().toString(), txtTabla.getText().toString(), txtUsername.getText().toString(), txtPassword.getText().toString(), Bool1, Bool2, Bool3, Bool4, Bool5, Bool6, Bool7, Bool8, Float1, Float2, Float3, Float4, Float5, URL);
                    if (id > 0) {
                        Toast.makeText(ActivityConfiguraciones.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(ActivityConfiguraciones.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ActivityConfiguraciones.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }

            }
        });
        Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mostrar();
            }
        });
    }

    private void limpiar() {
        txtIp.setText("");
        txtHostname.setText("");
        txtDatabase.setText("");
        txtTabla.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }


    public ArrayList<ConfiguracionesValues> Mostrar() {

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ConfiguracionesValues> listaConfigs = new ArrayList<>();
        ConfiguracionesValues configuracionesValues;
        Cursor Fila;

        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                configuracionesValues = new ConfiguracionesValues();
                configuracionesValues.setId(Fila.getInt(0));
                txtIp.setText(configuracionesValues.setIP(Fila.getString(1)));
                txtHostname.setText(configuracionesValues.setHost(Fila.getString(2)));
                txtDatabase.setText(configuracionesValues.setDBase(Fila.getString(3)));
                txtTabla.setText(configuracionesValues.setTable(Fila.getString(4)));
                txtUsername.setText(configuracionesValues.setUsername(Fila.getString(5)));
                txtPassword.setText(configuracionesValues.setPassword(Fila.getString(6)));

                listaConfigs.add(configuracionesValues);
            } while (Fila.moveToNext());
        }

        Fila.close();
        return listaConfigs;
    }
}
