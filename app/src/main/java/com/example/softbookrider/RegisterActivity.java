package com.example.softbookrider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView gologin;
    Button btn_signup;
    TextInputEditText uname,uemail,upass,ucnic,uphone;
    String url="https://softbooktown.xyz/api/rider_register.php";
    StringRequest request;
    RequestQueue res;
    ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gologin=findViewById(R.id.gologin);
        btn_signup=findViewById(R.id.btn_signup);
        uname=findViewById(R.id.uname);
        uemail=findViewById(R.id.uemail);
        upass=findViewById(R.id.upass);
        ucnic=findViewById(R.id.ucnic);
        uphone=findViewById(R.id.uphone);

        loadingbar=new ProgressDialog(this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingbar.setTitle("Register Process");
                loadingbar.setMessage("Please Wait..While Adding The Record");
                loadingbar.show();


                request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("Register Successfully")) {

                            Toast.makeText(RegisterActivity.this, "Register Successfully Please Wait For Admin Approval", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();



                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                            startActivity(intent);

                        }

                        if (response.contains("Register Un Successfully")) {
                            Toast.makeText(RegisterActivity.this, "Register Un Successfully Please TryAgain", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<>();
                        map.put("uname", uname.getText().toString());
                        map.put("uemail", uemail.getText().toString());
                        map.put("upass", upass.getText().toString());
                        map.put("uphone", uphone.getText().toString());
                        map.put("ucnic", ucnic.getText().toString());


                        return map;
                    }
                };


                res = Volley.newRequestQueue(RegisterActivity.this);
                res.add(request);

            }
        });

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}