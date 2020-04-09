package com.example.footballcompetitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class AreaActivity extends AppCompatActivity {

    private ArrayList<Area> areas = new ArrayList<Area>();
    private AreaAdapter areaAdapter;
    private ListView listView = null;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        listView = (ListView) findViewById(R.id.listViewAreas);
        areaAdapter = new AreaAdapter(this, areas);
        listView.setAdapter(areaAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Area area = (Area) parent.getAdapter().getItem(position);
                Log.d("LOL", area.getName());

                Intent i = new Intent(AreaActivity.this, MainActivity.class);
                i.putExtra("getArea", area);
                startActivity(i);
            }
        });

        mQueue = Volley.newRequestQueue(this);
        jsonParse();

    }

    public void jsonParse() {
        String url = "http://api.football-data.org/v2/areas";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("LOL", response.toString());
                        try
                        {
                            JSONArray jsonAreas = response.getJSONArray("areas");
                            for (int i = 0; i < jsonAreas.length(); i++)
                            {
                                JSONObject areaJSON = jsonAreas.getJSONObject(i);
                                int ID = areaJSON.getInt("id");
                                String name = areaJSON.getString("name");
                                Log.d("LOL", "" + name);
                                Area area = new Area(ID, name);
                                areaAdapter.add(area);

                            }
                            listView.setAdapter(areaAdapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("LOL", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("LOL", error.toString());
                    }
                });

        mQueue.add(jsonObjectRequest);
    }


}
