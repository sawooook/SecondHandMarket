package com.example.pc1.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class image_big_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_big_);
        ImageView image_big_view =(ImageView)findViewById(R.id.image_big_view);
        Button image_big_btn =(Button)findViewById(R.id.image_big_back_btn);


        Intent imagepicture =getIntent();
        String imagepictur =imagepicture.getStringExtra("imagepicture");
        Log.e("imagepicture",imagepictur);

        Glide.with(getApplicationContext()).load(imagepictur).into(image_big_view);
        image_big_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_intent =new Intent(getApplicationContext(),review_Activity.class);
                startActivity(back_intent);

                finish();
            }
        });

    }
}
