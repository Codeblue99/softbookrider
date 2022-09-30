package com.example.softbookrider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
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

public class OrderDetailActivity extends AppCompatActivity {
    ListView orderdetaillist;
    OrderDetail orderDetail;
    OrderDetail_Adapter orderDetail_adapter;
    public  static ArrayList<OrderDetail> vouchersArrayList=new ArrayList<>();
    String url="https://softbooktown.xyz/api/fetch_order_detail.php";
    StringRequest request;
    RequestQueue res;
    TextView orderid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        orderid=findViewById(R.id.orderid);
        final Bundle extras = getIntent().getExtras();
        orderid.setText("ORDER ID - ORD"+ extras.getString("orderid"));

        orderdetaillist=findViewById(R.id.orderdetaillist);
        orderDetail_adapter=new OrderDetail_Adapter(this,vouchersArrayList);
        orderdetaillist.setAdapter(orderDetail_adapter);

        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                vouchersArrayList.clear();

                try {
                    JSONArray array=new JSONArray(response);

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject obj=array.getJSONObject(i);
                        String del_address1=obj.getString("del_address1");
                        String order_qty=obj.getString("order_qty");
                        String product_name=obj.getString("product_name");
                        String bookstore_name=obj.getString("bookstore_name");
                        String bookstore_address=obj.getString("bookstore_address");
                        String bookstore_latitude=obj.getString("bookstore_latitude");
                        String bookstore_longitude=obj.getString("bookstore_longitude");
                        String user_latitude=obj.getString("user_latitude");
                        String user_longitude=obj.getString("user_longitude");

                        orderDetail=new OrderDetail(del_address1,order_qty,product_name,bookstore_name,bookstore_address,bookstore_latitude,bookstore_longitude,user_latitude,user_longitude);
                        vouchersArrayList.add(orderDetail);
                        orderDetail_adapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(OrderDetailActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                }





            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderDetailActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> map=new HashMap<>();
                map.put("oid",extras.getString("orderid"));



                return map;
            }
        };


        res= Volley.newRequestQueue(OrderDetailActivity.this);
        res.add(request);

    }
}