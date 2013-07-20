package dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "resourceHandler";
	public static final String COLUMN_ID = "_id";
	public static final String RESOURCE_NAME = "name";
	public static final String RESOURCE_SELECTED = "isSelected"; // resource is selected 0 , Not selected 0

	private static final String DATABASE_NAME = "resourceHandler.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " 
							   + RESOURCE_NAME + " text not null,"
							   + RESOURCE_SELECTED + ");";

	public MySqlHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySqlHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		    onCreate(db);

	}

}
