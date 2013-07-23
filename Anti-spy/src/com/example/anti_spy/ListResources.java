package com.example.anti_spy;

import java.util.List;
import java.util.Random;

import com.example.anti_spy.entity.Resource;

import dataBase.MySqlHelper;
import dataBase.ResourceDAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListResources extends ListActivity {
	private ResourceDAO datasource;

	MyAdapter mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor myCur = null;

        
		datasource = new ResourceDAO(this);
		datasource.open();
		
		List<Resource> values = datasource.getAllComments();

        mListAdapter = new MyAdapter(ListResources.this, datasource.myGetAllComments());
        setListAdapter(mListAdapter);
    }

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_list_resources);
//
//		datasource = new ResourceDAO(this);
//		datasource.open();
//
//		List<Resource> values = datasource.getAllComments();
//
//		// Use the SimpleCursorAdapter to show the
//		// elements in a ListView
//		ArrayAdapter<Resource> adapter = new ArrayAdapter<Resource>(this,
//				android.R.layout.simple_list_item_1, values);
//		setListAdapter(adapter);
//	}
//
	// Will be called via the onClick attribute
	// of the buttons in main.xml
//	public void onClick(View view) {
//		@SuppressWarnings("unchecked")
//		ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
//		Resource resource = null;
//		switch (view.getId()) {
//		case R.id.add:
//			String[] comments = new String[] { "Camera", "Microphone",
//					"Contacts" };
//			int nextInt = new Random().nextInt(3);
//			// Save the new comment to the database
//			resource = datasource.insertResource(comments[nextInt]);
//			adapter.add(resource.getResource_name());
//			break;
//		case R.id.delete:
//			if (getListAdapter().getCount() > 0) {
//				resource = (Resource) getListAdapter().getItem(0);
//				datasource.deleteResource(resource);
//				adapter.remove(resource.getResource_name());
//			}
//			break;
//		}
//		adapter.notifyDataSetChanged();
//	}

	private class MyAdapter extends ResourceCursorAdapter {

		public MyAdapter(Context context, Cursor cur) {
			super(context, R.layout.activity_list_resources, cur);
		}

		@Override
		public View newView(Context context, Cursor cur, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return li.inflate(R.layout.activity_list_resources, parent, false);
		}

		@Override
		public void bindView(View view, Context arg1, Cursor cur) {
			TextView tvListText = (TextView)view.findViewById(R.id.textView1);
            CheckBox cbListCheck = (CheckBox)view.findViewById(R.id.checkBox1);

            tvListText.setText(cur.getString(cur.getColumnIndex(MySqlHelper.RESOURCE_NAME)));
            cbListCheck.setChecked((cur.getInt(cur.getColumnIndex(MySqlHelper.RESOURCE_SELECTED)))==1? false:true);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_resources, menu);
		return true;
	}

}
