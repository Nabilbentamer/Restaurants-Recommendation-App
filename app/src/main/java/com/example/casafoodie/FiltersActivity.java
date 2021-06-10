package com.example.casafoodie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class FiltersActivity extends AppCompatActivity {


    private String priceType,categoryType,good_for;


    private RadioButton price_radio;
    private RadioGroup price_type_rbg;
    private Button act_filterns_apply_filter_btn;


    private  RadioGroup category_type_rbg;
    private RadioButton category_radio;

    private  RadioGroup good_for_type_rbg;
    private RadioButton good_for_radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_restaurants);


        price_type_rbg = findViewById(R.id.price_type_rbg);
        category_type_rbg = findViewById(R.id.category_type_rbg);
        good_for_type_rbg = findViewById(R.id.good_for_type_rbg);


        act_filterns_apply_filter_btn = findViewById(R.id.act_filterns_apply_filter_btn);
        act_filterns_apply_filter_btn.setOnClickListener(View->{
            //Price Value
            int selectedId=price_type_rbg.getCheckedRadioButtonId();
            price_radio = findViewById(selectedId);



            //Category Values
            int selectedCatId=category_type_rbg.getCheckedRadioButtonId();
            category_radio = findViewById(selectedCatId);

            //Good For Values
            int selectedGoodFortId=good_for_type_rbg.getCheckedRadioButtonId();
            good_for_radio = findViewById(selectedGoodFortId);



            priceType = price_radio.getText().toString();
            categoryType = category_radio.getText().toString();
            good_for = good_for_radio.getText().toString();


            Intent intent = new Intent(FiltersActivity.this,DisplayRestaurants.class);
            intent.putExtra("price",priceType);
            intent.putExtra("category",categoryType);
            intent.putExtra("good_for",good_for);
            startActivity(intent);



        });



    }
}
