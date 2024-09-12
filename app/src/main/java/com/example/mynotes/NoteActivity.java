package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynotes.db.bean.Nota;
import com.example.mynotes.db.dal.NotaDAL;

public class NoteActivity extends AppCompatActivity {
   private TextView tvTituloNota;
   private EditText etTexto;
   private int prioridade;
   private ImageView btVoltar;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_note);

      if (getSupportActionBar() != null)
         getSupportActionBar().hide();

      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });

      Intent intent = getIntent();

      tvTituloNota = findViewById(R.id.tvTituloNota);
      etTexto = findViewById(R.id.etTexto);
      btVoltar = findViewById(R.id.btVoltar);

      String titulo = intent.getStringExtra("titulo");
      int pri = Integer.parseInt(intent.getStringExtra("prioridade"));

      tvTituloNota.setText(titulo);

      btVoltar.setOnClickListener(e -> {
         Nota nota = new Nota(tvTituloNota.getText().toString(), etTexto.getText().toString(), pri);
         NotaDAL notaDAL = new NotaDAL(this);
         notaDAL.alterar(nota);

         finish();
      });
   }
}
