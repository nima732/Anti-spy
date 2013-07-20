package com.example.anti_spy;

import java.util.List;
import java.util.Random;

import dataBase.Resource;
import dataBase.ResourceDAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class ListResources extends ListActivity{
	 private ResourceDAO datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_resources);
		
		
		 datasource = new ResourceDAO(this);
		    datasource.open();

		    List<Resource> values = datasource.getAllComments();

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ArrayAdapter<Resource> adapter = new ArrayAdapter<Resource>(this,android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
	}
	

	  // Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
	    Resource resource = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[] comments = new String[] { "Camera", "Microphone", "Contacts" };
	      int nextInt = new Random().nextInt(3);
	      // Save the new comment to the database
	      resource = datasource.insertResource(comments[nextInt]);
	      adapter.add(resource.getResource_name());
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        resource = (Resource) getListAdapter().getItem(0);
	        datasource.deleteResource(resource);
	        adapter.remove(resource);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_resources, menu);
		return true;
	}

}
