package delta.got.Activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import delta.got.Database.Database;
import delta.got.R;

public class HistoryDetails extends AppCompatActivity {
    Database DB;
    TextView Name,Culture,House,Books,Titles;
    ImageView Image;
    String name,culture,house,books,titles;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        DB = new Database(this);
        Name = findViewById(R.id.name);
        Culture = findViewById(R.id.culture);
        House = findViewById(R.id.house);
        Books = findViewById(R.id.books);
        Titles = findViewById(R.id.titles);
        Image = findViewById(R.id.characterimage);

        int i = getIntent().getExtras().getInt("selecteditem");
        Cursor cursor = DB.getDetailbyID(i);
        if(cursor.moveToFirst()) {
             name = cursor.getString(1);
            culture = cursor.getString(2);
             house = cursor.getString(3);
            books = cursor.getString(4);
             titles = cursor.getString(5);
            image = cursor.getBlob(6);
        }
        if (image != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            Image.setImageBitmap(bm);
        }

        Name.setText(name);
        Culture.setText(culture);
        House.setText(house);
        Books.setText(books);
        Titles.setText(titles);

    }
}
