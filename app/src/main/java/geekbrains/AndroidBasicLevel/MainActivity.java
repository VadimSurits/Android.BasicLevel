package geekbrains.AndroidBasicLevel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import geekbrains.AndroidBasicLevel.ForecastData.WeatherRequest;
import geekbrains.AndroidBasicLevel.PreviousRequests.PreviousRequestsActivity;

public class MainActivity extends AppCompatActivity implements Constants {

    Uri uri = Uri.parse("http://geekbrains.ru");

    private RecyclerView recyclerView;
    private RecyclerDataAdapter recyclerDataAdapter;
    private List<String> forecastDays = new ArrayList<>();
    private List<String> forecastDescriptions = new ArrayList<>();

    private Toolbar toolbar;

    private static final String TAG = "WEATHER";
    private TextView cityName;
    private TextView temperature;
    private TextView pressure;
    private TextView windSpeed;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        pressure = findViewById(R.id.pressure);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String chosenCity = data.getStringExtra(CityChoiceActivity.CITY);
                cityName.setText(chosenCity);
                String receivedWindSpeed = data.getStringExtra(CityChoiceActivity.WIND_SPEED);
                windSpeed.setText(receivedWindSpeed);
                String receivedPressure = data.getStringExtra(CityChoiceActivity.PRESSURE);
                pressure.setText(receivedPressure);
                String receivedUri = data.getStringExtra(CityChoiceActivity.URI);
                uri = Uri.parse(receivedUri);
                }
            }else {
                cityName.setText("Город не выбран");
            }
        if (cityName.getText().equals(getString(R.string.buttonMoscow))) {
            init(WEATHER_URL_MOSCOW);
        }
        if (cityName.getText().equals(getString(R.string.buttonSpb))) {
            init(WEATHER_URL_SPb);
        }
        if (cityName.getText().equals(getString(R.string.buttonEkaterinburg))) {
            init(WEATHER_URL_EKB);
        }
        if (cityName.getText().equals(getString(R.string.buttonNovosibirsk))) {
            init(WEATHER_URL_NVS);
        }
        if (cityName.getText().equals(getString(R.string.buttonKhabarovsk))) {
            init(WEATHER_URL_KHV);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.AppBarToolbar);
        setSupportActionBar(toolbar);

        TextView cityName = findViewById(R.id.cityName);
        TextView temperature = findViewById(R.id.temperature);

        cityName.setText(R.string.cityName);
        temperature.setText(R.string.temperature);

        Button buttonChangeCity = findViewById(R.id.buttonChangeCity);
        Button buttonSettings = findViewById(R.id.buttonSettings);
        Button buttonInfoAboutCity = findViewById(R.id.buttonInfoaboutCity);

        buttonChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityChoiceActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        buttonInfoAboutCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
                startActivity(intent);
            }
        });

//        FragmentTemperatureHistory fragmentTemperatureHistory = new FragmentTemperatureHistory();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragmentContainer1, fragmentTemperatureHistory);
//        fragmentTransaction.commit();

        recyclerView = findViewById(R.id.recycler_view_Fragment);
        forecastDays = Arrays.asList(getResources().getStringArray(R.array.forecastDays));
        forecastDescriptions = Arrays.asList(getResources().getStringArray(R.array.forecastDescriptions));
        recyclerDataAdapter = new RecyclerDataAdapter(forecastDays, forecastDescriptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(recyclerDataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveIS){
        super.onRestoreInstanceState(saveIS);
        Toast.makeText(getApplicationContext(), "Повторный запуск MainActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " Повторный запуск MainActivity - onRestoreInstanceState())");

        TextView cityName = findViewById(R.id.cityName);
        TextView temperature = findViewById(R.id.temperature);
        TextView windSpeed = findViewById(R.id.windSpeed);
        TextView pressure = findViewById(R.id.pressure);
        cityName.setText(saveIS.getString("cityName").toString());
        temperature.setText(saveIS.getString("temperature").toString());
        windSpeed.setText(saveIS.getString("windSpeed").toString());
        pressure.setText(saveIS.getString("pressure").toString());
        uri = Uri.parse(saveIS.getString("URI"));
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveIS){
        super.onSaveInstanceState(saveIS);

        TextView cityName = findViewById(R.id.cityName);
        TextView temperature = findViewById(R.id.temperature);
        TextView windSpeed = findViewById(R.id.windSpeed);
        TextView pressure = findViewById(R.id.pressure);
        saveIS.putString("cityName", cityName.getText().toString());
        saveIS.putString("temperature", temperature.getText().toString());
        saveIS.putString("windSpeed",windSpeed.getText().toString());
        saveIS.putString("pressure", pressure.getText().toString());
        saveIS.putString("URI",uri.toString());
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // разместить меню в action bar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Управление касаниями на action bar.
        int id = item.getItemId();

        if(id == R.id.aboutDeveloper){
            Snackbar.make(toolbar, "Разработчик еще учится", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Закрыть окно", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    }).show();
            return true;
        }
        if(id == R.id.historyOfRequests) {
            startActivity(new Intent(MainActivity.this, PreviousRequestsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(String url){
        try {
            final URL uri = new URL(url + WEATHER_API_KEY);
            final Handler handler = new Handler(); // Запоминаем основной поток
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                        urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                        String result = getLines(in);
                        // преобразование данных запроса в модель
                        Gson gson = new Gson();
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
//                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        // Возвращаемся к основному потоку
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                setWeatherData(weatherRequest);
                            }
                        });
                    } catch (Exception e) {
                        Log.e(TAG, "Fail connection", e);
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    @SuppressLint("DefaultLocale")
    private void setWeatherData(WeatherRequest weatherRequest){
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