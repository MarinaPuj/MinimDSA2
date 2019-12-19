package com.example.minim2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView image = findViewById(R.id.imageSplash);
        Picasso.get().load("https://upload.wikimedia.org/wikipedia/ca/thumb/b/b5/Logo_upc.svg/1024px-Logo_upc.svg.png").into(image);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, PanelActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}

