package com.example.pc1.store;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.RequestOptions;

import org.web3j.abi.datatypes.Int;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Chatroom_Activity extends AppCompatActivity {
    public  Socket socket;
    String line, mac, idd, url, realurlimg, realuri, idrealplz, sql, querySelectAll,sql2,sql3;
    private static int port = 5001;
    private ImageView photogalleryandcamera;
    private static final int PICK_FROM_CAMERA = 0;
    long now;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    private static final String ipText = "172.30.1.39"; //
    TextView showText;
    private adapter adapter;
    byte[] byteArray;
    Button chatroom_Button_send;
    Bitmap photo, bmImg;
    EditText chatroom_editText_massage;
    private String imggogo="■사진";
    SharedPreferences.Editor editor;
    SharedPreferences login;
    Handler msghandler;
    ListView chatroom_listView;
    SocketClient client;
    ReceiveThread receive;
    public String urlString = "http://52.14.144.55/image.php";
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    private static String DB_NAME = "chat83.db";
    private static String DB_NAME2 = "chat2.db";
    private static int DB_VERSION = 1;
    private DBhelp mDBhelp;
    private DBhelp2 mDBhelp2;
    SendThread send;

    long noo = System.currentTimeMillis();
    //최근에 온방을 올리기위해서 만든 timestamp 숫자가 높으면 먼저옴

    String[] msg;
    Button sell_finish;
    String[] filt1;
    LinkedList<SocketClient> threadList;
    private ArrayList<item> arrayList;
    String roomm,chdt_username_what;
    String imgmsg, textmsg;
    SharedPreferences pref;
    ImageView chatroom_testimageView;
    private Process sock;
    String getproduct_index_id;
    private SQLiteDatabase db;
    private SQLiteDatabase db2;
    private Cursor cursor;
    private String Mypage_shared_img, Mypage_shared_nickname;
    private String Mypage_shared_id,dateToString , timeToString,Chating_shared ;
    String sendmsg;
    test test=new test();
    test2 test2 =new test2();
    private String userid;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String reviewplz;
    private String finish ="거래완료 대기중입니다...";
    private String Mypage_shared_lastmsg;
    private String numberone="읽음";
    private String numberzero="0";
    private String webrtc_id="Saouk";
    private Cursor cursor33;
    Calendar cal;
    Random rand;
    private int count_read_msg;
    private String insertzzz;
    long webrtc_room ;
    String webrtc_room_num_real;
    private String webrtc_room_num;
    private int randnum;
    private boolean Saoukthread=true;
    private String gogoroom;
     String entrancegogo;
    private String webrrc_id,webrrc_what,webrrc_user_id,webrrc_user_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        login = getSharedPreferences("hoho", MODE_PRIVATE);
        editor = login.edit();
        Mypage_shared_id = login.getString("id", "nooo");
        Intent chatroomintent = getIntent();
        webrtc_room = System.currentTimeMillis();
        Saoukthread=true;

        test.start();

        webrtc_room_num=webrtc_id+webrtc_room;
        Log.e("webrtc_room_num145345",webrtc_room_num);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/jeju.ttf");
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);

        saoukkkk.setTypeface(type);
        saoukkkk.setText("채팅");
        saoukkkk.setTextSize(19);

        roomm = chatroomintent.getStringExtra("pos"); //채팅방에서 방번호값을 가져온다 방번호값은 타임
        editor.putString("room",roomm);

        if(TextUtils.isEmpty(roomm)){

            roomm=login.getString("room","123");

        }else{



        }


        chdt_username_what=chatroomintent.getStringExtra("chdt_username_what");
        //클라이언트가 방에 들어왔는지 안들어왔는지 확인
        //존이랑 사욱이 있어 존이 접속을해 sharedPreference에 저장을해 존이라고
        //





//        finsh_sell_what_number();
        //해당아이템을 판매자들이 구매완료햇는지 숫자를 불러온다.


        Log.e("roommmmm", String.valueOf(roomm));
        chatroom_testimageView = (ImageView) findViewById(R.id.testimageView);
        chatroom_editText_massage = (EditText) findViewById(R.id.editText_massage);
        chatroom_Button_send = (Button) findViewById(R.id.Button_send);
        threadList = new LinkedList<Chatroom_Activity.SocketClient>();
        chatroom_listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<item>();
        userid = chatroomintent.getStringExtra("userid"); //채팅방에서 상대방의 아이디를 가져온다.
        mDBhelp = new DBhelp(this, DB_NAME, null, DB_VERSION);
//        mDBhelp2 = new DBhelp2(this, DB_NAME2, null, DB_VERSION);

        db = mDBhelp.getWritableDatabase();
//        db2= mDBhelp2.getWritableDatabase();


        client = new SocketClient("172.30.1.39", "5001");
        threadList.add(client);
        client.start();



