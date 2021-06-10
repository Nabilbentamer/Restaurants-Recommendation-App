package com.example.casafoodie.Adapter;

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
import com.example.casafoodie.R;

import java.util.List;

public class RestaurantsFavouriteListAdapter extends RecyclerView.Adapter<RestaurantsFavouriteListAdapter.RestaurantsListViewHolder> {

    private Context context;
    private List<Restaurants> RestauList;
    private RestaurantsFavouriteListAdapter.ItemClickListener mClickListener;


    public RestaurantsFavouriteListAdapter(Context context, List<Restaurants> RestauList) {
        this.context = context;
        this.RestauList = RestauList;

    }



    @NonNull
    @Override
    public RestaurantsFavouriteListAdapter.RestaurantsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View ItemView = layoutInflater.inflate(R.layout.restaurant_item,parent,false);
        RestaurantsListViewHolder restaurantsListViewHolder = new RestaurantsListViewHolder(ItemView);

        return restaurantsListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsListViewHolder holder, int position) {

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

    public class RestaurantsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView professor_item_name_tv,professor_item_email_tv;
        ImageView professor_item_name_iv;

        public RestaurantsListViewHolder(@NonNull View itemView) {
            super(itemView);
            professor_item_name_tv= itemView.findViewById(R.id.professor_item_name_tv);
            professor_item_email_tv= itemView.findViewById(R.id.professor_item_email_tv);
            professor_item_name_iv= itemView.findViewById(R.id.professor_item_name_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(),RestauList);
        }
    }


    public void setClickListener(RestaurantsFavouriteListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position,List<Restaurants> RestauList);
    }
}
