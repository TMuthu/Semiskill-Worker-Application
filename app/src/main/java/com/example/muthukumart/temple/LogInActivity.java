package com.example.muthukumart.temple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity{

    Button bt1,bt2;
    EditText et1,et2;
    private FirebaseAuth firebaseauth;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

       // ActionBar actionBar = getActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);


        firebaseauth = FirebaseAuth.getInstance();

        if(firebaseauth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }

        progressdialog = new ProgressDialog(this);


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(LogInActivity.this,Sign_up.class);
                startActivity(n);
            }
        });

      bt1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {



                final String email = et1.getText().toString().trim();
                final String password = et2.getText().toString().trim();


              if (TextUtils.isEmpty(email)) {
                  Toast.makeText(getApplicationContext(), "Please enter mail id", Toast.LENGTH_SHORT).show();
              }
              if (TextUtils.isEmpty(password)) {
                  Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
              }

                progressdialog.setMessage("Logging Please Wait...");
                progressdialog.show();

                firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                                progressdialog.dismiss();



                        if (task.isSuccessful()) {
                            // there was an error
                            Intent intent = new Intent(LogInActivity.this, Drawer_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


          }
      });


            }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    } */

    @Override
    public void onBackPressed() {
        Intent n = new Intent(LogInActivity.this,WelcomeActivity.class);
        startActivity(n);
    }
}
