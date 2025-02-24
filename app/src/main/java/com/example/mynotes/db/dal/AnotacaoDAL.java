package com.example.mynotes.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.mynotes.db.bean.Anotacao;
import com.example.mynotes.db.util.Conexao;

import java.util.ArrayList;

public class AnotacaoDAL {
   private Conexao con;
   private Context context;
   private final String TABLE="notes";

   public AnotacaoDAL(Context context) {
      this.context=context;
      con = new Conexao(context);
      try {
         con.conectar();
      }
      catch(Exception e)
      {
         Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
      }
   }

   public boolean salvar(Anotacao o)
   {
      ContentValues dados=new ContentValues();
      dados.put("titulo",o.getTitulo());
      dados.put("texto",o.getTexto());
      dados.put("prioridade",o.getPrioridade());
      return con.inserir(TABLE,dados)>0;
   }
   public boolean alterar(Anotacao o)
   {
      ContentValues dados=new ContentValues();
      dados.put("id",o.getId());
      dados.put("titulo",o.getTitulo());
      dados.put("texto",o.getTexto());
      dados.put("prioridade",o.getPrioridade());
      return con.alterar(TABLE,dados,"id="+o.getId())>0;
   }
   public boolean apagar(long chave)
   {
      return con.apagar(TABLE,"id="+chave)>0;
   }

   public Anotacao get(int id)
   {
      Anotacao o = null;
      Cursor cursor=con.consultar("select * from "+TABLE+" where id="+id);
      if(cursor.moveToFirst())
         o=new Anotacao(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
      cursor.close();;
      return o;
   }

   public ArrayList<Anotacao> get(String filtro) {
      ArrayList <Anotacao> objs = new ArrayList();
      String sql="select * from "+TABLE;
      if (!filtro.equals(""))
         sql+=" where "+filtro;

      Cursor cursor=con.consultar(sql);
      if(cursor.moveToFirst())
         while (!cursor.isAfterLast()) {
            objs.add(new Anotacao(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
         }
      cursor.close();;
      return objs;
   }
}
