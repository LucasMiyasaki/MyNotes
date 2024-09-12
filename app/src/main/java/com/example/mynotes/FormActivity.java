package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynotes.db.bean.Nota;
import com.example.mynotes.db.dal.NotaDAL;

public class FormActivity extends AppCompatActivity {
   private TextView etTitulo;
   private int rbSelecionado;
   private Button btNext;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_form);

      if (getSupportActionBar() != null)
         getSupportActionBar().hide();

      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });

      etTitulo = findViewById(R.id.etTitulo);

      if(findViewById(R.id.rbAlta).isActivated())
         rbSelecionado = 2;
      else if(findViewById(R.id.rbNormal).isActivated())
         rbSelecionado = 1;
      else
         rbSelecionado = 0;

      btNext = findViewById(R.id.btNext);

      btNext.setOnClickListener(e -> {
         Nota nota = new Nota(etTitulo.getText().toString(), "", rbSelecionado);

         NotaDAL notaDal = new NotaDAL(this);
         notaDal.salvar(nota);

         Intent intent = new Intent(this, FormActivity.class);
         intent.putExtra("titulo", etTitulo.getText().toString());
         intent.putExtra("prioridade", rbSelecionado);
         startActivity(intent);
         finish();
      });
   }
}
