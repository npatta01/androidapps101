package com.courserastudent.TedTalks;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;


public class TalkListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ArrayList<Map<String, ?>> talks = new ArrayList<Map<String, ?>>();

        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < obj.length(); i++) {
                JSONObject menuObject = obj.getJSONObject(i);


                Map m = new HashMap();
                m.put("name", menuObject.getString("Name"));
                m.put("description", menuObject.getString("Short Summary"));
                m.put("duration", menuObject.getString("Duration"));
                m.put("speaker", menuObject.getString("Speaker"));


                talks.add(m);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // setup the data adaptor
        String[] from = {"name", "description", "duration", "speaker"};


        int[] to = {R.id.name, R.id.description, R.id.duration, R.id.speaker};

        SimpleAdapter adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) talks, R.layout.talk_row, from, to);

        // specify the list adaptor
        setListAdapter(adapter);

        //setListAdapter(new ArrayAdapter<Talk>(this,android.R.layout.simple_list_item_1, talks));


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("talks.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


}

