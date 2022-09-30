package com.example.softbookrider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {

    TextView gologin;
    Button btn_signup;
    TextInputEditText uname,uemail,upass,ucnic,uphone;
    String url="https://softbooktown.xyz/api/fetch_rider_detail.php";
    String url2="https://softbooktown.xyz/api/update_profile.php";
    StringRequest request;
    StringRequest request2;
    RequestQueue res;
    RequestQueue res2;
    ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btn_signup=findViewById(R.id.btn_update);
        uname=findViewById(R.id.uname);
        upass=findViewById(R.id.upass);
        ucnic=findViewById(R.id.ucnic);
        uphone=findViewById(R.id.uphone);
        final SharedPreferences shared=getSharedPreferences("credientials",MODE_PRIVATE);
        final SharedPreferences.Editor edit=shared.edit();

        loadingbar=new ProgressDialog(this);
        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        JSONArray array=new JSONArray(response);

                        for (int i=0;i<array.length();i++)
                        {
                            JSONObject obj=array.getJSONObject(i);


                            uname.setText(obj.getString("rider_name"));
                            upass.setText(obj.getString("rider_pass"));
                            ucnic.setText(obj.getString("rider_cnic"));
                            uphone.setText(obj.getString("rider_phone"));

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(UpdateProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateProfileActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        })
        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> map=new HashMap<>();
                map.put("uid",shared.getString("rider_id",""));



                return map;
            }
        };


        res= Volley.newRequestQueue(UpdateProfileActivity.this);
        res.add(request);



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingbar.setTitle("Update Process");
                loadingbar.setMessage("Please Wait..While Update The Record");
                loadingbar.show();


                request2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("Update Successfully")) {

                            Toast.makeText(UpdateProfileActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();



                            Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);

                            startActivity(intent);

                        }

                        if (response.contains("Update Un Successfully")) {
                            Toast.makeText(UpdateProfileActivity.this, "Update Un Successfully Please TryAgain", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateProfileActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<>();
                        map.put("uname", uname.getText().toString());
                        map.put("uid", shared.getString("rider_id",""));
                        map.put("upass", upass.getText().toString());
                        map.put("uphone", uphone.getText().toString());
                        map.put("ucnic", ucnic.getText().toString());

                        return map;
                    }
                };


                res2 = Volley.newRequestQueue(UpdateProfileActivity.this);
                res2.add(request2);

            }
        });
    }
}