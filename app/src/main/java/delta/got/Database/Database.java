package delta.got.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DataBase="GOT";
    public static final String Table="characters";
    public static  final String Column1="_id";
    public static final String Column2="Name";
    public static final String Column3="Culture";
    public static final String Column4="House";
    public static final String Column5="Books";
    public static final String Column6="Titles";
    public static final String Column7="Image";

    public Database(Context context) {
        super(context, DataBase, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Table + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Culture TEXT,House TEXT,Books TEXT,Titles TEXT,Image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(sqLiteDatabase);
    }

    public long insertData( String name, String culture, String house, String books, String titles,byte[] image){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column2,name);
        contentValues.put(Column3,culture);
        contentValues.put(Column4,house);
        contentValues.put(Column5,books);
        contentValues.put(Column6,titles);
        contentValues.put(Column7,image);
        return sqLiteDatabase.insert(Table,null,contentValues);

    }
    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor data=sqLiteDatabase.rawQuery("Select * from " + Table,null);
        return data;
    }

    public Cursor getDetails(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cur = sqLiteDatabase.rawQuery("Select * from " + Table + " where Name = '" + name +"';",null);
        return cur;
    }

    public Cursor getDetailbyID(int i){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cur = sqLiteDatabase.rawQuery("Select * from " + Table + " where _id = " + (i+1) +";",null);
        return cur;
    }

}
