
package com.example.pc1.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Call_Webrtc_Activity extends AppCompatActivity {

    ImageView wer_rtc_call,wer_rtc_call_reject,web_rtc_who_image;
    private TextView webrtc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__webrtc_);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        wer_rtc_call=(ImageView)findViewById(R.id.wer_rtc_call);
        wer_rtc_call_reject=(ImageView)findViewById(R.id.wer_rtc_call_reject);
        web_rtc_who_image=(ImageView)findViewById(R.id.web_rtc_who_image);
        webrtc_id=(TextView)findViewById(R.id.webrtc_id);


        Intent webrtc_intet =getIntent();
        String webrtc_room_num= webrtc_intet.getStringExtra("webrtc_room_num");
        Log.e("webrtc_room_num1",webrtc_room_num);
        String webrto_img=webrtc_intet.getStringExtra("webrtc_img");
        String webrtc_id_idid=webrtc_intet.getStringExtra("webrtc_id");


        Glide.with(getApplicationContext()).load(webrto_img).apply(new RequestOptions().circleCrop()).into(web_rtc_who_image);
        webrtc_id.setText(webrtc_id_idid);
        wer_rtc_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webrtc_intent = new Intent(getApplicationContext(),ConnectActivity.class);
                webrtc_intent.putExtra("webrtc_room_num",webrtc_room_num);
                startActivity(webrtc_intent);
                finish();


            }
        });
        wer_rtc_call_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
