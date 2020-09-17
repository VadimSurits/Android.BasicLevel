package geekbrains.AndroidBasicLevel.PreviousRequests;

public class PreviousRequest {

    private String city;
    private float temperature;

    private int pressure;
    private int windSpeed;

    public PreviousRequest(String city, float temperature, int pressure, int windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getWindSpeed() {
        return windSpeed;
    }
}