//아래 msg핸들러로부터 소켓통신으로부터 받은 메세지를 받아온다
        //받은 msg는 잘라서 배열에 넣는다
        msghandler = new Handler() {
            @Override
            public void handleMessage(Message hdmsg) {
                Log.d("받은메세지 ", hdmsg.obj.toString());
                if (hdmsg.what == 222) {
//                    Log.e("who", "9999");
                    msg = hdmsg.obj.toString().split("@");

                    chat_last_msg_save();
                    chat_last_msg_save2();


                    webrtc_room_num_real= msg[5];
                    cal = Calendar.getInstance();


                    timeToString = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
                    dateToString = timeToString;


                    querySelectAll = "SELECT * FROM  CHAT WHERE room='" + msg[0] + "'";
                    //chat테이블중에서 room이름이 0 인데이터를 모두 가져온다
                    //그다음 chat 테이블에 메세지를 담아준다


                    if(msg[2].equals("hellooooooooooooowebrtc")&&msg[1].equals(Mypage_shared_id)) {

                        Intent action = new Intent(getApplicationContext(), ConnectActivity.class);
                        action.putExtra("webrtc_room_num", webrtc_room_num_real);


                        startActivity(action);
                        finish();
                        socket.isClosed();

                    }

                    if(msg[2].equals("hellooooooooooooowebrtc")&&msg[1].equals(userid)){


                        sendmsg = "■영상통화";
                        send = new SendThread(socket, sendmsg);
//                    Log.e("scoketsoso", String.valueOf(socket));
                        send.start();
                        chatroom_editText_massage.setText("");




                        Intent action3 =new Intent (getApplicationContext(),Call_Webrtc_Activity.class);
                        action3.putExtra("webrtc_img",Mypage_shared_img);
                        action3.putExtra("webrtc_id",idd);
                        action3.putExtra("webrtc_room_num",webrtc_room_num_real);

                        startActivity(action3);
                        finish();
                        socket.isClosed();


                    }



                    if(msg[2].equals("hellooooooooooooowebrtc")){

                        msg[2]="■영상통화";

                    }


                    sql = "insert into CHAT (_id,NAME,content,img,room,profile,read,time) values(null, '" + msg[1] + "','" + msg[2] + "','" + msg[3] + "','" + msg[0] + "','" + msg[4] + "','1','"+dateToString+"');";
                    Log.e("msg1", sql);

                    db.execSQL(sql);
                    editor.putString("lastchatmsgmsg", msg[2]);
                    editor.commit();


                    cursor = db.rawQuery(querySelectAll, null);
                    adapter.changeCursor(cursor);






//                    if(msg[2].equals("거래완료")){
//
//                        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                doTakeCamera();
//                            }
//                        };
//                        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                doTakeAlbumAction();
//                            }
//                        };
//
//                        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        };
//
//                        new AlertDialog.Builder(Chatroom_Activity.this)
//                                .setTitle("거래를 완료하시겠습니까?")
//                                .setPositiveButton("네", cameraListener)
//                                .setNegativeButton("아니요", cancelListener)
//                                .show();
//
//                    }

//                    imgmsg = msg[3];
//
//                    Log.e("msgmsg", imgmsg);
//                    textmsg = msg[2];
//                    Log.e("msgmsg", textmsg);
                }
//                    Log.e("hh", String.valueOf(arrayList.add(new item(hdmsg.obj.toString(),mac))));


            }
        };

        SELECTDB();
//if(client.){



        //채팅서버를 시작하는 부분
        //ipconfig에서 ip를맞춰줘야한다
        //쓰래드를통하여 시작한다

