package geekbrains.AndroidBasicLevel.ForecastData;

public class WeatherRequest {
    private ForecastCoord coord;
    private ForecastWeather[] weather;
    private ForecastMainData main;
    private ForecastWind wind;
    private ForecastClouds clouds;
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
