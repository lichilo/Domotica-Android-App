package com.electronicsenterprice.finaltry2_3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "BaseDeDatos.db";
    public static final String TABLE_Configs = "ConfiguracionesParaPHP";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_Configs + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IP TEXT NOT NULL," +
                "Host TEXT NOT NULL," +
                "DBase TEXT," +
                "Tabla TEXT," +
                "Username TEXT," +
                "Password TEXT," +
                "Bool1 TEXT ," +
                "Bool2 TEXT," +
                "Bool3 TEXT," +
                "Bool4 TEXT," +
                "Bool5 TEXT," +
                "Bool6 TEXT," +
                "Bool7 TEXT," +
                "Bool8 TEXT," +
                "Float1 TEXT," +
                "Float2 TEXT," +
                "Float3 TEXT," +
                "Float4 TEXT," +
                "Float5 TEXT," +
                "URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE " + TABLE_Configs);
            onCreate(sqLiteDatabase);
        }
}
