package geekbrains.AndroidBasicLevel.PreviousRequests.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"city_name", "date", "temperature"})})
public class PreviousRequest {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city_name")
    public String cityName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "temperature")
    public float temperature;
}
