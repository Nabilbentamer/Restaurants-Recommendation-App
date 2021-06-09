package com.example.casafoodie;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.casafoodie.Adapter.RestaurantsListAdapter;
import com.example.casafoodie.Model.Restaurants;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayRestaurants extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    private RecyclerView recyclerView;
    private List<Restaurants> RestauList;
    private RestaurantsListAdapter myAdapter;
    private DatabaseReference databaseReference;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurants);

        // Define HOOKS

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Tool Bar

        //setSupportActionBar(toolbar);

        // Navigation drawer menu

        ActionBarDrawerToggle myToogle = new ActionBarDrawerToggle(this,drawerLayout, toolbar ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(myToogle);
        myToogle.syncState();

        // The adapater

        recyclerView = findViewById(R.id.TeacherListRV);

        TeacherList = new ArrayList<>();
        myAdapter = new TeacherListAdapter(this,TeacherList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teachers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for( DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Teacher teacher = dataSnapshot1.getValue(Teacher.class);
                        TeacherList.add(teacher);

                    }

                }



                myAdapter.notifyDataSetChanged();
                myAdapter = new TeacherListAdapter(professeurList.this,TeacherList);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: ");


            }
        });



    }
}