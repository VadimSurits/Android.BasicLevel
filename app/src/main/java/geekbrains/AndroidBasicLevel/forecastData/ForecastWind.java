package geekbrains.AndroidBasicLevel.forecastData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastWind {
    @SerializedName("speed")
    @Expose
    private int speed;
    @SerializedName("deg")
    @Expose
    private int deg;

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