//현재 여기서 소켓을 없애버리면 데이터를 받을수 가 없음 그럼 데이터를 않끝기게하려면 중복입장을 방지해야함

        if(TextUtils.isEmpty(chatroomintent.getStringExtra("reviewplz"))){
            Log.e("reviewplz","reviewplz1");

        }else{

            reviewplz=chatroomintent.getStringExtra("reviewplz");
            Log.e("reviewplz",reviewplz);

            if(reviewplz.equals("reviewplz")){
                sendmsg = "거래가 완료되었습니다";
                Log.e("reviewplz","reviewplz3");
                send = new SendThread(socket, sendmsg);
//                    Log.e("scoketsoso", String.valueOf(socket));
                send.start();
                chatroom_editText_massage.setText("");



            }

        }



        chatroom_testimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Chatroom_Activity.this);
                builder.setTitle("원하시는것을 선택해주세요");
                builder.setItems(new CharSequence[]
                                {"이미지보내기", "음성보내기","영상통화","취소"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        imagedialog();
                                        break;
                                    case 1:
                                        sound_send();
                                        break;
                                    case 2:
                                        webrtc();
                                        break;
                                    case 3:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
        });


        chatroom_Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendmsg = chatroom_editText_massage.getText().toString();
                if (chatroom_editText_massage.getText().toString() != null) {
//                    arrayList.add(new item(editText_massage.getText().toString()));
//                    Log.e("hh", String.valueOf(arrayList.add(new item(editText_massage.getText().toString()))));
//                    adapter.notifyDataSetChanged();


                    send = new SendThread(socket, sendmsg);
//                    Log.e("scoketsoso", String.valueOf(socket));
                    send.start();
                    chatroom_editText_massage.setText("");
//                    fcm_read_message();

//                    read_msg();







                }
            }
        });
    }

    private void webrtc() {
//        webrtcroom();
        sendmsg="hellooooooooooooowebrtc";
        send = new SendThread(socket, sendmsg);
//                    Log.e("scoketsoso", String.valueOf(socket));
        send.start();
        chatroom_editText_massage.setText("");


    }

    private void read_msg() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/no_read.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패

                //1일때는 접속중일때

                if(response.equals("0")){
                    //0일때는  접속중이아닐때
                    //그럼 접속중이아니면
//                    fcm();


                }else if(response.equals("1")) {


                    sql2 ="UPDATE chat SET read = '"+numberzero+"' WHERE room ='"+roomm+"';";
                    db.execSQL(sql2);

                    querySelectAll = "SELECT * FROM  CHAT WHERE room='" + roomm + "'";
                    cursor = db.rawQuery(querySelectAll, null);
                    adapter.changeCursor(cursor);

                    //여기는 접속중일때임
                    Log.e("nononono","nononononoono");


                    //이때는 채팅방에 접속중임
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("u_id",Mypage_shared_id.toString());
                params.put("u_time",roomm.toString());

                //방제목값을 서버로 넘겨서 서버에서 해당 거래에서 진행상황이 어떤지 가져온다.


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);


    }


    private void finsh_sell_what_number() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/what_num.php", new Response.Listener<String>() {
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

                params.put("u_title", getproduct_index_id.toString());
                //방제목값을 서버로 넘겨서 서버에서 해당 거래에서 진행상황이 어떤지 가져온다.


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }


    private void sound_send(){
        promptSpeechInput();

//        sendmsg="hello";
//        send = new SendThread(socket, sendmsg);
////                    Log.e("scoketsoso", String.valueOf(socket));
//        send.start();
//        chatroom_editText_massage.setText("");
//
//        Intent action =new Intent (getApplicationContext(),ConnectActivity.class);
//        action.putExtra("webrtc_room_num",webrtc_room_num);
//        startActivity(action);
//



    }




    private void imagedialog() {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeCamera();
            }
        };
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(Chatroom_Activity.this)
                .setTitle("원하시는것을 선택해주세요")
                .setPositiveButton("카메라촬영", cameraListener)
                .setNeutralButton("갤러리", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();

    }

    private void promptSpeechInput() {

        //음성인식이가능한 메서드

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void SELECTDB() {
        //db에서 쓸수있도록한다
        db = mDBhelp.getWritableDatabase();


        Log.e("qqqqqqqqqqq", String.valueOf(db));
        sql = "SELECT * FROM chat WHERE room = '"+ roomm+"';";
        Log.e("qqqqqqqqqqqq", sql);
        cursor = db.rawQuery(sql, null);
        Log.e("qqqqqqqqqqqq", String.valueOf(cursor));



        adapter = new adapter(getApplicationContext(), cursor);
        chatroom_listView.setAdapter(adapter);
//        read_msg();




    }


    class SocketClient extends Thread {
        private final String ip;
        boolean threadAlive;
        //소켓서버가 시작하는곳
        String port;
        String id;
        //

        BufferedReader br = null;
        DataInputStream input = null;
        private DataOutputStream output = null;

        public SocketClient(String ip, String port) {
            threadAlive = true;
            this.ip = ip;
            this.port = port;
        }


        @Override
        public void run() {

            try {

                //해당 소켓과 포트로 tcp/ip통신을 진행함
                //소켓을 통하여 데이터를 받거나 보낼수 있음
                //그리고 recive쓰래드를 동시에 돌려서 오는 메세지를 받는다
                socket = new Socket(ipText, Integer.parseInt(port));
                //inputStream = socket.getInputStream();
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());

                receive = new ReceiveThread(socket);
                receive.start();
//                Log.e("who", "7777");
//                WifiManager mng = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//                WifiInfo info = mng.getConnectionInfo();
//                mac = info.getMacAddress();
                output.writeUTF(port + "@" + roomm + "@" + Mypage_shared_id+ "@" + "ss");
//
//                sql3 = "insert into chat2 (_id,ID,ROOM,entrance) values(null, '" + Mypage_shared_id + "','"+roomm+"','"+gogoroom+"');";
//                db2.execSQL(sql3);



                //방번호랑 아이디를 동시에저장함
                //처음에 접속할때 보내는 메세지 아웃풋에담아서 포트 룸 idd등을 보낸다 마지막은 맞추기위해서 억지로 하드코딩해봐았음
                //시간나면 수정해보자

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ReceiveThread extends Thread {
        private Socket socket = null;
        DataInputStream input;

        public ReceiveThread(Socket socket) {
            this.socket = socket;
            try {
                Log.e("whooooooo", "6666");
                input = new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
            }
        }

        public void run() {
            try {
                while (input != null) {
//                    Log.e("who", "5555");
                    String msg = input.readUTF();
                    //여기메세지는 소켓서버로부터 받은메세지를 읽는 곳이다
                    Log.e("whooooooo", msg);
                    if (msg != null) {

//                        Log.e("who", "4444");
                        Message hdmsg = msghandler.obtainMessage();
                        hdmsg.what = 222;
                        hdmsg.obj = msg;
                        msghandler.sendMessage(hdmsg);
                        //메세지를 받은다음 해당메세지를 맨위에있는 핸들러로 보낸다
//                        Log.e("who", String.valueOf(hdmsg));
//                        Log.e("who", msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     class SendThread extends Thread {
        private Socket socket;
        String sendmsg;
        DataOutputStream output;

        public SendThread(Socket socket, String sendmsg) {
            this.socket = socket;
            this.sendmsg = sendmsg;
            try {
                output = new DataOutputStream(socket.getOutputStream());
            } catch (Exception e) {
            }
        }

        public void run() {

            try {

//                Log.e("who", "11111");

//                WifiManager mng = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
////                WifiInfo info = mng.getConnectionInfo();
//                Intent Intent = getIntent();
//                mac = Intent.getStringExtra("id");
//                Log.e("saouk", mac);

                idd = login.getString("id", "nooo");
                Mypage_shared_img = login.getString("img", "nooo");
                Mypage_shared_nickname = login.getString("nickname", "nooo");

                Log.e("hphphp", String.valueOf(Mypage_shared_img));
                if (output != null) {
                    if (sendmsg != null) {
                        output.writeUTF(roomm + "@" + idd + "@" + sendmsg + "@" + null + "@" + Mypage_shared_img + "@" + webrtc_room_num);


//                      방번호 아이디 메세지를 담아서 보낸다 마지막 널은 이미지자리 이미지를 보낼수 없으니 여기서는 메세지만 보내는곳이다
//                          Log.e("no.1", String.valueOf(roomm));
//                        Log.e("no.1", String.valueOf(idd));
//                        Log.e("no.1", String.valueOf(sendmsg));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();

            }
        }
    }

    public class adapter extends CursorAdapter {

        public adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.listchat, viewGroup, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            LinearLayout mainlayout = (LinearLayout) view.findViewById(R.id.liner_main);
            ImageView imgg = (ImageView) view.findViewById(R.id.array_img);
            TextView text = (TextView) view.findViewById(R.id.text);
            LinearLayout read_layout =(LinearLayout)view.findViewById(R.id.read_layout);
            TextView read_time= (TextView)view.findViewById(R.id.read_time);
            TextView read_text=(TextView)view.findViewById(R.id.read_text);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.messageItem_linerlayout_destionation);

            LinearLayout opposite_read_layout = (LinearLayout) view.findViewById(R.id.opposite_read_layout);
            TextView opposite_read_time=(TextView)view.findViewById(R.id.opposite_read_time);




            TextView text3 = (TextView) view.findViewById(R.id.idididid);
            ImageView profileimg = (ImageView) view.findViewById(R.id.messageItem_imageview_profile);
            String getEdit = text.getText().toString();
            LinearLayout imglinear = (LinearLayout) view.findViewById(R.id.gooddood);





            idd = login.getString("id", "nooo");
            Mypage_shared_img = login.getString("img", "nooo");
            Mypage_shared_nickname = login.getString("nickname", "nooo");

            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .override(300, 300);
            //상대방이 보낸메세지
            if (!cursor.getString(cursor.getColumnIndex("NAME")).equals(idd)) {



                Glide.with(getApplicationContext()).load(cursor.getString(cursor.getColumnIndex("profile"))).apply(new RequestOptions().circleCrop()).into(profileimg);
                Glide.with(getApplicationContext()).load(cursor.getString(cursor.getColumnIndex("img"))).apply(myOptions).into(imgg);
                layout.setVisibility(View.VISIBLE);
                mainlayout.setGravity(Gravity.LEFT);
                opposite_read_time.setText(cursor.getString(cursor.getColumnIndex("time")));
                read_layout.setVisibility(View.GONE);
                text3.setText(cursor.getString(cursor.getColumnIndex("NAME")));
                text.setText(cursor.getString(cursor.getColumnIndex("content")));
                imglinear.setBackgroundResource(R.drawable.right1);


            } else {
                Glide.with(getApplicationContext()).load(cursor.getString(cursor.getColumnIndex("profile"))).into(profileimg);
                Glide.with(getApplicationContext()).load(cursor.getString(cursor.getColumnIndex("img"))).apply(myOptions).into(imgg);
                layout.setVisibility(View.GONE);
                read_time.setText(cursor.getString(cursor.getColumnIndex("time")));
                mainlayout.setGravity(Gravity.RIGHT);
                opposite_read_layout.setVisibility(View.GONE);
                read_layout.setVisibility(View.VISIBLE);
                text3.setText(cursor.getString(cursor.getColumnIndex("NAME")));
                text.setText(cursor.getString(cursor.getColumnIndex("content")));
                read_text.setText(cursor.getString(cursor.getColumnIndex("read")));
                imglinear.setBackgroundResource(R.drawable.left1);

            }

//            TextView room_text = (TextView) view.findViewById(R.id.room1_text);
//            room_text.setText(cursor.getString(cursor.getColumnIndex("NAME")));
////
//

        }
    }


//    private class SendThreadimage extends Thread {
//        byte[] byteArray;
//        private Socket socket;
//        DataOutputStream outputimage;
//
//        public SendThreadimage(Socket socket, byte[] byteArray) {
////
////
//            this.socket = socket;
//            this.byteArray = byteArray;
//            try {
//                outputimage = new DataOutputStream(socket.getOutputStream());
//            } catch (Exception e) {
//            }
//        }
//
//        public void run() {
//            try {
//                outputimage.write(byteArray);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                outputimage.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            try {
////
////            try {
////                DataInputStream dis = new DataInputStream(new FileInputStream(String.valueOf(byteArray)));
////            } catch (FileNotFoundException e) {
////                e.printStackTrace();
////            }
////
////                int b=0;
////                while( (b=dis.read()) != -1 ){
////                    outputimage.writeByte(b); outputimage.flush();
////                }
////
////                outputimage.close();
////                dis.close();socket.close();
////            } catch (IOException e) {
////                System.out.println(e);
////                e.printStackTrace();
////            }
////                 硫붿꽭吏 ?꾩넚遺 (?꾧뎔吏 ?앸퀎?섍린?꾪븳 諛⑸쾿?쇰줈 mac瑜??ъ슜)
//            Log.e("who", "11111");
//
////                WifiManager mng = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//////                WifiInfo info = mng.getConnectionInfo();
////                Intent Intent = getIntent();
////                mac = Intent.getStringExtra("id");
////                Log.e("saouk", mac);
//
////mac?먮떎媛 ???꾩씠?붽컪???ｌ뼱??蹂대궡硫??ъ슧: xxxx ?섏씠?섏씠 留뚮뱾???덉쓬
//            //?댁궗?뚯? ?앸퀎???꾪뼆??mac?대씪??二쇱냼瑜??ъ슜?쒓굅媛숈쓬
//            //?쒖븞?좉퉴 cc瑜??ｌ뼱?쒕낫?몃떎
//
////                if (outputimage != null) {
////
////                    Log.e("whoh", "2222");
////                    outputimage.write(byteArray);
////
////                    Log.e("whoh", String.valueOf(byteArray));
////
////                }
//        }
//    }


//    private void save() {
//
//
//        Gson gson = new Gson();
//
//        pref = getSharedPreferences("pref", MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = pref.edit();
//
//        String json = new Gson().toJson(arrayList);
//
//        editor.putString("my json", json);
//
//
//        editor.commit();
//
//
//    }

//    private void load() {
//        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
//
//        Gson gson = new Gson();
//
//        String json = pref.getString("my json", "");
//
//        Type type = new TypeToken<ArrayList<item>>(){}.getType();
//        ArrayList<item> carsList = gson.fromJson(json, type);
//        arrayList.addAll(carsList);
//
//
//    }


    private void doTakePhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";


//
//        save();

        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));


        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

//    private void save() {
//
//
//
//        SharedPreferences myPrefs = getSharedPreferences("image",MODE_PRIVATE);
//        SharedPreferences.Editor myPrefsEdit = myPrefs.edit();
//
//        myPrefsEdit.clear();
//
//
//        myPrefsEdit.putString("hn", url.toString());
//        myPrefsEdit.commit();
//
//
//    }


    private void doTakeAlbumAction() {
        // ?⑤쾾 ?몄텧
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);


    }

    private void doTakeCamera() {
        Intent intent33 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent33, PICK_FROM_ALBUM);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case CROP_FROM_CAMERA: {


                final Bundle extras = data.getExtras();


                if (extras != null) {
                    Bitmap photoo = extras.getParcelable("data");
//                    String path = MediaStore.Images.Media.insertImage(getContentResolver(), photo, null, null);
                    fixMediaDir();
                    String path = MediaStore.Images.Media.insertImage(getContentResolver(), photoo, null, null);
                    Uri photoUri = Uri.parse(path);
                    Log.e("uri", String.valueOf(photoUri));

/*
* realUri는 이미지의 진짜경로를 얻는 부분이다
이얻은부분을 이용해서 파일을 서버에 업로드할것이다
파일을 서버에 업로드 -> 파일을 서버에 저장하고 -> 해당이미지를 디바이스에 보내줌
*
* */
                    realuri = getPath(photoUri);
                    Log.e("urii", realuri);
//                   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                    byte[] currentData = byteArrayOutputStream.toByteArray();
//                    new SaveImageTask().execute(currentData);
                    DoFileUpload(realuri);

//                Log.e("this2", String.valueOf(byteArray));
//


                    ////                Log.e("photoreal", String.valueOf(photo));
//                    if(byteArray!=null){
//
//                        gogo();
//
//                    }

                    //                    try{
//
//                        PrintWriter out = new PrintWriter(new BufferedWriter(new
//                                OutputStreamWriter(sock.getOutputStream())), true);
//
//
//                        out.println(msg);
//                        out.flush();
//
//
//                        System.out.println("?곗씠?곗갼?붿쨷");
//
//
//                        DataInputStream dis = new DataInputStream(new FileInputStream(String.valueOf(photo))); //?쎌쓣 ?뚯씪 寃쎈줈 ?곸뼱 二쇱떆硫??⑸땲??
//
//
//                        DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
//
//
//                        byte[] buf = new byte[1024];
//
//                        long totalReadBytes = 0;
//
//                        int readBytes;
//                        while ((readBytes = dis.read(buf)) > 0) { //湲몄씠 ?뺥빐二쇨퀬 ??留욊쾶 ?쒕쾭濡?蹂대깄?덈떎.
//                            dos.write(buf, 0, readBytes);
//
//                            totalReadBytes += readBytes;
//                        }
//
//
//                        dos.close();
//                    }
//                    catch(Exception e){
//                        e.printStackTrace();
//                    }


//                    String path = MediaStore.Images.Media.insertImage(getContentResolver(), photo,null, null);
                }

                // ?꾩떆 ?뚯씪 ??젣
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM: {


                mImageCaptureUri = data.getData();


            }

            case PICK_FROM_CAMERA: {


                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 150);
                intent.putExtra("outputY", 150);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);

                startActivityForResult(intent, CROP_FROM_CAMERA);
                break;
            }
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    chatroom_editText_massage.setText(result.get(0));
                }
                break;
            }
        }


    }

    /*파일 업로드 메소드 getpath에서 얻은 진짜 경로를 통하여서 쓰래드를 돌린다
     * 아래쓰래드는 이미지를 서버에 보내는 코드이다
     *
     * */
    private void DoFileUpload(String realuri) {
        Runnable runnable1 = new background2();
        Thread thread = new Thread(runnable1);
        thread.start();

    }

    //이미지 절대경로를 얻는 메소드
    //일반적으로 사진에서 uri를 얻으면 완벽한주소는 나오지않는다 하지만 getpath이 메소드를 사용하면 완벽하게 주소가나온다
    // .jpg까지 나오는 메소드이다
    public String getPath(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor3 = managedQuery(contentUri, projection, null, null, null);
        int column_index = cursor3.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor3.moveToFirst();
        return cursor3.getString(column_index);
    }
