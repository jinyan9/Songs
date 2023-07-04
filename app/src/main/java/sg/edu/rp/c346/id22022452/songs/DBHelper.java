package sg.edu.rp.c346.id22022452.songs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "songs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SINGERS + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_STARS + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGERS, song.getSingers());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_STARS, song.getStars());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(0);
                String singers = cursor.getString(1);
                int year = cursor.getInt(2);
                int stars = cursor.getInt(3);
                Song obj = new Song(title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}