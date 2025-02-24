package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotaActivity extends AppCompatActivity {
   private ImageButton ibVoltar;
   private TextView tvTitulo;
   private EditText etNota;
   private String prioridade;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.nota);

      ibVoltar = findViewById(R.id.ibVoltar);
      tvTitulo = findViewById(R.id.tvTitulo);
      etNota = findViewById(R.id.etNota);

      Intent intent = getIntent();
      tvTitulo.setText(intent.getStringExtra("titulo"));
      etNota.setText(intent.getStringExtra("texto"));

      Toolbar tb = findViewById(R.id.toolbar);
      setSupportActionBar(tb);

      getSupportActionBar().setDisplayShowTitleEnabled(false);

      prioridade = getIntent().getStringExtra("prioridade");

      ibVoltar.setOnClickListener(e -> {
         String texto = etNota.getText().toString();
         String titulo = tvTitulo.getText().toString();
         int pos = getIntent().getIntExtra("pos", -1);
         int id = getIntent().getIntExtra("id", -1);

         Intent returnIntent = new Intent();
         returnIntent.putExtra("texto", texto);
         returnIntent.putExtra("prioridade", prioridade);
         returnIntent.putExtra("titulo", titulo);
         returnIntent.putExtra("pos", pos);
         returnIntent.putExtra("id", id);
         setResult(RESULT_OK, returnIntent);

         finish();
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_nota, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId() == R.id.itAlta) {
         prioridade = "Alta";
      }

      if (item.getItemId() == R.id.itNormal) {
         prioridade = "Normal";
      }

      if (item.getItemId() == R.id.itBaixa) {
         prioridade = "Baixa";
      }

      return super.onOptionsItemSelected(item);
   }
}
