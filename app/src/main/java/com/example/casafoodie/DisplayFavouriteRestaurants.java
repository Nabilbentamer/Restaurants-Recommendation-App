package com.example.casafoodie;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casafoodie.Adapter.RestaurantsFavouriteListAdapter;
import com.example.casafoodie.Adapter.RestaurantsListAdapter;
import com.example.casafoodie.Model.Restaurants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayFavouriteRestaurants extends AppCompatActivity {

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


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fillList();
    }

    public void deleteFavRestaurant(Restaurants restaurants) {
        String[] restName = restaurants.getName().split(" ");
        databaseReference = FirebaseDatabase.getInstance().getReference("Favorite_restaurants");

        FirebaseDatabase.getInstance().getReference("Favorite_restaurants")
                .child(Login.user.getUsername())
                .child(Login.user.getUsername() + "_" + restName[0])
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    fillList();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot Delete the Database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fillList() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Favorite_restaurants/" + Login.user.getUsername());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if(RestauList.size()>0)
                        RestauList.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        dataSnapshot1.getKey();
                        Restaurants restau = dataSnapshot1.getValue(Restaurants.class);
                        restau.setRestaurantID(dataSnapshot1.getKey());
                        RestauList.add(restau);

                    }
                }


                myAdapter = new RestaurantsFavouriteListAdapter(DisplayFavouriteRestaurants.this, RestauList);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: ");


            }
        });
    }
}
