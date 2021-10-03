package com.example.ammysmovieapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView popMovieList;
    RecyclerView recyclerView;
    List<MovieResults> generalMovieList;
    private static String BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=6be7ebcb9437d3c299b9d23e77ce6011";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        generalMovieList = new ArrayList<>();

        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String, String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String curr = "";
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{
                    url = new URL(BASE_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while ((data != -1)){
                        curr += (char) data;
                        data =isr.read();
                    }
                    return curr;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return curr;
        }
        @Override
        protected void onPostExecute(String s){
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i=0 ; i<jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieResults model = new MovieResults();
                    model.setImage(jsonObject1.getString("poster_path"));
                    model.setTitle(jsonObject1.getString("title"));
                    model.setVoteAverage(jsonObject1.getString("vote_average"));
                    model.setOverview(jsonObject1.getString("overview"));
                    generalMovieList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            putDataInView(generalMovieList);
        }
    }

    private void putDataInView(List<MovieResults> movies){
        Adaptery adaptery = new Adaptery(this, movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}

