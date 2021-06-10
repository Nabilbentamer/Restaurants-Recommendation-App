package com.example.casafoodie;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.casafoodie.Adapter.RestaurantsListAdapter;
import com.example.casafoodie.Model.Restaurants;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayRestaurants extends AppCompatActivity implements RestaurantsListAdapter.ItemClickListener{

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
        navigationView.bringToFront();

        ActionBarDrawerToggle myToogle = new ActionBarDrawerToggle(this,drawerLayout, toolbar ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(myToogle);
        myToogle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_fav_rest:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DisplayRestaurants.this,DisplayFavouriteRestaurants.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });




        recyclerView = findViewById(R.id.RestauListRV);

        RestauList = new ArrayList<>();
        //myAdapter = new RestaurantsListAdapter(this,RestauList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for( DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Restaurants restau = dataSnapshot1.getValue(Restaurants.class);
                        RestauList.add(restau);

                    }


                }




                myAdapter = new RestaurantsListAdapter(DisplayRestaurants.this,RestauList);
                myAdapter.setClickListener(DisplayRestaurants.this);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: ");


            }
        });
    }

    @Override
    public void onItemClick(View view, int position, List<Restaurants> RestauList) {

        Intent restIntent = new Intent(DisplayRestaurants.this,RestaurantDetails.class);
        restIntent.putExtra("restaurants", RestauList.get(position));
        startActivity(restIntent);

    }
}