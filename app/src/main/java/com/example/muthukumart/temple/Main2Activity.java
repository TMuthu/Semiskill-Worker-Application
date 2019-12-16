package com.example.muthukumart.temple;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity{

    private DrawerLayout mydrawer;
    private ActionBarDrawerToggle mytoggle;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private static final String TAG ="Log out" ;
    Button logout,subbt;
    EditText uname,uphone,uwork,ucity,ueq,udistrict;
    String U_name,U_phone,U_work,U_edu,U_city,U_dis;

    //TextView tv1,tv2;
   // private View my_header;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main2);
        //logout = (Button) findViewById(R.id.logoutbt);
        subbt = (Button) findViewById(R.id.submbt);
        uname = (EditText) findViewById(R.id.editText);
        uphone  = (EditText) findViewById(R.id.editText2);
        uwork = (EditText) findViewById(R.id.editText1);
        ucity = (EditText) findViewById(R.id.editText4);
        ueq = (EditText) findViewById(R.id.editText5);
        udistrict = (EditText) findViewById(R.id.editText3);

       // tv1 = (TextView) my_header.findViewById(R.id.pro_name);
       // tv2= (TextView) my_header.findViewById(R.id.pro_email);

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);




        subbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User_id = fAuth.getCurrentUser().getUid();
                DatabaseReference User_details = FirebaseDatabase.getInstance().getReference().child("Users").child(User_id);
                U_name = uname.getText().toString();
                U_phone = uphone.getText().toString();
                U_work = uwork.getText().toString();
                U_edu = ueq.getText().toString();
                U_city = ucity.getText().toString();
                U_dis = udistrict.getText().toString();

                Map newPost1 = new HashMap();
                newPost1.put("Name",U_name);
                newPost1.put("Phone",U_phone);
                newPost1.put("Known_Work",U_work);
                newPost1.put("Educational",U_edu);
                newPost1.put("City",U_city);
                newPost1.put("District",U_dis);
                //newPost.put("PassWord",email);

                User_details.setValue(newPost1);
               /* uname.setText("");
                uphone.setText("");
                uwork .setText("");
                ucity.setText("");
                ueq.setText("");
                udistrict.setText(""); */
            }
        });


       // mydrawer = (DrawerLayout) findViewById(R.id.drawer_id);
        //mytoggle = new ActionBarDrawerToggle(this,mydrawer,R.string.open,R.string.close);
        //mydrawer.addDrawerListener(mytoggle);
        //mytoggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mydrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fAuth.signOut();
            Intent s = new Intent(Main2Activity.this,LogInActivity.class);
            startActivity(s);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
       Intent n = new Intent(Main2Activity.this,Drawer_Activity.class);
       startActivity(n);
    }

}
