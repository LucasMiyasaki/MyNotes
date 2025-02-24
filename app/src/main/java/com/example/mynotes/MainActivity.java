package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynotes.db.bean.Anotacao;
import com.example.mynotes.db.dal.AnotacaoDAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private List<Anotacao> list = new ArrayList<>();
   private ActivityResultLauncher<Intent> activityResultLauncher;
   private ListView listView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_main);

      listView = findViewById(R.id.listView);

      AnotacaoDAL dal = new AnotacaoDAL(this);
      list = dal.get("");
      listView.setAdapter(new NotaAdapter(this,
              R.layout.item, list));

      activityResultLauncher = registerForActivityResult(
              new ActivityResultContracts.StartActivityForResult(),
              result -> {
                 if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                       String texto = data.getStringExtra("texto");
                       String prioridade = data.getStringExtra("prioridade");
                       String titulo = data.getStringExtra("titulo");
                       int pos = data.getIntExtra("pos", -1);
                       int id = data.getIntExtra("id", -1);

                       Anotacao anotacao = new Anotacao(id, titulo, texto, prioridade);

                       if (pos == -1) {
                          list.add(anotacao);
                          dal.salvar(anotacao);

                          list = dal.get("");
                       }
                       else {
                          list.set(pos, anotacao);
                          dal.alterar(anotacao);
                       }

                       listView.setAdapter(new NotaAdapter(this,
                               R.layout.item, list));
                    } else {
                       Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
                    }
                 }
              }
      );

      listView.setOnItemLongClickListener((parent, view, position, id) -> {
         Anotacao a = list.get(position);

         list.remove(position);
         listView.setAdapter(new NotaAdapter(this,
                 R.layout.item, list));

         dal.apagar(a.getId());

         return true;
      });

      listView.setOnItemClickListener((parent, view, position, id) -> {
         Anotacao anotacao = dal.get(list.get(position).getId());

         Intent intent = new Intent(this, NotaActivity.class);
         intent.putExtra("pos", position);
         intent.putExtra("id", anotacao.getId());
         intent.putExtra("texto", anotacao.getTexto());
         intent.putExtra("titulo", anotacao.getTitulo());
         intent.putExtra("prioridade", anotacao.getPrioridade());
         activityResultLauncher.launch(intent);
      });

      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
      MenuInflater menuInflater = getMenuInflater();
      menuInflater.inflate(R.menu.menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId() == R.id.itNovaAnotacao) {
         Intent intent = new Intent(this, FormActivity.class);
         activityResultLauncher.launch(intent);

      }
      if(item.getItemId() == R.id.itOrdenarPri) {
         ordenarPorPrioridade(list);
         listView.setAdapter(new NotaAdapter(this,
                 R.layout.item, list));

      }
      if(item.getItemId() == R.id.itOrdenarOrdem) {
         ordenarPorTitulo(list);
         listView.setAdapter(new NotaAdapter(this,
                 R.layout.item, list));
      }
      if(item.getItemId() == R.id.itFechar)
         finish();

      return super.onOptionsItemSelected(item);
   }

   public void ordenarPorPrioridade(List<Anotacao> list) {
      Collections.sort(list, new Comparator<Anotacao>() {
         @Override
         public int compare(Anotacao a1, Anotacao a2) {
            return getValorPrioridade(a1.getPrioridade()) - getValorPrioridade(a2.getPrioridade());
         }

         private int getValorPrioridade(String prioridade) {
            switch (prioridade.toLowerCase()) {
               case "alta":
                  return 1;
               case "normal":
                  return 2;
               case "baixa":
                  return 3;
               default:
                  return 4;
            }
         }
      });
   }

   public void ordenarPorTitulo(List<Anotacao> list) {
      Collections.sort(list, new Comparator<Anotacao>() {
         @Override
         public int compare(Anotacao a1, Anotacao a2) {
            return a1.getTitulo().compareToIgnoreCase(a2.getTitulo());
         }
      });
   }
}