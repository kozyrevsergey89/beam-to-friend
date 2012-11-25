package com.nfcfriend;

import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends ResultActivity implements CreateNdefMessageCallback, OnNdefPushCompleteCallback {

	public static final int MESSAGE_SENT = 1;
	private NfcAdapter nfcAdapter;
	private NdefMessage ndefMessage;
	
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
		Log.i("NFCFriend", new String(msg.getRecords()[0].getPayload()));
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
	
	
	private void showFunctionallityDialog(final int id) {
		 new AlertDialog.Builder(this)
         .setTitle("Topics or Friend Request?")
         .setMessage("TOPICS will show shared topics for conversation. REQUEST will proceed You to make friend request.")
         .setPositiveButton("TOPICS",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialog, final int which) {
                    	 //run service matching
                     }

                 })
         .setNegativeButton("REQUEST",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(final DialogInterface dialog, final int which) {
                    	//ENTER PERSONS ID
                     	String url = "http://www.facebook.com/addfriend.php?id="+ id;
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
