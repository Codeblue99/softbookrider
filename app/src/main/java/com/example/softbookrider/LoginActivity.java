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

public class LoginActivity extends AppCompatActivity {

    TextView goregister;
    Button btn_signin;
    TextInputEditText uemail,upass;
    String url="https://softbooktown.xyz/api/rider_login.php";
    StringRequest request;
    RequestQueue res;
    ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        goregister=findViewById(R.id.goregister);
        btn_signin=findViewById(R.id.btn_signin);
        uemail=findViewById(R.id.uemail);
        upass=findViewById(R.id.upass);
        loadingbar=new ProgressDialog(this);
        final SharedPreferences shared=getSharedPreferences("credientials",MODE_PRIVATE);
        final SharedPreferences.Editor edit=shared.edit();

        if(!shared.getString("rider_name","").equals(""))
        {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);

            startActivity(intent);
            finish();

        }

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingbar.setTitle("Login Now");
                loadingbar.setMessage("Please Wait..While Checking Your Crediential");
                loadingbar.show();
                request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        if(response.contains("Email & Password Does Not Match"))
                        {

                            Toast.makeText(LoginActivity.this, "Email & Password Does Not Match", Toast.LENGTH_LONG).show();
                            loadingbar.dismiss();

                        }
                        else if(response.contains("Please Wait For Approval"))
                        {

                            Toast.makeText(LoginActivity.this, "Account Unverify , Please Wait For Approval", Toast.LENGTH_LONG).show();
                            loadingbar.dismiss();

                        }
                        else
                        {
                            try {
                                JSONArray array=new JSONArray(response);

                                for (int i=0;i<array.length();i++)
                                {
                                    JSONObject obj=array.getJSONObject(i);

                                    edit.putString("rider_id",obj.getString("rider_id"));
                                    edit.putString("rider_name",obj.getString("rider_name"));
                                    edit.putString("rider_cnic",obj.getString("rider_cnic"));
                                    edit.putString("rider_email",obj.getString("rider_email"));
                                    edit.putString("rider_pass",obj.getString("rider_pass"));
                                    edit.putString("rider_phone",obj.getString("rider_phone"));


                                    edit.commit();
                                }

                                loadingbar.dismiss();
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                            }





                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                })
                {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String ,String> map=new HashMap<>();
                        map.put("uemail",uemail.getText().toString());
                        map.put("upass",upass.getText().toString());



                        return map;
                    }
                };


                res= Volley.newRequestQueue(LoginActivity.this);
                res.add(request);

            }
        });

        goregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}