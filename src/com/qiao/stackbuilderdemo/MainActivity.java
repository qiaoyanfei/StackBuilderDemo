package com.qiao.stackbuilderdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	/** Notification构造器 */
	NotificationCompat.Builder mBuilder;
	/** Notification的ID */
	int mId = 1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView=(TextView)findViewById(R.id.full_screen_text);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showNotify();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void showNotify()
	{
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("My notification")
		        .setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, TestActivity.class);
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(TestActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, mBuilder.build());
	}
}
