package geekbrains.AndroidBasicLevel.broadcastReceivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import geekbrains.AndroidBasicLevel.R;
import geekbrains.AndroidBasicLevel.previousRequests.App;

public class BatteryStateReceiver extends BroadcastReceiver {
    private int messageId;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.drawable.ic_battery_low)
                .setContentTitle(App.getInstance().getResources().
                        getString(R.string.lowBattery_notificationTitle))
                .setContentText(App.getInstance().getResources().
                        getString(R.string.lowBattery_notificationText));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
