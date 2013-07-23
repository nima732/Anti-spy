package dataBase;

import java.util.ArrayList;
import java.util.List;

import com.example.anti_spy.entity.Resource;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ResourceDAO {
	 // Database fields
	  private SQLiteDatabase database;
	  private MySqlHelper dbHelper;
	  private String[] allColumns = { MySqlHelper.COLUMN_ID, MySqlHelper.RESOURCE_NAME , MySqlHelper.RESOURCE_SELECTED};

	  public ResourceDAO(Context context) {
	    dbHelper = new MySqlHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Resource insertResource(String ResourceName) {
	    ContentValues values = new ContentValues();
	    values.put(MySqlHelper.RESOURCE_NAME, ResourceName);
	    values.put(MySqlHelper.RESOURCE_SELECTED, "1");
	    long insertId = database.insert(MySqlHelper.TABLE_NAME, null, values);
	    
	    Cursor cursor = database.query(MySqlHelper.TABLE_NAME, allColumns, MySqlHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Resource newResource = cursorToResource(cursor);
	    cursor.close();
	    return newResource;
	  }

	  public void deleteResource(Resource resource) {
	    long id = resource.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySqlHelper.TABLE_NAME, MySqlHelper.COLUMN_ID + " = " + id, null);
	  }

	  public Cursor myGetAllComments(){
		  return database.query(MySqlHelper.TABLE_NAME, allColumns, null, null, null, null, null);
	  }
	  
	  public List<Resource> getAllComments() {
	    List<Resource> resources = new ArrayList<Resource>();

	    Cursor cursor = database.query(MySqlHelper.TABLE_NAME, allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Resource resource = cursorToResource(cursor);
	      resources.add(resource);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return resources;
	  }

	  private Resource cursorToResource(Cursor cursor) {
		Resource resource = new Resource();
		resource.setId(cursor.getLong(0));
		resource.setResource_name(cursor.getString(1));
	    resource.setResource_selected(cursor.getString(2));
	    return resource;
	  }
}
