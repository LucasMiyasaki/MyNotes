package com.example.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMyNotes extends SQLiteOpenHelper {
   private static final int VERSAO = 1;
   public DBMyNotes(Context context) {
      super(context, "mynotes.db", null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE nota(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(15), texto VARCHAR(250), prioridade INTEGER);");

   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL ("DROP TABLE IF EXISTS nota");
      onCreate(db);
   }
}
