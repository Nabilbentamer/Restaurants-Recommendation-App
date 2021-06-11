package com.example.casafoodie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.casafoodie.Model.Restaurants;
import com.google.android.gms.common.internal.ResourceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class RestaurantDetails extends AppCompatActivity {

    ImageView act_res_restaurant_iv;
    TextView act_res_restaurant_name_tv,act_res_restaurant_des_tv,act_res_restaurant_price_tv;
    RatingBar simpleRatingBar;
    FloatingActionButton act_res_add_to_fav_fab;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detials);

        Intent intent = getIntent();
        Restaurants restaurants = (Restaurants) intent.getSerializableExtra("restaurants");

        String[] restName = restaurants.getName().split(" ");


        act_res_add_to_fav_fab = findViewById(R.id.act_res_add_to_fav_fab);
        act_res_add_to_fav_fab.setOnClickListener(View->{
            databaseReference = FirebaseDatabase.getInstance().getReference("Favorite_restaurants");

            databaseReference.child(Login.user.getUsername()).child(Login.user.getUsername()+"_"+restName[0])
                    .setValue(restaurants).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Restaurant has been added successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Cannot Complete the task ",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


        simpleRatingBar = findViewById(R.id.simpleRatingBar);
        simpleRatingBar.setRating(Float.parseFloat(restaurants.getRatings()));


        act_res_restaurant_price_tv = findViewById(R.id.act_res_restaurant_price_tv);
        act_res_restaurant_price_tv.setText("Price: " + restaurants.getPrice());


        act_res_restaurant_iv = findViewById(R.id.act_res_restaurant_iv);
        Picasso.with(RestaurantDetails.this)
                .load(restaurants.getImage())
                .placeholder(R.drawable.food_plate_top_2)
                .into(act_res_restaurant_iv);


        act_res_restaurant_name_tv = findViewById(R.id.act_res_restaurant_name_tv);
        act_res_restaurant_name_tv.setText(restaurants.getName());

        act_res_restaurant_des_tv = findViewById(R.id.act_res_restaurant_des_tv);
        act_res_restaurant_des_tv.setText(restaurants.getDescription());
    }

}
