package com.example.casafoodie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.casafoodie.Model.Restaurants;

import java.util.List;

public class RestaurantsRecommendedListAdapter extends RecyclerView.Adapter<RestaurantsRecommendedListAdapter.RestaurantsListViewHolder> {

    private Context context;
    private List<Restaurants> RestauList;

    public RestaurantsRecommendedListAdapter(Context context, List<Restaurants> RestauList) {
        this.context = context;
        this.RestauList = RestauList;


    }



    @NonNull
    @Override
    public RestaurantsRecommendedListAdapter.RestaurantsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View ItemView = layoutInflater.inflate(R.layout.restaurant_item,parent,false);
        RestaurantsRecommendedListAdapter.RestaurantsListViewHolder restaurantsListViewHolder = new RestaurantsRecommendedListAdapter.RestaurantsListViewHolder(ItemView);

        return restaurantsListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsRecommendedListAdapter.RestaurantsListViewHolder holder, int position) {

        Restaurants restaurants = RestauList.get(position);




        holder.professor_item_name_tv.setText(restaurants.getName());
        holder.professor_item_email_tv.setText("Email: " + restaurants.getPrice() + "\n" +
                "Matière ensignées: " + restaurants.getGood_for() + "\n"
        );
        //"Address: " + teacher.getAddress()

        Glide.with(context).load(restaurants.getImage()).into(holder.professor_item_name_iv);
    }

    @Override
    public int getItemCount() {

        return RestauList.size();
    }

    public class RestaurantsListViewHolder extends RecyclerView.ViewHolder  {
        TextView professor_item_name_tv,professor_item_email_tv;
        ImageView professor_item_name_iv;

        public RestaurantsListViewHolder(@NonNull View itemView) {
            super(itemView);
            professor_item_name_tv= itemView.findViewById(R.id.professor_item_name_tv);
            professor_item_email_tv= itemView.findViewById(R.id.professor_item_email_tv);
            professor_item_name_iv= itemView.findViewById(R.id.professor_item_name_iv);


        }

    }

}
