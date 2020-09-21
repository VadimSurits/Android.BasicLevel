package geekbrains.AndroidBasicLevel;

import android.annotation.SuppressLint;

import geekbrains.AndroidBasicLevel.ForecastData.WeatherRequest;

class DataParser extends MainActivity{

    @SuppressLint("DefaultLocale")
    void setWeatherData(WeatherRequest weatherRequest){
        temperature.setText(String.format("%.2f F", weatherRequest.getMain().getTemp()));
        if (pressure.getText().equals(getString(R.string.checkBoxPressure))) {
            pressure.setText(String.format("%s: %d", getString(R.string.checkBoxPressure),
                    weatherRequest.getMain().getPressure()));
        }
        if (windSpeed.getText().equals(getString(R.string.checkBoxWindSpeed))) {
            windSpeed.setText(String.format("%s: %d", getString(R.string.checkBoxWindSpeed),
                    weatherRequest.getWind().getSpeed()));
        }
    }
}






