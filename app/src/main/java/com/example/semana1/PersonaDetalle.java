package com.example.semana1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textview.MaterialTextView;

public class PersonaDetalle extends AppCompatActivity {

    MaterialTextView text_full_name;
    MaterialTextView text_birth;
    MaterialTextView text_email;
    MaterialTextView text_phone;
    MaterialTextView text_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_detalle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        text_full_name = (MaterialTextView) findViewById(R.id.nombre);
        text_birth = (MaterialTextView) findViewById(R.id.birth);
        text_email = (MaterialTextView) findViewById(R.id.email);
        text_phone = (MaterialTextView) findViewById(R.id.phone);
        text_description = (MaterialTextView) findViewById(R.id.description);

        Intent intent = getIntent();
        String full_name = intent.getStringExtra("full_name");
        String birth = intent.getStringExtra("birth");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String description = intent.getStringExtra("description");

        text_full_name.setText(full_name);
        text_birth.setText(birth);
        text_email.setText(email);
        text_phone.setText(phone);
        text_description.setText(description);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }//onCreate
}