package geekbrains.AndroidBasicLevel.previousRequests;

import android.app.Application;

import androidx.room.Room;

import geekbrains.AndroidBasicLevel.previousRequests.dao.RequestDao;
import geekbrains.AndroidBasicLevel.previousRequests.database.RequestDatabase;

public class App extends Application {

    private static App instance;

    private RequestDatabase rd;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        rd = Room.databaseBuilder
                (getApplicationContext(),
                RequestDatabase.class,
                "request_database")
                .allowMainThreadQueries()
                .build();
    }

    public RequestDao getRequestDao(){
        return rd.getRequestDao();
    }

    public RequestDatabase getRd() {
        return rd;
    }
}
