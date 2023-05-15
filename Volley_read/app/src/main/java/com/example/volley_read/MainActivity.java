package com.example.volley_read;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {

    public static  final String GET_By_ID="http://localhost/phpmyadmin/index.php?route=/table/sql&db=loginregister&table=client";
    public static  final String GET_All="http://localhost/phpmyadmin/index.php?route=/table/sql&db=loginregister&table=client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData("1");


   }
   public void getData(String id){
       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
               GET_By_ID,
               null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try {
                           JSONObject ob=response.getJSONObject("data");
                           String name=ob.getString("name");
                           Toast.makeText(getBaseContext(),name,Toast.LENGTH_LONG).show();
                       } catch (JSONException e) {
                           throw new RuntimeException(e);

                       }
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();
           }
       });
       RequestQueue requestQueue= Volley.newRequestQueue(this);
       requestQueue.add(jsonObjectRequest);
   }
    public void getAll() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_All,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ob = response.getJSONArray("data");
                            int size = ob.length();
                            Toast.makeText(getBaseContext(),size, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

}