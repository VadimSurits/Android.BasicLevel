package geekbrains.AndroidBasicLevel;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import geekbrains.AndroidBasicLevel.broadcastReceivers.BatteryStateReceiver;
import geekbrains.AndroidBasicLevel.broadcastReceivers.NetworkStateReceiver;
import geekbrains.AndroidBasicLevel.forecastData.WeatherRequest;
import geekbrains.AndroidBasicLevel.previousRequests.PreviousRequestsActivity;
import geekbrains.AndroidBasicLevel.previousRequests.RecyclerDataAdapterForPRActivity;
import geekbrains.AndroidBasicLevel.previousRequests.model.PreviousRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Constants {

    Uri uri = Uri.parse("https://geekbrains.ru");

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
    private ImageView picture;
    private ImageView forecastIcon;

    DialogBuilderFragment dlgBuilder;
    private OpenWeather openWeather;
    private String receivedIcon;
    private String cityImageUrl;
    private String chosenCity;

    private Calendar calendar = Calendar.getInstance();

    private final String APP_PREFERENCES = "mysettings";
    private final String APP_CITYNAME = "cityname";
    private final String APP_TEMPERATURE = "temperature";
    private final String APP_WETHERICON = "weathericon";
    private final String APP_FORECASTDESCRIPTION = "forecastdescription";
    private final String APP_CITYPICTURE = "citypicture";
    private final String APP_WINDSPEED = "windspeed";
    private final String APP_PRESSURE = "pressure";
    private final String APP_WIKIPEDIA = "wikipediaUrl";
    SharedPreferences sharedPreferences;
    private NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver();
    private BatteryStateReceiver batteryStateReceiver = new BatteryStateReceiver();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        pressure = findViewById(R.id.pressure);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                chosenCity = data.getStringExtra(CityChoiceActivity.CITY);
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
            getCityImage("https://kudago.com/media/images/event/36/85/36856ccd2546e42a17e3fa392a7d215b.jpg");
        }
        if (cityName.getText().equals(getString(R.string.buttonSpb))) {
            requestRetrofit(CITY_SPB,WEATHER_API_KEY);
            getCityImage("https://yandex.ru/images/_crpd/5zqA50u97/85d1e13vpFup/jpaTxCVocA0Bb2rNqGrAAnWGYVLeAL6JcGpkfTi2RsbnyJ8y_hDpp2yo5gNbqlka_45Bbo_Xl582TCio7vAvnGsSQnF0HXQtdUseeBzawfbAk4LhfZZMStPdtp8bYHZWtwwqJu903ZBqOdcX341YS5rYQwvNwsBaQ2xN_gxHacrbgXXld3pSP1J9XBxYiVbDtWRRkp22vHqzb8V9q5EFlEZ9GRfp5O9Squ22dJy-eRnKCgDrvsEgyOMPLo48NDvqS5DkJ7RKge5THUz-SRmWIuXmk1BvBf8JY78XH9lD1SQVzUj2-SduUDuvd0YNj8u4SBkyKf3CY8ryTmzvTnWbmyuz9CRFeuB8gt5PHvjLhJOX1FPz_KdvGXG-1n2LwDcWR89o5nrVDJNa3cdHr07IKfnaIXjM0SJbUG1PLz2EKwiqE3f3FilyX2NOng_ayaWwRuSyglwlHoizTDY_6LJF1oddGdeLtmwiqW-UZi8eK8prmUHIzrIyqjEvfO-c19or2KNUJWeZUB2z3C0NmFqF4mTHwXC9l00b0V20HSvBBxRkrEoW6KfPgTqtdQYvfAu46wtzC70DYVtTTJzvjvZ76DhSpgZmW-EPQhyOTRkbR5HXVACCncZ-ulAOZy37UPZF9s4o54i0vdHbXuUW7N2IW_mr4DjegtDIEZ5MHx-1-6qLILemR0owLWCObr3Ja-WDFcSQsB8UDrqgXgVdGOHl1yf-2LTqFq-TeNzl5B7PWumqyZJKnwGAiBPc_Q0cJ0o6mWAUJUSqIS3xDI2uGBpkUOVWEaO9B2_7oU-UP3qCB4ck7UlmKjauUDnuRNVdHeu56xnzqyxQwhliHJwcTKSYmsshFEYm6HINcm0erFjJZ7B0phAz7ub9O8O_pH7LQ_RkZJ3459gF3ZLr31VX7PwKa6uawRiPQ4OKsX8u7831e-gLIsSUJSpRreMtbL-oW0ZhZLZDgNzEDIvjvKbfE");
        }
        if (cityName.getText().equals(getString(R.string.buttonEkaterinburg))) {
            requestRetrofit(CITY_EKB,WEATHER_API_KEY);
            getCityImage("https://mebelmag1.ru/wp-content/uploads/2017/02/eburg_4.jpg");
        }
        if (cityName.getText().equals(getString(R.string.buttonNovosibirsk))) {
            requestRetrofit(CITY_NVS,WEATHER_API_KEY);
            getCityImage("https://sdelanounas.ru/i/a/w/1/f_aW1nLmdlbGlvcGhvdG8uY29tL25zazIwMTkvMTRfbnNrMjAxOS5qcGc_X19pZD0xMjYwNjM=.jpeg");
        }
        if (cityName.getText().equals(getString(R.string.buttonKhabarovsk))) {
            requestRetrofit(CITY_KHV,WEATHER_API_KEY);
            getCityImage("https://sdelanounas.ru/i/a/w/1/f_aW1nLmdlbGlvcGhvdG8uY29tL0toYWJhcm92c2svMTkuanBnP19faWQ9MTA4NTg5.jpeg");
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
        picture = findViewById(R.id.mainImage);
        forecastIcon = findViewById(R.id.forecastIcon);

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

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(APP_CITYNAME)){
            cityName.setText(sharedPreferences.getString(APP_CITYNAME,""));
        }
        if(sharedPreferences.contains(APP_TEMPERATURE)){
            temperature.setText(sharedPreferences.getString(APP_TEMPERATURE,""));
        }
        if(sharedPreferences.contains(APP_WETHERICON)){
            getForecastIcon("https://openweathermap.org/img/wn/"
                    + sharedPreferences.getString(APP_WETHERICON,"") + "@2x.png");
        }
        if(sharedPreferences.contains(APP_FORECASTDESCRIPTION)){
            forecastDescriptions.set(0,sharedPreferences.getString(APP_FORECASTDESCRIPTION,""));
        }
        if(sharedPreferences.contains(APP_CITYPICTURE)){
            getCityImage(sharedPreferences.getString(APP_CITYPICTURE,""));
        }
        if(sharedPreferences.contains(APP_WINDSPEED)){
            windSpeed.setText(sharedPreferences.getString(APP_WINDSPEED,""));
        }
        if(sharedPreferences.contains(APP_PRESSURE)){
            pressure.setText(sharedPreferences.getString(APP_PRESSURE,""));
        }
        if(sharedPreferences.contains(APP_WIKIPEDIA)){
            uri = Uri.parse(sharedPreferences.getString(APP_WIKIPEDIA,""));
        }

        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        registerReceiver(batteryStateReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        initNotificationChannel("1");
        initNotificationChannel("2");

        initGetToken();
        initNotificationChannel("3");
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
        cityName.setText(saveIS.getString("cityName"));
        temperature.setText(saveIS.getString("temperature"));
        windSpeed.setText(saveIS.getString("windSpeed"));
        pressure.setText(saveIS.getString("pressure"));
        uri = Uri.parse(saveIS.getString("URI"));
        forecastDescriptions.set(0, saveIS.getString("description"));
        receivedIcon = (String) saveIS.get("icon");
        getForecastIcon("https://openweathermap.org/img/wn/" + receivedIcon + "@2x.png");
        cityImageUrl = saveIS.getString("cityImage");
        getCityImage(cityImageUrl);
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
        saveIS.putString("description", forecastDescriptions.get(0));
        saveIS.putString("icon", receivedIcon);
        saveIS.putString("cityImage", cityImageUrl);
    }

    @Override
    protected void onStop(){
        super.onStop();
        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        pressure = findViewById(R.id.pressure);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_CITYNAME,cityName.getText().toString());
        editor.putString(APP_TEMPERATURE, temperature.getText().toString());
        if(receivedIcon != null) {
            editor.putString(APP_WETHERICON, receivedIcon);
        }
        editor.putString(APP_FORECASTDESCRIPTION, forecastDescriptions.get(0));
        editor.putString(APP_CITYPICTURE, cityImageUrl);
        if(pressure.getText().equals(getString(R.string.checkBoxPressure))){
            editor.putString(APP_PRESSURE, pressure.getText().toString());
        }
        if(windSpeed.getText().equals(getString(R.string.checkBoxWindSpeed))){
            editor.putString(APP_WINDSPEED, windSpeed.getText().toString());
        }
        editor.putString(APP_WIKIPEDIA, String.valueOf(uri));
        editor.apply();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
        unregisterReceiver(batteryStateReceiver);
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
                .baseUrl("https://api.openweathermap.org/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        openWeather = retrofit.create(OpenWeather.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    private void requestRetrofit(final String city, String keyApi) {
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
                            String receivedDescription = response.body().getWeather()[0].getDescription();
                            forecastDescriptions.set(0, receivedDescription);
                            receivedIcon = response.body().getWeather()[0].getIcon();
                            getForecastIcon("https://openweathermap.org/img/wn/" + receivedIcon + "@2x.png");
                            try{
                                String currentDate = calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "."
                                        + calendar.get(Calendar.YEAR);
                                RecyclerDataAdapterForPRActivity.dataSource.addPreviousRequest
                                        (new PreviousRequest(chosenCity,currentDate,String.format("%.1f C", receivedTemperature)));
                            } catch(NullPointerException e){
                                Log.e(TAG, "NullPointerException");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        onClickDialogBuilder(dlgBuilder.getView());
                        Log.e(TAG, "Fail connection");
                    }
                });
    }

    private void getCityImage(String url) {
        Picasso.get()
                .load(url)
                .into(picture);
        cityImageUrl = url;
    }

    private void getForecastIcon(String url){
        Picasso.get()
                .load(url)
                .into(forecastIcon);
    }

    private void initNotificationChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(channelId,"name",
                    importance);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initGetToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener
                (new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()){
                            Log.w("PushMessage","getInstanceId failed",
                                    task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("PushMessage","Token " + token);
                    }
                });
    }
}

