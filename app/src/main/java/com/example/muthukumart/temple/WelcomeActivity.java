package com.example.muthukumart.temple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    Button but1,but2;
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        but1 = (Button)findViewById(R.id.adminbut);
        but2 = (Button)findViewById(R.id.userbut);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(WelcomeActivity.this,LogInActivity.class);

                startActivity(n);
                //Toast.makeText(getApplicationContext(), "Admin clicked", Toast.LENGTH_SHORT).show();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(WelcomeActivity.this,User_Details.class);
                startActivity(n);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "press again to exit ! Good Bye ", Toast.LENGTH_SHORT).show();
    }
}
