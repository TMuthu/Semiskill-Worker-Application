package com.example.muthukumart.temple;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Details extends AppCompatActivity {

    public RecyclerView mBlogList;

    public DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView)findViewById(R.id.recycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

       // smsbt = (Button) findViewById(R.id.button7);



       // View vi = inflater.inflate(R.layout.blog_row, null);
       // mCardView = (CardView)findViewById(R.id.card_layout);
        //TextView tv = (TextView)mBlogList.findViewById(R.id.post_phone);

       /* tphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Phone Clicked",Toast.LENGTH_SHORT).show();
            }
        }); */

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog,User_Details.BlogViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Blog, User_Details.BlogViewHolder>
                (Blog.class,R.layout.blog_row,User_Details.BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(User_Details.BlogViewHolder viewHolder, Blog model, int position) {

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
            Blog model = new Blog();
            Button smsbt = (Button)mView.findViewById(R.id.smsbt);
            Button emailbt = (Button)mView.findViewById(R.id.emailbt);
            final String phone = setPhone1(model.getPhone());

            smsbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.setData(Uri.parse("sms:814806962"));
                    itemView.getContext().startActivity(smsIntent);

                }
            });
            emailbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dial = "tel:" + 12345;
                    itemView.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }
            });

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

        public String setPhone1(String Phone)
        {
           return Phone;
        }


    }
    @Override
    public void onBackPressed() {
        Intent n = new Intent(User_Details.this,WelcomeActivity.class);
        startActivity(n);
    }

}
