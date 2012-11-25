package com.nfcfriend;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FriendListActivity extends Activity{

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		ListView view = (ListView) findViewById(R.id.listview);
		Intent intent = getIntent();
		if (intent != null && intent.getExtras() != null) {
			if (intent.hasExtra(MainActivity.FRIENDS)) {
				ArrayList<String> names = intent.getStringArrayListExtra(MainActivity.FRIENDS);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, names);
				view.setAdapter(adapter);
			}
		}
	}
	
}
