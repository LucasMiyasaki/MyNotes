package com.example.mynotes.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.mynotes.db.bean.Nota;
import com.example.mynotes.db.util.Conexao;

import java.util.ArrayList;

public class NotaDAL {
   private Conexao con;
   private Context context;
   private final String TABLE = "nota";

   public NotaDAL(Context context) {
      this.context = context;
      con = new Conexao(context);
      try {
         con.conectar();
      } catch (Exception e) {
         Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
      }
   }

   public boolean salvar(Nota n) {
      ContentValues dados = new ContentValues();
      dados.put("titulo", n.getTitulo());
      dados.put("texto", n.getTexto());
      dados.put("prioridade", n.getPrioridade());
      return con.inserir(TABLE, dados) > 0;
   }

   public boolean alterar(Nota n) {
      ContentValues dados = new ContentValues();
      dados.put("titulo", n.getTitulo());
      dados.put("texto", n.getTexto());
      dados.put("prioridade", n.getPrioridade());
      return con.alterar(TABLE, dados, "mus_id=" + n.getId()) > 0;
   }

   public boolean apagar(long chave) {
      return con.apagar(TABLE, "id=" + chave) > 0;
   }

   public ArrayList<Nota> get(String filtro) {
      ArrayList<Nota> objs = new ArrayList();
      String sql = "select * from " + TABLE;
      if (!filtro.equals(""))
         sql += " where " + filtro;

      Cursor cursor = con.consultar(sql);
      if (cursor.moveToFirst())
         while (!cursor.isAfterLast()) {
            objs.add(new Nota(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3)));
            cursor.moveToNext();
         }
      cursor.close();

      return objs;
   }
}
