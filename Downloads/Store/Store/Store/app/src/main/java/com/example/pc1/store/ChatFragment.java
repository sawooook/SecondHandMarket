package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.kenai.jffi.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.MatOfByte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatFragment extends Fragment {
    ListView chatlist;
    JSONArray jsonArray;
    adapter adapter;
    ArrayList<chat> chat_arrayList;
    LinearLayout chat_linerbutton;
    JSONObject item;
    Button btn;
    SharedPreferences login;
    String listview_load_id, listview_load_content, listview_load_img;
    private String listview_Load_json, Mypage_shared_id, listview_load_user;
    private String listview_load_index;
    private String lastchat;
    private String product_index;
    private String text;
    private String numberzero = "1";
    private String sql2;
    private Cursor cursor33;
    private static String DB_NAME = "chat83.db";
    private static int DB_VERSION = 1;
    private DBhelp mDBhelp;
    private SQLiteDatabase db;
    test test;
    private int count_read_msg;
    private static int refresh_num = 0;
    private boolean Saoukthread = true;

    public static Fragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat_fragment, container, false); // 여기서 UI를 생성해서 View를 return
        chat_arrayList = new ArrayList<chat>();
        chatlist = (ListView) view.findViewById(R.id.list_chat); //리스트뷰
        mDBhelp = new DBhelp(getActivity(), DB_NAME, null, DB_VERSION);
//        listviewload();



        adapter = new adapter(getActivity(), chat_arrayList);
        chatlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        login = getActivity().getSharedPreferences("hoho", Context.MODE_PRIVATE);
        Mypage_shared_id = login.getString("id", "nooo");
        chatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intet = new Intent(getActivity(), Chatroom_Activity.class);

                String getproduct_index = chat_arrayList.get(i).getproduct_index();


                String chdt_username_what = chat_arrayList.get(i).getusername();


                TextView tv = (TextView) view.findViewById(R.id.textView11);
                //채팅 방번호값

                TextView user_id = (TextView) view.findViewById(R.id.readnum);
                //유저아이디를 넘김
                text = tv.getText().toString();

                intet.putExtra("userid", user_id.getText().toString());
                intet.putExtra("pos", text);
                //방번호값

                intet.putExtra("getproduct_index_id", getproduct_index);
                intet.putExtra("gogo", "gogo");
                intet.putExtra("chdt_username_what", chdt_username_what);
                //방제목을 넘김
                client_go_room();

                Toast.makeText(getActivity(), user_id.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivityForResult(intet, 1000);

            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            refresh();

        }
    }

    private void listviewload() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://18.223.187.117/listview_load.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
                chat_arrayList.clear();


//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                listview_Load_json = response;
                Log.e("idddd2", response);
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
//                params.put("u_")
//                refresh();
                Log.e("id", Mypage_shared_id);


                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);

    }

    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(listview_Load_json);
            jsonArray = jsonObject.getJSONArray("result");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);
                listview_load_index = item.getString("user_time");

                listview_load_id = item.getString("lastchat_name");
                listview_load_content = item.getString("lastchat_content");
                listview_load_img = item.getString("lastchat_img");
                listview_load_user = item.getString("last_user");
                product_index = item.getString("product_index");

                db = mDBhelp.getWritableDatabase();
                sql2 = "SELECT * FROM chat WHERE read = '" + numberzero + "'AND room ='" + listview_load_index + "';";
                //읽지않은메세지
//                Log.e("cursor33",sql2);
                cursor33 = db.rawQuery(sql2, null);
                count_read_msg = cursor33.getCount();
                Log.e("cursor33", String.valueOf(count_read_msg));


                chat_arrayList.add(new chat(listview_load_id, listview_load_user, listview_load_content, listview_load_img, listview_load_index, product_index, String.valueOf(count_read_msg)));

                adapter = new adapter(getActivity(), chat_arrayList);
                chatlist.setAdapter(adapter);
                adapter.notifyDataSetChanged();


//                    Log.e("fff", id);
            }


        } catch (JSONException e) {


        }

        Log.e("gggggggggggggggg", "ggggggggg");

    }

    private void client_go_room() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://18.223.187.117/read_msg.php", new Response.Listener<String>() {
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

                params.put("u_time", text.toString());

                //방제목값을 서버로 넘겨서 서버에서 해당 거래에서 진행상황이 어떤지 가져온다.


                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(request);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("frag1", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("frag2", "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        Log.d("frag3", "onStart()");
        test = new test();
        test.start();
        super.onStart();
    }



    public void onResume() {
        Log.d("frag4", "onResume()");
        Saoukthread =true;
        adapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.d("listviewLoad", "onpaufvfvfvfse()");
        super.onPause();

    }

    public void onStop() {
        Log.d("frag6", "onStop()");
        Saoukthread=false;
        super.onStop();
    }



    private void refresh() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


    private class test extends Thread {
        @Override
        public void run() {
            while (Saoukthread) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listviewload();


                    }
                });
            }
        }
    }

}
