package geekbrains.AndroidBasicLevel.forecastData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastClouds {
    @SerializedName("all")
    @Expose
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}