//
//    private void gogo() {
//        Log.e("whoh", "22222");
//        sendimage = new SendThreadimage(socket, byteArray);
//        Log.e("whoh123", String.valueOf(byteArray));
//        Log.e("whoh123", String.valueOf(socket));
//        sendimage.start();
//
//
//    }


    private class SaveImageTask extends AsyncTask<byte[], Void, Void> {

        {


        }

        //이미지파일을 업로드 하는 메서드
        @Override
        protected Void doInBackground(byte[]... data) {
            try {
                Socket sock = new Socket("172.30.1.39", 5001);
                OutputStream out = sock.getOutputStream();


                String fileLength = String.valueOf(data[0].length);
                Log.e("ImageFile Length: ", fileLength);

                String header = "0000000000".substring(0, 10 - fileLength.length()) + fileLength;
                Log.e("Header", header);

                out.write(header.getBytes());
                out.write(data[0]);
                Log.e("datawhat", String.valueOf(data[0]));
                sock.close();
                Log.e("Soket", "Running!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;


        }
    }

    private class background2 implements Runnable {
        @Override
        public void run() {
/*
위에서받은 uri를 통하여 해당 웹서버와 통신 urlstring은 서버와 통신할 웹주소이다 이 웹주소에는 이미지 저장 코드가 들어 있음
나는 이미지를 mulitpart로 보낼건데 이걸 사용하는 이유는 데이터와 이미지를 동시에 전송할 수 있어서이다
데이터를 전송하는 이유는 채팅에 필요한 아이디값을 받아오려고 하기때문임
*/

            try {
                FileInputStream mFileInputStream = new FileInputStream(realuri);
                URL connectUrl = new URL(urlString);
                Log.d("File Up1", "mFileInputStream is " + mFileInputStream);

                HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                now = System.currentTimeMillis();
/*
나는 여기서 파일이름 현재시간으로 저장함 해당 서버에서 파일을 저장하는데 중복이됨으로 해결방안으로 현재시간을 통하여 파일이름을
붙여서 이이름으로 서버에 저장을함
 */

                String fileName1 = String.valueOf(now);
                String chat_filename1 = "chat_" + fileName1;

                dos.writeBytes("\r\n--" + boundary + "\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"name\"\r\n\r\n" + idd);
                dos.writeBytes("\r\n--" + boundary + "\r\n");
//아이디를 보내는 코드
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + chat_filename1 + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
//이미지를 보내는 코드
                int bytesAvailable = mFileInputStream.available();
                int maxBufferSize = 1024;
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                byte[] buffer = new byte[bufferSize];
                int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
//이건 이미지를 그냥 uri로 보내는것이아니라 bytearray로 만들어서 서버에 보내는것이다

                Log.d("Test", "image byte is " + bytesRead);

                // read image
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = mFileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
                    //해당 바이트어레이가 다운이 되도록 기다리는코드이다
                }

                dos.writeBytes(lineEnd);
                dos.writeBytes("\r\n--" + boundary + "--\r\n");
                // close streams
                Log.e("Test", "File is written");
                mFileInputStream.close();
                dos.flush(); // finish upload...
                //파일 업로드를 완료하는 코드 매우중요
                // get response
                BufferedReader rd = null;


                /*
                여기 아래서부터는 서버로부터 응답을 받아오는것
                 */
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((line = rd.readLine()) != null) {
                    idrealplz = line;
                    Log.i("Lifeclue", line);
                    filt1 = idrealplz.split("@");

                }

                /*
               나는 서버로부터 아이디와 해당 이미지의 url을 받아왔다
               ex) lna1003@20180731 이렇게 받아와서 자른후 filt1에 데이터를 하나씩 넣어놨다
               idrealplz는 line에서 받은값
                 */

                dos.close();


            } catch (Throwable t) {
                Log.e("SampleJavaThread", "Exception in thread.", t);
            }


            realurlimg = "http://52.14.144.55/" + filt1[1];
            Log.e("plzplzplzplz", realurlimg);
            back task;
            task = new back();
            Saoukthread=true;
            task.execute("http://52.14.144.55/" + filt1[1]);

                /*
               realurlimg는 해당 이미지를 불러오는 메서드 서버에 저장되었기때문에 고정 서버주소에 뒤에 나의 파일이름을 붙임
               그래서 이미지를 불러오도록함 이 주소를 통해서  이미지를 보내는 메서드 진행
                 */
        }
    }

    private class back extends AsyncTask<String, Integer, Bitmap> {
        @Override

        protected Bitmap doInBackground(String... urls) {


            try {
                URL url = new URL(urls[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
//                int length =conn.getContentLength();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//
//
//                Log.e("gggggggggggggg", String.valueOf(length));

//                InputStream is = conn.getInputStream();


                bmImg = BitmapFactory.decodeStream(bis);
                Saoukthread=true;

            } catch (IOException e) {

                e.printStackTrace();

            }

            return bmImg;
        }


        protected void onPostExecute(Bitmap imgf) {
//         Log.e("gggggggggggggg", String.valueOf(img));
//            com.example.pc1.tcp.img.se
//            Log.e("linelineline", idrealplz);
//            arrayList.add(new item(idrealplz,null,imgf));
//
//            adapter.notifyDataSetChanged();

            SendThread3 send3 = new SendThread3(socket, realurlimg);
            send3.start();
            test2.start();


        }


    }

    class SendThread3 extends Thread {
        String realurlimg;
        Socket socket;
        DataOutputStream output;


        public SendThread3(Socket socket, String realurlimg) {
            this.socket = socket;
            this.realurlimg = realurlimg;
            try {
                // 채팅 서버로 메세지를 보내기 위한  스트림 생성.
                output = new DataOutputStream(socket.getOutputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // 서버로 메세지 전송 ( 이클립스 서버단에서 temp 로 전달이 된다.
        public void run() {
            try {
                if (output != null) {
                    if (realurlimg != null) {
                        idd = login.getString("id", "nooo");
                        Mypage_shared_nickname = login.getString("nickname", "nooo");
                        Mypage_shared_img = login.getString("img", "nooo");
                        // 여기서 방번호와 상대방 아이디 까지 해서 보내줘야 할거같다 .
                        // 서버로 메세지 전송하는 부분
                        output.writeUTF(roomm + "@" + filt1[0] + "@" + "" + "@" + realurlimg + "@" + Mypage_shared_img + "@" + Mypage_shared_nickname);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File mediaDir = new File(sdcard, "DCIM/Camera");
            if (!mediaDir.exists()) {
                mediaDir.mkdirs();
            }
        }
    }
//    protected void onStop(){
//        super.onStop();
//        Log.e("ss", "111111111111111111111111111");
//        try {
//            socket.close();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    private void fcm() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/push.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
                Log.e("RESULT",response);





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Mypage_shared_id = login.getString("id", "nooo");
                Mypage_shared_img=login.getString("img","nooo");

                Map<String, String> params = new HashMap<>();
                params.put("fcm", sendmsg);
                params.put("fcm_myid",Mypage_shared_id);
                params.put("fcm_userid",userid);
                Log.e("user_id",userid);
                params.put("fcm_img",Mypage_shared_img);
                params.put("fcm_room",roomm);


                //아이디값을 넘김


                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);

    }
    private void finsih_send_add() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/sell_update.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT", response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("u_title", Mypage_shared_id.toString());

//                params.put("finish_one", "1");



                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);


    }


    private void chat_last_msg_save2() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/chat_last_msg_save2.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Mypage_shared_lastmsg = login.getString("lastchatmsgmsg", "nooo");
                Map<String, String> params = new HashMap<>();
                if(!msg[3].equals("null")) {
                    //null일경우엔 이미지가 아니라 사진이다
                    Log.e("msg3333",msg[3]);
                    params.put("u_id", chdt_username_what.toString());
                    params.put("u_content",imggogo);
                    params.put("u_time",roomm.toString());


                }else {
                    params.put("u_id", chdt_username_what.toString());
                    params.put("u_content",Mypage_shared_lastmsg.toString());
                    params.put("u_time",roomm.toString());
                    params.put("timestamo", String.valueOf(noo));
                    Log.e("msgwhy",Mypage_shared_lastmsg.toString());
                }


                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);

    }

    private void chat_last_msg_save() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/chat_last_msg_save.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Mypage_shared_lastmsg = login.getString("lastchatmsgmsg", "nooo");
                Map<String, String> params = new HashMap<>();
                if(!msg[3].equals("null")) {
                    //null일경우엔 이미지가 아니라 사진이다
                    Log.e("msg3333",msg[3]);

                    params.put("u_id", chdt_username_what.toString());
                    params.put("u_content",imggogo);
                    params.put("u_time",roomm.toString());


                }else {

                    params.put("u_id", chdt_username_what.toString());
                    params.put("u_content",Mypage_shared_lastmsg.toString());
                    params.put("u_time",roomm.toString());
                    params.put("timestamo", String.valueOf(noo));
                    Log.e("msgwhy",Mypage_shared_lastmsg.toString());

                }



                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        out_client();

//        sql3 ="UPDATE chat2 SET entrance = '퇴장' WHERE room ='"+roomm+"' AND ID='"+Mypage_shared_id+"';";
        sql2 ="UPDATE chat SET read = '"+numberzero+"' WHERE room ='"+roomm+"';";
        Log.e("sql2543534534534534",sql2);
//        db2.execSQL(sql3);
        db.execSQL(sql2);


//        test.interrupt();

        Intent backPressedIntent = new Intent(getApplicationContext(),Chat_Main_Activity.class);
        startActivity(backPressedIntent);
        finish();

    }

    private void out_client() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/out_client.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Mypage_shared_lastmsg = login.getString("lastchatmsgmsg", "nooo");
                Map<String, String> params = new HashMap<>();
                params.put("u_id", Mypage_shared_id.toString());
                params.put("u_time",roomm.toString());
                params.put("u_msg", String.valueOf(count_read_msg));
                Log.e("umsg", String.valueOf(count_read_msg));


                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);



    }


    private void fcm_read_message() {
        //fcm을 보낼지 말지 확인하는 메서드
        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/fcm_read.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);

                if(response.equals("1")){
                    //접속해 있을때
                    Log.e("hihi","nono");

                }else if(response.equals("0")){
                    //접속해 있지 않을때


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Mypage_shared_lastmsg = login.getString("lastchatmsgmsg", "nooo");
                Map<String, String> params = new HashMap<>();
                params.put("u_id", Mypage_shared_id.toString());
                //내아이디를 보냄
                params.put("u_time",roomm.toString());
                //방번호를 보냄
                params.put("u_msg", String.valueOf(count_read_msg));
                Log.e("umsg", String.valueOf(count_read_msg));


                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(Chatroom_Activity.this).add(request);



    }

    private class test extends Thread{
        @Override
        public void run(){
            while(Saoukthread){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        read_msg();
//                            read();


                    }
                });
            }
        }
    }


    private class test2 extends Thread{
        @Override
        public void run(){
            while(Saoukthread){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        read_msg();
//                            read();


                    }
                });
            }
        }
    }

