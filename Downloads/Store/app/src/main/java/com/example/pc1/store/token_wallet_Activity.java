package com.example.pc1.store;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.LoginFilter;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
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
import org.json.simple.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import rx.Observable;

public class token_wallet_Activity extends AppCompatActivity {
    ListView token_Listview;
    tokenAdapter tokenAdapter;
    ArrayList<token>tokenArraylist;
    ArrayList<eth_transaction>ethArraylist;
    String url = config.addressethnode();
    SharedPreferences login;
    Button tokensend_btn;
//    eth_adapter eth_adapter;
    Web3j web3 = Web3jFactory.build(new HttpService(url));
    String smartcontract = config.addresssmartcontract();
    //  String passwordwallet = config.passwordwallet();
    LinearLayout tokenLinear;
    String passwordwallet;
    File DataDir;
    BigInteger GasPrice, GasLimit;
    String hhh;
    final Context context = this;
    TextView eth_tokenname,eth_token_symbol,eth_save_token;
    String address;
    ListView transaction_listview;
    private String Mypage_shared_id;
    private TextView send_token_transaction_result,send_token_transaction_result_text;
    TabHost tabHost;
    transaction_adapter transaction_adapter;
    private String transaction_api_response;
    private JSONArray jsonArray;
    ArrayList<transaction>token_transaction;
    private String transaction_api_response_hash,transaction_api_response_hash_timestamp,transaction_api_response_hash_from,transaction_api_response_hash_to,transaction_api_response_hash_value,transaction_api_response_hash_tokenname;
    private String transaction_api_response_hash_tokenSymbol;
    private ProgressDialog progressDialog4;
    private ListView eth_Listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_wallet_);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);
        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);
        saoukkkk.setText("토큰");
        saoukkkk.setTextSize(19);

        WalletCreate wc = new WalletCreate();
        send_token_transaction_result=(TextView)findViewById(R.id.transaction_result_edittext);
        send_token_transaction_result_text=(TextView)findViewById(R.id.transaction_result);


        Intent token_getinetent =getIntent();

        tokensend_btn=(Button)findViewById(R.id.button3);
        eth_tokenname=(TextView)findViewById(R.id.eth_tokenname);
        eth_token_symbol=(TextView)findViewById(R.id.eth_token_symbol1);
        eth_save_token=(TextView)findViewById(R.id.eth_save_token333333333);
        tokenLinear=(LinearLayout)findViewById(R.id.linearLayout3);
        tokenLinear.setVisibility(View.INVISIBLE);

        tokenArraylist = new ArrayList<token>();
        token_transaction= new ArrayList<transaction>();
//        ethArraylist=new ArrayList<eth_transaction>();

        transaction_listview=(ListView)findViewById(R.id.transaction_listview);
        token_Listview = (ListView)findViewById(R.id.tokenList); //리스트뷰
        eth_Listview=(ListView)findViewById(R.id.eth_list);


        tokenAdapter = new tokenAdapter(getApplicationContext(),tokenArraylist);
        token_Listview.setAdapter(tokenAdapter);

        transaction_adapter = new transaction_adapter(getApplicationContext(),token_transaction);
        transaction_listview.setAdapter(transaction_adapter);

//        eth_adapter =new eth_adapter(getApplicationContext(),ethArraylist);



        tabHost = (TabHost) findViewById(R.id.tab);
        tabHost.setup();


        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab1");
        tabSpec1.setIndicator("보유 코인"); // Tab Subject
        tabSpec1.setContent(R.id.pro); // Tab Content
        tabHost.addTab(tabSpec1);




        // Tab2 Setting

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab2");
        tabSpec2.setIndicator("TOKEN 거래내역"); // Tab Subject
        tabSpec2.setContent(R.id.teman); // Tab Content
        tabHost.addTab(tabSpec2);


//
//        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab3");
//        tabSpec3.setIndicator("ETH 거래내역"); // Tab Subject
//        tabSpec3.setContent(R.id.eth); // Tab Content
//        tabHost.addTab(tabSpec3);
//





        send_token_transaction_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent web_view = new Intent(getApplicationContext(),WebView_Activity.class);
                web_view.putExtra("send_token_transaction_result",send_token_transaction_result.getText().toString());
                web_view.putExtra("passwordwallet",passwordwallet);
                startActivity(web_view);


            }
        });

        tokensend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent token_intent =new Intent(getApplicationContext(),Token_Send_Activity.class);
                token_intent.putExtra("tokenpassword",hhh);


                startActivityForResult(token_intent,5);


            }
        });


