package com.example.pc1.store;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Main_Activity extends AppCompatActivity {
    ListView chatlist;
    JSONArray jsonArray;
    adapter adapter;
    ArrayList<chat> chat_arrayList;
    LinearLayout chat_linerbutton;
    JSONObject item;
    Button btn;
    SharedPreferences login;
    String listview_load_id,listview_load_content,listview_load_img;
    private String listview_Load_json,Mypage_shared_id,listview_load_user;
    private String listview_load_index;
    private String lastchat;
    private String product_index;
    private String text;
    private String numberzero="1";
    private String sql2;
    private Cursor cursor33;
    private Boolean thread =true;
    private static String DB_NAME = "chat83.db";
    private static int DB_VERSION = 1;
    private DBhelp mDBhelp;
    private SQLiteDatabase db;
    private int count_read_msg;
    private static int refresh_num=0;
    private test test= new test();
    public static String gogoroom= "입장";
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__main_);
        login = getSharedPreferences("hoho", Context.MODE_PRIVATE);
        Mypage_shared_id=login.getString("id","nooo");
        LinearLayout Main_Chat_add=(LinearLayout)findViewById(R.id.Main_Chat_add);
        LinearLayout Main_Chat_Home=(LinearLayout)findViewById(R.id.Main_Chat_Home);
        LinearLayout Main_Chat_Mypage=(LinearLayout)findViewById(R.id.Main_Chat_Mypage);
                        listviewload();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);



        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("채팅목록");
        saoukkkk.setTextSize(19);

        Main_Chat_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Chat_add_Intent = new Intent(getApplicationContext(),Add_Main_Activity.class);
                startActivity(Main_Chat_add_Intent);
                finish();

            }
        });
        Main_Chat_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Chat_Home_Intent = new Intent(getApplicationContext(),MainActivity_test1.class);
                startActivity(Main_Chat_Home_Intent);
                finish();

            }
        });

        Main_Chat_Mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main_Chat_Mypage=new Intent(getApplicationContext(),Mypage_Activity.class);
                startActivity(Main_Chat_Mypage);
                finish();

            }
        });

        chat_arrayList = new ArrayList<chat>();
        chatlist = (ListView)findViewById(R.id.list_chat); //리스트뷰
        mDBhelp = new DBhelp(getApplicationContext(), DB_NAME, null, DB_VERSION);
//        listviewload();


        test.start();


        adapter = new adapter(getApplicationContext(),chat_arrayList);
        chatlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Log.e("saouk22",Mypage_shared_id);
        chatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gogoroom="입장";
                Intent intet = new Intent(getApplicationContext(),Chatroom_Activity.class);

                String getproduct_index = chat_arrayList.get(i).getproduct_index();
                String chdt_username_what = chat_arrayList.get(i).getusername();
                TextView tv = (TextView) view.findViewById(R.id.textView11);
                //채팅 방번호값



                TextView user_id =(TextView)view.findViewById(R.id.readnum);
                //유저아이디를 넘김
                text = tv.getText().toString();

                intet.putExtra("userid",user_id.getText().toString());
                intet.putExtra("pos",text);
                //방번호값

                intet.putExtra("gogoroom",gogoroom);
                intet.putExtra("getproduct_index_id",getproduct_index);
                intet.putExtra("gogo","gogo");
                intet.putExtra("chdt_username_what",chdt_username_what);
                //방제목을 넘김
                client_go_room();

//                Toast.makeText(getApplicationContext(),user_id.getText().toString(),Toast.LENGTH_SHORT).show();
                startActivity(intet);
                finish();

            }
        });



    }


    private void listviewload() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/listview_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
                chat_arrayList.clear();


//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                listview_Load_json = response;
                showResult();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("u_id", Mypage_shared_id);
                Log.e("saouk",Mypage_shared_id);
//                params.put("u_")
//                refresh();


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(listview_Load_json);
            jsonArray = jsonObject.getJSONArray("result");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                listview_load_index = item.getString("user_time");

                listview_load_id = item.getString("lastchat_name");
                listview_load_content = item.getString("lastchat_content");
                listview_load_img= item.getString("lastchat_img");
                listview_load_user= item.getString("last_user");
                product_index=item.getString("product_index");

                db = mDBhelp.getWritableDatabase();
                sql2 = "SELECT * FROM chat WHERE read = '"+ numberzero+"'AND room ='"+listview_load_index+"';";
                //읽지않은메세지
                cursor33 = db.rawQuery(sql2, null);
                count_read_msg=cursor33.getCount();
                Log.e("cursor33", String.valueOf(count_read_msg));


                chat_arrayList.add(new chat(listview_load_id, listview_load_user, listview_load_content,listview_load_img,listview_load_index,product_index,String.valueOf(count_read_msg)));
                adapter = new adapter(getApplicationContext(),chat_arrayList);
                chatlist.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }




        } catch (JSONException e) {


        }




    }
    private void client_go_room() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/read_msg.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("u_id", Mypage_shared_id.toString());

                params.put("u_time",text.toString());

                //방제목값을 서버로 넘겨서 서버에서 해당 거래에서 진행상황이 어떤지 가져온다.


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }


    @Override
    public void onStart() {
        Log.e("hellomannnnn", "start");
        super.onStart();



    }

    public void onResume() {
        Log.e("hellomannnnn", "resume");
        super.onResume();

    }


    @Override
    public void onPause() {
        Log.e("hellomannnnn", "pause");
        super.onPause();

    }

    public void onStop() {
        Log.e("hellomannnnn", "stop");
        super.onStop();
        thread=false;

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("hellomannnnn","restart");


        thread=true;
        adapter = new adapter(getApplicationContext(),chat_arrayList);
        adapter.notifyDataSetChanged();
    }


    private class test extends Thread{
        @Override
        public void run(){
            int count =0;
            while(thread){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        listviewload();
                        Log.e("listviewLoad","listviewLoad");

                    }
                });
            }
        }
    }

}
