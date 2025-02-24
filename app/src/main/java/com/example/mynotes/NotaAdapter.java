package com.example.mynotes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynotes.db.bean.Anotacao;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Anotacao> {
   private int layout;
   public NotaAdapter(@NonNull Context context, int resource, @NonNull List<Anotacao> anotacao) {
      super(context, resource, anotacao);
      layout = resource;
   }

   @NonNull
   @Override
   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      if(convertView==null) {
         LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = layoutInflater.inflate(layout, parent, false);
      }
      TextView itTitulo=convertView.findViewById(R.id.itTitulo);

      Anotacao anotacao = getItem(position);
      itTitulo.setText(""+anotacao.getTitulo());

      int color;
      switch (anotacao.getPrioridade()) {
         case "Alta":
            color = Color.RED;
            break;

         case "Normal":
            color = Color.YELLOW;
            break;

         default:
            color = Color.GREEN;
      }

      convertView.setBackgroundColor(color);

      return convertView;
   }
}
