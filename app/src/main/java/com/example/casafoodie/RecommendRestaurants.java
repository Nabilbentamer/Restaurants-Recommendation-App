package com.example.casafoodie;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

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
import java.util.Random;

public class RecommendRestaurants extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Restaurants> RestauList;
    private List<Restaurants> AllRestsList;
    private RestaurantsRecommendedListAdapter myAdapter;
    private DatabaseReference databaseReference,databaseGetAllReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_recommend);


        recyclerView = findViewById(R.id.RestauListRV);

        RestauList = new ArrayList<>();
        AllRestsList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference("Favorite_restaurants/" + Login.user.getUsername());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (RestauList.size() > 0)
                        RestauList.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        dataSnapshot1.getKey();
                        Restaurants restau = dataSnapshot1.getValue(Restaurants.class);
                        restau.setRestaurantID(dataSnapshot1.getKey());
                        RestauList.add(restau);

                    }
                }


                if(RestauList.size()==0){
                    Toast.makeText(getApplicationContext(), "No Recommendations Found", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "No Recommendations Found", Toast.LENGTH_LONG).show();
                }else if(RestauList.size()==1){
                    getAllRestaurants(1);
                }else if(RestauList.size()>=2){
                    getAllRestaurants(2);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: ");


            }
        });
    }

    private void getAllRestaurants(int recommend){
        databaseGetAllReference = FirebaseDatabase.getInstance().getReference("Restaurants");
        databaseGetAllReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for( DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Restaurants restau = dataSnapshot1.getValue(Restaurants.class);
                        AllRestsList.add(restau);
                    }
                }



                if(recommend==1){
                    int check = (int )(Math.random() * AllRestsList.size() );
                    int checkTwo = (int )(Math.random() * AllRestsList.size() );

                    RestauList.clear();
                    RestauList.add(AllRestsList.get(check));
                    RestauList.add(AllRestsList.get(checkTwo));
                }else {

                    int check = (int )(Math.random() * AllRestsList.size() );
                    int checkTwo = (int )(Math.random() * AllRestsList.size() );
                    int checkThree = (int )(Math.random() * AllRestsList.size() );
                    int checkFour = (int )(Math.random() * AllRestsList.size() );
                    int checkfive = (int )(Math.random() * AllRestsList.size() );

                    RestauList.clear();
                    RestauList.add(AllRestsList.get(check));
                    RestauList.add(AllRestsList.get(checkTwo));
                    RestauList.add(AllRestsList.get(checkThree));
                    RestauList.add(AllRestsList.get(checkFour));
                    RestauList.add(AllRestsList.get(checkfive));
                }




                myAdapter = new RestaurantsRecommendedListAdapter(RecommendRestaurants.this, RestauList);
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
