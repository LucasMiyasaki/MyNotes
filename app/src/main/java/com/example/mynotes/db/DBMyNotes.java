package com.example.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMyNotes extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "mynotes.db";
   private static final int DATABASE_VERSION = 1;

   public DBMyNotes(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE notes (" +
              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
              "titulo TEXT, " +
              "texto TEXT, " +
              "prioridade TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS notes");
      onCreate(db);
   }
}
