package geekbrains.AndroidBasicLevel.broadcastReceivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import geekbrains.AndroidBasicLevel.R;

public class BatteryStateReceiver extends BroadcastReceiver {
    private int messageId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.drawable.ic_battery_low)
                .setContentTitle("VS BATTERY WARNING")
                .setContentText("BATTERY IS LOW");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
