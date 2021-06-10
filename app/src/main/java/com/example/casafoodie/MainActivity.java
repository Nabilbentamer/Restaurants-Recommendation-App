package com.example.casafoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button main_act_login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_act_login_btn = findViewById(R.id.main_act_login_btn);
        main_act_login_btn.setOnClickListener(View->{
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        });





    }
}