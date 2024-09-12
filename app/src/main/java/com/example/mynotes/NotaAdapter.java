package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynotes.db.bean.Nota;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Nota> {
   private int resource;

   public NotaAdapter(@NonNull Context context, int resource, @NonNull List<Nota> objects) {
      super(context, resource, objects);
      this.resource = resource;
   }

   @NonNull
   @Override
   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      if (convertView == null) {
         LayoutInflater layoutInflater = (LayoutInflater)
                 getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = layoutInflater.inflate(this.resource, parent, false);
      }

      TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
      tvTitulo.setText(getItem(position).getTitulo());
      return convertView;
   }
}
