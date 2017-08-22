package vudinhthanh.wallpaper.wallpaper_store;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import adapter.AdapterFeatured;
import model.Item;

public class ScrollingActivity extends AppCompatActivity {
    ImageView imageView,imglove,imgdislove;
    String image, text, title;
    TextView txttitle, txtdow;
    GridView gridView;
    AdapterFeatured ad;
    ArrayList<Item> lt;
    ProgressBar progressBar;
    LinearLayout ly1;
    ImageView set, save, sen;
    DatabaseReference mdb;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//         hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        addControl();
        addEvent();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ly1.getVisibility() == View.VISIBLE) {
                    ly1.setVisibility(View.GONE);

                } else {
                    ly1.setVisibility(View.VISIBLE);
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


    }

    private void addEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String img = lt.get(position).getBigimgeurl();
                String dow = lt.get(position).getDownload();
                String tieude = lt.get(position).getTitle();
                Picasso.with(ScrollingActivity.this).load(img).into(imageView);
                txtdow.setText("↓ " + dow + " " + " ");
                txttitle.setText(tieude);

            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File storagePath1 =new File(Environment.getExternalStorageDirectory(),"Wallpaper");
                storagePath1.mkdirs();

                URL url = null;
                try {
                    url = new URL(image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                InputStream input = null;
                try {
                    input = url.openStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    File storagePath =Environment.getExternalStorageDirectory();

                    OutputStream output = new FileOutputStream(storagePath +"/Wallpaper"+"/"+title+".png");

                    try {
                        byte[] buffer = new byte[2024];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Snackbar.make(v, "save image success to" + "Sdcard/DCIM" + "/" + title + ".png", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                WallpaperManager myWallpaperManager =
                        WallpaperManager.getInstance(ScrollingActivity.this);
                InputStream ins = null;
                try {
                    ins = new URL(image).openStream();
                    myWallpaperManager.setStream(ins);

                    Snackbar.make(v, "Set Wallpaper success", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Toast.makeText(ScrollingActivity.this,height+"",Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        imglove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             mdb.child("user1").child(key).setValue(true) ;
            }
        });

        imgdislove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdb.child("user1").child(key).setValue(false) ;
            }
        });


    }

    private void addControl() {
        imgdislove= (ImageView) findViewById(R.id.imgdislove);
        imgdislove.setVisibility(View.GONE);
        imglove= (ImageView) findViewById(R.id.imglove);
        set = (ImageView) findViewById(R.id.set);
        sen = (ImageView) findViewById(R.id.send);
        save = (ImageView) findViewById(R.id.dow);
        ly1 = (LinearLayout) findViewById(R.id.lyselect);
        ly1.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.pro2);
        gridView = (GridView) findViewById(R.id.GVscroll);
        imageView = (ImageView) findViewById(R.id.imgscrolling);
        txttitle = (TextView) findViewById(R.id.txttitle);
        txtdow = (TextView) findViewById(R.id.txtdow);
        Intent it = getIntent();
        image = it.getStringExtra("BIG");
        text = it.getStringExtra("DOW");
        title = it.getStringExtra("TITLE");
        key=it.getStringExtra("KEY");

        Picasso.with(ScrollingActivity.this).load(image).into(imageView);
        setTitle(title);
        txttitle.setText(title);
        txtdow.setText("↓ " + text + " " + " ");
        myTask mt = new myTask();
        mt.execute();
        mdb= FirebaseDatabase.getInstance().getReference();




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
            ad = new AdapterFeatured(ScrollingActivity.this, R.layout.item_view, lt);
            gridView.setAdapter(ad);
            progressBar.setVisibility(View.GONE);
            ad.filter(title);
            ad.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
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
