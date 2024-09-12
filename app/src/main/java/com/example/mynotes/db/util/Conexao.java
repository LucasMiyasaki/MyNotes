package com.example.mynotes.db.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mynotes.db.DBMyNotes;

public class Conexao {
   private SQLiteDatabase database;
   private DBMyNotes sqliteOpenHelper;

   public Conexao(Context context) {
      sqliteOpenHelper = new DBMyNotes(context);
   }

   public void conectar() throws SQLException {
      database = sqliteOpenHelper.getWritableDatabase();
   }

   public void desconectar() {
      sqliteOpenHelper.close();
   }

   public long inserir(String tabela, ContentValues dados) {
      long insertId = database.insert(tabela, null, dados);
      return insertId;
   }

   public long alterar(String tabela, ContentValues dados, String restricao) {
      long insertId = database.update(tabela, dados, restricao, null);
      return insertId;
   }

   public int apagar(String tabela, String query) {
      return database.delete(tabela, query, null);
   }

   public Cursor consultar(String query) {
      Cursor cursor = null;
      cursor = database.rawQuery(query, null);
      return cursor;
   }
}