//    private void read() {


//        String sql5 = "SELECT * FROM chat2 WHERE room = '" + roomm + "'AND ID = '" + userid + "';";
//        Cursor cursor4 = db2.rawQuery(sql5,null);
//        String saouk=cursor4.getString(cursor4.getColumnIndex("entrance"));
//        Log.e("cursorwjy", String.valueOf(saouk));
//


//    }

//    private void read() {
//        if(gogoroom.equals("0")){
//            //0일때는  접속중이아닐때
//            //그럼 접속중이아니면
//
//
//        }else if(gogoroom.equals("1")) {
//
//
//            sql2 ="UPDATE chat SET read = '"+numberzero+"' WHERE room ='"+roomm+"';";
//            db.execSQL(sql2);
//
//            querySelectAll = "SELECT * FROM  CHAT WHERE room='" + roomm + "'";
//            cursor = db.rawQuery(querySelectAll, null);
//            adapter.changeCursor(cursor);
//
//            //여기는 접속중일때임
//            Log.e("nononono","nononononoono");
//
//
//        }
//
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Saoukthread=true;
        adapter.notifyDataSetChanged();
        Log.e("helloman","1234");
        editor.putString("room",roomm);
        editor.commit();


    }
    @Override
    protected void onPause() {
        super.onPause();
        Saoukthread=true;

        Log.e("helloman","12345");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e("helloman","123456");
        Saoukthread=false;
        adapter.notifyDataSetChanged();



    }
    private void webrtcroom() {
//        StringRequest request = new StringRequest(Request.Method.POST, "http://52.15.42.189/webrtc.php", new Response.Listener<String>() {
//            //해당 mysql에 데이터를 저장함
//            @Override
//            public void onResponse(String response) {
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<>();
//
//                params.put("id", Mypage_shared_id);
//                params.put("room", userid);
//                params.put("u_id", Mypage_shared_id);
////                params.put("u_")
////                refresh();
//
//
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getApplicationContext()).add(request);



    }

}