package com.electronicsenterprice.finaltry2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.electronicsenterprice.finaltry2_3.db.DbConfiguraciones;
import com.electronicsenterprice.finaltry2_3.db.extrasConfigs;

import java.util.ArrayList;

public class menuExtras extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Spinner spinner;
    EditText textdlc;
    Button btnMySQL3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_extras);
        spinner = findViewById(R.id.Spinner);
        textdlc = findViewById(R.id.textdlc);
        btnMySQL3 = findViewById(R.id.btnMySQL3);
        Spinner();
        btnMySQL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!textdlc.getText().toString().equals("")){
                    int idSelected = (int) spinner.getSelectedItemId() + 1;

                    DbConfiguraciones dbConfiguraciones = new DbConfiguraciones(menuExtras.this);
                    boolean  funko = dbConfiguraciones.updateConfigs(idSelected, textdlc.getText().toString());

                    if (funko) {
                        Toast.makeText(menuExtras.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(menuExtras.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
                    }

                /*}else{
                    Toast.makeText(menuExtras.this, "El registro no puede estar vacio", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }

    private void Spinner(){
        ArrayList<extrasConfigs> Variables = new ArrayList<>();
        Variables.add(new extrasConfigs(1,"Bool 1"));
        Variables.add(new extrasConfigs(2,"Bool 2"));
        Variables.add(new extrasConfigs(3,"Bool 3"));
        Variables.add(new extrasConfigs(4,"Bool 4"));
        Variables.add(new extrasConfigs(5,"Bool 5"));
        Variables.add(new extrasConfigs(6,"Bool 6"));
        Variables.add(new extrasConfigs(7,"Bool 7"));
        Variables.add(new extrasConfigs(8,"Bool 8"));
        Variables.add(new extrasConfigs(9,"Float 1"));
        Variables.add(new extrasConfigs(10,"Float 2"));
        Variables.add(new extrasConfigs(11,"Float 3"));
        Variables.add(new extrasConfigs(12,"Float 4"));
        Variables.add(new extrasConfigs(13,"Float 5"));
        Variables.add(new extrasConfigs(14,"URL Camara"));


        ArrayAdapter<extrasConfigs> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Variables);
        spinner.setAdapter(adapter );
    }


    private void limpiar() {
        textdlc.setText("");

    }

}