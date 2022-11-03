package com.electronicsenterprice.finaltry2_3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbConfiguraciones extends DbHelper{
    Context context;
    public DbConfiguraciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarConfiguracion(String txtIp, String txtHostname, String txtDatabase, String txtTable, String txtUsername, String txtPassword, String Bool1, String Bool2, String Bool3, String Bool4, String Bool5, String Bool6, String Bool7, String Bool8, String Float1, String Float2, String Float3, String Float4, String Float5, String URL) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("IP", txtIp);
            values.put("Host", txtHostname);
            values.put("DBase", txtDatabase);
            values.put("Tabla", txtTable);
            values.put("Username", txtUsername);
            values.put("Password", txtPassword);
            values.put("Bool1", Bool1);
            values.put("Bool2", Bool2);
            values.put("Bool3", Bool3);
            values.put("Bool4", Bool4);
            values.put("Bool5", Bool5);
            values.put("Bool6", Bool6);
            values.put("Bool7", Bool7);
            values.put("Bool8", Bool8);
            values.put("Float1", Float1);
            values.put("Float2", Float2);
            values.put("Float3", Float3);
            values.put("Float4", Float4);
            values.put("Float5", Float5);
            values.put("URL", URL);



            id = db.insert(TABLE_Configs, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public boolean updateConfigs(Integer ID, String value) {
        String variable = "";
        boolean funko = false;
        if(ID != null){
            switch(ID) {
                case 1:
                    variable = "Bool1";
                    break;
                case 2:
                    variable = "Bool2";
                    break;
                case 3:
                    variable = "Bool3";
                    break;
                case 4:
                    variable = "Bool4";
                    break;
                case 5:
                    variable = "Bool5";
                    break;
                case 6:
                    variable = "Bool6";
                    break;
                case 7:
                    variable = "Bool7";
                    break;
                case 8:
                    variable = "Bool8";
                    break;
                case 9:
                    variable = "Float1";
                    break;
                case 10:
                    variable = "Float2";
                    break;
                case 11:
                    variable = "Float3";
                    break;
                case 12:
                    variable = "Float4";
                    break;
                case 13:
                    variable = "Float5";
                    break;
                case 14:
                    variable = "URL";
                    break;

            }


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String SQL = "UPDATE " + TABLE_Configs + " SET '" + variable + "' = '" + value + "' ";//ORDER BY 'id' DESC LIMIT 1'
            try {
                db.execSQL(SQL);
                funko = true;
            }catch (Exception e){
                e.toString();
                funko = false;
            }finally {
                db.close();
            }
        }
        return funko;
    }
}
