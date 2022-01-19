package com.example.miwok_arun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView num = (TextView) findViewById(R.id.clickOnNumber);
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(numbers);
            }
        });


        TextView pharse = (TextView) findViewById(R.id.clickOnPhrases);
        pharse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pharese = new Intent(MainActivity.this,PhraseActivity.class);
                startActivity(pharese);
            }
        });

    }

}