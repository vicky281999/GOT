package delta.got.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import delta.got.R;

public class ResultsFromDatabase extends AppCompatActivity {
    ImageView img;
    TextView Name,House,Culture,Books,Titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_from_database);

        img = findViewById(R.id.characterimage);
        Name = findViewById(R.id.name);
        House = findViewById(R.id.house);
        Culture = findViewById(R.id.culture);
        Books = findViewById(R.id.books);
        Titles = findViewById(R.id.titles);

        String name = getIntent().getExtras().getString("name");
        byte[] image = getIntent().getExtras().getByteArray("img");
        String house = getIntent().getExtras().getString("house");
        String culture = getIntent().getExtras().getString("culture");
        String books = getIntent().getExtras().getString("books");
        String titles = getIntent().getExtras().getString("titles");

        if (image != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            img.setImageBitmap(bm);
        }
        Name.setText(name);
        House.setText(house);
        Culture.setText(culture);
        Books.setText(books);
        Titles.setText(titles);

    }
    }

