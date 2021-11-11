package com.example.proclastinator.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.proclastinator.model.Atividade;

import java.util.ArrayList;
import java.util.List;

public class AtividadeDao implements IAtividadeDao {
    public SQLiteDatabase escreve;
    public SQLiteDatabase le;

    public AtividadeDao(Context context) {

        DbHelper dbHelper = new DbHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le =dbHelper.getReadableDatabase();
     }

    @Override
    public boolean salvar(Atividade atividade) {
       ContentValues cv = new ContentValues();

        cv.put("nome",atividade.getNome_ativiade());
        cv.put("inicio",atividade.getComeço());
        cv.put("fim",atividade.getTermino());


        try{
            escreve.insert(DbHelper.TABELA_TAREFAS,null,cv);
            Log.i("info","tarefa salva com sucesso");
        }catch (Exception e){
            Log.e("info","erro aoo salvar tarefa"+ e.getMessage());
            return false;


        }


        return true;
    }

    @Override
    public boolean atualizar(Atividade atividade) {
        ContentValues cv = new ContentValues();

        cv.put("nome",atividade.getNome_ativiade());
        cv.put("inicio",atividade.getComeço());
        cv.put("fim",atividade.getTermino());
        try{
            String [] args ={atividade.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS,cv,"id=?",args);

            Log.i("info","tarefa atualizada com sucesso");
        }catch (Exception e){
            Log.e("info","erro ao atualizar tarefa"+ e.getMessage());
            return false;


        }

        return true;
    }

    @Override
    public boolean deletar(Atividade atividade) {

        try{
            String [] args ={atividade.getId().toString()};
            escreve.delete(DbHelper.TABELA_TAREFAS,"id=?",args);

            Log.i("info","tarefa removida com sucesso");
        }catch (Exception e){
            Log.e("info","erro ao remover tarefa"+ e.getMessage());
            return false;


        }


        return true;
    }

    @Override
    public List<Atividade> listar() {
      List<Atividade> atividades = new ArrayList<>();
      String sql = "SELECT* FROM "+DbHelper.TABELA_TAREFAS +" ;";
      Cursor c = le.rawQuery(sql,null);

      while (c.moveToNext()){
          Atividade atividade = new Atividade();

          Long id = c.getLong(c.getColumnIndex("id"));
          String nome = c.getString(c.getColumnIndex("nome"));
          String inicio = c.getString(c.getColumnIndex("inicio"));
          String fim = c.getString(c.getColumnIndex("fim"));

          atividade.setId(id);
          atividade.setNome_ativiade(nome);
          atividade.setComeço(inicio);
          atividade.setTermino(fim);

          atividades.add(atividade);


      }
      return atividades;
    }
}
