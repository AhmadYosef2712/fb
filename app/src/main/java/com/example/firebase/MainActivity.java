package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button s,l;
    private EditText e,p;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        s=findViewById(R.id.s);
        l=findViewById(R.id.l);
        e=findViewById(R.id.e);
        p=findViewById(R.id.p);

        auth=FirebaseAuth.getInstance();


        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =e.getText().toString().trim();
                String pass =p.getText().toString().trim();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length()<6){
                    Toast.makeText(MainActivity.this, "password must contain 6 num or chars", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this, "wrong mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "signed in", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this, dash.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "wrong mail or pass", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email =e.getText().toString().trim();
                String pass =p.getText().toString().trim();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length()<6){
                    Toast.makeText(MainActivity.this, "password must contain 6 num or chars", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this, "wrong mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "user created", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "already created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}