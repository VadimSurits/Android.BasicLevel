package geekbrains.AndroidBasicLevel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import geekbrains.AndroidBasicLevel.ForecastData.WeatherRequest;
import geekbrains.AndroidBasicLevel.PreviousRequests.PreviousRequestsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Constants {

    Uri uri = Uri.parse("http://geekbrains.ru");

    private static final float AbsoluteZero = -273.15f;

    private RecyclerView recyclerView;
    private RecyclerDataAdapter recyclerDataAdapter;
    private List<String> forecastDays = new ArrayList<>();
    private List<String> forecastDescriptions = new ArrayList<>();

    private Toolbar toolbar;

    final static String TAG = "WEATHER";
    private TextView cityName;
    TextView temperature;
    TextView pressure;
    TextView windSpeed;

    DialogBuilderFragment dlgBuilder;
    private OpenWeather openWeather;

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
            requestRetrofit(CITY_MOSCOW, WEATHER_API_KEY);
        }
        if (cityName.getText().equals(getString(R.string.buttonSpb))) {
            requestRetrofit(CITY_SPB,WEATHER_API_KEY);
        }
        if (cityName.getText().equals(getString(R.string.buttonEkaterinburg))) {
            requestRetrofit(CITY_EKB,WEATHER_API_KEY);
        }
        if (cityName.getText().equals(getString(R.string.buttonNovosibirsk))) {
            requestRetrofit(CITY_NVS,WEATHER_API_KEY);
        }
        if (cityName.getText().equals(getString(R.string.buttonKhabarovsk))) {
            requestRetrofit(CITY_KHV,WEATHER_API_KEY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlgBuilder = new DialogBuilderFragment();

        toolbar = findViewById(R.id.AppBarToolbar);
        setSupportActionBar(toolbar);

        TextView cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        pressure = findViewById(R.id.pressure);

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

        recyclerView = findViewById(R.id.recycler_view_Fragment);
        forecastDays = Arrays.asList(getResources().getStringArray(R.array.forecastDays));
        forecastDescriptions = Arrays.asList(getResources().getStringArray(R.array.forecastDescriptions));
        recyclerDataAdapter = new RecyclerDataAdapter(forecastDays, forecastDescriptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(recyclerDataAdapter);

        initRetrofit();
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

    public void onClickDialogBuilder(View view){
        dlgBuilder.show(getSupportFragmentManager(), "dialogBuilder");
    }

    private void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        openWeather = retrofit.create(OpenWeather.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    private void requestRetrofit(String city, String keyApi) {
        openWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null) {
                            float receivedTemperature = response.body().getMain().getTemp() + AbsoluteZero;
                            temperature.setText(String.format("%.1f C", receivedTemperature));

                            int receivedPressure = response.body().getMain().getPressure();
                            if (pressure.getText().equals(getString(R.string.checkBoxPressure))) {
                            pressure.setText(String.format("%s: %d", getString(R.string.checkBoxPressure),
                                    receivedPressure));
                            }

                            int receivedWindSpeed = response.body().getWind().getSpeed();
                            if (windSpeed.getText().equals(getString(R.string.checkBoxWindSpeed))) {
                                windSpeed.setText(String.format("%s: %d", getString(R.string.checkBoxWindSpeed),
                                        receivedWindSpeed));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        onClickDialogBuilder(dlgBuilder.getView());
                    }
                });
    }
}