package com.example.softbookrider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAdapter extends ArrayAdapter<Order> {
    Context context;
    List<Order> arrayorder;
    String url="https://softbooktown.xyz/api/approve_order.php";
    StringRequest request;
    RequestQueue res;


    public OrderAdapter(@NonNull Context context, List<Order> arrayorder ) {
        super(context,R.layout.custom_orders,arrayorder);
        this.context=context;
        this.arrayorder=arrayorder;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_orders,null,true);

        TextView oid=(TextView) view.findViewById(R.id.oid);
        TextView uname=(TextView) view.findViewById(R.id.uname);
        TextView uphone=(TextView) view.findViewById(R.id.uphone);
        TextView oamount=(TextView) view.findViewById(R.id.oamount);
        TextView dcharge=(TextView) view.findViewById(R.id.dcharge);
        TextView ototal=(TextView) view.findViewById(R.id.ototal);
        TextView odate=(TextView) view.findViewById(R.id.odate);
        TextView odetail=(TextView) view.findViewById(R.id.odetail);
        TextView oaccept=(TextView) view.findViewById(R.id.oaccept);
        TextView oreject=(TextView) view.findViewById(R.id.oreject);



        final SharedPreferences shared2=parent.getContext().getSharedPreferences("credientials",parent.getContext().MODE_PRIVATE);
        final SharedPreferences.Editor edit2=shared2.edit();

        oreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayorder.remove(position);
                notifyDataSetChanged();
            }
        });

        odetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),OrderDetailActivity.class);
                intent.putExtra("orderid",arrayorder.get(position).getOrder_id());
                getContext().startActivity(intent);
            }
        });

        oaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("Update Successfully")) {

                            Toast.makeText(getContext(), "Assign Successfully", Toast.LENGTH_SHORT).show();
                            arrayorder.remove(position);
                            notifyDataSetChanged();

                        }

                        if (response.contains("Update Un Successfully")) {
                            Toast.makeText(getContext(), "Asign Un Successfully Please TryAgain", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<>();
                        map.put("rid", shared2.getString("rider_id",""));
                        map.put("oid", arrayorder.get(position).getOrder_id());


                        return map;
                    }
                };


                res = Volley.newRequestQueue(getContext());
                res.add(request);
            }
        });


        oid.setText("Order ID : "+arrayorder.get(position).getOrder_id() );
        uname.setText("User Name : "+arrayorder.get(position).getUser_name() );
        uphone.setText("User Phone : "+ arrayorder.get(position).getUser_phone() );
        oamount.setText("Order Amount Rs : "+arrayorder.get(position).getOrder_amount() );
        dcharge.setText("Delivery Charge Rs : "+arrayorder.get(position).getDelivery_charge() );
        ototal.setText("Total Amount Rs : "+arrayorder.get(position).getTotal_amount() );
        odate.setText("Order Date : "+arrayorder.get(position).getBill_date() );





        return view;
    }
}
