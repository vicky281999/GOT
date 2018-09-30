package delta.got.Activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayOutputStream;

import delta.got.Adapter.DBlistadapter;
import delta.got.Database.Database;
import delta.got.Fragments.TabHistory;
import delta.got.R;

public class SearchResult extends AppCompatActivity {
    ImageView img;
    TextView Name, House, Culture, Books, Titles;
    byte[] DBImage;
    Database DB;
    static String name, imgurl, house, culture, books, titles;
    ProgressBar progressBar;
    DBlistadapter adapter;
    public static Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        DB = new Database(SearchResult.this);
        img = findViewById(R.id.characterimage);
        Name = findViewById(R.id.name);
        House = findViewById(R.id.house);
        Culture = findViewById(R.id.culture);
        Books = findViewById(R.id.books);
        Titles = findViewById(R.id.titles);
        progressBar = findViewById(R.id.progressbar);

        name = getIntent().getExtras().getString("name");
        imgurl = getIntent().getExtras().getString("imgurl");
        house = getIntent().getExtras().getString("house");
        culture = getIntent().getExtras().getString("culture");
        books = getIntent().getExtras().getString("books");
        titles = getIntent().getExtras().getString("titles");

        String timgurl = "https://api.got.show" + imgurl;

        Name.setText(name);
        House.setText(house);
        Culture.setText(culture);
        Books.setText(books);
        Titles.setText(titles);
        Picasso.with(SearchResult.this).load(timgurl).into(img, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                DBImage = stream.toByteArray();
                Cursor cursor = DB.getDetails(name);
                if (cursor.moveToFirst()) {

                } else {
                    DB.insertData(name, culture, house, books, titles, DBImage);
                    Cursor cur = DB.getData();
           adapter = new DBlistadapter(SearchResult.this,cur);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                Cursor cursor = DB.getDetails(name);
                if (cursor.moveToFirst()) {

                } else {
                    DB.insertData(name, culture, house, books, titles, DBImage);
                   cur = DB.getData();
                    /*adapter = new DBlistadapter(SearchResult.this, cur);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });*/
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        }

}
