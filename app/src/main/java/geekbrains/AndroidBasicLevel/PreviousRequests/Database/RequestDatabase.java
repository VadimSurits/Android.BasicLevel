package geekbrains.AndroidBasicLevel.previousRequests.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import geekbrains.AndroidBasicLevel.previousRequests.dao.RequestDao;
import geekbrains.AndroidBasicLevel.previousRequests.model.PreviousRequest;

@Database(entities = {PreviousRequest.class}, version = 1)
public abstract class RequestDatabase extends RoomDatabase {
    public abstract RequestDao getRequestDao();
}
