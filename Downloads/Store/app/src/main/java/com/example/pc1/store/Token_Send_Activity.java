package com.example.pc1.store;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.simple.JSONObject;
import org.spongycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Token_Send_Activity extends AppCompatActivity {

    EditText send_token_address,send_token_money;
    Spinner send_token_spinner;
    TextView gas_price,gas_Limit33,gas_fee,token_what;
    Button send_token_trasnfer,send_token_address_find;
    String passwordwallet;
    SharedPreferences login;



    String url = config.addressethnode();
    Web3j web3 = Web3jFactory.build(new HttpService(url));
    String smartcontract = config.addresssmartcontract();
    final Context context = this;

    File DataDir;
    String token_password;
    BigInteger GasPrice, GasLimit;
    private String Mypage_shared_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token__send_);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        TextView saoukkkk=(TextView)findViewById(R.id.saoukkkk);
        saoukkkk.setText("토큰전송");
        saoukkkk.setTextSize(19);

        send_token_spinner=(Spinner)findViewById(R.id.send_token_spinner);
        send_token_trasnfer=(Button) findViewById(R.id.send_token_trasnfer);
        send_token_address_find=(Button)findViewById(R.id.send_token_address_find);
        send_token_money=(EditText) findViewById(R.id.send_token_money);
        send_token_address=(EditText)findViewById(R.id.send_token_address);
        Intent toooken =getIntent();
        token_password=toooken.getStringExtra("tokenpassword");

        passwordwallet = token_password;

        token_what=(TextView)findViewById(R.id.token_what);
        gas_price=(TextView)findViewById(R.id.gas_price);
        gas_Limit33=(TextView)findViewById(R.id.gas_Limit);
        gas_fee=(TextView)findViewById(R.id.gas_fee);

        DataDir = this.getExternalFilesDir("/keys/");
        File KeyDir = new File(this.DataDir.getAbsolutePath());



        GetGasLimit("42000");
        GetGasPrice("22");
        GetFee();
        send_token_address_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address_find = new Intent(getApplicationContext(),Address_find_Acitivity.class);
                startActivityForResult(address_find,1);

            }
        });

        send_token_trasnfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendingToken st = new SendingToken();
                SendingEther se = new SendingEther();
                if(token_what.getText().toString().equals("ETH")){
                    Log.e("token","ETH");
                    se.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);



                }else if(token_what.getText().toString().equals("SY")){

                    st.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    Log.e("token","SY");

                }


            }
        });

        send_token_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){
                    eth_send();

                }else if(position==1){
                    SY_send();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void SY_send() {
        token_what.setText("SY");

    }

    private void eth_send() {
        token_what.setText("ETH");

    }
    public class SendingToken extends AsyncTask<Void, Integer, JSONObject> {
        ProgressDialog progressDialog2 = new ProgressDialog(Token_Send_Activity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog2.setMessage("\t잠시만 기다려주세요...");
            //show dialog
            progressDialog2.show();
        }

        @Override
        protected JSONObject doInBackground(Void... param) {

            /**
             // Получаем список файлов в каталоге
             // Get list files in folder
             */
            File KeyDir = new File(DataDir.getAbsolutePath());
            File[] listfiles = KeyDir.listFiles();
            File file = new File(String.valueOf(listfiles[0]));

            try {
                /**
                 // Загружаем файл кошелька и получаем адрес
                 // Upload the wallet file and get the address
                 */
                Credentials credentials = WalletUtils.loadCredentials(passwordwallet, file);
                String address = credentials.getAddress();
                System.out.println("Eth Address: " + address);

                /**
                 * Загружаем Токен
                 * Load Token
                 */
                TokenERC20 token = TokenERC20.load(smartcontract, web3, credentials, GasPrice, GasLimit);
                System.out.println("token token: " + token);
                //
                String status = null;
                String balance = null;

                /**
                 * Конвертируем сумму токенов в BigInteger и отправляем на указанные адрес
                 * Convert the amount of tokens to BigInteger and send to the specified address
                 */
                BigInteger sendvalue = BigInteger.valueOf(Long.parseLong(String.valueOf(send_token_money.getText())));
                status = token.transfer(String.valueOf(send_token_address.getText()), sendvalue).send().getTransactionHash();

                /**
                 * Обновляем баланс Токенов
                 * Renew Token balance
                 */
                BigInteger tokenbalance = token.balanceOf(address).send();
                System.out.println("Balance Token: "+ tokenbalance.toString());
                balance = tokenbalance.toString();

                /**
                 * Возвращаем из потока, Статус транзакции и баланс Токенов
                 * Returned from thread, transaction Status and Token balance
                 */
                JSONObject result = new JSONObject();
                result.put("status",status);
                result.put("balance",balance);
                System.out.println("Balance status: "+ status);
                System.out.println("Balance balance: "+ balance);

//                fcm();
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

            if (result != null) {

//                Toast toast = Toast.makeText(getApplicationContext(),result.get("status").toString(), Toast.LENGTH_LONG);
//                toast.show();
                progressDialog2.dismiss();


                Intent tokensend = new Intent();
                tokensend.putExtra("address_id_find",result.get("status").toString());
                setResult(RESULT_OK,tokensend);
                finish();


            } else {System.out.println();}
        }
    }
    /////////////////////// End Sending Tokens ///////////////////

    ///////////////////////// Sending Ether //////////////////////
    public class SendingEther  extends AsyncTask<Void, Integer, JSONObject> {
        ProgressDialog progressDialog1 = new ProgressDialog(Token_Send_Activity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog1.setMessage("\t잠시만 기다려주세요...");
            //show dialog
            progressDialog1.show();



        }

        @Override
        protected JSONObject doInBackground(Void... param) {

            /**
             // Получаем список файлов в каталоге
             // Get list files in folder
             */
            File KeyDir = new File(DataDir.getAbsolutePath());
            File[] listfiles = KeyDir.listFiles();
            File file = new File(String.valueOf(listfiles[0]));

            try {
                /**
                 // Загружаем файл кошелька и получаем адрес
                 // Upload the wallet file and get the address
                 */
                Credentials credentials = WalletUtils.loadCredentials(passwordwallet, file);
                String address = credentials.getAddress();
                System.out.println("Eth Address: " + address);

                System.out.println("Eth Balance: " + send_token_money.getText().toString());

                /**
                 * Получаем счетчик транзакций
                 * Get count transaction
                 */
                EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).sendAsync().get();
                BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                System.out.println("nonce: "+ nonce);

                /**
                 * Convert ammount ether to BigInteger
                 */
                BigInteger value = Convert.toWei(String.valueOf(send_token_money.getText()), Convert.Unit.ETHER).toBigInteger();
                System.out.println("value: "+ value);
                /**
                 * Транзакция
                 * Transaction
                 */
                RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(nonce, GasPrice, GasLimit, String.valueOf(send_token_address.getText()), value);
                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                String hexValue = "0x"+ Hex.toHexString(signedMessage);
                EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue.toString()).sendAsync().get();
                System.out.println("Transaction: "+ethSendTransaction);


                /**
                 * Get Transaction Error and Hash
                 */
                System.out.println("Error: "+ ethSendTransaction.getError());
                System.out.println("Transaction: " + ethSendTransaction.getTransactionHash());

                /**
                 * Возвращаем из потока, Адрес и Хэш транзакции
                 * Returned from thread, Ether Address and transaction hash
                 */
                JSONObject JsonResult = new JSONObject();
                JsonResult.put("Address", address);
                JsonResult.put("TransactionHash", ethSendTransaction.getTransactionHash());
                Log.e("jsonjson", String.valueOf(JsonResult));

                return JsonResult;

            }catch (Exception ex) {ex.printStackTrace();}
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            try {
                /**
                 * Получаем баланс Ethereum
                 * Get balance Ethereum
                 */
                EthGetBalance etherbalance = web3.ethGetBalance(result.get("Address").toString(), DefaultBlockParameterName.LATEST).sendAsync().get();
                String ethbalanceafter = Convert.fromWei(String.valueOf(etherbalance.getBalance()), Convert.Unit.ETHER).toString();
                System.out.println("Eth Balance: " + ethbalanceafter);





                progressDialog1.dismiss();
            } catch(Exception ex) {System.out.println(ex);}


            Intent tokensend = new Intent();
            tokensend.putExtra("address_id_find",result.get("TransactionHash").toString());
            setResult(RESULT_OK,tokensend);
            Toast toast = Toast.makeText(getApplicationContext(),result.get("TransactionHash").toString(), Toast.LENGTH_LONG);
            toast.show();

            finish();
//            fcm();
        }

    }
    public void GetGasLimit(String value) {
        gas_Limit33.setText(value);

        Log.e("GasPrice1", gas_Limit33.getText().toString());
        GetFee();
    }
    public void GetGasPrice(String value) {
        gas_price.setText(value);
        Log.e("GasPrice2", gas_price.getText().toString());
        GetFee();
    }
    public void GetFee(){
        GasPrice = Convert.toWei(gas_price.getText().toString(),Convert.Unit.GWEI).toBigInteger();
        GasLimit = BigInteger.valueOf(Integer.valueOf(String.valueOf(gas_Limit33.getText())));

        Log.e("GasPrice3", String.valueOf(Convert.toWei(gas_price.getText().toString(),Convert.Unit.GWEI).toBigInteger()));
        Log.e("GasPrice4", String.valueOf(BigInteger.valueOf(Integer.valueOf(String.valueOf(gas_Limit33.getText())))));



        BigDecimal fee = BigDecimal.valueOf(GasPrice.doubleValue()*GasLimit.doubleValue());
        BigDecimal feeresult = Convert.fromWei(fee.toString(),Convert.Unit.ETHER);
        gas_fee.setText(feeresult.toPlainString() + " ETH");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
// MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 1:
                    send_token_address.setText(data.getStringExtra("address_id_find"));
                    break;
            }
        }
    }


//    private void fcm() {
//        login =getSharedPreferences("hoho",Context.MODE_PRIVATE);
//        Mypage_shared_id=login.getString("id","nooo");
//
//
//        StringRequest request = new StringRequest(Request.Method.POST, "http://13.58.3.24/token_fcm.php", new Response.Listener<String>() {
//
//
//            //해당 mysql에 데이터를 저장함
//            @Override
//            public void onResponse(String response) {
//                Log.e("RESULT",response);
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
//                Mypage_shared_id = login.getString("id", "nooo");
//
//                Map<String, String> params = new HashMap<>();
//                params.put("token_hash", sendmsg);
//                params.put("token_id",Mypage_shared_id);
//
//
//
//                //아이디값을 넘김
//
//
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(Chatroom_Activity.this).add(request);
//
//    }
}
