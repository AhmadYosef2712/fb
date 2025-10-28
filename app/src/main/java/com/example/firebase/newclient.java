package com.example.firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class newclient extends AppCompatActivity {
    Button add;
    EditText age, name, address, phone;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newclient);
        db = FirebaseFirestore.getInstance();
        age =findViewById(R.id.a);
        name =findViewById(R.id.n);
        address =findViewById(R.id.ad);
        phone=findViewById(R.id.ph);
        add=findViewById(R.id.l);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=age.getText().toString();
                String n=name.getText().toString();
                String add=address.getText().toString();
                String pho=phone.getText().toString();

                Map<String, Object> client = new HashMap<>();
                client.put("Name", n);
                client.put("Age", a);
                client.put("Address", add);
                client.put("Phone", pho);

                db.collection("Clients")
                        .add(client)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(newclient.this, "client has been added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(newclient.this, "client cant be added", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}