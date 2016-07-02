package practice.com.guitaristnotepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "pedals.db";
    private static final String TABLE_PEDALS = "pedals";
    private static final String COLUMN_IMAGE_ID = "imageId";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PEDALS + "(" + COLUMN_NAME + " TEXT, " + COLUMN_CATEGORY
                + " TEXT, " + COLUMN_IMAGE_ID + " INTEGER PRIMARY KEY, " + COLUMN_DESCRIPTION + " TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDALS);
        onCreate(db);
    }

    public void addPedal(Pedal pedal) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pedal.getName());
        values.put(COLUMN_CATEGORY, pedal.getCategory());
        values.put(COLUMN_DESCRIPTION, pedal.getDescription());
        values.put(COLUMN_IMAGE_ID, pedal.getImageId());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PEDALS, null, values);
        db.close();
    }

    public void deletePedal(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PEDALS + " WHERE " + COLUMN_NAME + "=\"" + name + "\";" );
        db.close();
    }

    public String dbToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PEDALS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return dbString;
    }

    public ArrayList<Pedal> getAllPedals(String categoryChoice) {
        ArrayList<Pedal> pedals = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PEDALS + " WHERE " + COLUMN_CATEGORY + "=\""
                + categoryChoice +"\";" ;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            String name = c.getString(c.getColumnIndex(COLUMN_NAME));
            String category = c.getString(c.getColumnIndex(COLUMN_CATEGORY));
            String description = c.getString(c.getColumnIndex(COLUMN_DESCRIPTION));
            int id = c.getInt(c.getColumnIndex(COLUMN_IMAGE_ID));

            Pedal pedal = new Pedal(name, id, description, category);
            pedals.add(pedal);
            c.moveToNext();
        }
        c.close();
        db.close();
        return pedals;
    }

}