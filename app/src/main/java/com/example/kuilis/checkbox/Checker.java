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

    private void Branch02(){

        int i = 10;
        String test = "Test";

        int kr = 60;

        int result = i+kr;

        if(result >= 100){

            test = "Did not worked";

        }else{test = "Good";}
    }

}




