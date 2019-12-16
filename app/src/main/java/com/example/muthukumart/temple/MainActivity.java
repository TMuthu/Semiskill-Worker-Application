package com.example.muthukumart.temple;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mBlogList;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.recycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }
        @Override
        protected void onStart() {
            super.onStart();

            FirebaseRecyclerAdapter<Blog,MainActivity.BlogViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Blog, MainActivity.BlogViewHolder>
                    (Blog.class,R.layout.blog1_row,MainActivity.BlogViewHolder.class,mDatabase) {
                @Override
                protected void populateViewHolder(MainActivity.BlogViewHolder viewHolder, Blog model, int position) {

                    viewHolder.setName(model.getName());
                    viewHolder.setPhone(model.getPhone());
                    viewHolder.setKnown_Work(model.getKnown_Work());
                    viewHolder.setEducational(model.getEducational());
                    viewHolder.setCity(model.getCity());
                    viewHolder.setDistrict(model.getDistrict());

                }
            };
            mBlogList.setAdapter(firebaseRecyclerAdapter);
        }
        public static class BlogViewHolder extends RecyclerView.ViewHolder
        {
            View mView;

            public BlogViewHolder(View itemView1)
            {
                super(itemView1);
                mView=itemView;
            }
            public void setName(String Name)
            {
                TextView post_Name = (TextView)mView.findViewById(R.id.post_title);
                post_Name.setText(Name);
            }
            public void setPhone(String Phone)
            {
                TextView post_No = (TextView)mView.findViewById(R.id.post_phone);
                post_No.setText(Phone);
            }
            public void setKnown_Work(String Known_Work)
            {
                TextView post_Work = (TextView)mView.findViewById(R.id.post_work);
                post_Work.setText(Known_Work);
            }
            public void setEducational(String Educational)
            {
                TextView post_Education = (TextView)mView.findViewById(R.id.post_edu);
                post_Education.setText(Educational);
            }
            public void setCity(String City)
            {
                TextView post_City = (TextView)mView.findViewById(R.id.post_city);
                post_City.setText( City);
            }
            public void setDistrict(String District)
            {
                TextView post_District = (TextView)mView.findViewById(R.id.post_dis);
                post_District.setText(District);
            }




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
            Intent s = new Intent(MainActivity.this,LogInActivity.class);
            startActivity(s);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent n = new Intent(MainActivity.this,Drawer_Activity.class);
        startActivity(n);
    }

}
