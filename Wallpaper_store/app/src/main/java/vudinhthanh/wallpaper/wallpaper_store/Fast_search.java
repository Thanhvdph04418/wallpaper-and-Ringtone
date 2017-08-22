package vudinhthanh.wallpaper.wallpaper_store;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import adapter.AdapterFeatured;
import model.Item;

public class Fast_search extends AppCompatActivity {
    ArrayList<Item>lt;
    AdapterFeatured ad;
    GridView gridView;
    ProgressBar progressBar;
    String a;
    TextView txtketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         hiển thị nút Up ở Home icon
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_fast_search);

    addControl();
        myTask mt=new myTask();
        mt.execute();
        Intent it=getIntent();
       a= it.getStringExtra("Fast");

addEvent();
    }

    private void addEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(Fast_search.this, ScrollingActivity.class);
                it.putExtra("BIG",lt.get(position).getBigimgeurl());
                it.putExtra("TITLE",lt.get(position).getTitle());
                it.putExtra("DOW",lt.get(position).getDownload());
                startActivity(it);

            }
        });
    }

    private void addControl() {
        txtketqua= (TextView) findViewById(R.id.txtketqua);
        gridView= (GridView) findViewById(R.id.gvfast);
        progressBar= (ProgressBar) findViewById(R.id.progressBarfast);
    }

    public String getJson(String url) {

        try {
            URL mUrl = new URL(url);
            InputStreamReader inputStreamReader = new InputStreamReader(mUrl.openStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    class myTask extends AsyncTask<Void, Void, String> {
        String url = "https://hinhnen.000webhostapp.com/";

        @Override
        protected void onPreExecute() {
            lt = new ArrayList<>();
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                JSONArray array = new JSONArray(getJson(url));

                for (int i = 0; i < array.length(); i++) {
                    String anh = array.getJSONObject(i).getString("anh");
                    String title = array.getJSONObject(i).getString("title");
                    String dowload = array.getJSONObject(i).getString("dowload");
                    String biganh = array.getJSONObject(i).getString("biganh");


                    lt.add(new Item(anh, title, dowload, biganh));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ad = new AdapterFeatured(Fast_search.this, R.layout.item_view, lt);
            gridView.setAdapter(ad);
            progressBar.setVisibility(View.GONE);
            ad.filter(a);
            txtketqua.setText(a+": Wallpaper("+lt.size()+")");
            ad.notifyDataSetChanged();
        }
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
