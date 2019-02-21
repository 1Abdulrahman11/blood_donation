package com.example.bloodbank10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button donor = (Button) findViewById(R.id.donor);
        Button hospital = (Button) findViewById(R.id.hospital);


        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent don = new Intent(MainActivity.this,SignUp.class);
                MainActivity.this.startActivity(don);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hos = new Intent(MainActivity.this,LoginH.class);
                MainActivity.this.startActivity(hos);

            }
        });


    }
}
