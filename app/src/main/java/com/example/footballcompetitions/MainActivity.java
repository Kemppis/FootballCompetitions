package com.example.footballcompetitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ArrayList<League> leagues;
    LeagueAdapter leagueAdapter;
    ListView listView = null;
    Area area1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leagues = new ArrayList<League>();
        leagueAdapter = new LeagueAdapter(this, leagues);
        listView = findViewById(R.id.listViewLeagues);
        listView.setAdapter(leagueAdapter);

        Intent i = getIntent();
        area1 = (Area) i.getSerializableExtra("getArea");
        Log.d("LOL", "NAME: " + area1.getName() + " ID: " + area1.getID());

        mQueue = Volley.newRequestQueue(this);
        jsonParse();
    }

    public void jsonParse() {
        String url = "http://api.football-data.org/v2/competitions?areas=" + area1.getID();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("LOL", response.toString());

                        try
                        {
                            JSONArray jsonLeagues = response.getJSONArray("competitions");
                            Log.d("LOL", jsonLeagues.toString());

                            for (int i = 0; i < jsonLeagues.length(); i++)
                            {
                                JSONObject leagueJSON = jsonLeagues.getJSONObject(i);
                                String name = leagueJSON.getString("name");
                                Log.d("LOL", "" + name);
                                League league = new League(name);
                                leagueAdapter.add(league);
                            }
                            listView.setAdapter(leagueAdapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("LOL", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LOL", error.toString());
                    }
                });

        mQueue.add(jsonObjectRequest);
    }

}
