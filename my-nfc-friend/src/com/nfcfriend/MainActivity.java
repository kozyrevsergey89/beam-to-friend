package com.nfcfriend;

import java.util.ArrayList;
import java.util.List;

import com.nfcfriend.jsonhandler.FacebookJSONObject;
import com.nfcfriend.jsonhandler.entity.Friend;
import com.nfcfriend.service.BeamTranslateService;
import com.nfcfriend.service.Matches;

import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends ResultActivity implements CreateNdefMessageCallback, OnNdefPushCompleteCallback {

	public static final int MESSAGE_SENT = 1;
	public static final String FRIENDS = "friends";
	private NfcAdapter nfcAdapter;
	private NdefMessage ndefMessage;
	private FacebookJSONObject friendObject = null;
	private String nativeUser;
	
	@Override
	protected void onStart() {
		super.onStart();
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if(nfcAdapter == null) {
			Log.e("ERROR", "NFC is not available");
		} else {
			nfcAdapter.setNdefPushMessageCallback(this, this);
			nfcAdapter.setOnNdefPushCompleteCallback(this, this);
		}

	}

	private Handler nfcHandler = new Handler() {
		public void handleMessage(final Message msg) {
			switch (msg.what) {
			case MESSAGE_SENT:
				Log.i("NFCFriend", "Message sent !");
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	public NdefMessage createNdefMessage(final NfcEvent event) {
		ndefMessage = new NdefMessage(NdefRecord.createMime("application/com.nfcfriend", responseString.getBytes()));
		return ndefMessage;
	}

	@Override
	public void onNdefPushComplete(final NfcEvent event) {
		nfcHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
	}
	
	private void processIntent(final Intent intent) {
		Parcelable[] rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		NdefMessage msg = (NdefMessage) rawMessage[0];
		nativeUser = new String(msg.getRecords()[0].getPayload());
		Log.i("NFCFriend", "MainActivity - " + nativeUser);
		if (beamService != null && nativeUser != null) {
			Log.i("NFCFriend","beam is not null");
			//friendObject = beamService.parseWithAsyncTask(nativeUser);
			friendObject = beamService.parseToModel(nativeUser);
		} else {
			Log.i("NFCFriend","beam is null");
			Intent binder = new Intent(this, BeamTranslateService.class);
			bindService(binder, connection, Context.BIND_AUTO_CREATE);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					friendObject = beamService.parseToModel(nativeUser);
					Log.i("NFCFriend", friendObject != null ? friendObject.getId() + "" : "friend object is null");
				}
			}, 500);
		}
		showFunctionallityDialog(friendObject, this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			processIntent(getIntent());
		}
 	}
	
	@Override
	protected void onNewIntent(final Intent intent) {
		setIntent(intent);
	}
	
	
	private void showFunctionallityDialog(final FacebookJSONObject object, final Activity activity) {
		 new AlertDialog.Builder(this)
         .setTitle("Topics or Friend Request?")
         .setMessage("TOPICS will show shared topics for conversation. REQUEST will proceed You to make friend request.")
         .setPositiveButton("TOPICS",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialog, final int which) {
                    	 if(beamService != null) {
                    		SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);
                    		responseString = prefs.getString(ResultActivity.RES, null);
                    		result = beamService.parseToModel(responseString);
                    		friendObject = beamService.parseToModel(nativeUser);
                    		Log.i("NFCFriend", result.getId() + " - my id");
                    		Log.i("NFCFriend", friendObject.getId() + " - friends id");
                    		Matches matches = beamService.findMatches(result, friendObject);
                    		/*
                    		Log.i("NFCFriend","MainActivity - " + matches.getCommonFriends().size());
                    		Log.i("NFCFriend","MainActivity - " + matches.getCommonActivities().size());
                    		Log.i("NFCFriend","MainActivity - " + matches.getCommonLikes().size());
                    		Log.i("NFCFriend","MainActivity - " + matches.getMatchedFeeds().size());
                    		*/
                    		ArrayList<String> names = new ArrayList<String>();
                    		for (Friend friend : matches.getCommonFriends()) {
                    			names.add(friend.getName());
                    		}
                    		
                    		Intent intent = new Intent(activity, FriendListActivity.class);
                    		intent.putStringArrayListExtra(FRIENDS, names);
                    		startActivity(intent);
                    		
                    	 } else {
                    		Log.i("NFCFriend", "showFunctionallityDialog beam is null"); 
                    	 }
                     }
                 })
         .setNegativeButton("REQUEST",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialog, final int which) {
                     	String url = "http://www.facebook.com/addfriend.php?id="+ friendObject.getId();
                     	Intent i = new Intent(Intent.ACTION_VIEW);
                     	i.setData(Uri.parse(url));
                     	startActivity(i);
                     }

                 }).setOnCancelListener(new DialogInterface.OnCancelListener() {
             @Override
             public void onCancel(DialogInterface d) {
                 d.dismiss();
             }
         }).show();
	}
}
