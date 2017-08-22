package tabs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

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
import vudinhthanh.wallpaper.wallpaper_store.R;
import vudinhthanh.wallpaper.wallpaper_store.ScrollingActivity;

/**
 * Created by Thanh-PC on 8/8/2017.
 */

public class Tab1 extends Fragment {
    GridView gridView;
    ProgressBar progressBar;
    ArrayList<Item> lt;
    AdapterFeatured ad;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_activity, container, false);
        setHasOptionsMenu(true);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        gridView = (GridView) view.findViewById(R.id.gv1);
        myTask mt = new myTask();
        mt.execute();
        addEvent();


        return view;


    }

    private void addEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getActivity(), ScrollingActivity.class);
                it.putExtra("BIG",lt.get(position).getBigimgeurl());
                it.putExtra("TITLE",lt.get(position).getTitle());
                it.putExtra("DOW",lt.get(position).getDownload());
                it.putExtra("KEY",position+"");
                startActivity(it);


            }
        });
    }

    public  String getJson(String url) {

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
            ad = new AdapterFeatured(getActivity(), R.layout.item_view,lt);
            gridView.setAdapter(ad);
            progressBar.setVisibility(View.GONE);
            ad.notifyDataSetChanged();
        }
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home2, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.filter(newText);
                return false;
            }
        });

    }
}

