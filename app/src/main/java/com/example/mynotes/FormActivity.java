package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
   private EditText etTitulo;
   private RadioGroup radioGroup;
   private Button btCriar;
   private Button btVoltar;
   private ActivityResultLauncher<Intent> notaResultLauncher;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.form);

      etTitulo = findViewById(R.id.etTitulo);
      radioGroup = findViewById(R.id.radioGroup);
      btCriar = findViewById(R.id.btCriar);
      btVoltar = findViewById(R.id.btVoltar);

      notaResultLauncher = registerForActivityResult(
              new ActivityResultContracts.StartActivityForResult(),
              result -> {
                 if(result.getResultCode() == RESULT_OK && result.getData() != null) {

                    Intent data = result.getData();
                    String texto = data.getStringExtra("texto");
                    String prioridade = data.getStringExtra("prioridade");
                    String titulo = data.getStringExtra("titulo");

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("texto", texto);
                    returnIntent.putExtra("prioridade", prioridade);
                    returnIntent.putExtra("titulo", titulo);
                    returnIntent.putExtra("pos", -1);

                    setResult(RESULT_OK, returnIntent);

                    finish();
                 }
              }
      );

      btVoltar.setOnClickListener(e -> finish());

      btCriar.setOnClickListener(e -> {
         int id = radioGroup.getCheckedRadioButtonId();
         RadioButton radio = findViewById(id);

         if (id == -1 || etTitulo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
         } else {
            String titulo = etTitulo.getText().toString();
            int i = getIntent().getIntExtra("id", -1);
            Intent intent = new Intent(this, NotaActivity.class);
            intent.putExtra("titulo", titulo);
            intent.putExtra("prioridade", radio.getText().toString());
            intent.putExtra("id", i);

            notaResultLauncher.launch(intent);
         }
      });
   }
}

