package com.example.pc1.store;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


public class Crowling_Activity extends AppCompatActivity {
    ListView crowling_list;
    private ArrayList<Crowling> arrayList;
    private String htmlPageUrl = "http://www.yonhapnews.co.kr/"; //파싱할 홈페이지의 URL주소
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";
    int cnt=0;
    Crowling_adapter adapter;
    private String my_link,my_title,my_imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowling_);
        crowling_list=(ListView)findViewById(R.id.crowling_list);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        arrayList = new ArrayList<Crowling>();
        adapter = new Crowling_adapter(getApplicationContext(),arrayList);
        crowling_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("오늘뉴스");
        saoukkkk.setTextSize(19);

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                arrayList.clear();
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();


            }
        });





        crowling_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent item_click = new Intent(getApplicationContext(),item_click_Activity.class);

                String link = arrayList.get(i).getNew_link();
                Log.e("link",link);
                item_click.putExtra("load_url",link);
                startActivity(item_click);

            }
        });



    }



    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect(htmlPageUrl).get();


                //테스트1

                //테스트2
                Elements titles= doc.select("div.column-area01").select("ul").select("li");
                int title_size3=titles.size();

                Log.e("saouk_size", String.valueOf(title_size3));


                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){

                    my_title = e.select("li h2.tit-news").text();
                    my_link = e.select("li div[class=img-con] a").attr("href");
                    my_imgUrl = e.select("li div[class=img-con] a img").attr("src");


                    Log.e("saouk1", String.valueOf(my_link));
                    Log.e("saouk2", String.valueOf(my_title));
                    Log.e("saouk3", String.valueOf(my_imgUrl));

                    if(!my_title.equals("")){

                        String str =my_imgUrl.replaceAll(" ", "");
                        arrayList.add(new Crowling(my_title,my_link,str));
//                        Log.e("saouk4","arrsylist");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.notifyDataSetChanged();

        }



    }



}
