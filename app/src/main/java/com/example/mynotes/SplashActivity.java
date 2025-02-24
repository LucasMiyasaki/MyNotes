package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
   private ImageView imageView;
   private Handler handler;
   private Runnable runnable;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.splash);
      if(getSupportActionBar()!=null)
         getSupportActionBar().hide();
      imageView=findViewById(R.id.imageView);
      imageView.setOnClickListener(e->{entrar();});
      handler=new Handler();
      runnable=() -> entrar();
      handler.postDelayed(runnable,3000);
      hideSystemUI();
   }

   private void hideSystemUI() {
      View decorView = getWindow().getDecorView();
      decorView.setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_FULLSCREEN
                      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
   }

   private void entrar() {
      Intent intent=new Intent(this,MainActivity.class);
      startActivity(intent);
      finish();
      handler.removeCallbacks(runnable);
   }
}
