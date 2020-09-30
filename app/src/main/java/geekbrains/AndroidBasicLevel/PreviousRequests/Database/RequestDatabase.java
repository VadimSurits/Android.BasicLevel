package geekbrains.AndroidBasicLevel.PreviousRequests.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import geekbrains.AndroidBasicLevel.PreviousRequests.Dao.RequestDao;
import geekbrains.AndroidBasicLevel.PreviousRequests.model.PreviousRequest;

@Database(entities = {PreviousRequest.class}, version = 1)
public abstract class RequestDatabase extends RoomDatabase {
    public abstract RequestDao getRequestDao();
}
