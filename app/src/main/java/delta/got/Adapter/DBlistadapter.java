package delta.got.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import delta.got.R;

public class DBlistadapter extends CursorAdapter {
    public DBlistadapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_row_items,viewGroup,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.name);
        ImageView image = view.findViewById(R.id.image);

        byte[] img = cursor.getBlob(6);
        if(img!=null) {
            Bitmap bm = BitmapFactory.decodeByteArray(img, 0, img.length);
            image.setImageBitmap(bm);
        }
        name.setText(cursor.getString(1));

    }
}
