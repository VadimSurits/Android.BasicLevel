package geekbrains.AndroidBasicLevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants {

    Uri uri = Uri.parse("http://geekbrains.ru");

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView cityName = findViewById(R.id.cityName);
        TextView temperature = findViewById(R.id.temperature);
        TextView windSpeed = findViewById(R.id.windSpeed);
        TextView pressure = findViewById(R.id.pressure);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String chosenCity = data.getStringExtra(CityChoiceActivity.CITY);
                cityName.setText(chosenCity);
                String receivedWindSpeed = data.getStringExtra(CityChoiceActivity.WIND_SPEED);
                windSpeed.setText(receivedWindSpeed);
                String receivedPressure = data.getStringExtra(CityChoiceActivity.PRESSURE);
                pressure.setText(receivedPressure);
                temperature.setText("??? градусов");
                String receivedUri = data.getStringExtra(CityChoiceActivity.URI);
                uri = Uri.parse(receivedUri);
            }else {
                cityName.setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState;
        if(savedInstanceState == null){
            instanceState = "Первый запуск";
        } else{
            instanceState = "Повторный запуск";
        }

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


        Toast.makeText(getApplicationContext(), instanceState + " MainActivity - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext()," MainActivity - onStart()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onStart()");
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
        Toast.makeText(getApplicationContext(), " MainActivity - onResume()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), " MainActivity - onPause()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveIS){
        super.onSaveInstanceState(saveIS);
        Toast.makeText(getApplicationContext(), " MainActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onSaveInstanceState()");

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
        Toast.makeText(getApplicationContext(), " MainActivity - onStop()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(), " MainActivity - onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), " MainActivity - onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onDestroy()");
    }
}
