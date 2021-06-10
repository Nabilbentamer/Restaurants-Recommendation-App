package com.example.casafoodie;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casafoodie.Adapter.RestaurantsFavouriteListAdapter;
import com.example.casafoodie.Adapter.RestaurantsListAdapter;
import com.example.casafoodie.Model.Restaurants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayFavouriteRestaurants extends AppCompatActivity implements RestaurantsFavouriteListAdapter.ItemClickListener{

    private RecyclerView recyclerView;
    private List<Restaurants> RestauList;
    private RestaurantsFavouriteListAdapter myAdapter;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fav_rest);

        recyclerView = findViewById(R.id.RestauListRV);

        RestauList = new ArrayList<>();
        //myAdapter = new RestaurantsListAdapter(this,RestauList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Favorite_restaurants/"+Login.user.getPhone_number());
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




                myAdapter = new RestaurantsFavouriteListAdapter(DisplayFavouriteRestaurants.this,RestauList);
                myAdapter.setClickListener(DisplayFavouriteRestaurants.this);
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

    }
}
