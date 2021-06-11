package com.example.casafoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.casafoodie.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {


    EditText login_act_email_edt,login_act_pass_edt;
    Button login_act_valid_btn;
    private DatabaseReference databaseReference;
    public static Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        login_act_email_edt = findViewById(R.id.login_act_email_edt);
        login_act_pass_edt = findViewById(R.id.login_act_pass_edt);

        login_act_valid_btn = findViewById(R.id.login_act_valid_btn);
        login_act_valid_btn.setOnClickListener(View->{
            if(!TextUtils.isEmpty(login_act_email_edt.getText().toString()) && !TextUtils.isEmpty(login_act_pass_edt.getText().toString())){
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                Query emailQuery = databaseReference.orderByChild("email").equalTo(login_act_email_edt.getText().toString());
                emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            user = singleSnapshot.getValue(Users.class);
                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,DisplayRestaurants.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(),"Both Fields are mandatory",Toast.LENGTH_SHORT).show();
            }
        });




    }
}