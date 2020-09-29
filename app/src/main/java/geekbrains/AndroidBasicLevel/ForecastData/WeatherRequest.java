package geekbrains.AndroidBasicLevel.ForecastData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherRequest {
    @SerializedName("coord")
    @Expose
    private ForecastCoord coord;
    @SerializedName("weather")
    @Expose
    private ForecastWeather[] weather;
    @SerializedName("main")
    @Expose
    private ForecastMainData main;
    @SerializedName("wind")
    @Expose
    private ForecastWind wind;
    @SerializedName("clouds")
    @Expose
    private ForecastClouds clouds;
    @SerializedName("name")
    @Expose
    private String name;

    public ForecastCoord getCoord() {
        return coord;
    }

    public void setCoord(ForecastCoord coord) {
        this.coord = coord;
    }

    public ForecastWeather[] getWeather() {
        return weather;
    }

    public void setWeather(ForecastWeather[] weather) {
        this.weather = weather;
    }

    public ForecastMainData getMain() {
        return main;
    }

    public void setMain(ForecastMainData main) {
        this.main = main;
    }

    public ForecastWind getWind() {
        return wind;
    }

    public void setWind(ForecastWind wind) {
        this.wind = wind;
    }

    public ForecastClouds getClouds() {
        return clouds;
    }

    public void setClouds(ForecastClouds clouds) {
        this.clouds = clouds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
