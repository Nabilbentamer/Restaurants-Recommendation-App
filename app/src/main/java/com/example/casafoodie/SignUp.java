package com.example.casafoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.casafoodie.Model.Restaurants;
import com.example.casafoodie.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private DatabaseReference databaseReference;
    EditText act_signup_password_edt,act_signup_phone_edt,act_signup_username_edt,act_signup_email_edt;
    Button act_signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        act_signup_email_edt = findViewById(R.id.act_signup_email_edt);
        act_signup_password_edt = findViewById(R.id.act_signup_password_edt);
        act_signup_phone_edt = findViewById(R.id.act_signup_phone_edt);
        act_signup_username_edt = findViewById(R.id.act_signup_username_edt);
        act_signup_btn = findViewById(R.id.act_signup_btn);

        act_signup_btn.setOnClickListener(View->{

            Users users = new Users();
            users.setEmail(act_signup_email_edt.getText().toString());
            users.setPassword(act_signup_password_edt.getText().toString());
            users.setPhone_number(act_signup_phone_edt.getText().toString());
            users.setUsername(act_signup_username_edt.getText().toString());



            FirebaseDatabase.getInstance()
                    .getReference("Users/" + act_signup_phone_edt.getText().toString()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"User Signed Up successfully",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignUp.this,Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(),"Cannot Complete the task ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });



    }
}