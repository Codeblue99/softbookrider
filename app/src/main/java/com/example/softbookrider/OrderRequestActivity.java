package com.example.softbookrider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class OrderRequestActivity extends AppCompatActivity {

    ListView orderrequestlist;
    Order order;
    OrderAdapter orderAdapter;
    public  static ArrayList<Order> vouchersArrayList=new ArrayList<>();
    String url="https://softbooktown.xyz/api/fetch_order_request.php";
    StringRequest request;
    RequestQueue res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_request);
        orderrequestlist=findViewById(R.id.orderrequestlist);
        orderAdapter=new OrderAdapter(this,vouchersArrayList);
        orderrequestlist.setAdapter(orderAdapter);

        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                vouchersArrayList.clear();

                    try {
                        JSONArray array=new JSONArray(response);

                        for (int i=0;i<array.length();i++)
                        {
                            JSONObject obj=array.getJSONObject(i);
                            String order_id=obj.getString("order_id");
                            String user_id=obj.getString("user_id");
                            String order_amount=obj.getString("order_amount");
                            String delivery_charge=obj.getString("delivery_charge");
                            String total_amount=obj.getString("total_amount");
                            String order_note=obj.getString("order_note");
                            String bill_date=obj.getString("bill_date");
                            String user_name=obj.getString("user_name");
                            String user_phone=obj.getString("user_phone");

                            order=new Order(order_id,user_id,order_amount,delivery_charge,total_amount,order_note,bill_date,user_name,user_phone);
                            vouchersArrayList.add(order);
                            orderAdapter.notifyDataSetChanged();

                        }

                       } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(OrderRequestActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }





                }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderRequestActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        res= Volley.newRequestQueue(OrderRequestActivity.this);
        res.add(request);


    }
}