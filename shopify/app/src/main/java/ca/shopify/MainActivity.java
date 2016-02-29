package ca.shopify;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.shopify.dto.Product;
import ca.shopify.dto.Products;
import ca.shopify.dto.Variant;

public class MainActivity extends AppCompatActivity {

    private String url="http://shopicruit.myshopify.com/products.json";
    public static int TIME_OUT=100000;
    private TextView textMain;
    Products mProduct;
    private Button btn_retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textMain=(TextView)findViewById(R.id.textMain);
        btn_retry=(Button)findViewById(R.id.btn_retry);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email to programmer ?", Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "harsh13592@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Re: Regarding Shopicruit Task");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                }).show();

            }
        });
        fetchProductData();
    }
    private void fetchProductData(){
        btn_retry.setVisibility(View.GONE);
        try {

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TAG", response.toString());
                            if(response!=null && !TextUtils.isEmpty(response.toString())){
                                mProduct=new Gson().fromJson(response.toString(),Products.class);
                                Collections.sort(mProduct.getProducts());
                                Log.e("TAG", mProduct.getProducts().size()+"");
                                setPriceForAll(mProduct.getProducts());
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                    textMain.setText(error.getMessage());

                }
            });
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            if(isNetworkAvailable(MainActivity.this)){
                AppController.getInstance().addToRequestQueue(jsonObjReq);
            }
            else{
                showAlert(this, getResources().getString(R.string.internet_dialog_title), getResources().getString(R.string.internet_notavailable), "OK");
                btn_retry.setVisibility(View.VISIBLE);
                btn_retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchProductData();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setPriceForAll(List<Product> mListProduct){
        StringBuilder allProductPrice=new StringBuilder();
        List<Float> price_Wallet=new ArrayList<Float>();
        List<Float> price_Lamps=new ArrayList<Float>();
        for(Product i : mListProduct){
            if(i.getVariants() != null && i.getProductType().equals("Wallet")){
                float totalPriceWallets=getPriceTotal(i.getVariants());
                allProductPrice.append(i.getProductType()+ ": "+totalPriceWallets);
                allProductPrice.append(" \n");
                price_Wallet.add(totalPriceWallets);
            }
            else if(i.getVariants() != null && i.getProductType().equals("Lamp")){
                float totalPriceLamps=getPriceTotal(i.getVariants());
                allProductPrice.append(i.getProductType()+ ": "+totalPriceLamps);
                allProductPrice.append(" \n");
                price_Lamps.add(totalPriceLamps);
            }

        }
        float initialTotalWallets=0,initialTotalLamps=0;
        for(Float i : price_Wallet) {
            initialTotalWallets=initialTotalWallets+i.floatValue();
        }
        allProductPrice.append("Total Wallets Price: "+initialTotalWallets+" \n");
        for(Float i : price_Lamps) {
            initialTotalLamps=initialTotalLamps+i.floatValue();
        }
        allProductPrice.append("Total Lamps Price: "+initialTotalLamps+" \n\n");
        allProductPrice.append("All wallets and Lamps would cost: "+(initialTotalLamps+initialTotalWallets)+" \n");
        textMain.setText(allProductPrice);
    }
    private float getPriceTotal(List<Variant> mVariants){
        float total = 0;
        for(Variant v: mVariants){
                total=total+Float.parseFloat(v.getPrice());
        }
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public BigDecimal sum(float[] floats) {
        BigDecimal sum = BigDecimal.ZERO;

        for (float aFloat : floats) {
            sum = sum.add(new BigDecimal(aFloat));
        }

        return sum;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
    public static void showAlert(Context context, String title, String message, String btnText) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
