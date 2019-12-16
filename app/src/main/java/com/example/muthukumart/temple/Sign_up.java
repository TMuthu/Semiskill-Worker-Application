package com.example.muthukumart.temple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {

    Button sbt1;
    EditText set1,set2,sname,sage,ssex;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    Boolean EditTextStatus ;
    String EmailHolder, PasswordHolder ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        firebaseAuth = FirebaseAuth.getInstance();

        sbt1 = (Button)findViewById(R.id.signupsubbt);
        set2 = (EditText)findViewById(R.id.editText_esignup);
        sname = (EditText)findViewById(R.id.editText_ename);
        sage = (EditText)findViewById(R.id.editText_eage);
        ssex = (EditText)findViewById(R.id.editText_esex);
        set1 = (EditText)findViewById(R.id.editText_psignup);
        progressDialog = new ProgressDialog(this);


        sbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to check EditText is empty or no status.
                CheckEditTextIsEmptyOrNot();

                // If EditText is true then this block with execute.
                if(EditTextStatus){

                    // If EditText is not empty than UserRegistrationFunction method will call.
                    UserRegistrationFunction();

                }
                // If EditText is false then this block with execute.
                else {

                    Toast.makeText(Sign_up.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    // Creating UserRegistrationFunction
    public void UserRegistrationFunction(){

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
        progressDialog.show();

        // Creating createUserWithEmailAndPassword method and pass email and password inside it.
        firebaseAuth.createUserWithEmailAndPassword(EmailHolder, PasswordHolder).
                addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Checking if user is registered successfully.
                        if(task.isSuccessful()){

                            // If user registered successfully then show this toast message.
                            Toast.makeText(Sign_up.this,"User Registration Successfully",Toast.LENGTH_LONG).show();
                            String User_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users_SignUp_Details").child(User_id);
                            String name = sname.getText().toString();
                            String age = sage.getText().toString();
                            String sex = ssex.getText().toString();
                            //String email = set2.getText().toString();
                            String email = set1.getText().toString();

                            Map newPost = new HashMap();
                            newPost.put("Name",name);
                            newPost.put("Age",age);
                            newPost.put("Sex",sex);
                            newPost.put("Email",email);
                            //newPost.put("PassWord",email);

                            currrent_user_db.setValue(newPost);
                            sname.setText("");
                            sage.setText("");
                            ssex.setText("");
                            set1.setText("");
                            set2.setText("");
                           // Intent n = new Intent(Sign_up.this,LogInActivity.class);
                            //startActivity(n);

                        }else{

                            // If something goes wrong.
                            String s = "Sign up Failed" + task.getException();
                            Toast.makeText(Sign_up.this, s,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                    }
                });

    }

    public void CheckEditTextIsEmptyOrNot(){

        // Getting name and email from EditText and save into string variables.
        EmailHolder = set1.getText().toString().trim();
        PasswordHolder = set2.getText().toString().trim();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            EditTextStatus = false;

        }
        else {

            EditTextStatus = true ;
        }

    }
    @Override
    public void onBackPressed() {
        Intent n = new Intent(Sign_up.this,LogInActivity.class);
        startActivity(n);
    }


}