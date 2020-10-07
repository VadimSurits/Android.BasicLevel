package geekbrains.AndroidBasicLevel.broadcastReceivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.app.NotificationCompat;

import geekbrains.AndroidBasicLevel.R;
import geekbrains.AndroidBasicLevel.previousRequests.App;

public class NetworkStateReceiver extends BroadcastReceiver {
    private int messageId;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkInfo networkInfo = intent.getParcelableExtra
                    (ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null && networkInfo.
                    getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context,"2")
                        .setSmallIcon(R.drawable.ic_network_off)
                        .setContentTitle(App.getInstance().getResources().
                                getString(R.string.noNetwork_notificationTitle))
                        .setContentText(App.getInstance().getResources().
                                getString(R.string.noNetwork_notificationText));
                NotificationManager notificationManager = (NotificationManager) context.getSystemService
                        (Context.NOTIFICATION_SERVICE);
                notificationManager.notify(messageId++, builder.build());
            }
        }
    }
}

