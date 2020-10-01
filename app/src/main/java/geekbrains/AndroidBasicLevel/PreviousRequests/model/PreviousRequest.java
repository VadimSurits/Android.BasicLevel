package geekbrains.AndroidBasicLevel.PreviousRequests.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"city_name", "date", "temperature"})})
public class PreviousRequest {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "city_name")
    public String cityName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "temperature")
    public String temperature;

    public PreviousRequest() {
    }

    public PreviousRequest(String cityName, String date, String temperature) {
        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
