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
import android.widget.ImageView;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayRestaurants extends AppCompatActivity implements RestaurantsListAdapter.ItemClickListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView filter_iv;

    private RecyclerView recyclerView;
    private List<Restaurants> RestauList;
    private ArrayList<Restaurants> priceFilteredResultsList;
    private ArrayList<Restaurants> categoryFilteredResultsList;
    private ArrayList<Restaurants> goodForFilteredResultsList;

    ArrayList<Restaurants> finalFilteredResultsList = new ArrayList<>();
    ArrayList<Restaurants> maskFilteredResultsList = new ArrayList<>();

    private RestaurantsListAdapter myAdapter;
    private DatabaseReference databaseReference;

    String priceType,categoryType,good_for;


    private void filterList(){
        finalFilteredResultsList = new ArrayList<>();

        if(!priceType.equals("") && !categoryType.equals("") && !good_for.equals("")){

            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getPrice().equals(priceType.toLowerCase()) &&
                        RestauList.get(i).getCategory().equals(categoryType.toLowerCase()) &&
                        RestauList.get(i).getGood_for().equals(good_for.toLowerCase())){
                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }else if(!priceType.equals("") && !categoryType.equals("")){//1 & 2 == 2 && 1
            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getPrice().equals(priceType.toLowerCase()) &&
                        RestauList.get(i).getCategory().equals(categoryType.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }else if(!priceType.equals("") && !good_for.equals("")){//1 && 3 == 3 %% 1
            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getPrice().equals(priceType.toLowerCase()) &&
                        RestauList.get(i).getGood_for().equals(good_for.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }else if(!categoryType.equals("") && !good_for.equals("")){//2 && 3 == 3 %% 2

            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getCategory().equals(categoryType.toLowerCase()) &&
                        RestauList.get(i).getGood_for().equals(good_for.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }

        }else if(!priceType.equals("")){// 1
            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getPrice().equals(priceType.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }else if(!categoryType.equals("")){// 2
            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getCategory().equals(categoryType.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }else if(!good_for.equals("")){// 3
            for (int i = 0;i<RestauList.size();i++){
                if(RestauList.get(i).getGood_for().equals(good_for.toLowerCase())){

                    finalFilteredResultsList.add(RestauList.get(i));
                }
            }
        }

        myAdapter = new RestaurantsListAdapter(DisplayRestaurants.this,finalFilteredResultsList);
        myAdapter.setClickListener(DisplayRestaurants.this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurants);


        if(getIntent()!=null){
            priceType = getIntent().getStringExtra("price");
            categoryType = getIntent().getStringExtra("category");
            good_for = getIntent().getStringExtra("good_for");




        }


        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        filter_iv = findViewById(R.id.filter_iv);
        filter_iv.setOnClickListener(View->{
            Toast.makeText(getApplicationContext(), "Filtering", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(DisplayRestaurants.this,FiltersActivity.class);
            startActivity(intent);

        });


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

                    case R.id.nav_recommend:
                        Intent intTwo = new Intent(DisplayRestaurants.this,RecommendRestaurants.class);
                        startActivity(intTwo);
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


                if(priceType!=null || categoryType!=null || good_for!=null){
                    if(priceType==null)
                        priceType="";

                    if(categoryType==null)
                        categoryType="";

                    if(good_for==null)
                        good_for="";

                    filterList();
                }
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