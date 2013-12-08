package com.example.anti_spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResourceTest extends ListActivity {

	static final String[] MOBILE_OS = 
            new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
		

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	
//    ================================================	
//    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
//	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
//	        "Android", "iPhone", "WindowsMobile" };
//
//	@Override
//	  protected void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
////	    setContentView(R.layout.activity_display_resource_test);
//	    
//	    setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_display_resource_test,values));
//	    
//	    ListView listView = getListView();
//	    listView.setTextFilterEnabled(true);
//	    
//	    listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), ((TextView) arg1).getText(), Toast.LENGTH_LONG).show();
//			}
//		});

//	    ================================================
	    
//	    final ListView listview = (ListView) findViewById(R.id.textView1);
//
//	    final ArrayList<String> list = new ArrayList<String>();
//	    for (int i = 0; i < values.length; ++i) {
//	      list.add(values[i]);
//	    }
//	    final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
////	    listview.setAdapter(adapter);
//	    setListAdapter(adapter);
//	    
//	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//	      @Override
//	      public void onItemClick(AdapterView<?> parent, final View view,int position, long id) {
//	        final String item = (String) parent.getItemAtPosition(position);
//	        view.animate().setDuration(2000).alpha(0)
//	            .withEndAction(new Runnable() {
//	              @Override
//	              public void run() {
//	                list.remove(item);
//	                adapter.notifyDataSetChanged();
//	                view.setAlpha(1);
//	              }
//	            });
//	      }
//
//	    });
	  }

	  private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }
}
