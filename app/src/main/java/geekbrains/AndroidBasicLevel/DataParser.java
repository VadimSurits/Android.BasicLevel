package geekbrains.AndroidBasicLevel;

import android.annotation.SuppressLint;

import geekbrains.AndroidBasicLevel.ForecastData.WeatherRequest;

class DataParser{
    private DataReceiver dataReceiver;

    public DataParser(DataReceiver dataReceiver) {
        this.dataReceiver = dataReceiver;
    }

    @SuppressLint("DefaultLocale")
    void setWeatherData(WeatherRequest weatherRequest){
        dataReceiver.mainActivity.temperature.setText(String.format("%.2f F", weatherRequest.getMain().getTemp()));
        if (dataReceiver.mainActivity.pressure.getText().equals(dataReceiver.mainActivity.getString(R.string.checkBoxPressure))) {
            dataReceiver.mainActivity.pressure.setText(String.format("%s: %d", dataReceiver.mainActivity.getString(R.string.checkBoxPressure),
                    weatherRequest.getMain().getPressure()));
        }
        if (dataReceiver.mainActivity.windSpeed.getText().equals(dataReceiver.mainActivity.getString(R.string.checkBoxWindSpeed))) {
            dataReceiver.mainActivity.windSpeed.setText(String.format("%s: %d", dataReceiver.mainActivity.getString(R.string.checkBoxWindSpeed),
                    weatherRequest.getWind().getSpeed()));
        }
    }
}






