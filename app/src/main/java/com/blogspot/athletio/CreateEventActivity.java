package com.blogspot.athletio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateEventActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;




    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        setupUI();


        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Events");


    }

    private void setupUI() {
        bt=(Button)findViewById(R.id.createeventbt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.push().setValue(new Event(new Day(),12,23,mAuth.getCurrentUser().getUid().toString(),new LatLng(1.2,2.3),new LatLng(5.2,6.3),Event.RUNTYPE,200,600000));
                mDatabase.push().setValue(new Event(new Day(),11,32,mAuth.getCurrentUser().getUid().toString(),new LatLng(1.2,2.3),Event.CRICKETTYPE,600000));
            }
        });
    }
}
