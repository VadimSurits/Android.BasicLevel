package geekbrains.AndroidBasicLevel.PreviousRequests.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import geekbrains.AndroidBasicLevel.PreviousRequests.model.PreviousRequest;


@Dao
public interface RequestDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertRequest(PreviousRequest previousRequest);

    @Update
    void updateRequest(PreviousRequest previousRequest);

    @Delete
    void deleteRequest(PreviousRequest previousRequest);

    @Query("DELETE FROM previousrequest WHERE id = :id")
    void deleteRequestbyId(long id);

    @Query("SELECT * FROM previousrequest")
    List<PreviousRequest> getAllRequests();

    @Query("SELECT * FROM previousrequest WHERE id = :id")
    PreviousRequest getRequeestById(long id);

    @Query("SELECT COUNT() FROM previousrequest")
    long getCountRequests();
}