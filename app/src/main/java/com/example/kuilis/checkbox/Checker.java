package com.example.kuilis.checkbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Checker extends AppCompatActivity {

    private Button btnSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        btnSetup = (Button) findViewById(R.id.btnSetup);
        btnSetup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Checker.this, SetupActivity.class));
            }
        });

    }

    protected void Brench02(){
        int i = 2;
    }
}




