package com.electronicsenterprice.finaltry2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.electronicsenterprice.finaltry2_3.db.DbHelper;


public class ActivityCamara extends AppCompatActivity {

    String URL = "";
    WebView webView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        button = findViewById(R.id.btnDLC);
        webView = findViewById(R.id.WebView);
        webView.setWebViewClient(new WebViewClient());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DLC();
            }
        });
        LoadPages();
    }

    void DLC() {
        webView.reload();
    }

    void LoadPages() {

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor Fila;

        Fila = db.rawQuery("SELECT * FROM ConfiguracionesParaPHP ORDER BY id DESC LIMIT 1", null);

        if (Fila.moveToFirst()) {
            do {
                URL = Fila.getString(20);
            } while (Fila.moveToNext());
        }

        Fila.close();
        Toast.makeText(ActivityCamara.this, "" + URL, Toast.LENGTH_SHORT).show();
        webView.getSettings();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (my app)");
        webView.loadUrl(URL);
    }

}