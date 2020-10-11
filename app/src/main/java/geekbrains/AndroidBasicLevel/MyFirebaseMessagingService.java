package geekbrains.AndroidBasicLevel;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private int messageId;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("PushMessage", remoteMessage.getNotification().getBody());
        String title = remoteMessage.getNotification().getTitle();
        if (title == null) {
            title = "Push Message";
        }
        String text = remoteMessage.getNotification().getBody();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "3")
                        .setSmallIcon(R.drawable.ic_push_notification)
                        .setContentTitle(title)
                        .setContentText(text);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService
                (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId, builder.build());
    }

    @Override
    public void onNewToken(String token) {
        Log.d("PushMessage", "Token" + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token){
    }

}

