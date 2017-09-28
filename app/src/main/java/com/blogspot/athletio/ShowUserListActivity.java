package com.blogspot.athletio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class ShowUserListActivity extends AppCompatActivity {
    DatabaseReference mDatabase;

    Vector<SmallUser> smallUsers;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_list);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Userlist");
        smallUsers=new Vector<SmallUser>();


        setupUI();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                       SmallUser smallUser=new SmallUser(d.getValue().toString());
                        smallUsers.add(smallUser);
                    }
                    updateUI();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateUI() {
        tv.setText(smallUsers.toString());
    }

    private void setupUI() {
        tv=(TextView)findViewById(R.id.showuserlisttv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(smallUsers.get(0)!=null){
                    showProfile(smallUsers.get(0).UID);
                }
            }
        });
    }

    private void showProfile(String uid) {
        Intent intent=new Intent(ShowUserListActivity.this,ShowProfileActivity.class);
        intent.putExtra("UID",uid);
        startActivity(intent);
    }
}