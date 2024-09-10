package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_main);

      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu1, menu);

      return true;
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if(item.getItemId() == R.id.itNovaAnotacao) {
         Intent intent=new Intent(this,FormActivity.class);
         startActivity(intent);
      }

      else if(item.getItemId() == R.id.itOrdenarPrioridade)
         Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();

      else if(item.getItemId() == R.id.itOrdenarOrdem)
         Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();

      else
         finish();

      return super.onOptionsItemSelected(item);
   }
}