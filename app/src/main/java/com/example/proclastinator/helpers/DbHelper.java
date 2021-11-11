package com.example.proclastinator.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION =1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(Context context) {
        super(context, NOME_DB,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " +TABELA_TAREFAS+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        " nome TEXT NOT NULL,"+"inicio TEXT NOT NULL,"+"fim TEXT NOT NULL ); ";

        try{
            db.execSQL(sql);
            Log.i("INFO DB","sucesso CRIAR TABELA");


        }catch (Exception e){
            Log.i("INFO DB","ERRO CRIAR TABELA"+e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