//        GetGasLimit("42000");
//        GetGasPrice("22");

        Intent toooken =getIntent();
        //지갑의 비밀번호를 받아서 가져옴
        //이거조금 변형시킬예정
        hhh=toooken.getStringExtra("tokennumber");
        //로그인할때받아오는 비밀번호가 hhh
        passwordwallet=hhh;
        Log.e("passworldld",passwordwallet);
        Log.e("passworldld",hhh);
        DataDir = this.getExternalFilesDir("/keys/");
        File KeyDir = new File(this.DataDir.getAbsolutePath());




        /**
         * Проверяем есть ли кошельки
         * Check whether there are purses
         */
        File[] listfiles = KeyDir.listFiles();
        if (listfiles.length == 0 ) {
            /**
             * Если в директории файла кошелька, добавляем кошелек
             * If the directory file of the wallet, add the wallet
             */
            try {
                String fileName = WalletUtils.generateNewWalletFile(passwordwallet, DataDir, false);

                System.out.println("FileName: " + DataDir.toString() + fileName);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            /**
             * Если кошелек создан, начинаем выполнение потока
             * If the wallet is created, start the thread
             */
            wc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }



    }
    public class WalletCreate extends AsyncTask<Void, Integer, JSONObject> {
        //지갑을 생성함

        ProgressDialog progressDialog = new ProgressDialog(token_wallet_Activity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t잠시만 기다려주세요...");
            //show dialog
            progressDialog.show();


        }

        @Override
        protected JSONObject doInBackground(Void... params) {

            /**
             // Получаем список файлов в каталоге
             // Get list files in folder
             */
            File KeyDir = new File(DataDir.getAbsolutePath());
            File[] listfiles = KeyDir.listFiles();
            File file = new File(String.valueOf(listfiles[0]));

            try {
                Log.e("realreal","realplz");
                /**
                 // Загружаем файл кошелька и получаем адрес
                 // Upload the wallet file and get the address
                 */
                //지갑의 주소를 불러옴
                Credentials credentials = WalletUtils.loadCredentials(passwordwallet, file);
                address = credentials.getAddress();

                address_save();
                System.out.println("Eth Address: " + address);
                transaction_api();
                /**
                 // Получаем Баланс
                 // Get balance Ethereum
                 */
                EthGetBalance etherbalance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
                String ethbalance = Convert.fromWei(String.valueOf(etherbalance.getBalance()), Convert.Unit.ETHER).toString();
                System.out.println("Eth Balance: " + ethbalance);



                /**
                 // Загружаем Токен
                 // Download Token
                 */
                TokenERC20 token = TokenERC20.load(smartcontract, web3, credentials, GasPrice, GasLimit);

                /**
                 // Получаем название токена
                 // Get the name of the token
                 */
                String tokenname = token.name().send();
                System.out.println("Token Name: " + tokenname);

                /**
                 // Получаем Символ Токена
                 // Get Symbol marking token
                 */
                String tokensymbol = token.symbol().send();
                System.out.println("Symbol Token: " + tokensymbol);




                EthBlock.Block ethblock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock();
                List txlist = ethblock.getTransactions();

                EthBlock.Block block = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock();
                org.web3j.protocol.core.methods.response.Transaction transaction = web3.ethGetTransactionByBlockHashAndIndex(block.getHash(), BigInteger.ZERO).send().getTransaction();
                String hash = transaction.getHash();
                System.out.println("Symbol hohoho: " + hash);

                /**
                 // Получаем адрес Токена
                 // Get The Address Token
                 */

                String tokenaddress = token.getContractAddress();
                System.out.println("Address Token: " + tokenaddress);






                EthBlock ethBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
                        .send();
                BigInteger latestBlockNumber = ethBlock.getBlock().getNumber();


                Log.e("ggghelloeth", String.valueOf(latestBlockNumber));

                /**
                 // Получаем общее количество выпускаемых токенов
                 // Get the total amount of issued tokens
                 */
                BigInteger totalSupply = token.totalSupply().send();
                System.out.println("Supply Token: "+totalSupply.toString());

                /**
                 // Получаем количество токенов в кошельке
                 // Receive the Balance of Tokens in the wallet
                 */
                BigInteger tokenbalance = token.balanceOf(address).send();
                System.out.println("Balance Token: "+ tokenbalance.toString());

                JSONObject result = new JSONObject();

                result.put("ethaddress",address);
                result.put("ethbalance", ethbalance);
                result.put("tokenbalance", tokenbalance.toString());
                result.put("tokenname", tokenname);
                result.put("tokensymbol", tokensymbol);
                result.put("tokenaddress",tokenaddress);
                result.put("tokensupply", totalSupply.toString());

                Log.e("rkjkfdsjks", String.valueOf(result));


                return result;
            } catch (Exception ex) {System.out.println("ERROR:" + ex);}

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            if (result != null ){


                String tokenname = result.get("tokenname").toString();
                String tokenbalance =result.get("tokenbalance").toString();
                String tokensymbol= result.get("tokensymbol").toString();
                String ethBalance = result.get("ethbalance").toString();
                Log.e("eth_save_token",ethBalance);


                eth_save_token.setText(ethBalance);
                tokenLinear.setVisibility(View.VISIBLE);
                tokenArraylist.add(new token(tokenname,tokenbalance,tokensymbol));

                tokenAdapter.notifyDataSetChanged();

                Log.e("tokenname",tokenname);
                Log.e("tokenname",tokenbalance);
                Log.e("tokenname",tokensymbol);
//                ethaddress.setText(result.get("ethaddress").toString());
//                ethbalance.setText(result.get("ethbalance").toString());
//                tokenname.setText(result.get("tokenname").toString());
//                tokensymbol.setText(result.get("tokensymbol").toString());
//                tokensupply.setText(result.get("tokensupply").toString());
//                tokenaddress.setText(result.get("tokenaddress").toString());
//                tokenbalance.setText(result.get("tokenbalance").toString());
//                tokensymbolbalance.setText(" "+result.get("tokensymbol").toString());
//
//                qr_small.setImageBitmap(QRGen(result.get("ethaddress").toString(), 200, 200));
                progressDialog.dismiss();

            }
            else{
                System.out.println("Error!!!");
            }

        }
    }

    private void address_save() {


        login =getSharedPreferences("hoho",Context.MODE_PRIVATE);
        Mypage_shared_id=login.getString("id","nooo");


        StringRequest request = new StringRequest(Request.Method.POST, "http://52.14.144.55/adress_save.php", new Response.Listener<String>() {
            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {
//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                Log.e("RESULT",response);
                if(response.equals("nono")){


////                    Log.e("로그인", String.valueOf(response.equals("1")));
//                    Toast.makeText(getApplicationContext(),"저장안합니다 있어요",Toast.LENGTH_SHORT).show();
//                    Intent SuccessIntent = new Intent(Login_Activity.this,Main_Activity.class);
//                    startActivity(SuccessIntent);
//                    Log.e("RESULT","성공적으로 처리되었습니다!");
//                    editor.putString("id", MainActivity_id.getText().toString());
//                    editor.putString("pw", MainActivity_pw.getText().toString());
//                    editor.commit();

                }else{
//                    Log.e("RESULT","성공적으로 처리아님!");
////                    Log.e("로그인실패", String.valueOf(response.equals("0")));
//                    Toast.makeText(getApplicationContext(),"저장해요",Toast.LENGTH_SHORT).show();

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
                params.put("address_id", address);
                params.put("u_id", Mypage_shared_id);

                //이미지 저장함

                return params;
            }
        };

        Volley.newRequestQueue(token_wallet_Activity.this).add(request);



    }
    ////////////////// End create and load wallet ////////////////

    ///////////////////////// Sending Tokens /////////////////////
//    public class SendingToken extends AsyncTask<Void, Integer, JSONObject> {
//        ProgressDialog progressDialog2 = new ProgressDialog(token_wallet_Activity.this);
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog2.setMessage("\t잠시만 기다려주세요...");
//            //show dialog
//            progressDialog2.show();
//        }
//
//        @Override
//        protected JSONObject doInBackground(Void... param) {
//
//            /**
//             // Получаем список файлов в каталоге
//             // Get list files in folder
//             */
//            File KeyDir = new File(DataDir.getAbsolutePath());
//            File[] listfiles = KeyDir.listFiles();
//            File file = new File(String.valueOf(listfiles[0]));
//
//            try {
//                /**
//                 // Загружаем файл кошелька и получаем адрес
//                 // Upload the wallet file and get the address
//                 */
//                Credentials credentials = WalletUtils.loadCredentials(passwordwallet, file);
//                String address = credentials.getAddress();
//                System.out.println("Eth Address: " + address);
//
//                /**
//                 * Загружаем Токен
//                 * Load Token
//                 */
//                TokenERC20 token = TokenERC20.load(smartcontract, web3, credentials, GasPrice, GasLimit);
//                //
//                String status = null;
//                String balance = null;
//
//                /**
//                 * Конвертируем сумму токенов в BigInteger и отправляем на указанные адрес
//                 * Convert the amount of tokens to BigInteger and send to the specified address
//                 */
//                BigInteger sendvalue = BigInteger.valueOf(Long.parseLong(String.valueOf(sendtokenvalue.getText())));
//                status = token.transfer(String.valueOf(sendtoaddress.getText()), sendvalue).send().getTransactionHash();
//
//                /**
//                 * Обновляем баланс Токенов
//                 * Renew Token balance
//                 */
//                BigInteger tokenbalance = token.balanceOf(address).send();
//                System.out.println("Balance Token: "+ tokenbalance.toString());
//                balance = tokenbalance.toString();
//
//                /**
//                 * Возвращаем из потока, Статус транзакции и баланс Токенов
//                 * Returned from thread, transaction Status and Token balance
//                 */
//                JSONObject result = new JSONObject();
//                result.put("status",status);
//                result.put("balance",balance);
//
//                return result;
//            } catch (Exception ex) {System.out.println("ERROR:" + ex);}
//
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result) {
//            super.onPostExecute(result);
//
//            if (result != null) {
//                tokenbalance.setText(result.get("balance").toString());
//                Toast toast = Toast.makeText(getApplicationContext(),result.get("status").toString(), Toast.LENGTH_LONG);
//                toast.show();
//                progressDialog2.dismiss();
//
//            } else {System.out.println();}
//        }
//    }
//
//    public void GetGasLimit(String value) {
//        tv_gas_limit.setText(value);
//        GetFee();
//    }
//    public void GetGasPrice(String value) {
//        tv_gas_price.setText(value);
//        GetFee();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
// MainActivity 에서 요청할 때 보낸 요청 코드 (3000)




                case 5:

                    send_token_transaction_result.setVisibility(View.VISIBLE);
                    send_token_transaction_result_text.setVisibility(View.VISIBLE);

                    String address_id_find = data.getStringExtra("address_id_find");

                    SpannableString content = new SpannableString(address_id_find);
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//                    send_token_transaction_result.setTextColor(Color.BLUE);
                    send_token_transaction_result.setText(content);



                    break;
            }
        }
    }

    private void transaction_api() {



        StringRequest request = new StringRequest(Request.Method.POST,    "http://api-ropsten.etherscan.io/api?module=account&action=tokentx&address="+address+"&startblock=0&endblock=999999999&sort=asc&apikey=6F1TYVAPQ6J8PKRT5QKMB9PH813GPFF2GB"
                , new Response.Listener<String>() {

            //해당 mysql에 데이터를 저장함
            @Override
            public void onResponse(String response) {

//해당 데이터를 받아온다 1일시에는 로그인성공 0일시에는 로그인실패
                System.out.println("response: "+response);
                transaction_api_response=response;
                Log.e("response",response);
                transaction_api_response_json();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

//                params.put("u_id", "1");
////                params.put("u_")
////                    Log.e("id", id);?


                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void transaction_api_response_json(){
        try {

            Log.e("aaaaaaaaaaaaaa", "cccccccccccccc");
            org.json.JSONObject jsonObject = new org.json.JSONObject(transaction_api_response);
            jsonArray = jsonObject.getJSONArray("result");
            Log.e("json", String.valueOf(jsonArray.length()));

            for(int i=jsonArray.length()-1;i>-1;i--){

                org.json.JSONObject item = jsonArray.getJSONObject(i);
//                Log.e("jsonArray", String.valueOf(item));


                transaction_api_response_hash = item.getString("hash");
                transaction_api_response_hash_timestamp= item.getString("timeStamp");
                transaction_api_response_hash_from= item.getString("from");
                transaction_api_response_hash_to = item.getString("to");
                transaction_api_response_hash_value = item.getString("value");
                transaction_api_response_hash_tokenname= item.getString("tokenName");
                transaction_api_response_hash_tokenSymbol= item.getString("tokenSymbol");

                String transation_api_date=getDate(Long.parseLong(transaction_api_response_hash_timestamp));
                Log.e("transation_api_date",transation_api_date);

//                token_transaction.add(new transaction("1","1","1","1","1","1"));




                if(address.equals(transaction_api_response_hash_from)) {
                    //나의 주소랑 보낸사람이 똑같을 경우 이럴때는 그럼 OUT임
                    String tokenIn = "OUT";
                    token_transaction.add(new transaction(transaction_api_response_hash_tokenname, transaction_api_response_hash_value, transaction_api_response_hash_tokenSymbol, transation_api_date, transaction_api_response_hash_to,R.drawable.lett_arrow));
                    transaction_adapter.notifyDataSetChanged();
                }else{

                    String tokenIn = "IN";
                    token_transaction.add(new transaction(transaction_api_response_hash_tokenname, transaction_api_response_hash_value, transaction_api_response_hash_tokenSymbol, transation_api_date, transaction_api_response_hash_from,R.drawable.right_arrow));
                    transaction_adapter.notifyDataSetChanged();
                }

            }




        } catch (JSONException e) {


        }

    }


    private String getDate(long time) {
        Date date = new Date(time*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));

        return sdf.format(date);
    }
}
//}
